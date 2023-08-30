package com.example.weathertracker.weather.entities;

import com.example.weathertracker.auth.entities.User;
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
public class Location {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(length = 60, columnDefinition = "varchar(60)", nullable = false, unique = true)
    private String name;

    @Column(length = 13, columnDefinition = "double(13,10)", nullable = false)
    private double latitude;

    @Column(length = 13, columnDefinition = "double(13,10)", nullable = false)
    private double longitude;

    @ManyToMany(mappedBy = "locations")
    private Set<User> users = new HashSet<>();

    public void addUser(User user){
        this.users.add(user);
        user.getLocations().add(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getLocations().remove(this);
    }


}
