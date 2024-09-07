package com.csci318.microservice.address.Mappers.Impl;

import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;
import com.csci318.microservice.address.Entities.Address;
import com.csci318.microservice.address.Mappers.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper implements Mapper<Address, AddressDTOResponse, AddressDTORequest> {

    @Override
    public AddressDTOResponse toDtos(Address entity) {
        AddressDTOResponse dto = new AddressDTOResponse();
        dto.setId(entity.getId());
        dto.setStreet(entity.getStreet());
        dto.setSuburb(entity.getSuburb());
        dto.setState(entity.getState());
        dto.setPostcode(entity.getPostcode());
        dto.setCountry(entity.getCountry());
        dto.setUserId(entity.getUserId());
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        return dto;
    }

    @Override
    public Address toEntities(AddressDTORequest dto) {
        Address entity = new Address();
        entity.setStreet(dto.getStreet());
        entity.setSuburb(dto.getSuburb());
        entity.setState(dto.getState());
        entity.setPostcode(dto.getPostcode());
        entity.setCountry(dto.getCountry());
        return entity;
    }

    @Override
    public List<AddressDTOResponse> toDtos(List<Address> addresses) {
        List<AddressDTOResponse> dtos = new ArrayList<>();
        for (Address address : addresses) {
            dtos.add(toDtos(address));
        }
        return dtos;
    }

    @Override
    public List<Address> toEntities(List<AddressDTORequest> dtos) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDTORequest dto : dtos) {
            addresses.add(toEntities(dto));
        }
        return addresses;
    }
}
