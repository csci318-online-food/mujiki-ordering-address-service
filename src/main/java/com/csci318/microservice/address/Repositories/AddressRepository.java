package com.csci318.microservice.address.Repositories;

import com.csci318.microservice.address.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("SELECT address FROM Address address WHERE address.userId = :userId")
    List<Address> findAllByUserId(String userId);
}
