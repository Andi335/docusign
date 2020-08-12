package com.forcont.digsigproto.providers.docusign.converter;

import com.forcont.digsigproto.common.api.ModelConverter;
import com.forcont.digsigproto.common.model.RecipientDigSig;

public class SignerDigSigSignerConverterImpl implements ModelConverter<RecipientDigSig, com.docusign.esign.model.Signer>
{
    @Override
    public RecipientDigSig convertFromProvider(com.docusign.esign.model.Signer signer)
    {
        return new RecipientDigSig(
                signer.getName(),
                signer.getEmail(),
                signer.getRoleName()
        );
    }

    @Override
    public com.docusign.esign.model.Signer convertToProvider(RecipientDigSig digsigModel)
    {
        com.docusign.esign.model.Signer signer = new com.docusign.esign.model.Signer();
        signer.setName(digsigModel.getName());
        signer.setEmail(digsigModel.getEmail());
        signer.setRoleName(digsigModel.getRole());
        return signer;
    }
}
