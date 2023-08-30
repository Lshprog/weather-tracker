package com.example.weathertracker.auth.repositories;

import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.weather.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
    @Query("SELECT u.locations FROM User u WHERE u.id = :userId")
    Set<Location> findLocationsByUserId(UUID userId);

    @Query("SELECT u.locations FROM User u WHERE u.username = :username")
    Set<Location> findLocationsByUsername(String username);

}
