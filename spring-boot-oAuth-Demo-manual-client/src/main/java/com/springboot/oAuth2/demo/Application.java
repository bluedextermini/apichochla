package com.springboot.oAuth2.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.Filter;

@SpringBootApplication
@EnableOAuth2Client
public class Application extends WebSecurityConfigurerAdapter {



    @Autowired
    private AuthorizationCodeResourceDetails facebook;

    @Autowired
    private ResourceServerProperties facebookResource;

    @Autowired
    private Filter ssoFilter;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/error",
                        "/login","/","/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                /**
                 * for logout start
                 */
                .and()
                .logout()
                .logoutSuccessUrl("/")
                /**
                 * for logout end
                 */
                .permitAll()
                .and().csrf()
                .csrfTokenRepository(
                        CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .addFilterBefore(ssoFilter,
                        BasicAuthenticationFilter.class);
    }





}
