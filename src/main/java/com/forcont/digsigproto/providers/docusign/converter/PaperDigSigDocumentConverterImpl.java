package com.forcont.digsigproto.providers.docusign.converter;

import com.docusign.esign.model.Document;
import com.docusign.esign.model.EnvelopeDocument;
import com.forcont.digsigproto.common.api.ModelConverter;
import com.forcont.digsigproto.common.model.Paper;
import com.sun.jersey.core.util.Base64;

public class PaperDigSigDocumentConverterImpl implements ModelConverter<Paper, Document>
{
    @Override
    public Paper convertFromProvider(Document document)
    {
        return new Paper(
                document.getDocumentId(),
                document.getName(),
                document.getUri(),
                Integer.parseInt(document.getPages())
        );
    }

    @Override
    public Document convertToProvider(Paper digsigModel)
    {
        Document document = new Document();
        document.setDocumentId(digsigModel.getId());
        document.setName(digsigModel.getTitle());
        document.setUri(digsigModel.getUri());
        //document.setPages(digsigModel.getPages().toString());
        document.setDocumentBase64(new String(Base64.encode(digsigModel.getContent())));
        return document;
    }
}
