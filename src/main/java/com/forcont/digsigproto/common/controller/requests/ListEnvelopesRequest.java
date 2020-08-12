package com.forcont.digsigproto.common.controller.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.model.EnvelopeDigSig;
import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.common.sandbox.signing.ListEnvelopes;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Profile("docusign | adobesign")
public class ListEnvelopesRequest
{
    private ListEnvelopes listEnvelopes;


    public ListEnvelopesRequest(ListEnvelopes listEnvelopes)
    {
        this.listEnvelopes = listEnvelopes;
    }


    @GetMapping(EndPoints.Json.STATUS_SIGNING)
    public String listEnvelopes(@RequestParam() String envelopeId)
    {
        String status = listEnvelopes.listEnvelopes(envelopeId);
        Map<String, String> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        body.put("envelopeId", envelopeId);
        body.put("status", status);

        String bodyString = "";
        try
        {
            bodyString = objectMapper.writeValueAsString(body);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        return bodyString;
    }

    @GetMapping(EndPoints.Json.LIST_ENVELOPES)
    public Map<String, List<EnvelopeDigSig>> getAllEnvelopesStatus()
    {
        Map<String, List<EnvelopeDigSig>> envelopes = new HashMap<>();
        envelopes.put("listEnvelopes", this.listEnvelopes.getAllEnvelopesStatus());
        return envelopes;

    }

    @GetMapping(EndPoints.Json.STATUS_SIGNERS)
    public Map<String, List<RecipientDigSig>> getAllSignersStatus(@RequestParam() String envelopeId)
    {
        Map<String, List<RecipientDigSig>> returnMap = new HashMap<>();
        returnMap.put("statusSigners", listEnvelopes.getAllSignersStatus(envelopeId));
        return returnMap;
    }
}
