package com.example.weathertracker.apiexternal.services;

import com.example.weathertracker.apiexternal.common.WeatherConditions;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class WeatherAPIService {

    @Value("${weatherapi.token}")
    private String apitoken;

    private final WebClient webClient;

    public WeatherAPIService(WebClient.Builder webClient) {
        this.webClient = WebClient.builder().baseUrl("http://api.weatherapi.com/v1/")
                .build();
    }

    public Flux<WeatherConditions> getCurrentWeatherFromLocation(LocationDTO location) {

        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("current.json")
                .queryParam("key", apitoken)
                .queryParam("q", location.getLat() + "," + location.getLon())
                .build()
        )
                .retrieve()
                .bodyToFlux(String.class)
                .map(this::deserializeAndExtractWeatherConditions);

    }

    // Later need to create special class for it
    private WeatherConditions deserializeAndExtractWeatherConditions(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
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
