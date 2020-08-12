package com.forcont.digsigproto.common.api.features;

import com.forcont.digsigproto.common.api.Feature;

public class WebhookNotification extends Feature
{
    private final String listenerUrl;

    public WebhookNotification(String listenerUrl)
    {
        this.listenerUrl = listenerUrl;
    }

    public String getListenerUrl()
    {
        return listenerUrl;
    }
}
