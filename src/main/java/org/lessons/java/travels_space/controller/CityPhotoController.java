package org.lessons.java.travels_space.controller;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.model.CityPhoto;
import org.lessons.java.travels_space.service.CityPhotoService;
import org.lessons.java.travels_space.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities/{cityId}/photos")
public class CityPhotoController {

    @Autowired
    private CityService cityServ;

    @Autowired
    private CityPhotoService cityPhServ;

    // aggiunta foto:
    @GetMapping("/new")
    public String addPhoto(@PathVariable Integer cityId, Model model) {
        City city = cityServ.getById(cityId);
        model.addAttribute("city", city);
        model.addAttribute("photo", new CityPhoto());
        return "cityPhotos/new";
    }

    @PostMapping
    public String savePhoto(@PathVariable Integer cityId, @ModelAttribute("photo") CityPhoto photo) {
        City city = cityServ.getById(cityId);
        photo.setCity(city);
        cityPhServ.create(photo);
        return "redirect:/cities/" + cityId;
    }

    // Eliminazione foto:
    @PostMapping("/{photoId}/delete")
    public String deletePhoto(@PathVariable Integer cityId, @PathVariable Integer photoId) {
        cityPhServ.delete(photoId);
        return "redirect:/cities/" + cityId;
    }
}
