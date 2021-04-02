package com.skilldistillery.spendingtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.spendingtracker.entities.Transaction;

public interface SpendingRepository extends JpaRepository<Transaction, Integer> {

}
