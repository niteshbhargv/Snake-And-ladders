package com.niteshbhargv.demo.repository;

import com.niteshbhargv.demo.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {
}
