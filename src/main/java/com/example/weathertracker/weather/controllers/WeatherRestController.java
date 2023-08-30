package com.example.weathertracker.weather.controllers;

import com.example.weathertracker.apiexternal.services.CountrySearchAPIService;
import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.services.IUserService;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;
import com.example.weathertracker.weather.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;

import java.util.Optional;

@RestController
@RequestMapping("weather/main_page")
public class WeatherRestController {

    private final CountrySearchAPIService countrySearchAPIService;

    private final LocationService locationService;

    private final IUserService userService;

    @Autowired
    public WeatherRestController(CountrySearchAPIService countrySearchAPIService, LocationService locationService, IUserService userService) {
        this.countrySearchAPIService = countrySearchAPIService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/citysuggestions")
    public Flux<LocationDTO> getCitySuggestions(@RequestParam String query) {
        return countrySearchAPIService.getLocationsBasedOnInput(query);
    }

    // Need to make no refresh page because, need to go to database again
    @PostMapping("/addlocation")
    @ResponseBody
    public LocationDTO addNewLocation(@RequestBody LocationDTO new_loc){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<LocationDTO> location = locationService.getLocationByLatitudeAndLongitude(new_loc.getLat(),
                new_loc.getLon());

        if(location.isEmpty()){
            locationService.saveNewLocation(new_loc);

        }
        else{

        }

        // need to add the new location to user_location table ...

        // change here based on exception
        return new_loc;
    }

}
