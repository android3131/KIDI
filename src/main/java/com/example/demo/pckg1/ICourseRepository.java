package com.example.demo.pckg1;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICourseRepository extends MongoRepository <Course, String> {
}
