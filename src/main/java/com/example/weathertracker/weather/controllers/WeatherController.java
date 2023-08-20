package com.example.weathertracker.weather.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("weather")
public class WeatherController {

    @GetMapping("/main_page")
    public String showMainPage(){
        return "weather/main_page";
    }

}
