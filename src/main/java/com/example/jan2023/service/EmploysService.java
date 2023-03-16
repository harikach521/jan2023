package com.example.jan2023.service;

import com.example.jan2023.model.Employs;
import com.example.jan2023.repository.EmploysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploysService {

    @Autowired
    EmploysRepository employsRepository;

    public void addEmploy(Employs employs){
        employsRepository.save(employs);
    }

    public List<Employs> getAllEmploys(){
        return employsRepository.findAll();
    }
}
