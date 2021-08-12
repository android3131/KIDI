package com.example.demo.pckg1;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<Category, String>{

	/*
	public Category createCategory(Category category);
	public Category getCategoryById(String id);
	public Category setCategoryImage(String categoryId, String imagePath);
	
	public ArrayList<Category> addCategory(Category category);
	public ArrayList<Category> getAllCategories();*/
	
	
} 
