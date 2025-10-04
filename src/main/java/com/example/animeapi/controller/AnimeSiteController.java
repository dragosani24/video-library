package com.example.animeapi.controller;

import com.example.animeapi.model.AnimeSite;
import com.example.animeapi.service.AnimeSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/api/anime-sites")
public class AnimeSiteController {
    private final AnimeSiteService animeSiteService;

    @Autowired
    public AnimeSiteController(AnimeSiteService animeSiteService) {
        this.animeSiteService = animeSiteService;
    }
    
    @GetMapping
    public List<AnimeSite> getAllAnimeSites() {
        return animeSiteService.getAllAnimeSites();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AnimeSite> getAnimeSiteById(@PathVariable Long id) {
        return animeSiteService.getAnimeSiteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{category}")
    public List<AnimeSite> getAnimeSitesByCategory(@PathVariable String category) {
        return animeSiteService.getAnimeSitesByCategory(category);
    }
    
    @GetMapping("/status/{isActive}")
    public List<AnimeSite> getAnimeSitesByStatus(@PathVariable Boolean isActive) {
        return animeSiteService.getActiveAnimeSites(isActive);
    }
    
    @PostMapping
    public ResponseEntity<AnimeSite> createAnimeSite(@RequestBody AnimeSite animeSite) {
        AnimeSite savedAnimeSite = animeSiteService.saveAnimeSite(animeSite);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAnimeSite.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedAnimeSite);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AnimeSite> updateAnimeSite(@PathVariable Long id, @RequestBody AnimeSite animeSiteDetails) {
        try {
            AnimeSite updatedAnimeSite = animeSiteService.updateAnimeSite(id, animeSiteDetails);
            return ResponseEntity.ok(updatedAnimeSite);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimeSite(@PathVariable Long id) {
        animeSiteService.deleteAnimeSite(id);
        return ResponseEntity.noContent().build();
    }
}
