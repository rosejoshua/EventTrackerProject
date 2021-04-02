package com.skilldistillery.spendingtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Purchase> listAll() {
		return svc.allTransactions();
	}

}
