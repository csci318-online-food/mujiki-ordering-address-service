package com.csci318.microservice.address.Entities.Relations;

import com.csci318.microservice.address.Constants.CuisineType;

import java.time.LocalTime;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private CuisineType cuisine;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Double rating;
    private String description;
    private boolean isOpened;
}
