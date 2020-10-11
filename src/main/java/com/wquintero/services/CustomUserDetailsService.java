/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wquintero.services;

import com.wquintero.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author developer
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository rp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rp
                .findByUsername(username)
                .map(u -> new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
        true,true,true,true,
                Arrays.stream(u.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList())))
                .orElseThrow(() -> new UsernameNotFoundException("No user with "
                        + "the name " + username + "was found in the database"));
    }


}
