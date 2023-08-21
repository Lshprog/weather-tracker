package com.example.weathertracker.apiexternal.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherConditions {

    @JsonProperty("temp_c")
    private double temperature_c;
    @JsonProperty("temp_f")
    private double temperature_f;

    private int humidity;
    private int cloud;

    @JsonProperty("wind_mph")
    private double windSpeed_mph;
    @JsonProperty("wind_kph")
    private double windSpeed_kph;

    @JsonProperty("text")
    private String weather_summary;

}
