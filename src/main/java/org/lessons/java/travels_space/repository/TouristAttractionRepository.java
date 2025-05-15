package org.lessons.java.travels_space.repository;

import java.util.List;

import org.lessons.java.travels_space.model.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Integer> {
    List<TouristAttraction> findByNameContainingIgnoreCaseOrCity_NameContainingIgnoreCase(String name, String cityName);
}
