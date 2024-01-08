package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class UserManager {

	
	private static UserManager instance;
	private DAO<User> userDAO;
	
    private UserManager() {
        this.userDAO = new DAOFactory().getUserDAO();
    }
	
	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
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
	
	    public User login (String username, String password) throws BLLException, DALException {
	    	UserDAO loginDAO = (UserDAO) new DAOFactory().getUserDAO();
			return loginDAO.validateLogin(username, password);
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
