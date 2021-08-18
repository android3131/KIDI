package com.example.demo.pckg1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication//(exclude={MongoAutoConfiguration.class})
public class KidiApplication {
	public static void main(String[] args) {
		SpringApplication.run(KidiApplication.class, args);
	}

}
