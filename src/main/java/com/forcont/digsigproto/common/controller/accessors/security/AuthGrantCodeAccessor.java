package com.forcont.digsigproto.common.controller.accessors.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;

/**
 * Accessor for OAuth2 credentials stored by {@link SecurityContextHolder}
 */
@Controller
public class AuthGrantCodeAccessor implements AuthDataAccessor
{

    @Override
    public boolean isAuthenticated()
    {
        return this.getSecurityContextAuth().isAuthenticated();
    }

    @Override
    public String getAccessToken()
    {
        return this.convertOAuth2ToOAuth2Details(this.convertAuthToOAuth2(this.getSecurityContextAuth())).getTokenValue();
    }

    @Override
    public Object getAuthUser()
    {
        return this.getSecurityContextAuth().getPrincipal();
    }

    private Authentication getSecurityContextAuth()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private OAuth2Authentication convertAuthToOAuth2(Authentication authentication)
    {
        return (OAuth2Authentication) authentication;
    }

    private OAuth2AuthenticationDetails convertOAuth2ToOAuth2Details(OAuth2Authentication oAuth2)
    {
        return (OAuth2AuthenticationDetails) oAuth2.getDetails();
    }
}
