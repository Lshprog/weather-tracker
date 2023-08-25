package com.example.weathertracker.weather.services;

import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;
import com.example.weathertracker.weather.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService implements ILocationService{

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

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

        locationRepository.deleteById(id);

    }

    @Override
    public void patchLocationById(UUID id, LocationDTO location) {

    }

    @Override
    public Location saveNewLocation(LocationDTO newlocation) {
        Location loctoadd = Location.builder()
                .name(newlocation.getName())
                .latitude(newlocation.getLat())
                .longitude(newlocation.getLon())
                .build();

        locationRepository.save(loctoadd);
        return loctoadd;
    }

    @Override
    public Location findByName(String name) {

        return locationRepository.findByName(name);

    }
}
