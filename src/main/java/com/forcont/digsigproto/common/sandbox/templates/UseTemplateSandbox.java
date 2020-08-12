package com.forcont.digsigproto.common.sandbox.templates;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.common.controller.requests.model.TemplateUsageForm;
import com.forcont.digsigproto.common.model.Initiator;
import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.common.model.TemplateDigSig;
import org.apache.commons.httpclient.HttpException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UseTemplateSandbox
{

    private final AbstractBaseApi baseApi;
    private final AuthGrantCodeAccessor authGrantCodeAccessor;

    public UseTemplateSandbox(AbstractBaseApi baseApi, AuthGrantCodeAccessor authGrantCodeAccessor)
    {
        this.baseApi = baseApi;
        this.authGrantCodeAccessor = authGrantCodeAccessor;
    }


    public String createEnvelopeFromTemplate(TemplateUsageForm templateUsageForm) throws HttpException
    {

        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();

        String templateId = templateUsageForm.getTemplateId();
        List<RecipientDigSig> recipientDigSigs = new ArrayList<>();

        if (templateUsageForm.getRole() != null) {
            for(int i = 0; i < templateUsageForm.getRole().size(); i++){
                recipientDigSigs.add(
                        new RecipientDigSig(
                                templateUsageForm.getName().get(i), templateUsageForm.getEmail().get(i), templateUsageForm.getRole().get(i)
                        )
                );
            }
        }

        return baseApi.getSubmissionApi().useTemplate(initiator, recipientDigSigs, templateId);
    }

    public List<TemplateDigSig> listTemplates()
    {
        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();
        List<TemplateDigSig> templateDigSigs = new ArrayList<>();
        try
        {
            templateDigSigs = this.baseApi.getSubmissionApi().getTemplates(initiator);
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }
        return templateDigSigs;
    }
}
