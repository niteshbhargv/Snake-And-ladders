package com.niteshbhargv.demo.controller;

import com.niteshbhargv.demo.config.CityConfiguration;
import com.niteshbhargv.demo.model.City;
import com.niteshbhargv.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private
    CityService cityService;

    @Autowired
    private CityConfiguration cityConfiguration;

    @GetMapping("/city")
    private List<City> getAllCities() {
        System.out.println("city config: " + cityConfiguration.getState());
        return cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    private City getCity(@PathVariable("id") int id) {
        return cityService.getCityById(id);
    }

    @PostMapping("/city")
    private int saveCity(@RequestBody City city) {
        cityService.saveOrUpdate(city);
        return city.getId();
    }
}
