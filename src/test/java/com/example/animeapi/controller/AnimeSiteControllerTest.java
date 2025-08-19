package com.example.animeapi.controller;

import com.example.animeapi.model.AnimeSite;
import com.example.animeapi.service.AnimeSiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnimeSiteController.class)
public class AnimeSiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private AnimeSiteService animeSiteService;

    @Autowired
    private ObjectMapper objectMapper;

    private AnimeSite animeSite;

    @BeforeEach
    void setUp() {
        animeSite = new AnimeSite("Test Name", "http://test.com", "Test Description", "Streaming", true);
    }

    @Test
    void testGetAllAnimeSites() throws Exception {
        when(animeSiteService.getAllAnimeSites()).thenReturn(Collections.singletonList(animeSite));

        mockMvc.perform(get("/api/anime-sites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void testGetAnimeSiteById() throws Exception {
        when(animeSiteService.getAnimeSiteById(1L)).thenReturn(Optional.of(animeSite));

        mockMvc.perform(get("/api/anime-sites/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Name"));
    }

    @Test
    void testGetAnimeSitesByCategory() throws Exception {
        when(animeSiteService.getAnimeSitesByCategory("Streaming")).thenReturn(Collections.singletonList(animeSite));

        mockMvc.perform(get("/api/anime-sites/category/Streaming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void testGetAnimeSitesByStatus() throws Exception {
        when(animeSiteService.getActiveAnimeSites(true)).thenReturn(Collections.singletonList(animeSite));

        mockMvc.perform(get("/api/anime-sites/status/true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void testCreateAnimeSite() throws Exception {
        when(animeSiteService.saveAnimeSite(any(AnimeSite.class))).thenReturn(animeSite);

        mockMvc.perform(post("/api/anime-sites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animeSite)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Name"));
    }

    @Test
    void testUpdateAnimeSite() throws Exception {
        when(animeSiteService.updateAnimeSite(any(Long.class), any(AnimeSite.class))).thenReturn(animeSite);

        mockMvc.perform(put("/api/anime-sites/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animeSite)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Name"));
    }

    @Test
    void testDeleteAnimeSite() throws Exception {
        mockMvc.perform(delete("/api/anime-sites/1"))
                .andExpect(status().isNoContent());
    }
}
