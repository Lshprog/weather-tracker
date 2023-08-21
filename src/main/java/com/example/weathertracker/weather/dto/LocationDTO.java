package com.example.weathertracker.weather.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    private String name;
    private double lat;
    private double lon;
}
