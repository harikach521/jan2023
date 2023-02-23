package com.example.jan2023.controller;

import com.example.jan2023.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    @Autowired
    CalculateService calculateService;
    @GetMapping("/add/{first}/{second}")
    public double add(@PathVariable("first") double first, @PathVariable("second")double second){
        return calculateService.add(first,second);
    }

    @GetMapping("/divide/{first}/{second}")
    public ResponseEntity<Object> divide(@PathVariable("first") double first, @PathVariable("second") double second){
        if(second == 0){
            return new ResponseEntity<>("Divide a number with 0 is not applicable please try with another number", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(calculateService.divide(first, second), HttpStatus.OK);
    }
}
