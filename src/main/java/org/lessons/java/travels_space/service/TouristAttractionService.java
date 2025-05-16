package org.lessons.java.travels_space.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.travels_space.exception.NotFoundException;
import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.model.TouristAttraction;
import org.lessons.java.travels_space.repository.TouristAttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristAttractionService {

    @Autowired
    private TouristAttractionRepository attrRepo;

    public List<TouristAttraction> searchByNameOrCity(String query) {
        return attrRepo.findByNameContainingIgnoreCaseOrCity_NameContainingIgnoreCase(query, query);
    }

    public TouristAttraction getById(Integer id) {
        Optional<TouristAttraction> attrAttempt = attrRepo.findById(id);
        if (attrAttempt.isEmpty()) {
            throw new NotFoundException("Attraction with ID: " + id + " not found!");
        }
        return attrAttempt.get();
    }

    public List<TouristAttraction> findAll() {
        return attrRepo.findAll();
    }

    public TouristAttraction create(TouristAttraction attr) {
        return attrRepo.save(attr);
    }

    public TouristAttraction update(TouristAttraction attr) {
        if (!attrRepo.existsById(attr.getId())) {
            throw new NotFoundException("Attraction with ID: " + attr.getId() + " not found!");
        }
        return create(attr);
    }

    public void delete(TouristAttraction attr) {
        attrRepo.deleteById(attr.getId());
    }

}
