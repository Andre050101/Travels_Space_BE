package org.lessons.java.travels_space.controller;

import org.lessons.java.travels_space.model.TouristAttraction;
import org.lessons.java.travels_space.model.TouristAttractionPhoto;
import org.lessons.java.travels_space.service.TouristAttractionPhotoService;
import org.lessons.java.travels_space.service.TouristAttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/touristAttractions/{touristAttractionId}/photos")
public class TouristAttractionPhotoController {

    @Autowired
    private TouristAttractionService attrServ;

    @Autowired
    private TouristAttractionPhotoService attrPhServ;

    @GetMapping("/new")
    public String add(@PathVariable Integer attractionId, Model model) {
        TouristAttraction attraction = attrServ.getById(attractionId);
        model.addAttribute("touristAttraction", attraction);
        model.addAttribute("photo", new TouristAttractionPhoto());
        return "attractionPhotos/new";
    }

    // Salva nuova foto
    @PostMapping
    public String savePhoto(@PathVariable("touristAttractionId") Integer touristAttractionId,
            @ModelAttribute("photo") TouristAttractionPhoto photo) {
        TouristAttraction attraction = attrServ.getById(touristAttractionId);
        photo.setAttraction(attraction);
        attrPhServ.create(photo);
        return "redirect:/touristAttractions/" + touristAttractionId;
    }

    // Elimina una foto
    @PostMapping("/{photoId}/delete")
    public String deletePhoto(@PathVariable("touristAttractionId") Integer touristAttractionId,
            @PathVariable Integer photoId) {
        attrPhServ.delete(photoId);
        return "redirect:/touristAttractions/" + touristAttractionId; // Corretto il percorso
    }
}
