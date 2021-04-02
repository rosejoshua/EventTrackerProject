package com.skilldistillery.spendingtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.spendingtracker.entities.Transaction;
import com.skilldistillery.spendingtracker.services.TransactionService;

@RequestMapping("api")
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService svc;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("transactions")
	public List<Transaction> listAll() {
		return svc.allTransactions();
	}

}
