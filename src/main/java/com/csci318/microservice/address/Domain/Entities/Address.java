package com.csci318.microservice.address.Domain.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Random;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId; // Aggregation (User)

    @Column(name = "restaurant_id", unique = true) // Only one address per restaurant
    private UUID restaurantId; // Aggregation (Restaurant)

    @Column(name = "street")
    private String street;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "state")
    private String state;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "create_by")
    private String createBy;

    // Additional methods
    private void generateRandomCoordinates() {
        double minLat = -118.43; // Minimum latitude
        double maxLat = -117.48; // Maximum latitude
        double minLon = 144.58; // Minimum longitude
        double maxLon = 145.21; // Maximum longitude

        Random random = new Random();
        latitude = minLat + (maxLat - minLat) * random.nextDouble();
        longitude = minLon + (maxLon - minLon) * random.nextDouble();
    }

    public Double getLatitude() {
        if (latitude == null) {
            generateRandomCoordinates();
        }
        return latitude;
    }

    public Double getLongitude() {
        if (longitude == null) {
            generateRandomCoordinates();
        }
        return longitude;
    }
}
