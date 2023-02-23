package com.example.jan2023.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/greet")
    public String sayHello(){
        return "Welcome to the SpringBoot Framework";
    }

    @GetMapping("/greet/{name}")
    public String sayHelloWithName(@PathVariable("name") String inputName){
        return "HI" + inputName +", WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    @GetMapping("/greeting")
    public String sayHelloWithGreeting(@RequestParam("name") String inputName){
        return "HI" + inputName +", WELCOME TO SPRINGBOOT FRAMEWORK";
    }
}
