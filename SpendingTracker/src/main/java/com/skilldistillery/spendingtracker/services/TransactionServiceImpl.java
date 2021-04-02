package com.skilldistillery.spendingtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.spendingtracker.entities.Transaction;
import com.skilldistillery.spendingtracker.repositories.SpendingRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	private SpendingRepository repo;

	@Override
	public List<Transaction> allTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction retrieveTransaction(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
