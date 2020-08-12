package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.sandbox.authentication.AuthenticationSandbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Mapping requests to wep pages.
 */
@Controller
public class WebsiteRequest
{

    private final EndPoints endPoints;
    private final AuthenticationSandbox authenticationSandbox;

    @Autowired
    public WebsiteRequest(EndPoints endPoints, AuthenticationSandbox authenticationSandbox)
    {
        this.endPoints = endPoints;
        this.authenticationSandbox = authenticationSandbox;
    }

    @GetMapping(EndPoints.Page.LOGIN_FORWARD)
    public String authForward(Model model)
    {
        model.addAttribute("endpointLogin", this.endPoints.getWithContextPath(EndPoints.Page.LOGIN));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Page.LOGIN_FORWARD);
    }

    /**
     * TODO just for testing authentication
     *
     * @param model thymeleaf injection
     * @return thymeleaf template
     */
    @GetMapping(EndPoints.Page.MISC_ACCESS)
    public String pageTestAccess(Model model)
    {
        model.addAllAttributes(authenticationSandbox.authInfo());
        return this.endPoints.stripLeadingSlash(EndPoints.Page.MISC_ACCESS);
    }

    @GetMapping(EndPoints.Link.SIGNING_INFO)
    public String pageEnvelopeInfo(Model model)
    {
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Json.LIST_ENVELOPES));
        model.addAttribute("endpointStatusSigners", this.endPoints.getWithContextPath(EndPoints.Json.STATUS_SIGNERS));
        model.addAttribute("endpointDownloadDocs", this.endPoints.getWithContextPath(EndPoints.Api.SIGNING_DOWNLOAD));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Link.SIGNING_INFO);
    }

    @GetMapping(EndPoints.Link.SINGING_TEMPLATE)
    public String pageSigningSetupWithTemplate(Model model)
    {
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Json.LIST_TEMPLATES));
        model.addAttribute("endpointaction", this.endPoints.getWithContextPath(EndPoints.Api.USE_TEMPLATE));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Link.SINGING_TEMPLATE);
    }

    @GetMapping(EndPoints.Link.TEMPLATE_CREATE)
    public String createTemplate(Model model)
    {
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Api.TEMPLATE_CREATE));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Link.TEMPLATE_CREATE);
    }

    @GetMapping(EndPoints.Link.SHOW_WEBHOOKS)
    public String getWebhookPage(Model model)
    {
        model.addAttribute("endpointEnvelopeIds", this.endPoints.getWithContextPath(EndPoints.Json.WEBHOOK_LIST_ENVEOPE_IDS));
        model.addAttribute("endpointWebhookPolling", this.endPoints.getWithContextPath(EndPoints.Json.WEBHOOK_LAST_ENTRY));
        this.endPoints.addCommonModelAttr(model);
        return this.endPoints.stripLeadingSlash(EndPoints.Link.SHOW_WEBHOOKS);
    }
}
