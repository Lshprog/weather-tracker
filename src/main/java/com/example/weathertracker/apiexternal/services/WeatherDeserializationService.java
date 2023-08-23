package com.example.weathertracker.apiexternal.services;

import com.example.weathertracker.apiexternal.common.HourlyForecast;
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

    private WeatherConditions extractWeatherConditions(JsonNode currentNode){

        double temperatureC = currentNode.path("temp_c").asDouble();
        double temperatureF = currentNode.path("temp_f").asDouble();
        int humidity = currentNode.path("humidity").asInt();
        int cloud = currentNode.path("cloud").asInt();
        double windSpeedMph = currentNode.path("wind_mph").asDouble();
        double windSpeedKph = currentNode.path("wind_kph").asDouble();
        String weatherSummary = currentNode.path("condition").path("text").asText();

        return WeatherConditions.builder()
                .temperature_c(temperatureC)
                .temperature_f(temperatureF)
                .humidity(humidity)
                .cloud(cloud)
                .windSpeed_mph(windSpeedMph)
                .windSpeed_kph(windSpeedKph)
                .weather_summary(weatherSummary)
                .build();

    }

    public WeatherConditions deserializeAndExtractCurrentWeatherConditions(String jsonString) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString).path("current");
            return extractWeatherConditions(rootNode);

        } catch (IOException e) {

            return null;
        }
    }

    public HourlyForecast deserializeAndExtractHourlyForecast(String jsonString) {

        try {

            JsonNode rootNode = objectMapper.readTree(jsonString).path("forecast");

            HourlyForecast hourlyForecast = new HourlyForecast();

            for (JsonNode forecastDay : rootNode.path("forecastday")) {
                for (JsonNode forecastHour : forecastDay.path("hour")) {

                    String time = forecastHour.path("time").asText();
                    WeatherConditions weather = extractWeatherConditions(forecastHour);

                    hourlyForecast.addForecast(time, weather);

                }
            }

            return hourlyForecast;

        } catch (IOException e) {
            return null;
        }

    }

}
