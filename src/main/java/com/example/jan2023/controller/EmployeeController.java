package com.example.jan2023.controller;

import com.example.jan2023.model.Emp;
import com.example.jan2023.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    public void addEmployee(@RequestBody Emp emp) throws JsonProcessingException {
        employeeService.upsertEmployee(emp);
    }

    @PutMapping("/update")
    public void updateEmployee(@RequestBody Emp emp) throws JsonProcessingException {
        employeeService.upsertEmployee(emp);
    }

    @DeleteMapping("/delete/{empNo}")
    public void deleteEmployee(@PathVariable("empNo") int eno){
        employeeService.deleteEmployee(eno);
    }

    @GetMapping("/getByDeptNo/{deptno}")
    public List<Emp> getByDeptNo(@PathVariable("deptno") int dno){
        return employeeService.listOfEmpByDeptNo(dno);
    }

    //method for CSV file upload
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file")MultipartFile file){
        String fileName = file.getOriginalFilename();
        try{
            employeeService.readFileContents(file.getInputStream());
            //FileUtils.forceDelete(file.getResource().getFile());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File upload successful");
    }

    //method for CSV download
    @RequestMapping(path="/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws Exception{
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"emps.csv\"");
        employeeService.writeEmpToCSV(servletResponse.getWriter());
    }
}
