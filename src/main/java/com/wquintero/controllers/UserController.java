package com.wquintero.controllers;

import com.wquintero.model.ResponseMsg;
import com.wquintero.model.User;
import com.wquintero.services.UserDataService;
import com.wquintero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDataService userDataService;

    @PostMapping(path = "/user/create")
    public ResponseEntity createUser(@RequestBody User user) {
        //System.out.println(user);
        if (user==null || user.getUsername()==null) {
            return new ResponseEntity(new ResponseMsg(-101,"Missing user information"), HttpStatus.NOT_FOUND);
        }
        if (!validateUser(user, true)) {
            return new ResponseEntity(new ResponseMsg(-102,"Missing information for user registration " +
                    "(username, password, firstName, lastName, idType, idNumber, age. are required)"), HttpStatus.NOT_FOUND);
        }
        if (userService.findByUserName(user.getUsername().toLowerCase())!=null) {
            return new ResponseEntity(new ResponseMsg(-103,"User name (" + user.getUsername() + ") " +
                    "already exist in DB"), HttpStatus.NOT_FOUND);
        }
        try {
            user.setRoles("ROLE_USER");
            user.setActive(true);
            if (user.getUserData() != null) user.getUserData().setCreditCard(null);
            userService.save(user);
            return new ResponseEntity(new ResponseMsg(100,"User has been created"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseMsg(-104,"Error creating user - errMsg: " + ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/private/user")
    public ResponseEntity getUser(Authentication authentication){
        User user = userService.findByUserName(authentication.getName());
        if (user == null) {
            return new ResponseEntity(new ResponseMsg(-100,"User not found"), HttpStatus.NOT_FOUND);
        } else {
            user.setPassword("****");
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @PutMapping(path = "/private/user/update")
    public ResponseEntity udateUser(Authentication authentication, @RequestBody User user) {
        //System.out.println(user);
        if (user==null) {
            return new ResponseEntity(new ResponseMsg(-105,"Missing user information for update"), HttpStatus.NOT_FOUND);
        }
        User curUser = userService.findByUserName(authentication.getName());
        if (curUser==null) {
            return new ResponseEntity(new ResponseMsg(-106,"User name (" + user.getUsername() + ") " +
                    "not found in DB"), HttpStatus.NOT_FOUND);
        }
        if (!validateUser(user, false)) {
            return new ResponseEntity(new ResponseMsg(-107,"Missing information for user updating " +
                    "(firstName, lastName, idType, idNumber, age. are required)"), HttpStatus.NOT_FOUND);
        }

        try {
            if (curUser.getUserData() != null && curUser.getUserData().getAddresses()!=null) {
                userDataService.deleteAddresses(curUser.getUserData());
            }
            mergeUser(curUser, user);
            userService.update(curUser);
            return new ResponseEntity(new ResponseMsg(101,"User has been updated"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseMsg(-108,"Error updating user - errMsg: " + ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/private/user/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getUsers(){
        List<User> users = userService.findAll();
        if (users == null) {
            return new ResponseEntity(new ResponseMsg(-100,"Users not found"), HttpStatus.NOT_FOUND);
        } else {
            //users.stream().forEach(o->o.setPassword("****"));
            return new ResponseEntity(users, HttpStatus.OK);
        }
    }

    private User mergeUser(User currentUsr, User newUsr) {
        if (currentUsr.getUserData() == null || newUsr.getUserData() == null) return currentUsr;
        currentUsr.getUserData().setFirstName(newUsr.getUserData().getFirstName())
                .setSecondName(newUsr.getUserData().getSecondName())
                .setLastName(newUsr.getUserData().getLastName())
                .setSecondLastName(newUsr.getUserData().getSecondLastName())
                .setIdType(newUsr.getUserData().getIdType())
                .setIdNumber(newUsr.getUserData().getIdNumber())
                .setAge(newUsr.getUserData().getAge())
                .setPhoneNumber(newUsr.getUserData().getPhoneNumber())
                .setEmail(newUsr.getUserData().getEmail())
                .setAddresses(newUsr.getUserData().getAddresses());
        currentUsr.setUpdatedDate(new Date());
        return currentUsr;
    }

    private boolean validateUser(User user, boolean isCreating) {
        if (isCreating && (user == null || user.getUsername()==null || user.getPassword()==null)) {
            return false;
        }
        if (user == null || user.getUserData() == null ||
                user.getUserData().getFirstName()==null || user.getUserData().getLastName()==null ||
                user.getUserData().getIdType()==null || user.getUserData().getIdNumber()==null ||
                user.getUserData().getAge()==null) {
            return false;
        }
        return true;
    }

}
