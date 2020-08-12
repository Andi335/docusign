package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.controller.requests.model.TemplateSetupForm;
import com.forcont.digsigproto.common.sandbox.templates.CreateTemplateSandbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class CreateTemplateRequest
{
    private final EndPoints endPoints;
    private final CreateTemplateSandbox createTemplateSandbox;

    @Autowired
    public CreateTemplateRequest(
            EndPoints endPoints, CreateTemplateSandbox createTemplateSandbox)
    {
        this.endPoints = endPoints;
        this.createTemplateSandbox = createTemplateSandbox;
    }

    @PostMapping(EndPoints.Api.TEMPLATE_CREATE)
    public String createTemplate(@ModelAttribute TemplateSetupForm templateSetupForm, Model model) throws IOException
    {
        this.createTemplateSandbox.createTemplate(templateSetupForm);
        model.addAttribute("endpoint", this.endPoints.getWithContextPath(EndPoints.Json.LIST_TEMPLATES));
        model.addAttribute("endpointaction", this.endPoints.getWithContextPath(EndPoints.Api.USE_TEMPLATE));
        this.endPoints.addCommonModelAttr(model);
        return EndPoints.Link.SINGING_TEMPLATE;
    }

}
