package com.example.jan2023.controller;

import com.example.jan2023.model.Emp;
import com.example.jan2023.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    //testing spring controller so our simulation should be available inside the EmployeeController
    @InjectMocks //in which class we are trying to run scenarion
    EmployeeController employeeController;

    @Mock //for which classes we are going to mock
    EmployeeService employeeService;

    private static List<Emp> empList;

    //Performing Data preparation
    @Before
    public void doSetUp(){ //will do datasetup for us
        empList = new ArrayList<>();
        Emp emp1 = new Emp();
        emp1.setEmpno(8000);
        emp1.setEname("TestStu1");
        emp1.setDeptno(20);

        Emp emp2 = new Emp();
        emp2.setEmpno(8500);
        emp2.setEname("TestStu2");
        emp2.setDeptno(30);

        empList.add(emp1);
        empList.add(emp2);
    }
    @Test
    public void testListOfEmpByDeptNo(){
        when(employeeService.listOfEmpByDeptNo(anyInt())).thenReturn(empList);
        List<Emp> actualEmpList = employeeController.getByDeptNo(20);
        assertNotNull(actualEmpList);
        assertEquals(empList.size(),actualEmpList.size());
    }

}
