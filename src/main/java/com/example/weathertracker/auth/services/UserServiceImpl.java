package com.example.weathertracker.auth.services;


import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> listUsers() {
        return null;
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> getUserByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void updateUserById(UUID id, UserDTO user) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void patchUserById(UUID id, UserDTO user) {

    }

    @Override
    public User saveNewUser(UserDTO newuser){

        User user = new User();
        user.setLogin(newuser.getLogin());
        user.setPassword(passwordEncoder.encode(newuser.getPassword()));

        return userRepository.save(user);
    }
}
