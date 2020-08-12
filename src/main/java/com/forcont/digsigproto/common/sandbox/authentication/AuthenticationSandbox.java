package com.forcont.digsigproto.common.sandbox.authentication;

import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.common.model.Initiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Sandbox for authentication process using the common DigSig API.
 */
@Component
@Profile("sandbox & (docusign | adobesign)")
public class AuthenticationSandbox
{
    private final AbstractBaseApi baseApi;
    private final AuthGrantCodeAccessor authGrantCodeAccessor;

    @Autowired
    public AuthenticationSandbox(AbstractBaseApi baseApi, AuthGrantCodeAccessor authGrantCodeAccessor)
    {
        this.baseApi = baseApi;
        this.authGrantCodeAccessor = authGrantCodeAccessor;
    }

    public Map<String, String> authInfo()
    {
        HashMap<String, String> authInfoMap = new HashMap<>();
        boolean isAuth = this.authGrantCodeAccessor.isAuthenticated();
        if (isAuth)
        {
            Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();
            authInfoMap.put("accesstoken", this.authGrantCodeAccessor.getAccessToken());
            authInfoMap.put("userInfoClass", initiator.getClass().getSimpleName());
            authInfoMap.put("accIdVal", initiator.getId());
            authInfoMap.put("nameVal", initiator.getName());
            authInfoMap.put("emailVal", initiator.getEmail());
        }
        return authInfoMap;
    }
}
