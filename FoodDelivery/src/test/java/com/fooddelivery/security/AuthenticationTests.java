package com.fooddelivery.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    void testUnauthenticatedHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isFound());
    }

    @Test
    void testUnauthenticatedCooksEndpoint() throws Exception {
        mockMvc.perform(get("/cooks"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testAuthenticatedWithoutRoleAdminCooksEndpoint() throws Exception {
        mockMvc.perform(get("/cooks")
                .with(oauth2Login()))
                .andExpect(status().isOk());
    }

    @Test
    void testAuthenticatedWithRoleAdminCooksEndpoint() throws Exception {
        mockMvc.perform(get("/cooks")
                .with(oauth2Login()
                        .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(status().isOk());
    }
}
