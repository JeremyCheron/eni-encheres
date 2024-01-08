package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoryManager {

	
	private static CategoryManager instance;
	private DAO<Category> categoryDAO;
	
    private CategoryManager() {
        this.categoryDAO = new DAOFactory().getCategoryDAO();
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
			throw new BLLException();
		}
	}

}
