package com.skilldistillery.spendingtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.spendingtracker.entities.Purchase;

public interface SpendingRepository extends JpaRepository<Purchase, Integer> {

}
