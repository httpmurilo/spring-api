package com.example.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @GetMapping(value = "/status", produces ="application/json")
    public ResponseEntity init(){
        return new ResponseEntity("UP", HttpStatus.OK);
    }
}
