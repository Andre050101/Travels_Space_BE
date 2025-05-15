package org.lessons.java.travels_space.repository;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByNameContainingIgnoreCase(String name);
}
