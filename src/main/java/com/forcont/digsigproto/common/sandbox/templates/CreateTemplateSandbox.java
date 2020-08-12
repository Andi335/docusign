package com.forcont.digsigproto.common.sandbox.templates;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.api.Feature;
import com.forcont.digsigproto.common.api.features.CovenantExpiration;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.common.controller.requests.model.*;
import com.forcont.digsigproto.common.model.*;
import com.forcont.digsigproto.common.sandbox.SandboxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CreateTemplateSandbox
{
    private final AbstractBaseApi baseApi;
    private final AuthGrantCodeAccessor authGrantCodeAccessor;


    @Autowired
    public CreateTemplateSandbox(AbstractBaseApi baseApi, AuthGrantCodeAccessor authGrantCodeAccessor)
    {
        this.baseApi = baseApi;
        this.authGrantCodeAccessor = authGrantCodeAccessor;
    }

    public Initiator getInitiator()
    {
        return (Initiator) this.authGrantCodeAccessor.getAuthUser();
    }

    public String createTemplate(TemplateSetupForm templateSetupForm) throws IOException
    {

        Map<String, Paper> paperMap = SandboxUtils.converMultipartFilesToPaperMap(templateSetupForm.getDocument());

        Map<RecipientDigSig, List<SigningAction>> signerMap = SandboxUtils.convertRoleFormsToSignerMap(templateSetupForm.getRoles(), paperMap);

        List<Feature> features = new ArrayList<>();
        features.add(
                new CovenantExpiration(
                        Integer.valueOf(templateSetupForm.getExpirationDays()),
                        Integer.valueOf(templateSetupForm.getExpirationReminderDays())
                )
        );

        this.baseApi.getSubmissionApi().createTemplate(this.getInitiator(), templateSetupForm.getTemplateName(), signerMap,features);

        return "";
    }
}
