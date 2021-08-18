package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class CategoryRepository {
	
@Autowired
ICategoryRepository categoryRepo;


/**
 * 
 * @param category new category to add
 * @return new category added.
 */
public Category createCategory(Category category) {
	return categoryRepo.save(category);
}

/**
 * 
 * @param category to add to the db
 * @return list of all categories.
 */
public ArrayList<Category> addCategory(Category category){
	categoryRepo.save(category);
	return (ArrayList<Category>) categoryRepo.findAll();
}

/**
 * 
 * @param id of category wants to retrieve
 * @return Category
 */
public Category getCategoryById(String id) {
	Optional<Category> optional = categoryRepo.findById(id);
	if(optional.isPresent()) {
		Category cat = optional.get();
		System.out.println("Returns Category now.");
		return cat;
	}
	System.out.println("Category with" + id  + "Not Found.");
 	return null;
}

/**
 * 
 * @param categoryId of the category to set image for.
 * @param imagePath the path of the image to set for the category
 * @return the category with added image.
 */
public Category setCategoryImage(String categoryId, String imagePath) {
	Optional<Category> optional  = categoryRepo.findById(categoryId);
	if(optional.isPresent()) {
		Category cat = optional.get();
		cat.setCategoryImage(imagePath);
		categoryRepo.save(cat);
		System.out.println("Category image Saved");
		return cat;
	}
	System.out.println("There is no Category with id: " +categoryId);
	return null;
}

/**
 * 
 * @return a list of all categories.
 */
public ArrayList<Category> getAllCategories(){
	return (ArrayList<Category>) categoryRepo.findAll();
}

}
