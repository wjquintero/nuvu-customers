package com.wquintero.services;

import com.wquintero.model.User;
import com.wquintero.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository rp;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());
        rp.save(user);
        if (user.getUserData() != null) {
            user.getUserData().setUser(user);
            userDataService.save(user.getUserData());
        }
    }

    public void update(User user){
        user.setUpdatedDate(new Date());
        rp.save(user);
        if (user.getUserData() != null) {
            user.getUserData().setUser(user);
            userDataService.save(user.getUserData());
        }
    }

    public User findByUserName(String username) {
        User user = rp.findByUsername(username).orElse(null);
        return user;
    }

    public List<User> findAll() {
        return rp.findAll();
    }

}
