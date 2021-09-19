package dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.UserDAO;
import entity.Users;

public class UserDAOTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() {
	    entityManagerFactory = Persistence.createEntityManagerFactory("H2");
		entityManager = entityManagerFactory.createEntityManager();
	    userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() throws Exception {
		Users users = new Users();
		users.setEmail("abvaca@gmail.com");
		users.setFullName("Abvaca Samir");
		users.setPassword("mypswd55");
		
		Users userSaveToDB = userDAO.create(users);
		
		assertTrue(userSaveToDB.getUserId() > 0);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void createUsersFailedToSetFields() throws Exception {
		Users users = new Users();
		userDAO.create(users);
	}
	
	@Test
	public void testUpdateUsers() {
		Users users = userDAO.find(Users.class, 19);
		users.setEmail("cesare.augusto@gmail.com");
		Users userUpdated = userDAO.update(users);
		assertEquals(userUpdated.getEmail(),users.getEmail());
	}
	
	@Test
	public void testGetUsersFound() {
		Users user = userDAO.get(19);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 12345;
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteUser() {
		userDAO.delete(21);
		Users user = userDAO.get(21);
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistentUser() {
		userDAO.delete(12345);
	}
	
	@Test
	public void testListAllUsers() {
		List<Users> users = userDAO.listAll();
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertTrue(totalUsers > 0);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
