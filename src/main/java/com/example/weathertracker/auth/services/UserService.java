package com.example.weathertracker.auth.services;


import com.example.weathertracker.auth.common.exceptions.UserAlreadyExistsException;
import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.repositories.UserRepository;
import com.example.weathertracker.weather.entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
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
    public Optional<UserDTO> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void updateUserById(UUID id, UserDTO user) {

    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void patchUserById(UUID id, UserDTO user) {

    }

    @Override
    public User saveNewUser(UserDTO newuser) throws UserAlreadyExistsException{

        if(userRepository.findByUsername(newuser.getUsername())!=null){
            throw new UserAlreadyExistsException("User with this username already exists.");
        }

        User user = new User();
        user.setUsername(newuser.getUsername());
        user.setPassword(passwordEncoder.encode(newuser.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Set<Location> getLocationsForUser(UUID id) {
        return userRepository.findLocationsByUserId(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        System.out.println("Works");

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
