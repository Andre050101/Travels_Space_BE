package org.lessons.java.travels_space.service;

import java.util.Optional;

import org.lessons.java.travels_space.exception.NotFoundException;
import org.lessons.java.travels_space.model.TouristAttraction;
import org.lessons.java.travels_space.repository.TouristAttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristAttractionService {

    @Autowired
    private TouristAttractionRepository attrRepo;

    public TouristAttraction getById(Integer id) {
        Optional<TouristAttraction> attrAttempt = attrRepo.findById(id);
        if (attrAttempt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return attrAttempt.get();
    }

    public TouristAttraction create(TouristAttraction attr) {
        return attrRepo.save(attr);
    }

    public TouristAttraction update(TouristAttraction attr) {
        if (!attrRepo.existsById(attr.getId())) {
            throw new NotFoundException("Attraction with id " + attr.getId() + " not found!");
        }
        return create(attr);
    }

    public void delete(TouristAttraction attr) {
        attrRepo.deleteById(attr.getId());
    }

}
