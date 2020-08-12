package com.forcont.digsigproto.common.sandbox;

import com.forcont.digsigproto.common.controller.requests.model.ActionForm;
import com.forcont.digsigproto.common.controller.requests.model.RoleForm;
import com.forcont.digsigproto.common.controller.requests.model.SignerForm;
import com.forcont.digsigproto.common.model.Paper;
import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.common.model.SigningAction;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class SandboxUtils
{

    public static List<Paper> converMultipartFilesToOrderedPapers(List<MultipartFile> multipartFiles) throws IOException
    {
        List<Paper> papers = new ArrayList<>();
        for (int i = 0; i < multipartFiles.size(); i++)
        {
            MultipartFile multipartFile = multipartFiles.get(i);
            papers.add(new Paper(String.valueOf(i), multipartFile.getOriginalFilename(), multipartFile.getBytes()));
        }

        return papers;
    }

    public static Map<String, Paper> converMultipartFilesToPaperMap(List<MultipartFile> multipartFiles) throws IOException
    {
        Map<String, Paper> paperMap = new HashMap<>();
        for (int i = 0; i < multipartFiles.size(); i++)
        {
            String paperId = String.valueOf(i + 1);
            MultipartFile multipartFile = multipartFiles.get(i);
            paperMap.put(paperId, new Paper(paperId, multipartFile.getOriginalFilename(), multipartFile.getBytes()));
        }

        return paperMap;
    }

    public static Map<RecipientDigSig, List<SigningAction>> convertRoleFormsToSignerMap(List<RoleForm> roleForms, Map<String, Paper> paperMap)
    {
        Map<RecipientDigSig, List<SigningAction>> signerActions = new HashMap<>();
        for (RoleForm roleForm : roleForms)
        {
            signerActions.put(
                    new RecipientDigSig(roleForm.getRole()),
                    SandboxUtils.convertActionFormToSigningAction(roleForm.getActions(), paperMap)
            );

        }
        return signerActions;
    }

    public static Map<RecipientDigSig, List<SigningAction>> convertSignerFormsToSignerMap(List<SignerForm> signerForms, Map<String, Paper> paperMap)
    {
        Map<RecipientDigSig, List<SigningAction>> signerActions = new LinkedHashMap<>(signerForms.size());
        for (SignerForm signerForm : signerForms)
        {
            signerActions.put(
                    new RecipientDigSig(signerForm.getName(), signerForm.getMail()),
                    SandboxUtils.convertActionFormToSigningAction(signerForm.getActions(), paperMap)
            );
        }
        return signerActions;
    }

    public static List<SigningAction> convertActionFormToSigningAction(List<ActionForm> actionForms, Map<String, Paper> paperMap)
    {
        List<SigningAction> signingActions = new ArrayList<>();
        for (ActionForm actionForm : actionForms)
        {
            signingActions.add(
                    new SigningAction(
                            paperMap.get(actionForm.getDoc()),
                            actionForm.getType()
                    )
            );
        }
        return signingActions;
    }
}
