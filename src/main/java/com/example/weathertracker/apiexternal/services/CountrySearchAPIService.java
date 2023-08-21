package com.example.weathertracker.apiexternal.services;

import com.example.weathertracker.weather.dto.LocationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Service
public class CountrySearchAPIService {

    private final WebClient webClient;

    public CountrySearchAPIService(WebClient.Builder webClientbuilder) {
        this.webClient = WebClient.builder().baseUrl("https://nominatim.openstreetmap.org/search?")
                .build();
    }

    public Flux<LocationDTO> getLocationsBasedOnInput(String name){

        return webClient.get().uri(uriBuilder -> uriBuilder
                .queryParam("city", name)
                .queryParam("format", "json")
                .build()
        ).retrieve().bodyToFlux(LocationDTO.class);


    }

}
