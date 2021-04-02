package com.skilldistillery.spendingtracker.services;

import java.util.List;

import com.skilldistillery.spendingtracker.entities.Transaction;

public interface TransactionService {
	
	List<Transaction> allTransactions();
	
	Transaction retrieveTransaction(int transactionId);

}
