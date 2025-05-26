package org.lessons.java.travels_space.controller;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cities")
public class CityRestController {

    @Autowired
    private CityService cityServ;

    // Index
    @GetMapping
    public ResponseEntity<List<City>> index() {
        List<City> cities = cityServ.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // Show
    @GetMapping("/{id}")
    public ResponseEntity<City> show(@PathVariable("id") Integer id) {
        City city = cityServ.getById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    // Search
    @GetMapping("/search")
    public ResponseEntity<List<City>> searchCities(@RequestParam("query") String query) {
        List<City> res = cityServ.searchByName(query);
        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
