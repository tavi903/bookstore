package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;

import entity.Users;



public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {
	
	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public Users create(Users users) throws Exception {
		return super.create(users);
	}
	
	@Override
	public Users update(Users users) {
		return super.update(users);
	}

	@Override
	public Users get(Object userId) {
		return super.find(Users.class, userId);
	}
	
	/**
	 * Find a user given the email.
	 * */
	public Users findByEmail(String email) {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.putIfAbsent("email", email);
		
		List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", parameters);
	
		if(Objects.nonNull(listUsers) && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		
		return null;
	
	}
	
	public boolean checkLogin(String email, String password) {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.putIfAbsent("email", email);
		parameters.putIfAbsent("password", password);
		
		List<Users> userLogged = super.findWithNamedQuery("Users.checkLogin", parameters);
	
		if(Objects.nonNull(userLogged) && userLogged.size() == 1) {
			return true;
		}
		
		return false;
	
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
	}

}
