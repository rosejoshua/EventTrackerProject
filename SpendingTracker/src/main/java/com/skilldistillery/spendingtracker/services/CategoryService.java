package com.skilldistillery.spendingtracker.services;

import java.util.List;

import com.skilldistillery.spendingtracker.entities.Category;

public interface CategoryService {
	
	List<Category> allCategories();
	
	Category retrieveCategory(int categoryId);

}
