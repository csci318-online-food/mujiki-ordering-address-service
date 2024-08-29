package com.csci318.microservice.address.Services.Impl;

import com.csci318.microservice.address.DTOs.AddressDTORequest;
import com.csci318.microservice.address.DTOs.AddressDTOResponse;
import com.csci318.microservice.address.Entities.Address;
import com.csci318.microservice.address.Entities.Relations.User;
import com.csci318.microservice.address.Mappers.Impl.AddressMapper;
import com.csci318.microservice.address.Repositories.AddressRepository;
import com.csci318.microservice.address.Services.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, ApplicationEventPublisher eventPublisher) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public AddressDTOResponse createAddressForUser(AddressDTORequest addressDTORequest, String userId) {
        User user = restTemplate.getForObject(USER_URL + "/" + userId, User.class); // Find user by userId from user service
        if (user == null) {
            log.error("User not found with userId: " + userId);
            return null;
        }

        try {
            Address address = addressMapper.toEntities(addressDTORequest);
            address.setUserId(userId);
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


}
