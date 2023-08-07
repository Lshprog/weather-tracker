package com.example.weathertracker.auth.services;


import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public User save(UserDTO newuser){

        User user = new User();
        user.setLogin(newuser.getLogin());
        user.setPassword(passwordEncoder.encode(newuser.getPassword()));

        return userRepository.save(user);
    }
}
