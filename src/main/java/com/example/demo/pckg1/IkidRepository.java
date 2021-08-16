package com.example.demo.pckg1;


import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IkidRepository extends MongoRepository<Kid, String>{

}
