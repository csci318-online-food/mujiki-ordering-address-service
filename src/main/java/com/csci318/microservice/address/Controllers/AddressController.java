package com.csci318.microservice.address.Controllers;


import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;
import com.csci318.microservice.address.Services.IAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/address")
public class AddressController {

    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/{userId}")
    @ManagedOperation(description = "Create a new address for a user")
    public ResponseEntity<AddressDTOResponse> createAddress(@RequestBody AddressDTORequest address, @PathVariable String userId) {
        AddressDTOResponse addressObj = addressService.createAddressForUser(address, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressObj);
    }

}