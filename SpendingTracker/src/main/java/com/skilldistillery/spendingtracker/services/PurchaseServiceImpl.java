package com.skilldistillery.spendingtracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.spendingtracker.entities.Category;
import com.skilldistillery.spendingtracker.entities.Purchase;
import com.skilldistillery.spendingtracker.repositories.CategoryRepository;
import com.skilldistillery.spendingtracker.repositories.SpendingRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private SpendingRepository purchRepo;

	@Autowired
	private CategoryRepository catRepo;
	

	@Override
	public List<Purchase> allPurchases() {
		return purchRepo.findAll();
	}

	@Override
	public List<Purchase> allPurchasesByCategory(Integer categoryId) {
		List<Purchase> results = new ArrayList<>();
		for (Purchase purchase : purchRepo.findAll()) {
			if(purchase.getCategory().getId()==categoryId.intValue()) {
				results.add(purchase);
			}
		}
		return results;
	}

	@Override
	public Purchase retrievePurchase(int purchaseId) {
		Optional<Purchase> purchaseOptional = purchRepo.findById(purchaseId);
		if (purchaseOptional.isPresent()) {
			return purchaseOptional.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Purchase createPurchase(Integer categoryId, Purchase purchase) {
		
		Optional<Category> categoryOptional = catRepo.findById(categoryId);
		
		if (categoryOptional.isPresent()) {
			purchase.setCategory(categoryOptional.get());
			purchRepo.saveAndFlush(purchase);
		}
		return purchase;
	}
	
	
	//TODO: **************CHECK USER OWNERSHIP HERE ONCE SESSIONS ARE IMPLIMENTED**************
	@Override
	public Purchase updatePurchase(Integer categoryId, Integer purchaseId, Purchase purchase) {
		
		Optional<Category> categoryOptional = catRepo.findById(categoryId);
		Optional<Purchase> purchaseOptional = purchRepo.findById(purchase.getId());
		
		if (categoryOptional.isPresent() && purchaseOptional.isPresent() && purchaseOptional.get().getId()==purchaseId.intValue()) {
			purchase.setCategory(categoryOptional.get());
			purchRepo.saveAndFlush(purchase);
		}
		return purchase;
	}

	//TODO: **************CHECK USER OWNERSHIP HERE ONCE SESSIONS ARE IMPLIMENTED**************
	@Override
	public boolean deletePurchase(Integer purchaseId) {
		
		boolean deleted = false;
		Optional<Purchase> purchaseOptional = purchRepo.findById(purchaseId);
		if(purchaseOptional.isPresent()) {
			purchRepo.deleteById(purchaseId);
			deleted = true;
		}
		return deleted;
	}



}
