package com.example.weathertracker.weather.services;

import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationService {

    List<LocationDTO> listLocations();

    Optional<Location> getLocationById(UUID id);

    Optional<Location> getLocationByName(String name);

    void updateLocationById(UUID id, LocationDTO location);

    void deleteById(UUID id);

    void patchLocationById(UUID id, LocationDTO location);

    Location saveNewLocation(LocationDTO newlocation);

    Location findByName(String name);

    Optional<LocationDTO> getLocationByLatitudeAndLongitude(double latitude, double longitude);

    LocationDTO saveLocationToUser(LocationDTO locationDTO, String username);

}
