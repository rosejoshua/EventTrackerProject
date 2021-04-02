package com.skilldistillery.spendingtracker.services;

import java.util.List;

import com.skilldistillery.spendingtracker.entities.Purchase;

public interface PurchaseService {
	
	List<Purchase> allTransactions();
	
	Purchase retrieveTransaction(int transactionId);

}
