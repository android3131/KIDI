package com.example.demo.pckg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IParentRepository extends MongoRepository <Parent, String>{
		
}
