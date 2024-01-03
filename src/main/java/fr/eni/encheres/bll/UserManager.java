package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;

public class UserManager {

	
	private static UserManager instance;
	private DAO<User> userDAO;
	
    private UserManager(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }
	
	public static UserManager getInstance(DAO<User> userDAO) {
		if (instance == null) {
			instance = new UserManager(userDAO);
		}
		return instance;
	}
	
	 public void setUserDAO(DAO<User> userDAO) {
	        this.userDAO = userDAO;
	    }

	
	    public void createUser(User user) throws BLLException {
	        validateUserData(user);

	        try {
	            userDAO.insert(user);
	        } catch (DALException e) {
	            throw new BLLException();
	        }
	    }
	
	private void validateUserData(User user) throws BLLException {

		if (isUsernameAlreadyTaken(user.getUsername())) {
			throw new BLLException();
		}
		
		if (isEmailAlreadyTaken(user.getMail())) {
			throw new BLLException();
		}
	
	}
	
	private boolean isUsernameAlreadyTaken(String username) {
		return false;
	}
	
	private boolean isEmailAlreadyTaken(String email) {
		return false;
	}
	
}
