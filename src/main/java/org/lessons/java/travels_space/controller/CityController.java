package org.lessons.java.travels_space.controller;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.model.CityPhoto;
import org.lessons.java.travels_space.model.TouristAttraction;
import org.lessons.java.travels_space.service.CityPhotoService;
import org.lessons.java.travels_space.service.CityService;
import org.lessons.java.travels_space.service.TouristAttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private TouristAttractionService attrService;

    @Autowired
    private CityPhotoService cityPhService;

    // Index
    @GetMapping
    public String index(Model model) {
        List<City> cities = cityService.findAll();

        model.addAttribute("cities", cities);

        return "cities/index";
    }

    // Show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("city", cityService.getById(id));
        return "/cities/show";
    }

    // Create e store
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("formAction", "/cities/create");
        model.addAttribute("pageTitle", "Add new city");
        model.addAttribute("submitLabel", "Add");

        return "/cities/createOrEdit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("city") City formCity, BindingResult binding,
            @RequestParam(name = "photoUrl", required = false) String photoUrl, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("formAction", "/cities/create");
            model.addAttribute("pageTitle", "Add new City");
            model.addAttribute("submitLabel", "Add");
            return "/cities/createOrEdit";
        }
        City savedCity = cityService.create(formCity);

        if (photoUrl != null && !photoUrl.trim().isEmpty()) {
            CityPhoto photo = new CityPhoto();
            photo.setUrl(photoUrl);
            photo.setCity(savedCity);
            cityPhService.create(photo);
        }
        return "redirect:/cities";
    }

    // Edit e update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        City city = cityService.getById(id);
        model.addAttribute("city", city);
        model.addAttribute("formAction", "/cities/edit/" + id);
        model.addAttribute("pageTitle", "Edit " + city.getName());
        model.addAttribute("submitLabel", "Edit");
        if (!city.getPhotos().isEmpty()) {
            model.addAttribute("photoUrl", city.getPhotos().get(0).getUrl());
        } else {
            model.addAttribute("photoUrl", "");
        }
        return "cities/createOrEdit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("city") City formCity, BindingResult binding,
            @RequestParam(name = "photoUrl", required = false) String photoUrl, @PathVariable Integer id, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("formAction", "/cities/edit/" + id);
            model.addAttribute("pageTitle", "Edit " + formCity.getName());
            model.addAttribute("submitLabel", "Edit");
            model.addAttribute("photoUrl", photoUrl != null ? photoUrl : "");
            return "cities/createOrEdit";
        }
        City updatedCity = cityService.update(formCity);
        if (photoUrl != null && !photoUrl.trim().isEmpty()) {
            CityPhoto photo = new CityPhoto();
            photo.setUrl(photoUrl);
            photo.setCity(updatedCity);
            cityPhService.create(photo);
        }
        return "redirect:/cities";
    }

    // Delete
    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        City city = cityService.getById(id);
        for (TouristAttraction attraction : city.getTouristAttractions()) {
            attrService.delete(attraction);
        }
        cityService.deleteById(id);
        return "redirect:/cities";
    }

    // SearchByName
    @GetMapping("/search")
    public String searchCities(@RequestParam("query") String query, Model model) {
        List<City> result = cityService.searchByName(query);
        model.addAttribute("query", query);
        model.addAttribute("cities", result);
        return "cities/index";
    }
}
