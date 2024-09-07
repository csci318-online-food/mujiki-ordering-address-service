package com.csci318.microservice.address.Services;

import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;

import java.util.List;
import java.util.UUID;

public interface IAddressService {

    AddressDTOResponse createAddressForUser(AddressDTORequest addressDTORequest, UUID userId);
    List<AddressDTOResponse> getAllAddressesForUser(UUID userId);
    AddressDTOResponse getAddressForRestaurant(UUID restaurantId);
    AddressDTOResponse createAddressForRestaurant(AddressDTORequest addressDTORequest, UUID restaurantId);
}
