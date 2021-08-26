package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
	
@Autowired
ICategoryRepository categoryRepo;
@Autowired
CourseRepository courseRepo;
long DAY_IN_MS = 1000 * 60 * 60 * 24;

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
 *Adds a category, and returns all categories 	
 * @param category	
 * @return list of all categories	
 */	
public List<Category> addANewCategory(Category category) {	
	Category c = findCategoryByName(category.getName());	
	if(c != null)	
		return categoryRepo.findAll();	
	categoryRepo.save(category);	
	return categoryRepo.findAll();
}

/**	
 * Given a category name, returns the category	
 * 	
 * @param name	
 * @return category	
 */	
private Category findCategoryByName(String name) {	
	for (Category c : categoryRepo.findAll()) {	
		if (c.getName().equals(name))	
			return c;	
	}	
	return null;	
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

/**
 * 
 * @return a list of all categories ids.
 */
public ArrayList<String> getAllCategoriesIds(){
	ArrayList<Category> categories = (ArrayList<Category>) categoryRepo.findAll();
	ArrayList<String> toReturn = new ArrayList<String>();
	for (Category category : categories) {
		toReturn.add(category.getId());
	}
	return toReturn;
}

/***
 *  I assume that the startDate of a course is after the given period
 * 
 * @param period
 * @return hasHMAP that contains keys as category names, and the number of kids in its courses in value.
 */
public HashMap<String, Integer> getKidsCountByCategory(int period){
	if(period != 1 && period !=2 && period !=3) {
		new ResponseEntity<>("Input: 1- For week 2- For month 3- For year.", HttpStatus.NOT_ACCEPTABLE);
		return null;
	}
	Date d;
	if(period == 1) {
		d = new Date((new Date()).getTime()- 7*DAY_IN_MS);
	}else if(period == 2) {
		d = new Date((new Date()).getTime()-35*DAY_IN_MS);
	}
	else {
		d = new Date((new Date()).getTime()- 365*DAY_IN_MS);
	}
	HashMap<String, Integer> kidsCountByCategory = new HashMap<String, Integer>();
	for(String catId : getAllCategoriesIds()) {
		int categoryKids = 0;
		ArrayList<Course> courses = courseRepo.getCategoryCourses(catId);
		for(Course c : courses) {
			if(c.getStartDateTime().after(d)) {
				categoryKids += c.getKidsIDs().size()+1;
			}
		}
		kidsCountByCategory.put(getCategoryById(catId).getName(), categoryKids);
	}
	return kidsCountByCategory;
}

}
