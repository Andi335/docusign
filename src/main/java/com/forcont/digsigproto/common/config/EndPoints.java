package com.forcont.digsigproto.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Centralized custom DigSig REST endpoints.
 */

@Component
public final class EndPoints
{

    private Environment environment;

    @Autowired
    public EndPoints(Environment environment)
    {
        this.environment = environment;
    }

    public final class Link
    {
        public static final String SINGING_TEMPLATE = "/sandbox/signing/template";
        public static final String SIGNING_SETUP = "/sandbox/signing/setup";
        public static final String TEMPLATE_CREATE = "/sandbox/template/create";
        public static final String SIGNING_INFO = "/sandbox/signing/info";
        public static final String UPLOAD_DOCUMENTS = "/sandbox/signing/signer/extract";
        public static final String SHOW_WEBHOOKS = "/sandbox/signing/webhook";
    }

    public final class Page
    {
        public static final String LOGIN_FORWARD = "/forward";
        public static final String MISC_ACCESS = "/sandbox/misc/access";
        public static final String SIGNING_STATUS_VIEW = "/sandbox/signing/status";
        public static final String SIGNER_EXTRACT_MAPPING = "/sandbox/signing/signer/map";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";

    }

    public final class Json
    {

        public static final String STATUS_SIGNERS = "/signing/status/signers";
        public static final String STATUS_SIGNING = "/signing/status";
        public static final String LIST_TEMPLATES = "/listalltemplates";
        public static final String LIST_ENVELOPES = "/listallenvelopes";
        public static final String LIST_QUAL_SIGS = "/listsbs";
        public static final String LINKS = "/links";
        public static final String WEBHOOK_LIST_ENVEOPE_IDS = "/list/webhook/envelopes";
        public static final String WEBHOOK_LAST_ENTRY = "/webhook/last";
    }

    public final class Api
    {
        public static final String TEMPLATE_CREATE = "/template/create";
        public static final String SIGNING_DOWNLOAD = "/signing/download";
        public static final String SIGNER_MAPPING = "/signer/mapping";
        public static final String USE_TEMPLATE = "/signing/send/template";
        public static final String SINGING_SEND = "/signing/send";
        public static final String WEBHOOK_LISTENER = "/webhooks/listener";
    }

    public Map<Field,String> getLinks() throws NoSuchFieldException
    {

        Map<Field,String> indexLink = new HashMap<>();
        indexLink.put(Link.class.getField("SINGING_TEMPLATE"), "Create and send an envelope with a template");
        indexLink.put(Link.class.getField("SIGNING_SETUP"),"Create and send an envelope");
        indexLink.put(Link.class.getField("TEMPLATE_CREATE"), "Create a template");
        indexLink.put(Link.class.getField("SIGNING_INFO"), "Show envelopes");
        indexLink.put(Link.class.getField("UPLOAD_DOCUMENTS"),"Upload documents with bookmarks in it");
        indexLink.put(Link.class.getField("SHOW_WEBHOOKS"),"Show the status of envelopes");
        return indexLink;
    }


    public String getWithContextPath(String url)
    {
        String contextPath = this.environment.getProperty("server.servlet.context-path");
        return contextPath + url;
    }

    public String stripLeadingSlash(String viewPath)
    {
        if (viewPath.startsWith("/"))
        {
            return viewPath.substring(1);
        }
        else
        {
            return viewPath;
        }
    }

    public void addCommonModelAttr(Model model) {
        model.addAttribute("endpointHome", this.getWithContextPath(""));
        model.addAttribute("endpointLogout", this.getWithContextPath(EndPoints.Page.LOGOUT));
        model.addAttribute("contextPath", this.getWithContextPath(""));
    }
}
