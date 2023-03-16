package com.example.jan2023.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection="employs")
public class Employs {

    @Id
    private int empId;
    private int empName;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpName() {
        return empName;
    }

    public void setEmpName(int empName) {
        empName = empName;
    }
}
