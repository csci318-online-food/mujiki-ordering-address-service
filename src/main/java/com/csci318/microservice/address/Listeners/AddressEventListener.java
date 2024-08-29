package com.csci318.microservice.address.Listeners;

import com.csci318.microservice.address.DTOs.AddressDTOResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddressEventListener {

    private static final Logger log = LoggerFactory.getLogger(AddressEventListener.class);

    @EventListener
    public void handleAddressHandler(AddressDTOResponse event) {
        log.info("Restaurant created: {}", event.getId());
    }
}
