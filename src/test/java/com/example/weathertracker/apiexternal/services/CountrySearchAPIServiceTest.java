package com.example.weathertracker.apiexternal.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountrySearchAPIServiceTest {

    @Autowired
    CountrySearchAPIService countrySearchAPIService;

    @Test
    void testGetListOfLocations(){

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        countrySearchAPIService.getLocationsBasedOnInput("Lond").subscribe(dto -> {
            System.out.println(dto.getName() + " " + dto.getLon() + " " + dto.getLat());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);

    }

}