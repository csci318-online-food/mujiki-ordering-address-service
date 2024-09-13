package com.csci318.microservice.address.Services.Impl;

import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;
import com.csci318.microservice.address.Domain.Entities.Address;
import com.csci318.microservice.address.Domain.Relations.User;
import com.csci318.microservice.address.Mappers.Impl.AddressMapper;
import com.csci318.microservice.address.Repositories.AddressRepository;
import com.csci318.microservice.address.Services.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ApplicationEventPublisher eventPublisher;

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.url.service}")
    private String USER_URL;

    @Value("${restaurant.url.service}")
    private String RESTAURANT_URL;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, ApplicationEventPublisher eventPublisher) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public AddressDTOResponse createAddressForUser(AddressDTORequest addressDTORequest, UUID userId) {
        User user = restTemplate.getForObject(USER_URL + "/findById/" + userId, User.class); // Find user by userId from user service
        if (user == null) {
            log.error("User not found with userId: " + userId);
            return null;
        }

        try {
            Address address = addressMapper.toEntities(addressDTORequest);
            // TODO: Control UUID generation.
            address.setId(UUID.randomUUID());
            address.setUserId(userId);
            address.setLatitude(address.getLatitude());
            address.setLongitude(address.getLongitude());
            addressRepository.save(address);
            log.info("Address created: " + address.getId());
            log.info("Restaurant created successfully with id: {}", address.getId());
            eventPublisher.publishEvent(address);
            return addressMapper.toDtos(address);
        } catch (Exception e) {
            log.error("Error creating address: " + e.getMessage());
            return null;
        }
    }

    public AddressDTOResponse createAddressForRestaurant(AddressDTORequest addressDTORequest, UUID restaurantId) {
        User user = restTemplate.getForObject(RESTAURANT_URL + "/findById/" + restaurantId, User.class); // Find user by restaurant from user service
        if (user == null) {
            log.error("Restaurant not found with restaurant id: " + restaurantId);
            return null;
        }
        try {
            Address address = addressMapper.toEntities(addressDTORequest);
            // TODO: Control UUID generation.
            address.setId(UUID.randomUUID());
            address.setRestaurantId(restaurantId);
            address.setLatitude(address.getLatitude());
            address.setLongitude(address.getLongitude());
            addressRepository.save(address);
            log.info("Address created: " + address.getId());
            log.info("Restaurant created successfully with id: {}", address.getId());
            eventPublisher.publishEvent(address);
            return addressMapper.toDtos(address);
        } catch (Exception e) {
            log.error("Error creating address: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<AddressDTOResponse> getAllAddressesForUser(UUID userId) {
        return addressRepository.findAllByUserId(userId).stream()
                .map(addressMapper::toDtos)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTOResponse getAddressForRestaurant(UUID restaurantId) {
        Address address = addressRepository.findByRestaurantId(restaurantId).orElse(null);
        if (address == null) {
            log.error("Address not found with addressId: " + restaurantId);
            return null;
        }
        return addressMapper.toDtos(address);
    }
}
