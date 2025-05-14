package org.lessons.java.travels_space.controller;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    // Index
    @GetMapping
    public String index(Model model) {
        List<City> cities = cityService.findAll();

        model.addAttribute("cities", cities);

        return "cities/index";
    }

    // Show
    @GetMapping("id")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("city", cityService.getById(id));
        return "/cities/show";
    }

    // Create e store
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("city", new City());
        return "/cities/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("city") City formCity, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "/cities/create";
        }
        cityService.create(formCity);
        return "redirect:/cities";
    }

    // Edit e update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("city", cityService.getById(id));
        return "cities/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("city") City formCity, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "cities/edit";
        }
        cityService.update(formCity);
        return "redirect:/cities";
    }

    // Delete
    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        cityService.deleteById(id);
        return "redirect:/cities";
    }
}
