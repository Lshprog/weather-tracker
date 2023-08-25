package com.example.weathertracker.weather.controllers;

import com.example.weathertracker.apiexternal.services.CountrySearchAPIService;
import com.example.weathertracker.weather.dto.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("weather/main_page")
public class WeatherRestController {

    private final CountrySearchAPIService countrySearchAPIService;

    @Autowired
    public WeatherRestController(CountrySearchAPIService countrySearchAPIService) {
        this.countrySearchAPIService = countrySearchAPIService;
    }

    @GetMapping("/citysuggestions")
    public Flux<LocationDTO> getCitySuggestions(@RequestParam String query) {
        return countrySearchAPIService.getLocationsBasedOnInput(query);
    }

}
