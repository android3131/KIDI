package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IkidRepository extends MongoRepository<Kid, String>{

}
