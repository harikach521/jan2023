package com.example.jan2023.service;

import com.example.jan2023.kafka.KafkaProducerService;
import com.example.jan2023.model.Emp;
import com.example.jan2023.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

//Service class contains all the business logic which is deligated by Controller class
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    KafkaProducerService producer;
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
    public void upsertEmployee(Emp emp) throws JsonProcessingException {
        String returnMessage = producer.sendSimpleMessage(emp);
        //employeeRepository.save(emp);//save method acts like upsert-update or insert
    }

    //Deleting Emp from DB
    public void deleteEmployee(int empNo){
        employeeRepository.deleteById(empNo);
    }
    //list of employees based on deptno
   public List<Emp> listOfEmpByDeptNo(int deptno){
       return employeeRepository.findByDeptNo(deptno);
    }

    //method for CSV file upload
    public void readFileContents(InputStream inputStream) throws Exception{
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();
        Random randomEmpno = new Random();
        for(CSVRecord record: records){
            Emp emp = new Emp();
            emp.setEmpno(randomEmpno.nextInt(100));
            emp.setEname(record.get(1));
            emp.setJob(record.get(2));
            //emp.setMgr(Integer.parseInt(record.get(3)));
            //emp.setHiredate(Timestamp.valueOf(record.get(4)));
            //emp.setSal(Integer.parseInt(record.get(5)));
            //emp.setComm(Integer.parseInt(record.get(6)));
            //emp.setDeptno(Integer.parseInt(record.get(7)));

            employeeRepository.save(emp);
        }

    }

    //method for CSV file download
    public void writeEmpToCSV(Writer writer){
        List<Emp> emps = employeeRepository.findAll();
        try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("EMPNO","ENAME","JOB","MGR","HIREDATE","SAL","COMM","DEPTNO");
            for(Emp emp:emps){
                csvPrinter.printRecord(emp.getEmpno(),emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno());
            }
        }catch(Exception e){
            System.out.println("Exception Raised");
        }
    }
}
