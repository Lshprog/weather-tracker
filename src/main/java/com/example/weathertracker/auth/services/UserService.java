package com.example.weathertracker.auth.services;

import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    List<UserDTO> listUsers();

    Optional<UserDTO> getUserById(UUID id);

    Optional<UserDTO> getUserByUsername(String username);

    void updateUserById(UUID id, UserDTO user);

    void deleteById(UUID id);

    void patchUserById(UUID id, UserDTO user);

    User saveNewUser(UserDTO newuser);

    public User findByUserName(String userName);

}
