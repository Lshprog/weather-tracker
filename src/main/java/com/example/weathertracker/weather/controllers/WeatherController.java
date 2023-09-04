package com.example.weathertracker.weather.controllers;

import com.example.weathertracker.auth.services.UserService;
import com.example.weathertracker.weather.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("weather")
public class WeatherController {

    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public WeatherController(LocationService locationService, UserService userService) {
        this.locationService = locationService;
        this.userService = userService;
    }


    @GetMapping("/main_page")
    public String showMainPage(WebRequest webRequest, Model theModel){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        theModel.addAttribute("username", username);
        theModel.addAttribute("userLocations", userService.getLocationsForUser(username));

        return "weather/main_page";
    }


}
