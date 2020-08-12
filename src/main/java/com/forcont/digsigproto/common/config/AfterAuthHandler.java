package com.forcont.digsigproto.common.config;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.model.Initiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Listener for successful authentication event.
 */
@Component
public class AfterAuthHandler implements ApplicationListener<InteractiveAuthenticationSuccessEvent>
{
    private final AbstractBaseApi abstractBaseApi;

    @Autowired
    public AfterAuthHandler(AbstractBaseApi abstractBaseApi)
    {
        this.abstractBaseApi = abstractBaseApi;
    }

    /**
     * Handler for authentication success.
     *
     * @param event Contains authentication infos.
     */
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
        provideAccessTokenToBaseApi(event.getAuthentication());
        printLoginSuccess(event.getAuthentication());
    }

    /**
     * Forwarding the access token to {@link AbstractBaseApi} implementation.
     *
     * @param authentication Authentication infos.
     */
    private void provideAccessTokenToBaseApi(Authentication authentication)
    {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        this.abstractBaseApi.setAccessToken(oAuth2AuthenticationDetails.getTokenValue());
    }

    private void printLoginSuccess(Authentication authentication)
    {
        Initiator initiator = (Initiator) authentication.getPrincipal();
        System.out.println("LOGGED IN: " + initiator.getName());
    }
}
