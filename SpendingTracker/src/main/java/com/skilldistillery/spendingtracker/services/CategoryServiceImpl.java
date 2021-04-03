package com.skilldistillery.spendingtracker.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.spendingtracker.entities.Category;
import com.skilldistillery.spendingtracker.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repo;

	@Override
	public List<Category> allCategories() {
		return repo.findAll();
	}

	@Override
	public Category retrieveCategory(int categoryId) {
		Optional<Category> categoryOptional = repo.findById(categoryId);
		if (categoryOptional.isPresent()) {
			return categoryOptional.get();
		}
		else {
			return null;
		}
		
	}

}
