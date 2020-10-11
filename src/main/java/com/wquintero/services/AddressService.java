package com.wquintero.services;

import com.wquintero.model.Address;
import com.wquintero.model.UserData;
import com.wquintero.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository rp;

    public void save(Address address){
        rp.save(address);
    }

    public void save(List<Address> address){
        rp.saveAll(address);
    }

    public Address findByUserData(UserData userData) {
        return rp.findByUserData(userData).orElse(null);
    }

}
