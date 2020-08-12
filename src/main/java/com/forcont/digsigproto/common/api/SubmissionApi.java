package com.forcont.digsigproto.common.api;

import com.forcont.digsigproto.common.model.*;
import org.apache.commons.httpclient.HttpException;

import java.util.List;
import java.util.Map;

public interface SubmissionApi
{
    Map<String, String> createTemplate(Initiator initiator, String templateName, Map<RecipientDigSig, List<SigningAction>> signerMap, List<Feature> features) throws HttpException;

    List<TemplateDigSig> getTemplates(Initiator initiator) throws HttpException;

    String useTemplate(Initiator initiator, List<RecipientDigSig> recipientDigSigs, String id) throws HttpException;
}
