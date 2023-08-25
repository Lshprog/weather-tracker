package com.example.weathertracker.apiexternal.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherConditions {

    private double temperature_c;
    private double temperature_f;

    private int humidity;
    private int cloud;

    private double windSpeed_mph;
    private double windSpeed_kph;

    private String weather_summary;

}
