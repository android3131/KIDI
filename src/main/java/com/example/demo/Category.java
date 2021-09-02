package com.example.demo;

import java.util.Objects;

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
	

	public Category(String id, String name, String image) {
		super();
		this.id = id;
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
	public void setCategoryImage(String image) {
		this.image = image;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, image, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(image, other.image) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ",id="+id+ ", image=" + image + "]";
	}
	
}
