package com.skilldistillery.spendingtracker.services;

import java.util.List;

import com.skilldistillery.spendingtracker.entities.Purchase;

public interface PurchaseService {
	
	List<Purchase> allPurchases();
	
	List<Purchase> allPurchasesByCategory(Integer categoryId);

	Purchase retrievePurchase(int purchaseId);

	Purchase createPurchase(Integer categoryId, Purchase purchase);
	
	Purchase updatePurchase(Integer categoryId, Integer purchaseId, Purchase purchase);
	
	boolean deletePurchase(Integer purchaseId);
}
