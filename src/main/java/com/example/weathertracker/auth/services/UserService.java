package com.example.weathertracker.auth.services;

import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;

import java.util.List;

public interface UserService {

    User save(UserDTO newuser);

}
