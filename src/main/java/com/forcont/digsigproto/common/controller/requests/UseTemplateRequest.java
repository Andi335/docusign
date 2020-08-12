package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.controller.requests.model.TemplateUsageForm;
import com.forcont.digsigproto.common.sandbox.templates.UseTemplateSandbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@Controller
public class UseTemplateRequest
{

    private final EndPoints endPoints;
    private UseTemplateSandbox useTemplateSandbox;

    @Autowired
    public UseTemplateRequest(EndPoints endPoints, UseTemplateSandbox useTemplateSandbox)
    {
        this.endPoints = endPoints;
        this.useTemplateSandbox = useTemplateSandbox;
    }

    @GetMapping(EndPoints.Api.USE_TEMPLATE)
    public String useTemplate(@ModelAttribute TemplateUsageForm templateUsageForm, Model model) throws IOException
    {
        String enevopeId = this.useTemplateSandbox.createEnvelopeFromTemplate(templateUsageForm);
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Json.STATUS_SIGNING));
        model.addAttribute("envelopeId", enevopeId);
        model.addAttribute("url", "");
        this.endPoints.addCommonModelAttr(model);
        return "sandbox/signing/status";
    }
}
