package com.fooddelivery;

import com.fooddelivery.controller.CookController;
import com.fooddelivery.model.Cook;
import com.fooddelivery.service.CookService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CookController.class)
@ContextConfiguration(classes = CookControllerTests.TestController.class)
@WithMockUser
public class CookControllerTests {

    @MockBean
    private CookService cookService;

    @Resource
    private MockMvc mockMvc;

    public CookControllerTests() {
    }

    @Test
    void getAllCooks() throws Exception {
        List<Cook> cooks = Arrays.asList(
                new Cook(100, "ABC"),
                new Cook(101, "DEF")
        );

        when(cookService.getAll()).thenReturn(cooks);

        mockMvc.perform(get("/cooks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cookId").value("100"))
                .andExpect(jsonPath("$[1].name").value("DEF"));

        verify(cookService, only()).getAll();
    }

    @Test
    void getCookById() throws Exception {
        Cook cook = new Cook(100, "ABC");

        when(cookService.getById(cook.getCookId())).thenReturn(cook);

        mockMvc.perform(get("/cooks/{id}", 100))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("ABC"));

        verify(cookService, only()).getById(cook.getCookId());
    }

    @Test
    void getCookByIdNotFound() throws Exception {
        Cook cook = new Cook(100, "ABC");

        when(cookService.getById(cook.getCookId())).thenReturn(null);

        mockMvc.perform(get("/cooks/{id}", 100))
                .andExpect(status().isNotFound());

        verify(cookService, only()).getById(cook.getCookId());
    }

    @Test
    void addCook() throws Exception {
        Cook cook = new Cook(100, "ABC");

        when(cookService.save(cook)).thenReturn(cook);

        mockMvc.perform(post("/cooks")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cookId\": \"100\", \"name\": \"ABC\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ABC"));

        verify(cookService, only()).save(cook);
    }

    @Test
    void updateCook() throws Exception {
        Cook cook = new Cook(100, "ABC");

        when(cookService.save(cook)).thenReturn(cook);

        mockMvc.perform(put("/cooks/{id}", 100)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cookId\": \"100\", \"name\": \"ABC\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ABC"));

        verify(cookService, only()).save(cook);
    }

    @Test
    void deleteCookById() throws Exception {
        Cook cook = new Cook(100, "ABC");

        when(cookService.deleteById(cook.getCookId())).thenReturn(cook);

        mockMvc.perform(delete("/cooks/{id}", 100)
                .with(csrf()))
                .andExpect(status().isOk());

        verify(cookService, only()).deleteById(cook.getCookId());
    }

    @Test
    void deleteCookByIdNotFound() throws Exception {
        Cook cook = new Cook(100, "ABC");

        when(cookService.deleteById(cook.getCookId())).thenReturn(null);

        mockMvc.perform(delete("/cooks/{id}", 100)
                .with(csrf()))
                .andExpect(status().isNotFound());

        verify(cookService, only()).deleteById(cook.getCookId());
    }

    @Configuration
    static class TestController {

        @Bean
        public CookController getCookController() {
            return new CookController();
        }
    }
}