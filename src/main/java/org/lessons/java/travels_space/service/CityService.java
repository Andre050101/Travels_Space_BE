package org.lessons.java.travels_space.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lessons.java.travels_space.exception.NotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepo;

    public List<City> findAll() {
        return cityRepo.findAll();
    }

    public City getById(Integer id) {
        Optional<City> cityAttempt = cityRepo.findById(id);
        if (cityAttempt.isEmpty()) {
            throw new IllegalArgumentException(); // Da cambiare!
        }

        return cityAttempt.get();
    }

    public City create(City city) {
        return cityRepo.save(city);
    }

    public City update(City city) {
        if (!cityRepo.existsById(city.getId())) {
            throw new NotFoundException("City with ID: " + city.getId() + " not found!");
        }
        return cityRepo.save(city);

    }

    public void deleteById(Integer id) {
        City city = getById(id);
        cityRepo.delete(city);
    }

}
