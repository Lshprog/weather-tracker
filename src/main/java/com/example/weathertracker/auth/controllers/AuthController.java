package com.example.weathertracker.auth.controllers;

import com.example.weathertracker.auth.common.exceptions.UserAlreadyExistsException;
import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.services.UserService;
import com.example.weathertracker.auth.services.UserServiceImpl;
import io.netty.channel.unix.Errors;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("auth")
public class AuthController {

    private UserServiceImpl userService;

    @GetMapping("/login")
    public String showLoginForm(WebRequest request, Model model){

        UserDTO userDTO = new UserDTO();

        model.addAttribute(userDTO);

        return "registration";
    }

    @PostMapping("/login")
    public String processLogin(){
        return null;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return null;
    }

    @PostMapping("/register")
    public ModelAndView processRegistration(@ModelAttribute("user") UserDTO userDTO, HttpServletRequest request, Errors errors) {
        ModelAndView mav = new ModelAndView();
        try {
            User registered = userService.saveNewUser(userDTO);
        }
        catch (UserAlreadyExistsException uaeEx){
            mav.setViewName("registration");
            mav.addObject("message", "An account for that username already exists.");
            return mav;
        }

        mav.setViewName("successRegistration"); // Set the view name
        mav.addObject("user", userDTO);

        return mav;
    }


}
