package com.skilldistillery.spendingtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.spendingtracker.entities.Purchase;
import com.skilldistillery.spendingtracker.repositories.SpendingRepository;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private SpendingRepository repo;

	@Override
	public List<Purchase> allTransactions() {
		return repo.findAll();
	}

	@Override
	public Purchase retrieveTransaction(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}