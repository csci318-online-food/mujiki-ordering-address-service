package com.csci318.microservice.address.Services;

import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;

public interface IAddressService {

    AddressDTOResponse createAddressForUser(AddressDTORequest addressDTORequest, String userId);
}
