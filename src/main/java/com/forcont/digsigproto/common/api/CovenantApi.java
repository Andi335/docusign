package com.forcont.digsigproto.common.api;

import com.forcont.digsigproto.common.model.*;
import org.apache.commons.httpclient.HttpException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CovenantApi
{

    String listEnvelopes(Initiator initiator, String envelopeId) throws HttpException;

    List<EnvelopeDigSig> getAllEnvelopesStatus(Initiator initiator) throws HttpException;

    List<RecipientDigSig> getAllSignersStatus(Initiator initiator, String id) throws HttpException;

    Map<String, String> sendSignatureRequest(Initiator initiator, Map<RecipientDigSig, List<SigningAction>> signerMap,
                                             List<Feature> features) throws IOException;

    Map<String, String> startSigningWithBookmarks(Initiator initiator, Map<RecipientDigSig, List<SigningAction>> signerListMap);

    void listQualifiedSignatureProviders(Initiator initiator) throws HttpException;

    void downloadEnvelopeDocument(Initiator initiator, String id, HttpServletResponse response) throws IOException;
}
