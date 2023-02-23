package com.example.jan2023.controller;

import com.example.jan2023.model.Emp;
import com.example.jan2023.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/all")
    public List<Emp> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/{empNo}")
    public ResponseEntity<Object> getEmpById(@PathVariable("empNo") int eno){
        Emp emp = employeeService.getEmpById(eno);
        if(emp.getEmpno()==0){
            return new ResponseEntity<>("Employee with given EmpNo doesn't exists", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(emp,HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Emp emp){
        employeeService.upsertEmployee(emp);
    }

    @PutMapping("/update")
    public void updateEmployee(@RequestBody Emp emp){
        employeeService.upsertEmployee(emp);
    }

    @DeleteMapping("/delete/{empNo}")
    public void deleteEmployee(@PathVariable("empNo") int eno){
        employeeService.deleteEmployee(eno);
    }

//    @GetMapping("/getByDeptNo/{deptno}")
//    public List<Emp> getByDeptNo(@PathVariable("deptno") int dno){
//        return employeeService.listOfEmpByDeptNo(dno);
//    }
}
