package com.skilldistillery.spendingtracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Purchase purchase;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SpendingTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		purchase = em.find(Purchase.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		purchase = null;
	}

	@Test
	@DisplayName("test purchase entity getNotes")
	void test() {
		assertNotNull(purchase);
		assertEquals("groceries", purchase.getNotes());
	}

	@Test
	@DisplayName("test purchase entity getTimeStamp")
	void test2() {
		assertNotNull(purchase.getDatetime());
	}

	@Test
	@DisplayName("test mapping ManyToOne purchase to category")
	void test3() {
		assertNotNull(purchase.getCategory());
		assertEquals("Groceries Essential", purchase.getCategory().getName());
		assertNotNull(purchase.getCategory().getPurchases());
	}

}
