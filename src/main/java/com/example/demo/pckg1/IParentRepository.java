package com.shelly.myApp.demo.pkg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IParentRepository extends MongoRepository <Parent, String>{
		
}
