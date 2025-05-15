package org.lessons.java.travels_space.service;

import org.lessons.java.travels_space.model.CityPhoto;
import org.lessons.java.travels_space.model.TouristAttractionPhoto;
import org.lessons.java.travels_space.repository.TouristAttractionPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristAttractionPhotoService {

    @Autowired
    private TouristAttractionPhotoRepository tourAttrPhRepo;

    public TouristAttractionPhoto create(TouristAttractionPhoto ph) {
        return tourAttrPhRepo.save(ph);
    }

    public void delete(Integer id) {
        tourAttrPhRepo.deleteById(id);
    }
}
