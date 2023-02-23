package com.example.jan2023.repository;

import com.example.jan2023.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//responsible for running the queries
@Repository
public interface EmployeeRepository extends JpaRepository<Emp, Integer> {
    //public List<Emp> findByDeptNo(int deptno);
}
