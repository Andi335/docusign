package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.model.TemplateDigSig;
import com.forcont.digsigproto.common.sandbox.templates.UseTemplateSandbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Profile("docusign | adobesign")
public class ListTemplatesRequest
{

    private UseTemplateSandbox useTemplateSandbox;

    @Autowired
    public ListTemplatesRequest(UseTemplateSandbox useTemplateSandbox)
    {
        this.useTemplateSandbox = useTemplateSandbox;
    }

    @GetMapping(EndPoints.Json.LIST_TEMPLATES)
    public Map<String, List<TemplateDigSig>> listAllTemplates()
    {
        Map<String, List<TemplateDigSig>> templates = new HashMap<>();
        templates.put("listTemplates", this.useTemplateSandbox.listTemplates());
        return templates;
    }
}
