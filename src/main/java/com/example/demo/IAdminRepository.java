package com.example.demo;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAdminRepository extends MongoRepository<Admin, String> {

}
