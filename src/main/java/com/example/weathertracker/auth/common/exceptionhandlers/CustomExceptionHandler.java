package com.example.weathertracker.auth.common.exceptionhandlers;

import com.example.weathertracker.auth.dto.UserDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationException(MethodArgumentNotValidException ex) {
        ModelAndView modelAndView = new ModelAndView("auth/registration");
        modelAndView.addObject("registrationError", "Password and username have to be non empty");
        modelAndView.addObject("userDTO", new UserDTO());
        return modelAndView;
    }
}