package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILeaderRepository extends MongoRepository <Leader, String> {

}
