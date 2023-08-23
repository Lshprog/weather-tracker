package com.example.weathertracker.weather.repositories;

import com.example.weathertracker.weather.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

    Location findByName(String name);

}
