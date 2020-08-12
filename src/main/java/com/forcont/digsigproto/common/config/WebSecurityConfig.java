package com.forcont.digsigproto.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth)
    {

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf().disable() //https://github.com/spring-guides/tut-spring-security-and-angular-js/issues/71
                .authorizeRequests().antMatchers("/", "/links", "login**", "/error**", "/forward", "/webjars/**", "/webhooks/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                // instead of authenticate silently, show a page with authentication link
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/forward"))
                .and()
                .logout().permitAll().logoutSuccessUrl("/");
    }

    /**
     * Replacing the Spring Boot standard handler for oauth2 user info request.
     *
     * @return Special user info response handler.
     */
    @Bean
    @Profile("docusign")
    public PrincipalExtractor docuSignPrincipalExtractor()
    {
        return new InitiatorDocusignExtractor();
    }
}
