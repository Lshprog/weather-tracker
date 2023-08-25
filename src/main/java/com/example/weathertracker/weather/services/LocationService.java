package com.example.weathertracker.weather.services;

import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService implements ILocationService{

    @Override
    public List<LocationDTO> listLocations() {
        return null;
    }

    @Override
    public Optional<LocationDTO> getLocationById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<LocationDTO> getLocationByName(String name) {
        return Optional.empty();
    }

    @Override
    public void updateLocationById(UUID id, LocationDTO location) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void patchLocationById(UUID id, LocationDTO location) {

    }

    @Override
    public User saveNewLocation(LocationDTO newlocation) {
        return null;
    }

    @Override
    public Location findByName(String name) {
        return null;
    }
}
