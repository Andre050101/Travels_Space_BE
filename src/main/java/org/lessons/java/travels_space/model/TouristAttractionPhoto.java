package org.lessons.java.travels_space.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "touristAttractions_photos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" // Usa l'ID come
                                                                                          // identificatore unico
)
public class TouristAttractionPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String url;

    @ManyToOne
    @JoinColumn(name = "tourist_attraction_id", nullable = false)
    private TouristAttraction touristAttraction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TouristAttraction getAttraction() {
        return touristAttraction;
    }

    public void setAttraction(TouristAttraction attraction) {
        this.touristAttraction = attraction;
    }

    public TouristAttraction getTouristAttraction() {
        return touristAttraction;
    }

    public void setTouristAttraction(TouristAttraction touristAttraction) {
        this.touristAttraction = touristAttraction;
    }

}
