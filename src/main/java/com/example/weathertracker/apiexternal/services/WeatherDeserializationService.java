package com.example.weathertracker.apiexternal.services;

import com.example.weathertracker.apiexternal.common.WeatherConditions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherDeserializationService {

    private final ObjectMapper objectMapper;

    @Autowired
    public WeatherDeserializationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WeatherConditions deserializeAndExtractWeatherConditions(String jsonString) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);

            double temperatureC = rootNode.path("current").path("temp_c").asDouble();
            double temperatureF = rootNode.path("current").path("temp_f").asDouble();
            int humidity = rootNode.path("current").path("humidity").asInt();
            int cloud = rootNode.path("current").path("cloud").asInt();
            double windSpeedMph = rootNode.path("current").path("wind_mph").asDouble();
            double windSpeedKph = rootNode.path("current").path("wind_kph").asDouble();
            String weatherSummary = rootNode.path("current").path("condition").path("text").asText();

            return WeatherConditions.builder()
                    .temperature_c(temperatureC)
                    .temperature_f(temperatureF)
                    .humidity(humidity)
                    .cloud(cloud)
                    .windSpeed_mph(windSpeedMph)
                    .windSpeed_kph(windSpeedKph)
                    .weather_summary(weatherSummary)
                    .build();
        } catch (IOException e) {
            // Handle deserialization error if needed
            return null;
        }
    }

}
