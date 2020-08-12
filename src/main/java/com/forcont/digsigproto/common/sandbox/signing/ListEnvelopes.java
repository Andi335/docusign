package com.forcont.digsigproto.common.sandbox.signing;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.common.model.EnvelopeDigSig;
import com.forcont.digsigproto.common.model.Initiator;
import com.forcont.digsigproto.common.model.RecipientDigSig;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListEnvelopes
{
    private final AbstractBaseApi baseApi;
    private final AuthGrantCodeAccessor authGrantCodeAccessor;

    @Autowired
    public ListEnvelopes(AbstractBaseApi baseApi, AuthGrantCodeAccessor authGrantCodeAccessor)
    {
        this.baseApi = baseApi;
        this.authGrantCodeAccessor = authGrantCodeAccessor;
    }

    public String listEnvelopes(String envelopeId)
    {
        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();

        String status = "";
        try
        {
            status = baseApi.getCovenantApi().listEnvelopes(initiator, envelopeId);
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }

        return status;
    }

    public List<EnvelopeDigSig> getAllEnvelopesStatus()
    {
        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();

        List<EnvelopeDigSig> envelopeDigSigs = new ArrayList<>();
        try
        {
            envelopeDigSigs = baseApi.getCovenantApi().getAllEnvelopesStatus(initiator);
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }

        return envelopeDigSigs;
    }

    public List<RecipientDigSig> getAllSignersStatus(String envelopeid){

        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();

        List<RecipientDigSig> recipientDigSigs = new ArrayList<>();
        try
        {
            recipientDigSigs = baseApi.getCovenantApi().getAllSignersStatus(initiator, envelopeid);
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }

        return recipientDigSigs;

    }
}
