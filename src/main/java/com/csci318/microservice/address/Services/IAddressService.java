package com.csci318.microservice.address.Services;

import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;

import java.util.List;

public interface IAddressService {

    AddressDTOResponse createAddressForUser(AddressDTORequest addressDTORequest, String userId);
    List<AddressDTOResponse> getAllAddressesForUser(String userId);
    AddressDTOResponse getAddressForRestaurant(String restaurantId);
}
