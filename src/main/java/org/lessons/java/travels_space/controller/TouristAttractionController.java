package org.lessons.java.travels_space.controller;

import java.util.List;

import org.lessons.java.travels_space.model.TouristAttraction;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/touristAttractions")
public class TouristAttractionController {

    @Autowired
    private TouristAttractionService attrService;

    @Autowired
    private CityService cityService;

    // Index
    @GetMapping
    public String index(Model model) {
        List<TouristAttraction> attractions = attrService.findAll();
        model.addAttribute("touristAttractions", attractions);
        return "/touristAttractions/index";
    }

    // Show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("touristAttraction", attrService.getById(id));
        return "/touristAttractions/show";
    }

    // Create e store con id città prelevato da url(bottone cliccato in show city)
    @GetMapping("/create/{cityId}")
    public String create(@PathVariable Integer cityId, Model model) {
        TouristAttraction newAttraction = new TouristAttraction();
        newAttraction.setCity(cityService.getById(cityId)); // Imposto la città

        model.addAttribute("touristAttraction", newAttraction);
        model.addAttribute("city", newAttraction.getCity());
        model.addAttribute("formAction", "/touristAttractions/create/" + cityId);
        model.addAttribute("pageTitle", "Add new Tourist Attraction to " + newAttraction.getCity().getName());
        model.addAttribute("submitLabel", "Add");
        return "/touristAttractions/createOrEdit";
    }

    @PostMapping("/create/{cityId}")
    public String store(@PathVariable Integer cityId,
            @Valid @ModelAttribute("touristAttraction") TouristAttraction formAttr,
            BindingResult binding,
            Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("city", cityService.getById(cityId));
            return "/touristAttractions/createOrEdit";
        }

        formAttr.setCity(cityService.getById(cityId));
        attrService.create(formAttr);
        return "redirect:/cities/" + cityId;
    }

    // Create e store con id città passato tramite select(bottone cliccato in index
    // attractions)
    @GetMapping("/create")
    public String createInAttractions(Model model) {
        TouristAttraction attr = new TouristAttraction();
        model.addAttribute("touristAttraction", attr);
        model.addAttribute("formAction", "/touristAttractions/create");
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("pageTitle", "Add new Tourist Attraction");
        model.addAttribute("submitLabel", "Add");
        return "/touristAttractions/createOrEdit";
    }

    @PostMapping("/create")
    public String storeInAttractions(@Valid @ModelAttribute("touristAttraction") TouristAttraction formAttr,
            BindingResult binding, Model model) {
        if (formAttr.getCity() == null || formAttr.getCity().getId() == null) {
            binding.rejectValue("city", "NotNull", "City must be selected.");
        }
        if (binding.hasErrors()) {
            model.addAttribute("cities", cityService.findAll());
            model.addAttribute("formAction", "/touristAttractions/create");
            model.addAttribute("pageTitle", "Add new Tourist Attraction");
            model.addAttribute("submitLabel", "Add");
            return "/touristAttractions/createOrEdit";
        }
        attrService.create(formAttr);
        return "redirect:/touristAttractions";
    }

    // Edit e update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        TouristAttraction attraction = attrService.getById(id);
        model.addAttribute("touristAttraction", attraction);
        model.addAttribute("city", attraction.getCity());
        model.addAttribute("formAction", "/touristAttractions/edit/" + id);
        model.addAttribute("pageTitle", "Edit " + attraction.getName() + " in " + attraction.getCity().getName());
        model.addAttribute("submitLabel", "Edit");
        return "/touristAttractions/createOrEdit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id,
            @Valid @ModelAttribute("touristAttraction") TouristAttraction attraction,
            BindingResult binding,
            Model model) {
        TouristAttraction original = attrService.getById(id);

        if (binding.hasErrors()) {
            model.addAttribute("city", original.getCity());
            return "/touristAttractions/createOrEdit";
        }

        attraction.setCity(original.getCity());

        attrService.update(attraction);
        return "redirect:/cities/" + attraction.getCity().getId();
    }

    // Delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        TouristAttraction attrToDelete = attrService.getById(id);
        ;
        attrService.delete(attrToDelete);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

    }

    // Search by name or cityName
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<TouristAttraction> results = attrService.searchByNameOrCity(query);
        model.addAttribute("touristAttractions", results);
        model.addAttribute("query", query);
        return "touristAttractions/index";
    }
}
