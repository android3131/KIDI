package com.example.demo.pckg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IkidRepository extends MongoRepository<Kid, String>{

}
