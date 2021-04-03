package com.skilldistillery.spendingtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.spendingtracker.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
