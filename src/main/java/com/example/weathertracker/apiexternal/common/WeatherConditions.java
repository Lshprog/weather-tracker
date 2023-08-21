package com.example.weathertracker.apiexternal.entities;

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

    private double windSpeed_mph;
    private double windSpeed_kph;

}
