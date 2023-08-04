package com.example.weathertracker.weather.entities;

import com.example.weathertracker.auth.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double latitude;
    private double longitude;

    @ManyToMany(mappedBy = "locations")
    private Set<User> users;

}
