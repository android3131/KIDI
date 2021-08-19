package com.example.demo.pckg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMeetingRepository extends MongoRepository <Meeting, String>{
	
}
