package com.example.weathertracker.weather.services;

import com.example.weathertracker.auth.entities.User;
import com.example.weathertracker.auth.repositories.UserRepository;
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
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper modelMapper,
                               UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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
    public Optional<Location> getLocationById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Location> getLocationByName(String name) {
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

    @Override
    public LocationDTO saveLocationToUser(LocationDTO locationDTO, String username) {

        User user = userRepository.findByUsername(username);

        Optional<Location> location = Optional.ofNullable(locationRepository.findByName(locationDTO.getName()));

        if(location.isEmpty()){
            this.saveNewLocation(locationDTO);
        }

        user.addLocation(mapToEntity(locationDTO));

        return locationDTO;

    }

}
