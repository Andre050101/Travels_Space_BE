package org.lessons.java.travels_space.service;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepo;

    public List<City> findAll() {
        return cityRepo.findAll();
    }

}
