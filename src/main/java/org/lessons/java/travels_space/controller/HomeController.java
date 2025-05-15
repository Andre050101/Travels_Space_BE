package org.lessons.java.travels_space.controller;

import org.lessons.java.travels_space.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "home";
    }
}
