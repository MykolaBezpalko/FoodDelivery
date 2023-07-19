package com.fooddelivery.configuration;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

//todo need to create new oauth account - this class is commented until it will be created
@Configuration
@PropertySource("classpath:security_users.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private Environment environment;

    @Value("${roles.admin.emails}")
    private String[] adminsEmails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.oauth2Login()
//                .userInfoEndpoint()
//                .userAuthoritiesMapper(this.userAuthoritiesMapper());
//
//        http.csrf().disable().authorizeRequests()
//                .anyRequest().authenticated();
//                .mvcMatchers("/").permitAll()
//                .mvcMatchers("/**").hasRole("ADMIN");
    }

    // https://docs.spring.io/spring-security/site/docs/5.0.7.RELEASE/reference/html/oauth2login-advanced.html#oauth2login-advanced-map-authorities
//    private GrantedAuthoritiesMapper userAuthoritiesMapper() {
//        return (authorities) -> {
//            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//
//            authorities.forEach(authority -> {
//                if (authority instanceof OidcUserAuthority) {
//                    OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;
//                    OidcIdToken idToken = oidcUserAuthority.getIdToken();
//                    for (String mail: adminsEmails) {
//                        if (idToken.getEmail().equals(mail)) {
//                            // retrieve token info to the console
//                            idToken.getClaims().forEach((s, o) -> System.out.println(s + ": " + o.toString()));
//                            System.out.println("idToken:\n" + idToken.getTokenValue().toString());
//
//                            mappedAuthorities.add((GrantedAuthority) () -> "ROLE_ADMIN");
//                        }
//                    }
//                }
//            });
//            return mappedAuthorities;
//        };
//    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(googleClient());
//    }
//
//    private ClientRegistration googleClient() {
//        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
//                .clientId(environment.getProperty("spring.security.oauth2.client.registration.google.clientId"))
//                .clientSecret(environment.getProperty("spring.security.oauth2.client.registration.google.clientSecret"))
//                .build();
//    }
}