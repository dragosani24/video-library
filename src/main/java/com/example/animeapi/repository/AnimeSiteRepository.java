package com.example.animeapi.repository;

import com.example.animeapi.model.AnimeSite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimeSiteRepository extends JpaRepository<AnimeSite, Long> {
    List<AnimeSite> findByCategory(String category);
    List<AnimeSite> findByIsActive(Boolean isActive);
}
