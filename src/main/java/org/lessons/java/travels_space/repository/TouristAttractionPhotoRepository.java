package org.lessons.java.travels_space.repository;

import org.lessons.java.travels_space.model.TouristAttraction;
import org.lessons.java.travels_space.model.TouristAttractionPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristAttractionPhotoRepository extends JpaRepository<TouristAttractionPhoto, Integer> {

}
