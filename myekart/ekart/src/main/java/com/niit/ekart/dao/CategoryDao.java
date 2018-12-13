package com.niit.ekart.dao;

import java.util.List;

import com.niit.ekart.model.Category;


public interface CategoryDao {
	public List<Category> getAllCategory();
    public void updateCategory(Category category);
    public Category deleteCategoryById(int id);
	public Category getCategoryById(int id);
	public void addCategory(Category category);
   
}
