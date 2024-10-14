package com.csci318.microservice.address.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTORequest {
    private String street;
    private String suburb;
    private String state;
    private String postcode;
    private String country;
    private Double latitude;
    private Double longitude;
}
