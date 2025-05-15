package org.lessons.java.travels_space.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "touristAttractions_photos")
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

}
