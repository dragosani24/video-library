package com.example.animeapi.service;

import com.example.animeapi.model.AnimeSite;
import com.example.animeapi.repository.AnimeSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeSiteService {
    private final AnimeSiteRepository animeSiteRepository;

    @Autowired
    public AnimeSiteService(AnimeSiteRepository animeSiteRepository) {
        this.animeSiteRepository = animeSiteRepository;
    }
    
    public List<AnimeSite> getAllAnimeSites() {
        return animeSiteRepository.findAll();
    }
    
    public Optional<AnimeSite> getAnimeSiteById(Long id) {
        return animeSiteRepository.findById(id);
    }
    
    public List<AnimeSite> getAnimeSitesByCategory(String category) {
        return animeSiteRepository.findByCategory(category);
    }
    
    public List<AnimeSite> getActiveAnimeSites(Boolean isActive) {
        return animeSiteRepository.findByIsActive(isActive);
    }
    
    public AnimeSite saveAnimeSite(AnimeSite animeSite) {
        return animeSiteRepository.save(animeSite);
    }
    
    public AnimeSite updateAnimeSite(Long id, AnimeSite animeSiteDetails) {
        AnimeSite animeSite = animeSiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnimeSite not found with id: " + id));
        
        animeSite.setName(animeSiteDetails.getName());
        animeSite.setUrl(animeSiteDetails.getUrl());
        animeSite.setDescription(animeSiteDetails.getDescription());
        animeSite.setCategory(animeSiteDetails.getCategory());
        animeSite.setActive(animeSiteDetails.getActive());
        
        return animeSiteRepository.save(animeSite);
    }
    
    public void deleteAnimeSite(Long id) {
        animeSiteRepository.deleteById(id);
    }
}
