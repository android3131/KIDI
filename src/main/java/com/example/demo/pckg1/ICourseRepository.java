package com.example.demo.pckg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICourseRepository extends MongoRepository <Course, String> {
}
