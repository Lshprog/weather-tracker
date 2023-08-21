package com.example.weathertracker.apiexternal.services;

import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherAPIServiceTest {

    @Autowired
    WeatherAPIService weatherAPIService;

    private LocationDTO location1;

    @BeforeEach
    void setUp() {

        location1 = new LocationDTO("London", 51.509865, -0.118092);

    }

    @Test
    void testGetCurrentWeatherAtLocation(){

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        weatherAPIService.getCurrentWeatherFromLocation(location1).subscribe(dto -> {
            System.out.println(dto.getTemperature_c() + " " + dto.getTemperature_f()+ " " + dto.getHumidity());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);

    }
}