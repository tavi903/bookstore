package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
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
		users.setEmail("abvaca@gmail.com");
		users.setFullName("Abvaca Samir");
		users.setPassword("mypswd55");
		
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
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 40;
		Users user = userDAO.get(userId);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 40;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteNonExistentUser() {
		Integer userId = 40;
		assertThrows(EntityNotFoundException.class, () -> userDAO.delete(userId));
	}
	
	@Test
	public void testListAllUsers() {
		List<Users> users = userDAO.listAll();
		assertEquals(users.size(), 2);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertTrue(totalUsers > 0);
	}
	
	@AfterAll
	public static void tearDownAfterClass() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
