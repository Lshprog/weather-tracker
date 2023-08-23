package com.example.weathertracker.apiexternal.services;

import com.example.weathertracker.apiexternal.common.HourlyForecast;
import com.example.weathertracker.apiexternal.common.WeatherConditions;
import com.example.weathertracker.weather.dto.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class WeatherAPIService {

    @Value("${weatherapi.token}")
    private String apitoken;

    private final WebClient webClient;

    private WeatherDeserializationService weatherDeserializationService;

    public WeatherAPIService(WebClient.Builder webClient) {
        this.webClient = WebClient.builder().baseUrl("http://api.weatherapi.com/v1/")
                .build();
    }

    @Autowired
    public WeatherAPIService(WebClient.Builder webClient, WeatherDeserializationService weatherDeserializationService) {
        this.webClient = WebClient.builder().baseUrl("http://api.weatherapi.com/v1/")
                .build();

        this.weatherDeserializationService = weatherDeserializationService;
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
                .map(weatherDeserializationService::deserializeAndExtractCurrentWeatherConditions);

    }

    public Flux<HourlyForecast> getForecastFromLocation(LocationDTO location) {

        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("forecast.json")
                        .queryParam("key", apitoken)
                        .queryParam("q", location.getLat() + "," + location.getLon())
                        .queryParam("days", 3)
                        .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .map(weatherDeserializationService::deserializeAndExtractHourlyForecast);

    }

}
