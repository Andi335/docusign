package com.forcont.digsigproto.common.api.features;

import com.forcont.digsigproto.common.api.Feature;
import com.forcont.digsigproto.common.model.RecipientDigSig;

import java.util.List;

public class RecipientSigningOrder extends Feature
{
    private final List<RecipientDigSig> recipientOrder;

    public RecipientSigningOrder(List<RecipientDigSig> recipientOrder)
    {
        this.recipientOrder = recipientOrder;
    }

    public List<RecipientDigSig> getRecipientOrder()
    {
        return recipientOrder;
    }
}
