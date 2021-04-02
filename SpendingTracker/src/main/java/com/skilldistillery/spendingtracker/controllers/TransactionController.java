package com.skilldistillery.spendingtracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class TransactionController {
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

}
