package com.wquintero.repositories;

import com.wquintero.model.Address;
import com.wquintero.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findByUserData(UserData userData);
}
