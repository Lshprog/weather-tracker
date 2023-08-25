package com.example.weathertracker.auth.dto;

import com.example.weathertracker.weather.entities.Location;
import lombok.*;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    //private UUID id;
    @NotBlank(message = "Username cannot be empty")
    @Size(min=1, max = 12, message = "is required")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min=1, max = 60,  message = "is required")
    private String password;
    //private Set<Location> locations;

}
