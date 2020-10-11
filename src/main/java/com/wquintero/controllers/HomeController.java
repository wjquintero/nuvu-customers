package com.wquintero.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index(){
        return "Welcome to nuvu software for customer management";
    }

    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area (test)";
    }

}
