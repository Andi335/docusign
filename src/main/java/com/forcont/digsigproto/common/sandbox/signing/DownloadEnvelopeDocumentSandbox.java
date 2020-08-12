package com.forcont.digsigproto.common.sandbox.signing;


import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.common.model.Initiator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DownloadEnvelopeDocumentSandbox
{
    private final AbstractBaseApi baseApi;
    private final AuthGrantCodeAccessor authGrantCodeAccessor;

    public DownloadEnvelopeDocumentSandbox(AbstractBaseApi baseApi,
                                           AuthGrantCodeAccessor authGrantCodeAccessor)
    {
        this.baseApi = baseApi;
        this.authGrantCodeAccessor = authGrantCodeAccessor;
    }

    public void downloadEnvelopeDocument(String id, HttpServletResponse response) throws IOException
    {

        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();
        this.baseApi.getCovenantApi().downloadEnvelopeDocument(initiator, id,response);
    }

}
