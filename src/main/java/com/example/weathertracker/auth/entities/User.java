package com.example.weathertracker.auth.entities;

import com.example.weathertracker.weather.entities.Location;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false, unique = true)
    private String username;

    @Column(length = 60, columnDefinition = "varchar(60)", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_location",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations = new HashSet<>();

    public void addLocation(Location location){
        this.locations.add(location);
        location.getUsers().add(this);
    }

    public void removeLocation(Location location){
        this.locations.remove(location);
        location.getUsers().remove(this);
    }

}
