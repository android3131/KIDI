package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IParentRepository extends MongoRepository <Parent, String>{
}
