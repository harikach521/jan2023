package com.example.jan2023.repository;

import com.example.jan2023.model.Employs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploysRepository extends MongoRepository<Employs, Integer> {
}
