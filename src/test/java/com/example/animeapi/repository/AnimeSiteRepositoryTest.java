package com.example.animeapi.repository;

import com.example.animeapi.model.AnimeSite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnimeSiteRepositoryTest {

    @Autowired
    private AnimeSiteRepository animeSiteRepository;

    @BeforeEach
    void setUp() {
        animeSiteRepository.deleteAll();
    }

    @Test
    public void testFindByCategory() {
        AnimeSite animeSite = new AnimeSite("Test Name", "http://test.com", "Test Description", "Streaming", true);
        animeSiteRepository.save(animeSite);

        List<AnimeSite> foundSites = animeSiteRepository.findByCategory("Streaming");
        assertEquals(1, foundSites.size());
        assertEquals("Streaming", foundSites.get(0).getCategory());
    }

    @Test
    public void testFindByIsActive() {
        AnimeSite animeSite = new AnimeSite("Test Name", "http://test.com", "Test Description", "Streaming", true);
        animeSiteRepository.save(animeSite);

        List<AnimeSite> foundSites = animeSiteRepository.findByIsActive(true);
        assertEquals(1, foundSites.size());
        assertEquals(true, foundSites.get(0).getActive());
    }
}
