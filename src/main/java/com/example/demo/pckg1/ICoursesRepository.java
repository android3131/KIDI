package com.amal.myNewApp.pkg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICoursesRepository extends MongoRepository <Courses, String> {
}
