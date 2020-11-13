package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Users;

class UserDAOTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;
	
	@BeforeAll
	public static void setUpClass() {
	    entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
	    userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users users = new Users();
		users.setEmail("david@gmail.com");
		users.setFullName("David Beckham");
		users.setPassword("myPwd");
		
		Users userSaveToDB = userDAO.create(users);
		
		assertTrue(userSaveToDB.getUserId() > 0 );
	}
	
	@Test()
	public void createUsersFailedToSetFields() {
		Users users = new Users();
		
		assertThrows(PersistenceException.class, () -> userDAO.create(users));
	}
	
	@Test()
	public void testUpdateUsers() {
		Users users = new Users();
		users.setUserId(33);
		users.setFullName("Tommy Avanzato");
		users.setPassword("12345");
		users.setEmail("tommy@gmail.com");
		Users userSaveToDb = userDAO.update(users);
		assertEquals(userSaveToDb.getFullName(),"Tommy Avanzato");
	}
	
	@AfterAll
	public static void tearDownAfterClass() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
