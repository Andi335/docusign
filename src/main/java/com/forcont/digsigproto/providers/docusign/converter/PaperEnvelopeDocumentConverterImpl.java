package com.forcont.digsigproto.providers.docusign.converter;

import com.docusign.esign.model.EnvelopeDocument;
import com.forcont.digsigproto.common.api.ModelConverter;
import com.forcont.digsigproto.common.model.Paper;

public class PaperEnvelopeDocumentConverterImpl implements ModelConverter<Paper, EnvelopeDocument>
{
    @Override
    public Paper convertFromProvider(EnvelopeDocument document)
    {
        return new Paper(
                document.getDocumentId(),
                document.getName(),
                document.getUri(),
                Integer.parseInt(document.getPages())
        );

    }

    @Override
    public EnvelopeDocument convertToProvider(Paper digsigModel)
    {
        EnvelopeDocument envelopeDocument = new EnvelopeDocument();
        envelopeDocument.setDocumentId(digsigModel.getId());
        envelopeDocument.setName(digsigModel.getTitle());
        envelopeDocument.setUri(digsigModel.getUri());
        envelopeDocument.setPages(digsigModel.getPages().toString());
        return envelopeDocument;
    }
}
