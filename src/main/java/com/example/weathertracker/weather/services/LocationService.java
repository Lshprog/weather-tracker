package com.example.weathertracker.weather.services;

import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.weather.dto.LocationDTO;
import com.example.weathertracker.weather.entities.Location;
import com.example.weathertracker.weather.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService implements ILocationService{

    private final LocationRepository locationRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public LocationService(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    private LocationDTO mapToDTO(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }

    private Location mapToEntity(LocationDTO locationDTO) {
        return modelMapper.map(locationDTO, Location.class);
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

        Location loctoadd = mapToEntity(newlocation);

        locationRepository.save(loctoadd);
        return loctoadd;
    }

    @Override
    public Location findByName(String name) {

        return locationRepository.findByName(name);

    }

    @Override
    public Optional<LocationDTO> getLocationByLatitudeAndLongitude(double latitude, double longitude) {
        Optional<Location> location = Optional.ofNullable(locationRepository.findByLatitudeAndLongitude(latitude, longitude));
        return location.map(this::mapToDTO);

    }

}
