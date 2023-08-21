package com.example.weathertracker.apiexternal.common;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HourlyForecast {

    private Map<String, WeatherConditions> forecasts = new HashMap<>();

    public void addForecast(String time, WeatherConditions details) {
        forecasts.put(time, details);
    }

    public WeatherConditions getForecast(String time) {
        return forecasts.get(time);
    }

}
