package com.example.jan2023.service;

import com.example.jan2023.model.Emp;
import com.example.jan2023.repository.EmployeeRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService service;

    @Mock
    EmployeeRepository repo;

    private static List<Emp> empList;

    @Before
    public void doSetUp(){
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
    public void testGetAllEmployees(){
        when(repo.findAll()).thenReturn(empList);
        List<Emp> actualEmpList = service.getAllEmployees();
        assertNotNull(actualEmpList);
        assertEquals(empList.size(), actualEmpList.size());
    }
}
