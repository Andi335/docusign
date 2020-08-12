package com.forcont.digsigproto.common.config;

import com.docusign.esign.client.auth.OAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forcont.digsigproto.providers.docusign.converter.InitiatorUserInfoConverterImpl;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Profile;

import java.util.Map;

/**
 * Replacing the Spring BOot standard handler for oauth2 user info request.
 */
@Profile("docusign")
class InitiatorDocusignExtractor implements PrincipalExtractor
{
    /**
     * Converts the response from the oauth2 user info request
     * to {@link com.forcont.digsigproto.common.model.Initiator} model class.
     * Thus it can safely be cast from the returned {@link Object} to the model class.
     *
     * @param map JSON as {@link Map} from oauth2 user info request.
     * @return Initiator object.
     */
    @Override
    public Object extractPrincipal(Map<String, Object> map)
    {
        final ObjectMapper objectMapper = new ObjectMapper();
        OAuth.UserInfo userInfo = objectMapper.convertValue(map, OAuth.UserInfo.class);
        InitiatorUserInfoConverterImpl initiatorUserInfoConverterImpl = new InitiatorUserInfoConverterImpl();
        return initiatorUserInfoConverterImpl.convertFromProvider(userInfo);
    }
}
