package com.example.weathertracker.weather.dto;

import com.example.weathertracker.auth.entities.User;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class LocationDTO {
    private UUID id;
    private String name;
    private double latitude;
    private double longitude;
    private Set<User> users;
}
