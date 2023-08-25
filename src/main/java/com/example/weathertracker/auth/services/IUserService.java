package com.example.weathertracker.auth.services;

import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.weather.entities.Location;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IUserService extends UserDetailsService {

    List<UserDTO> listUsers();

    Optional<UserDTO> getUserById(UUID id);

    Optional<UserDTO> getUserByUsername(String username);

    void updateUserById(UUID id, UserDTO user);

    void deleteById(UUID id);

    void patchUserById(UUID id, UserDTO user);

    User saveNewUser(UserDTO newuser);

    User findByUserName(String userName);

    Set<Location> getLocationsForUser(UUID id);

}
