package com.skilldistillery.spendingtracker.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.spendingtracker.entities.Purchase;
import com.skilldistillery.spendingtracker.services.PurchaseService;

@RequestMapping("api")
@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseService svc;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("purchases")
	public List<Purchase> listAll(HttpServletResponse resp) {
		return svc.allPurchases();
	}
	
	@GetMapping("purchases/{categoryId}")
	public List<Purchase> listCategoryPurchases(@PathVariable Integer categoryId) {		
		return svc.allPurchasesByCategory(categoryId);
	}
}