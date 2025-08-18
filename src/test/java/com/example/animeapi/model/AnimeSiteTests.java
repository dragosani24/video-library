package com.example.animeapi.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimeSiteTests {

    @Test
    public void testAnimeSite() {
        AnimeSite animeSite = new AnimeSite("Test Name", "http://test.com", "Test Description", "Test Category", true);
        assertEquals("Test Name", animeSite.getName());
        assertEquals("http://test.com", animeSite.getUrl());
        assertEquals("Test Description", animeSite.getDescription());
        assertEquals("Test Category", animeSite.getCategory());
        assertEquals(true, animeSite.getActive());
    }
}