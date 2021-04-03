package com.skilldistillery.spendingtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.spendingtracker.entities.Category;
import com.skilldistillery.spendingtracker.services.CategoryService;

@RequestMapping("api")
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService svc;
	
	@GetMapping("categories")
	public List<Category> ListAll() {
		return svc.allCategories();
	}

}
