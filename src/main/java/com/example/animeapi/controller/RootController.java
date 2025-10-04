package com.example.animeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, String> getRoot() {
        Map<String, String> info = new HashMap<>();
        info.put("api_name", "Anime API");
        info.put("version", "1.0.0");
        info.put("description", "An API to manage a collection of anime websites.");
        info.put("documentation_url", "/swagger-ui.html"); // Asumiendo que se usará Swagger en el futuro
        return info;
    }
}
