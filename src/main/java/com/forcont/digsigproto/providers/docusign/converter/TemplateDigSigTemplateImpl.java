package com.forcont.digsigproto.providers.docusign.converter;

import com.docusign.esign.model.EnvelopeTemplateResult;
import com.forcont.digsigproto.common.api.ModelConverter;
import com.forcont.digsigproto.common.model.TemplateDigSig;

public class TemplateDigSigTemplateImpl implements ModelConverter<TemplateDigSig, EnvelopeTemplateResult>
{
    @Override
    public TemplateDigSig convertFromProvider(EnvelopeTemplateResult envelopeTemplateResult)
    {
        return new TemplateDigSig(
            envelopeTemplateResult.getTemplateId(),
            envelopeTemplateResult.getName()
        );
    }

    @Override
    public EnvelopeTemplateResult convertToProvider(TemplateDigSig digsigModel)
    {
        return null;
    }
}
