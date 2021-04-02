package com.skilldistillery.spendingtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.spendingtracker.entities.Transaction;
import com.skilldistillery.spendingtracker.repositories.SpendingRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private SpendingRepository repo;

	@Override
	public List<Transaction> allTransactions() {
		return repo.findAll();
	}

	@Override
	public Transaction retrieveTransaction(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
