package org.lessons.java.travels_space.service;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.model.CityPhoto;
import org.lessons.java.travels_space.repository.CityPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityPhotoService {

    @Autowired
    private CityPhotoRepository cityPhRepo;

    public CityPhoto create(CityPhoto ph) {
        return cityPhRepo.save(ph);
    }

    public void delete(Integer id) {
        cityPhRepo.deleteById(id);
    }

}
