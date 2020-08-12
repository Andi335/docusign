package com.forcont.digsigproto.common.controller.bookmarks;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class BookmarksTest
{


    private List<byte[]> files = new ArrayList<>();
    private List<String> fileNames = new ArrayList<>();

    @Before
    public void generateDocuments() throws IOException
    {
        byte[] array = Files.readAllBytes(
                Paths.get("src/test/java/com/forcont/digsigproto/common/controller/bookmarks/Prämienzahlung_Prozentual--exported.textmarks.pdf"));
        files.add(array);

        String fileName = "Arbeitsvertrag";
        fileNames.add(fileName);

    }

    @Test
    public void getBookmarks() throws IOException
    {
        Bookmarks bookmarks = new Bookmarks();
        Set<String> sets = new HashSet<>();
        sets.add("SIGN_Abteilungsleiter");      // Bookmark name
        sets.add("SIGN_Geschaeftsfuehrung");    // Bookmark name
        assertEquals(bookmarks.getBookmarks(files, fileNames).get("signerFields"),sets);
    }
}