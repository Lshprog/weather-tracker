package com.example.weathertracker.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    @JsonProperty("display_name")
    private String name;
    private double lat;
    private double lon;
}
