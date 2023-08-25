package com.example.weathertracker.weather.controllers;

import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/main_page")
    public String showMainPage(WebRequest webRequest, Model theModel){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        theModel.addAttribute("username", username);

        return "weather/main_page";
    }


}
