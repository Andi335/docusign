package com.forcont.digsigproto.common.controller.bookmarks;

import com.forcont.digsigproto.common.config.DefaultValues;
import com.forcont.digsigproto.common.model.Action;
import com.forcont.digsigproto.common.model.Paper;
import com.forcont.digsigproto.common.model.SigningAction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Bookmarks
{
    public Map<String, Map<String, List<SigningAction>>> cache = new HashMap<>();

    private Map<String, Object> doGetBookmarks(List<byte[]> bytes, List<String> fileNames) throws IOException
    {
        this.cache.clear();
        Map<String, List<SigningAction>> signerMap = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        md.update(sdf.format(cal.getTime()).getBytes());

        for (int i = 0; i < bytes.size(); i++)
        {
            byte[] file = bytes.get(i);
            String fileName = fileNames.get(i);
            md.update(file);

            PDDocument doc = PDDocument.load(file);
            PDDocumentOutline outline = doc.getDocumentCatalog().getDocumentOutline();
            if (outline == null) {
                Map<String, Object> returnMap = new HashMap<>();
                returnMap.put("processId", "null");
                returnMap.put("signerFields", new ArrayList<>());
                return returnMap;
            }

            PDOutlineItem current = outline.getFirstChild();
            while (current != null)
            {
                PDPageXYZDestination destination = (PDPageXYZDestination) current.getDestination();
                PDPageDestination destination1 = (PDPageDestination) current.getDestination();
                PDPage currentPage = current.findDestinationPage(doc);
                PDRectangle mediaBox = currentPage.getMediaBox();
                int height = (int) mediaBox.getHeight();// ~ 841
                int pageNumber = destination1.retrievePageNumber() + 1;

                if (current.getTitle().startsWith(DefaultValues.SIGNATURE_PLACEHOLDER_IDENTIFIER))
                {
                    int posX = destination.getLeft();
                    int posY = height - destination.getTop();
                    System.out.println("Bookmark: "+current.getTitle());
                    System.out.println("       X: "+posX);
                    System.out.println("       Y: "+posY);
                    SigningAction signingAction = new SigningAction(
                            new Paper(String.valueOf(i + 1), fileName, file),
                            Action.SIGN,
                            posX,
                            posY,
                            pageNumber
                    );
                    if (!signerMap.containsKey(current.getTitle()))
                    {
                        signerMap.put(current.getTitle(), new ArrayList<>());
                    }
                    signerMap.get(current.getTitle()).add(signingAction);
                }
                current = current.getNextSibling();
            }
            doc.close();
        }
        String processId = DatatypeConverter.printHexBinary(md.digest());
        this.cache.put(processId, signerMap);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("processId", processId);
        returnMap.put("signerFields", signerMap.keySet());

        return returnMap;
    }


    public Map<String, Object> getBookmarks(List<byte[]> bytes, List<String> fileNames) throws IOException
    {
        return doGetBookmarks(bytes,fileNames);
    }

    public Map<String, Object> getBookmarks(List<MultipartFile> documents) throws IOException
    {
        List<byte[]> byteList = new ArrayList<>();
        List<String> fileNamesList = new ArrayList<>();

        for (MultipartFile document : documents)
        {
            byteList.add(document.getBytes());
            fileNamesList.add(document.getOriginalFilename());
        }
        return doGetBookmarks(byteList,fileNamesList);
    }

}
