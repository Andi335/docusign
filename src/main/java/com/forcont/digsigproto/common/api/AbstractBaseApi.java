package com.forcont.digsigproto.common.api;

/**
 * Common interface for all signing providers.
 * Each provider has to implement this abstract class
 * in order to map its special processes to a generic API.
 */
public abstract class AbstractBaseApi
{

    private CovenantApi covenantApi;
    private SubmissionApi submissionApi;


    protected abstract CovenantApi createCovenantApi();
    protected abstract SubmissionApi createSubmissionApi();

    public CovenantApi getCovenantApi()
    {
        if (this.covenantApi != null) {
            return covenantApi;
        }
        else {
            this.covenantApi = this.createCovenantApi();
            return this.covenantApi;
        }
    }

    public SubmissionApi getSubmissionApi()
    {
        if (this.submissionApi != null) {
            return submissionApi;
        }
        else {
            this.submissionApi = this.createSubmissionApi();
            return this.submissionApi;
        }
    }

    /**
     * @param accessToken Token required to use an signing provider API
     */
    abstract public void setAccessToken(String accessToken);

}
