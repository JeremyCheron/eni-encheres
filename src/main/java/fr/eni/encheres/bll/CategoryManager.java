package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bll.error.ErrorManager;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoryManager {

	
	private static CategoryManager instance;
	private DAO<Category> categoryDAO;
	private ErrorManager errorManager;
	
    private CategoryManager() {
        this.categoryDAO = new DAOFactory().getCategoryDAO();
        this.errorManager = new ErrorManager();
    }
	
	public static CategoryManager getInstance() {
		if (instance == null) {
			instance = new CategoryManager();
		}
		return instance;
	}
	
	// TODO supprimer code commenté ???

	//	 public void setCategoryDAO(DAO<Category> categoryDAO) {
//	        this.categoryDAO = categoryDAO;
//	    }

	
//	    public void createCategory(Category category) throws BLLException {
//	        try {
//	        	categoryDAO.insert(category);
//	        } catch (DALException e) {
//	            throw new BLLException();
//	        }
//	    }
	    
	
	public List<Category> getCategories() throws BLLException{
		try {
			return categoryDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException(errorManager.getErrorMessage("20200"), "20200");
		}
	}

}
