package org.lessons.java.travels_space.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 25, message = "city name must contain from 2 to 25 characters")
    @Column(name = "city_name", nullable = false)
    @NotBlank(message = "City name cannot be blank")
    private String name;

    @Size(min = 2, max = 25, message = "province must contain from 2 to 25 characters")
    @Column(nullable = false)
    @NotBlank(message = "City province cannot be blank")
    private String province;

    @Size(min = 3, max = 25, message = "region must contain from 3 to 25 characters")
    @Column(nullable = false)
    @NotBlank(message = "City region cannot be blank")
    private String region;

    @Size(min = 4, max = 25, message = "country must contain from 4 to 25 characters")
    @Column(nullable = false)
    @NotBlank(message = "City country cannot be blank")
    private String country;

    private String postal_code;

    // Relazione onetoMany con touristAttraction
    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<TouristAttraction> touristAttractions;

    // Relazione onetoMany con CityPhoto
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CityPhoto> photos = new ArrayList<>();
    // Getter e setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public List<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }

    public void setTouristAttractions(List<TouristAttraction> touristAttractions) {
        this.touristAttractions = touristAttractions;
    }

    public List<CityPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<CityPhoto> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Name: " + this.name + ", Province: " + this.province +
                ", Region: " + this.region + ", Country: " + this.country + ", Postal code: " + this.postal_code;
    }

}
