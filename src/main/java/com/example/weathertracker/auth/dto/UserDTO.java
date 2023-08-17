package com.example.weathertracker.auth.dto;

import com.example.weathertracker.weather.entities.Location;
import lombok.*;

import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    //private UUID id;
    @NotNull
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    private String password;
    //private Set<Location> locations;

}
