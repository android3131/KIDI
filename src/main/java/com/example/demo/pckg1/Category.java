package com.example.demo.pckg1;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Category {
	@Id
	private String id;
	@Field
	private String name;
	@Field
	private String image;
	public Category() {
		
	}
	public Category(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ",id="+id+ ", image=" + image + "]";
	}
	
}
