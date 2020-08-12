package com.forcont.digsigproto.providers.docusign.converter;

import com.docusign.esign.client.auth.OAuth;
import com.forcont.digsigproto.common.api.ModelConverter;
import com.forcont.digsigproto.common.model.Initiator;

public class InitiatorUserInfoConverterImpl implements ModelConverter<Initiator, OAuth.UserInfo>
{

    @Override
    public Initiator convertFromProvider(OAuth.UserInfo userInfo)
    {
        return new Initiator(
                userInfo.getAccounts().get(0).getAccountId(),
                userInfo.getName(),
                userInfo.getEmail()
        );
    }

    @Override
    public OAuth.UserInfo convertToProvider(Initiator digsigModel)
    {
        OAuth.UserInfo userInfo = new OAuth.UserInfo();
        userInfo.setName(digsigModel.getName());
        userInfo.setEmail(digsigModel.getEmail());

        OAuth.Account account = new OAuth.Account();
        account.setAccountId(digsigModel.getId());
        userInfo.addAccountsItem(account);
        return userInfo;
    }
}
