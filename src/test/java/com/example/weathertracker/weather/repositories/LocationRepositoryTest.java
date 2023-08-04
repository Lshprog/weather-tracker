package com.example.weathertracker.weather.repositories;

import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testSaveUser(){
        User saveduser = userRepository.save(User.builder()
                        .login("smth")
                        .password("2231")
                .build());

        assertThat(saveduser).isNotNull();
        assertThat(saveduser.getId()).isNotNull();

    }

}