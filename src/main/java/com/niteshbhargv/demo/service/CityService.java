package com.niteshbhargv.demo.service;

import com.niteshbhargv.demo.model.City;
import com.niteshbhargv.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Value("${length}")
    private int length;

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<City>();
        System.out.println(length);
        cityRepository.findAll().forEach(city -> cities.add(city));
        return cities;
    }

    public City getCityById(Integer cityId) {
        return cityRepository.findById(cityId).get();
    }

    public void saveOrUpdate(City city)
    {
        cityRepository.save(city);
    }



}
