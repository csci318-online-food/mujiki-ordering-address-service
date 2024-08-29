package com.csci318.microservice.address.Repositories;

import com.csci318.microservice.address.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
