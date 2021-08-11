package com.amal.myNewApp.pkg1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILeaderRepository extends MongoRepository <Leader, String> {
}
