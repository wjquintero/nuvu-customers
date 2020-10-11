package com.wquintero.services;

import com.wquintero.model.User;
import com.wquintero.model.UserData;
import com.wquintero.repositories.AddressRepository;
import com.wquintero.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository rp;

    @Autowired
    private AddressRepository addressRp;

    public void save(UserData userData){
        rp.save(userData);
        if (userData.getAddresses()!=null) {
            userData.getAddresses().stream().forEach(o -> o.setUserData(userData));
            addressRp.saveAll(userData.getAddresses());
        }
    }

    public void update(UserData userData){
        rp.save(userData);
        if (userData.getAddresses()!=null) {
            userData.getAddresses().stream().forEach(o -> o.setUserData(userData));
            addressRp.saveAll(userData.getAddresses());
        }
    }

    public void deleteAddresses(UserData userData){
        if (userData.getAddresses()!=null) {
            addressRp.deleteInBatch(userData.getAddresses());
        }
    }

    public UserData findByUser(User user) {
        return rp.findByUser(user).orElse(null);
    }
}
