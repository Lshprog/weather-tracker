package com.example.weathertracker.auth.repositories;

import com.example.weathertracker.auth.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void testSaveUser(){
        User saveduser = userRepository.save(User.builder()
                .login("smthelse")
                .password("2231")
                .build());

        System.out.println("before checks");
        assertThat(saveduser).isNotNull();
        assertThat(saveduser.getId()).isNotNull();

    }

}