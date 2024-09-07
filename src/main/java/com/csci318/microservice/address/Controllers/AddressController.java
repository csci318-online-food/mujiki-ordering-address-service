package com.csci318.microservice.address.Controllers;


import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;
import com.csci318.microservice.address.Services.IAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.endpoint.base-url}/address")
public class AddressController {

    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/forUser/{userId}")
    @ManagedOperation(description = "Create a new address for a user")
    public ResponseEntity<AddressDTOResponse> createAddress(@RequestBody AddressDTORequest address, @PathVariable UUID userId) {
        AddressDTOResponse addressObj = addressService.createAddressForUser(address, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressObj);
    }

    @PostMapping("/forRestaurant/{addressId}")
    @ManagedOperation(description = "Create a new address for a restaurant")
    public ResponseEntity<AddressDTOResponse> createAddressForRestaurant(@RequestBody AddressDTORequest address, @PathVariable UUID addressId) {
        AddressDTOResponse addressObj = addressService.createAddressForRestaurant(address, addressId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressObj);
    }

    @GetMapping("/forUser/{userId}")
    @ManagedOperation(description = "Get all addresses for a user")
    public ResponseEntity<List<AddressDTOResponse>> getAllAddresses(@PathVariable UUID userId) {
        List<AddressDTOResponse> addresses = addressService.getAllAddressesForUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(addresses);
    }

    @GetMapping("/forRestaurant/{restaurantId}")
    @ManagedOperation(description = "Get address for a restaurant")
    public ResponseEntity<AddressDTOResponse> getAddressForRestaurant(@PathVariable UUID restaurantId) {
        AddressDTOResponse address = addressService.getAddressForRestaurant(restaurantId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(address);
    }
}