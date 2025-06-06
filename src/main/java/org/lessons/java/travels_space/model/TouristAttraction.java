package org.lessons.java.travels_space.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tourist_attractions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" // Usa l'ID come
                                                                                          // identificatore unico
)
public class TouristAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Attraction name cannot be blank")
    @Column(name = "attraction_name", nullable = false)
    private String name;

    @Lob
    private String description;

    @NotBlank(message = "Attraction address cannot be blank")
    private String address;

    @NotBlank(message = "Attraction opening hours cannot be blank")
    private String openingHours;

    @Column(name = "ticket_price", nullable = false)
    private BigDecimal ticketPrice;

    private String website;

    @Column(name = "created_at", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedAt;

    // Relazione manyTo1 con city
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonBackReference
    private City city;

    // Relazione manyTo1 con touristAttractionsPhotos
    @OneToMany(mappedBy = "touristAttraction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TouristAttractionPhoto> photos = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<TouristAttractionPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<TouristAttractionPhoto> photos) {
        this.photos = photos;
    }

}
