package com.forcont.digsigproto.common;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.providers.adobesign.api.AdobesignApi;
import com.forcont.digsigproto.providers.docusign.api.DocusignApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Factory which produces instances of provider APIs
 */
@Component
public class DigSig
{
    /**
     * Produces an instance of a specified signing provider.
     *
     * @param provider Identifier from {@link DigSigProviders}
     * @return Instance of {@link AbstractBaseApi} implementation
     */
    public static AbstractBaseApi createProvider(DigSigProviders provider)
    {
        if (provider == DigSigProviders.DOCUSIGN)
        {
            return DigSig.createDocusignProvider();
        }
        else if (provider == DigSigProviders.ADOBESIGN)
        {
            return DigSig.createAdobesignProvider();
        }
        else
        {
            return new DocusignApi();
        }
    }

    /**
     * Produces an instance of {@link DocusignApi}
     * Spring Profile dependent Spring Bean for auto wiring.
     *
     * @return Instance of {@link DocusignApi}
     */
    @Bean
    @Profile("docusign")
    public static DocusignApi createDocusignProvider()
    {
        return new DocusignApi();
    }

    /**
     * Produces an instance of {@link AdobesignApi}
     * Spring Profile dependent Spring Bean for auto wiring.
     *
     * @return Instance of {@link AdobesignApi}
     */
    @Bean
    @Profile("adobesign")
    public static AdobesignApi createAdobesignProvider()
    {
        return new AdobesignApi();
    }


    /**
     *
     * @return Accessor for Spring Security
     */
    @Bean
    public AuthGrantCodeAccessor getAuthGrantCodeAccessor()
    {
        return new AuthGrantCodeAccessor();
    }
}
