package com.example.weathertracker.auth.common.exceptionhandlers;

import com.example.weathertracker.auth.dto.UserDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationException(MethodArgumentNotValidException ex) {
        ModelAndView modelAndView = new ModelAndView("auth/registration");
        modelAndView.addObject("registrationError", "Validation failed. Please check your input.");

        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            modelAndView.addObject(fieldError.getField() + "Error", fieldError.getDefaultMessage());
        }

        UserDTO userDTO = (UserDTO) bindingResult.getTarget();
        modelAndView.addObject("userDTO",  userDTO);

        return modelAndView;


    }
}