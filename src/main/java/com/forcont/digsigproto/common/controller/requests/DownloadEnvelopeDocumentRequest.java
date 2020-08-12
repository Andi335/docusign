package com.forcont.digsigproto.common.controller.requests;


import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.sandbox.signing.DownloadEnvelopeDocumentSandbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DownloadEnvelopeDocumentRequest
{
    private final DownloadEnvelopeDocumentSandbox downloadEnvelopeDocumentSandbox;

    @Autowired
    public DownloadEnvelopeDocumentRequest(DownloadEnvelopeDocumentSandbox downloadEnvelopeDocumentSandbox)
    {
        this.downloadEnvelopeDocumentSandbox = downloadEnvelopeDocumentSandbox;
    }


    @GetMapping(EndPoints.Api.SIGNING_DOWNLOAD)
    public void downloadEnvelopeDocument(@RequestParam(value= "id") String id, HttpServletResponse response) throws IOException
    {

        this.downloadEnvelopeDocumentSandbox.downloadEnvelopeDocument(id, response);
    }
}
