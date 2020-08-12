package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.client.ApiClient;
import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.api.CovenantApi;
import com.forcont.digsigproto.common.api.SubmissionApi;

/**
 * API class for DocuSign, implementing the common API {@link AbstractBaseApi}
 */
public class DocusignApi extends AbstractBaseApi
{

    private final ApiClient apiClient;

    public DocusignApi()
    {
        this.apiClient = new ApiClient(ApiClient.DEMO_REST_BASEPATH);
    }

    @Override
    protected CovenantApi createCovenantApi()
    {
        return new EnvelopeImpl(this.apiClient);
    }

    @Override
    protected SubmissionApi createSubmissionApi()
    {
        return new TemplateImpl(this.apiClient);
    }

    @Override
    public void setAccessToken(String accessToken)
    {
        this.apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
    }
}
