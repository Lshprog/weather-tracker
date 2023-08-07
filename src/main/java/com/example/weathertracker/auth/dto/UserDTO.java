package com.example.weathertracker.auth.dto;

import com.example.weathertracker.weather.entities.Location;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String login;
    private String password;
    private Set<Location> locations;

}
