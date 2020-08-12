package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.model.*;
import com.forcont.digsigproto.common.model.Initiator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@WebAppConfiguration
//@SpringBootTest(classes = AuthorizationServerApplication.class)
public class EnvelopeImplTest
{
    private ApiClient apiClient;









    private Initiator initiator = new Initiator("12345678", "Tom", "tom@email.de");

    private Envelope env = new Envelope();

    @Autowired
    private WebApplicationContext wac;

//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .build();
    }

    private String obtainAccessToken(String username, String password) throws Exception
    {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "fooClientIdPassword");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                                          .params(params)
                                          .with(httpBasic("fooClientIdPassword", "secret"))
                                          .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }


    @Before
    public void setUp()
    {
//        EnvelopesApi envelopesApi = new EnvelopesApi();
//        Envelope env = new Envelope();
        env.setEnvelopeId("87654321");
        env.setStatus("sent");

    }

    @Mock
    EnvelopesApi envelopesApi;

    @Test
    public void listEnvelopes() throws Exception
    {
        ApiClient apiClient = new ApiClient(ApiClient.DEMO_REST_BASEPATH);
        EnvelopeImpl envelope = new EnvelopeImpl(apiClient);


        String accessToken = obtainAccessToken("", "");

        mockMvc.perform(get("http://localhost:8008/signing/status")
                                .header("Authorization", "Bearer " + accessToken)
                                .param("envelopeId", "8c7ef843-71ea-4ddf-9db6-8ab4928ca87c\n")
                                .accept("application/json;charset=UTF-8"));


        when(envelopesApi.getEnvelope("677dfa8d-6be4-438b-bc51-c66b394e7313","8c7ef843-71ea-4ddf-9db6-8ab4928ca87c")).thenReturn(env);
        assertEquals("in progress", envelope.listEnvelopes(initiator, "87654321"));
    }


    @Test
    public void getAllEnvelopesStatus()
    {

    }

    @Test
    public void getAllSignersStatus()
    {
    }

    @Test
    public void startMultipleSigningProcess()
    {
    }

    @Test
    public void downloadEnvelopeDocument()
    {
    }
}