package fr.eni.encheres.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.error.ErrorManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class UserManager {

	
	private static UserManager instance;
	private DAO<User> userDAO;
	private ErrorManager errorManager;
	
    private UserManager() {
        this.userDAO = new DAOFactory().getUserDAO();
        this.errorManager = new ErrorManager();
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
        	if (!isUsernameAlreadyTaken(user.getUsername()) && !isEmailAlreadyTaken(user.getMail())) {
        		userDAO.insert(user);
        	} else {
    			throw new BLLException(errorManager.getErrorMessage("20005"), "20005");
        	}
        } catch (DALException e) {
			throw new BLLException(errorManager.getErrorMessage("20002"), "20002");
        }
    }

    public User login (String username, String password) throws BLLException, DALException {
    	UserDAO loginDAO = (UserDAO) new DAOFactory().getUserDAO();
		return loginDAO.validateLogin(username, password);
    }
    
    public User loginByCookie(String username) {
		UserDAO loginDAO = (UserDAO) new DAOFactory().getUserDAO();
		return loginDAO.getLogin(username);
	}
    
	private void validateUserData(User user) throws BLLException {

		if (isUsernameAlreadyTaken(user.getUsername())) {
			throw new BLLException(errorManager.getErrorMessage("20000"), "20000");
		}
		
		if (isEmailAlreadyTaken(user.getMail())) {
			throw new BLLException(errorManager.getErrorMessage("20001"), "20001");
		}
	
	}
	
	private boolean isUsernameAlreadyTaken(String username) {

		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("username", username);
			
			List<User> users = userDAO.selectByCriteria(criteria);
			
			return !users.isEmpty();
			
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	private boolean isEmailAlreadyTaken(String email) {
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("email", email);
			
			List<User> users = userDAO.selectByCriteria(criteria);
			
			return !users.isEmpty();
			
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}	}
	
    
	public int getUserCredits(int userId) throws BLLException{
		
		int userCredits = 0;
		
		try {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("user_id", userId);
			
			UserDAO creditsByUser = (UserDAO) new DAOFactory().getUserDAO();
			List<User> users = creditsByUser.selectByCriteria(criteria);
			
			if(!users.isEmpty()) {
				userCredits = users.get(0).getPoints();
			}
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(errorManager.getErrorMessage("10002"), "10002");
		}
		
		return userCredits;
		
	}

	public void adjustUserCredits(int userId, int creditsAdjustment) throws BLLException{

		try {
			
			User user = userDAO.selectByID(userId);
			if (user != null) {
				user.setPoints(user.getPoints() + creditsAdjustment);
				userDAO.update(user);
			} else {
				throw new BLLException(errorManager.getErrorMessage("20003"), "20003");
			}
			
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(errorManager.getErrorMessage("20004"), "20004");
		}
		
	}
	
	public User getUser(int id) throws BLLException {
		User user = null;
		
		try {
			
			user = userDAO.selectByID(id);
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
}
