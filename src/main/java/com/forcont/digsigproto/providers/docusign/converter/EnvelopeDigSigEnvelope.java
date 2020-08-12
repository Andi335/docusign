package com.forcont.digsigproto.providers.docusign.converter;

import com.docusign.esign.model.Envelope;
import com.forcont.digsigproto.common.api.ModelConverter;
import com.forcont.digsigproto.common.model.EnvelopeDigSig;

public class EnvelopeDigSigEnvelope implements ModelConverter<EnvelopeDigSig, Envelope>
{
    @Override
    public EnvelopeDigSig convertFromProvider(Envelope env)
    {
        return new EnvelopeDigSig(
                env.getEnvelopeId(),
                env.getStatus()

        );
    }

    @Override
    public Envelope convertToProvider(EnvelopeDigSig digsigModel)
    {
        Envelope envelope = new Envelope();
        envelope.setEnvelopeId(digsigModel.getId());
        envelope.setStatus(digsigModel.getStatus());
        return envelope;
    }
}
