package com.example.jan2023.controller;

import com.example.jan2023.model.Employs;
import com.example.jan2023.service.EmploysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employs")
public class EmploysController {

    @Autowired
    EmploysService employsService;

    @GetMapping("/all")
    public List<Employs> getAllEmploys(){
        return employsService.getAllEmploys();
    }

    @PostMapping("/adding")
    public void addEmploys(@RequestBody Employs employs){
        employsService.addEmploy(employs);
    }

}
