package com.example.jan2023.service;

import com.example.jan2023.model.Emp;
import com.example.jan2023.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Service class contains all the business logic which is deligated by Controller class
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    public List<Emp> getAllEmployees(){
        return employeeRepository.findAll();//findAll will find all records from DB
        //findAll works like SELECT * FROM EMP;
        //Hibernate library will prepare this query and execute against DB get results and mapp to Emp object
    }

    public Emp getEmpById(int empNo){
        //return employeeRepository.findById(empNo).get();
        //there are chances that employee doesnot exists to handle it we use optional
        Optional<Emp> optEmp = employeeRepository.findById(empNo);
        if(optEmp.isPresent()){
            return optEmp.get();
        }
        else
            return new Emp();//new dummy student will be created which is not an specific response to user.
    }

    //Inserting data into the DB
    public void upsertEmployee(Emp emp){
        employeeRepository.save(emp);//save method acts like upsert-update or insert
    }

    //Deleting Emp from DB
    public void deleteEmployee(int empNo){
        employeeRepository.deleteById(empNo);
    }
    //list of employees based on deptno
//    public List<Emp> listOfEmpByDeptNo(int deptno){
//       return employeeRepository.findByDeptNo(deptno);
//    }
}
