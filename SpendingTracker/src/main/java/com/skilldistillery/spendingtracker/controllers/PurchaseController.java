package com.skilldistillery.spendingtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("purchase/{purchaseId}")
	public Purchase getPurchaseById(@PathVariable Integer purchaseId) {		
		return svc.retrievePurchase(purchaseId);
	}

	@PostMapping("purchases")
	public Purchase createPurchase(@RequestBody Purchase purchase, 
		HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			svc.createPurchase(purchase.getCategory().getId(), purchase);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(purchase.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			System.err.println(e);
			resp.setStatus(400);
			purchase = null;
		}
		
		return purchase;
	}

	//TODO: **************ADD SESSION ID AND PURCHASE ID LATER**************
	@PutMapping("purchase/{purchaseId}")
	public Purchase updatePurchase(@PathVariable Integer purchaseId, @RequestBody Purchase purchase, 
			HttpServletResponse resp) {
		
		try {
			purchase = svc.updatePurchase(purchase.getCategory().getId(), purchaseId, purchase);
			if(purchase==null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			System.err.println(e);
			resp.setStatus(400);
			purchase = null;
		}
		
		return purchase;
	}
	
	//TODO: **************ADD SESSION ID AND PURCHASE ID LATER**************
	@DeleteMapping("purchase/{purchaseId}")
	public void deletePurchase(@PathVariable Integer purchaseId, HttpServletResponse resp) {
		
		try {
			if(svc.deletePurchase(purchaseId)) {
				resp.setStatus(204);
			}
			else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			System.err.println(e);
			resp.setStatus(400);
		}
		
	}

	@GetMapping("purchases/{categoryId}")
	public List<Purchase> listCategoryPurchases(@PathVariable Integer categoryId) {		
		return svc.allPurchasesByCategory(categoryId);
	}
}