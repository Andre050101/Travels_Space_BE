package org.lessons.java.travels_space.controller;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public String index(Model model) {
        List<City> cities = cityService.findAll();

        model.addAttribute("cities", cities);

        return "cities/index";
    }
}
