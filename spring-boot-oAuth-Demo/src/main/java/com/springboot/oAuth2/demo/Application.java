package com.springboot.oAuth2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
@EnableOAuth2Sso
public class Application extends WebSecurityConfigurerAdapter {
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
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
