package com.forcont.digsigproto.providers.adobesign.api;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.api.CovenantApi;
import com.forcont.digsigproto.common.api.SubmissionApi;
import org.apache.commons.lang3.NotImplementedException;

public class AdobesignApi extends AbstractBaseApi
{
    public AdobesignApi()
    {
        throw new NotImplementedException("Not implemented yet: " + this.getClass().getSimpleName());
    }

    @Override
    protected CovenantApi createCovenantApi()
    {
        return null;
    }

    @Override
    protected SubmissionApi createSubmissionApi()
    {
        return null;
    }

    @Override
    public void setAccessToken(String accessToken)
    {

    }
}
