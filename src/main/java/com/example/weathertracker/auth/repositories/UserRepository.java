package com.example.weathertracker.auth.repositories;

import com.example.weathertracker.auth.entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByLogin(String login);

}
