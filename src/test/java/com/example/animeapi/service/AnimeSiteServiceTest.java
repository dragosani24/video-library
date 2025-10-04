package com.example.animeapi.service;

import com.example.animeapi.model.AnimeSite;
import com.example.animeapi.repository.AnimeSiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimeSiteServiceTest {

    @Mock
    private AnimeSiteRepository animeSiteRepository;

    @InjectMocks
    private AnimeSiteService animeSiteService;

    private AnimeSite animeSite;

    @BeforeEach
    void setUp() {
        animeSite = new AnimeSite("Test Name", "http://test.com", "Test Description", "Streaming", true);
    }

    @Test
    void testGetAllAnimeSites() {
        when(animeSiteRepository.findAll()).thenReturn(Collections.singletonList(animeSite));
        List<AnimeSite> animeSites = animeSiteService.getAllAnimeSites();
        assertEquals(1, animeSites.size());
        verify(animeSiteRepository, times(1)).findAll();
    }

    @Test
    void testGetAnimeSiteById() {
        when(animeSiteRepository.findById(1L)).thenReturn(Optional.of(animeSite));
        Optional<AnimeSite> foundAnimeSite = animeSiteService.getAnimeSiteById(1L);
        assertEquals(animeSite.getName(), foundAnimeSite.get().getName());
        verify(animeSiteRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAnimeSitesByCategory() {
        when(animeSiteRepository.findByCategory("Streaming")).thenReturn(Collections.singletonList(animeSite));
        List<AnimeSite> animeSites = animeSiteService.getAnimeSitesByCategory("Streaming");
        assertEquals(1, animeSites.size());
        verify(animeSiteRepository, times(1)).findByCategory("Streaming");
    }

    @Test
    void testGetActiveAnimeSites() {
        when(animeSiteRepository.findByIsActive(true)).thenReturn(Collections.singletonList(animeSite));
        List<AnimeSite> animeSites = animeSiteService.getActiveAnimeSites(true);
        assertEquals(1, animeSites.size());
        verify(animeSiteRepository, times(1)).findByIsActive(true);
    }

    @Test
    void testSaveAnimeSite() {
        when(animeSiteRepository.save(animeSite)).thenReturn(animeSite);
        AnimeSite savedAnimeSite = animeSiteService.saveAnimeSite(animeSite);
        assertEquals(animeSite.getName(), savedAnimeSite.getName());
        verify(animeSiteRepository, times(1)).save(animeSite);
    }

    @Test
    void testUpdateAnimeSite() {
        when(animeSiteRepository.findById(1L)).thenReturn(Optional.of(animeSite));
        when(animeSiteRepository.save(animeSite)).thenReturn(animeSite);
        AnimeSite updatedAnimeSite = new AnimeSite("Updated Name", "http://updated.com", "Updated Description", "News", false);
        AnimeSite result = animeSiteService.updateAnimeSite(1L, updatedAnimeSite);
        assertEquals("Updated Name", result.getName());
        verify(animeSiteRepository, times(1)).findById(1L);
        verify(animeSiteRepository, times(1)).save(animeSite);
    }

    @Test
    void testDeleteAnimeSite() {
        animeSiteService.deleteAnimeSite(1L);
        verify(animeSiteRepository, times(1)).deleteById(1L);
    }
}
