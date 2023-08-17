package com.example.weathertracker.weather.repositories;

import com.example.weathertracker.weather.entities.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    @Transactional
    void testSaveLocation(){
         Location savedlocation = locationRepository.save(Location.builder()
                        .name("smth")
                        .longitude(2231.3)
                        .latitude(221.2)
                .build());


        assertThat(savedlocation).isNotNull();
        assertThat(savedlocation.getId()).isNotNull();

    }

}