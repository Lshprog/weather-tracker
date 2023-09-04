package com.example.weathertracker.weather.controllers;

import com.example.weathertracker.apiexternal.services.CountrySearchAPIService;
import com.example.weathertracker.auth.services.UserService;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Optional;

@RestController
@RequestMapping("weather/main_page")
public class WeatherRestController {

    private final CountrySearchAPIService countrySearchAPIService;

    private final LocationService locationService;

    private final UserService userService;

    @Autowired
    public WeatherRestController(CountrySearchAPIService countrySearchAPIService, LocationService locationService, UserService userService) {
        this.countrySearchAPIService = countrySearchAPIService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/citysuggestions")
    public Flux<LocationDTO> getCitySuggestions(@RequestParam String query) {
        return countrySearchAPIService.getLocationsBasedOnInput(query);
    }

    @PostMapping("/addLocation")
    @ResponseBody
    public LocationDTO addNewLocation(@RequestBody LocationDTO new_loc){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        locationService.saveLocationToUser(new_loc, username);

        // maybe add some handling of exception later

        return new_loc;
    }

}
