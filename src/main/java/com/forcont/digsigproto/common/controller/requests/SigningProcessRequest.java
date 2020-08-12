package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.controller.bookmarks.Bookmarks;
import com.forcont.digsigproto.common.controller.requests.model.CovenantMultiSignerForm;
import com.forcont.digsigproto.common.controller.requests.model.DocumentUploadForm;
import com.forcont.digsigproto.common.controller.requests.model.SignerProcessMapForm;
import com.forcont.digsigproto.common.sandbox.signing.SigningProcessSandbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Map;

@Controller
@Profile("docusign | adobesign")
public class SigningProcessRequest
{

    private final EndPoints endPoints;
    private final SigningProcessSandbox signingProcessSandbox;
    private final Bookmarks bookmarks;

    @Autowired
    public SigningProcessRequest(EndPoints endPoints, SigningProcessSandbox signingProcessSandbox, Bookmarks bookmarks)
    {
        this.endPoints = endPoints;
        this.signingProcessSandbox = signingProcessSandbox;
        this.bookmarks = bookmarks;
    }

    @PostMapping(EndPoints.Api.SINGING_SEND)
    public String sendEnvelopeViaEmailMultiSigner(
            @ModelAttribute CovenantMultiSignerForm covenantMultiSignerForm,
            Model model
    ) throws IOException
    {
        Map<String, String> returnMap = this.signingProcessSandbox.signMultipleDocumentsByMultipleUsers(covenantMultiSignerForm);
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Json.STATUS_SIGNING));
        model.addAttribute("envelopeId", returnMap.get("envelopeId"));
        model.addAttribute("url", returnMap.get("url"));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Page.SIGNING_STATUS_VIEW);
    }

    @GetMapping(EndPoints.Link.SIGNING_SETUP)
    public String sendEnvelopeViaEmailMultiSigner(Model model)
    {
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Api.SINGING_SEND));
        model.addAttribute("initiatorname", this.signingProcessSandbox.getInitiator().getName());
        model.addAttribute("initiatormail", this.signingProcessSandbox.getInitiator().getEmail());
        this.endPoints.addCommonModelAttr(model);
        return "sandbox/signing/setup";
    }

    @GetMapping(EndPoints.Json.LIST_QUAL_SIGS)
    public void listSigProvTest()
    {
        this.signingProcessSandbox.listSignatureProviders();
        System.out.println("ping! GET");
    }

    @GetMapping(EndPoints.Link.UPLOAD_DOCUMENTS)
    public String extractSigners(Model model)
    {
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Page.SIGNER_EXTRACT_MAPPING));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Link.UPLOAD_DOCUMENTS);
    }

    @PostMapping(EndPoints.Page.SIGNER_EXTRACT_MAPPING)
    public String getBookmarks(@ModelAttribute DocumentUploadForm documentUploadForm, Model model) throws IOException
    {
        Map<String, Object> signerMappingData = bookmarks.getBookmarks(documentUploadForm.getDocument());
//        this.bookmarks.getBookmark(documentUploadForm.getDocument());
//        Map<String, Object> signerMappingData = signingProcessSandbox.getBookmarks(documentUploadForm.getDocument());

        model.addAttribute("signerFields", signerMappingData);
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Api.SIGNER_MAPPING));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Page.SIGNER_EXTRACT_MAPPING);
    }

    @GetMapping(EndPoints.Api.SIGNER_MAPPING)
    public String signingWithBookmarks(@ModelAttribute SignerProcessMapForm signerProcessMapForm, Model model)
    {
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Json.STATUS_SIGNING));
        Map<String, String> returnMap = signingProcessSandbox.signingWithBookMarks(signerProcessMapForm);
        model.addAttribute("envelopeId", returnMap.get("envelopeId"));
        model.addAttribute("url", returnMap.get("url"));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Page.SIGNING_STATUS_VIEW);
    }

}
