package com.example.weathertracker.weather.services;

import com.example.weathertracker.auth.dto.UserDTO;
import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ILocationService {

    List<LocationDTO> listLocations();

    Optional<LocationDTO> getLocationById(UUID id);

    Optional<LocationDTO> getLocationByName(String name);

    void updateLocationById(UUID id, LocationDTO location);

    void deleteById(UUID id);

    void patchLocationById(UUID id, LocationDTO location);

    Location saveNewLocation(LocationDTO newlocation);

    Location findByName(String name);

}
