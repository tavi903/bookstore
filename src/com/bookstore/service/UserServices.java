package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);
	}

	public void listUser() throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		request.setAttribute("listUsers", listUsers);
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void listUser(String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		listUser();
	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		Users user = new Users(email, password, fullName);

		// check if a user already exist with that email
		if (userDAO.findByEmail(email) != null) {
			String message = "A user with email " + email + " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			userDAO.create(user);
			listUser("New user created successfully.");
		}

	}

	public void getUser() throws ServletException, IOException {
		Users users = userDAO.find(Users.class, Integer.parseInt(request.getParameter("id")));
		request.setAttribute("user", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user_form.jsp");
		dispatcher.forward(request, response);
	}
	
	public void updateUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		Users user = new Users(email, password, fullName);
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		
		// check if a user already exist with that email and is different from the current
		if (userDAO.findByEmail(email) != null && !userDAO.get(user.getUserId()).getEmail().equals(email)) {
			String message = "A user with email " + email + " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			// Users users = userDAO.update(user);
			StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("update_user");
			query.setParameter("p_last_accessed_time", request.getSession().getLastAccessedTime());
			query.setParameter("p_user_id", user.getUserId());
			query.setParameter("p_email", user.getEmail());
			query.setParameter("p_password", user.getPassword());
			query.setParameter("p_full_name", user.getPassword());
			
			try {
				query.execute();
				listUser("User updated.");
			} catch (PersistenceException persistenceException) {
				request.setAttribute("message", new ExceptionUtils().getRootCauseMessage(persistenceException));
				RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
				dispatcher.forward(request, response);
			}

		}
		
	}
	
	public void deleteUser() throws ServletException, IOException {
		try {
			userDAO.delete(Integer.parseInt(request.getParameter("id")));
			listUser("The user has been deleted.");
		} catch(EntityNotFoundException entityNotFoundException) {
			listUser();
		}
	}
	
}