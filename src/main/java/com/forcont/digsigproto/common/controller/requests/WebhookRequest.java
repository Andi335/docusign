package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.model.WebhookNotification;
import com.forcont.digsigproto.common.sandbox.signing.ParseWebhooksStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebhookRequest
{
    private final EndPoints endPoints;
    private final ParseWebhooksStatus parseWebhooksStatus;

    public WebhookRequest(EndPoints endPoints, ParseWebhooksStatus parseWebhooksStatus)
    {
        this.endPoints = endPoints;
        this.parseWebhooksStatus = parseWebhooksStatus;
    }

    @PostMapping(
            path = EndPoints.Api.WEBHOOK_LISTENER,
            consumes = {MediaType.TEXT_XML_VALUE}
    )
    public void getWebhook(HttpEntity<String> xml)
    {
        System.out.println("ping! "+ xml.getBody());
        try
        {
            this.parseWebhooksStatus.parseSignerStatusXML(xml.getBody());
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }

    }

    @GetMapping(EndPoints.Json.WEBHOOK_LIST_ENVEOPE_IDS)
    public Map<String, List<String>> getWebhookEnvelopeIds()
    {
        List<String> envelopeIds = this.parseWebhooksStatus.getAvailableEnvelopeIds();
        Map<String, List<String>> returnMap = new HashMap<>();
        returnMap.put("listEnvelopeIds", envelopeIds);
        return returnMap;
    }

    @GetMapping(value = EndPoints.Json.WEBHOOK_LAST_ENTRY)
    @ResponseBody
    public Map<String, WebhookNotification> getLastWebhookEntry(
            @RequestParam("envelopeId") String envelopeId
    )
    {
        Map<String, WebhookNotification> returnMap = new HashMap<>();
        returnMap.put("webhook", this.parseWebhooksStatus.getNotification(envelopeId));
        return returnMap;
    }


    @Bean
    public static ParseWebhooksStatus getParseWebhookStatus()
    {
        return new ParseWebhooksStatus();
    }
}
