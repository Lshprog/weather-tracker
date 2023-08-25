package com.example.weathertracker.auth.controllers;

import com.example.weathertracker.auth.common.exceptions.UserAlreadyExistsException;
import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/login")
    public String showLoginForm(WebRequest request, Model model){

        UserDTO userDTO = new UserDTO();

        model.addAttribute(userDTO);

        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDTO = new UserDTO();

        model.addAttribute(userDTO);

        return "auth/registration";
    }

//    @PostMapping("/register")
//    public ModelAndView processRegistration(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
//                                            HttpServletRequest request, HttpSession session,
//                                            BindingResult theBindingResult) {
//
//        ModelAndView mav = new ModelAndView();
//
//        System.out.println("before");
//
//        if (theBindingResult.hasErrors()){
//            mav.addObject("userDTO", userDTO);
//            mav.setViewName("auth/registration");
//            return mav;
//        }
//
//        System.out.println("after");
//
//        try {
//            User registered = userService.saveNewUser(userDTO);
//        }
//        catch (UserAlreadyExistsException uaeEx){
//            mav.setViewName("auth/registration");
//            mav.addObject("registrationError", "An account for that username already exists.");
//            mav.addObject("userDTO", userDTO);
//            return mav;
//        }
//
//        mav.setViewName("auth/registration_confirmation"); // Set the view name
//        mav.addObject("username", userDTO.getUsername());
//
//        return mav;
//    }


    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
                                      HttpSession session,
                                            BindingResult theBindingResult, Model theModel) {

        System.out.println("before");

        if (theBindingResult.hasErrors()){
            return "auth/registration";
        }

        System.out.println("after");

        try {
            User registered = userService.saveNewUser(userDTO);
        }
        catch (UserAlreadyExistsException uaeEx){
            theModel.addAttribute("userDTO",  userDTO);
            theModel.addAttribute("registrationError",
                    "An account for that username already exists.");
            return "auth/registration";
        }

        // Set the view name
        theModel.addAttribute("username", userDTO.getUsername());

        return "auth/registration_confirmation";
    }

}
