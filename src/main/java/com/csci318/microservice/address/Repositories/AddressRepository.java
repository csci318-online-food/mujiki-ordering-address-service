package com.csci318.microservice.address.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csci318.microservice.address.Domain.Entities.Address;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("SELECT address FROM Address address WHERE address.userId = :userId")
    List<Address> findAllByUserId(UUID userId);

    @Query("SELECT address FROM Address address WHERE address.restaurantId = :restaurantId")
    Optional<Address> findByRestaurantId(UUID restaurantId);
}
