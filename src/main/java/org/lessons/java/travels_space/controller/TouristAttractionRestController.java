package org.lessons.java.travels_space.controller;

import java.util.List;

import org.lessons.java.travels_space.model.City;
import org.lessons.java.travels_space.model.TouristAttraction;
import org.lessons.java.travels_space.service.TouristAttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/touristAttractions")
public class TouristAttractionRestController {

    @Autowired
    private TouristAttractionService attrServ;

    // Index
    @GetMapping
    public ResponseEntity<List<TouristAttraction>> index() {
        List<TouristAttraction> tourAttrs = attrServ.findAll();
        return new ResponseEntity<>(tourAttrs, HttpStatus.OK);
    }

    // Show
    @GetMapping("/{id}")
    public ResponseEntity<TouristAttraction> show(@PathVariable("id") Integer id) {
        TouristAttraction tourAttr = attrServ.getById(id);
        if (tourAttr == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tourAttr, HttpStatus.OK);
    }

    // Search
    @GetMapping("/search")
    public ResponseEntity<List<TouristAttraction>> searchAttractions(@RequestParam("query") String query) {
        List<TouristAttraction> res = attrServ.searchByNameOrCity(query);
        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
