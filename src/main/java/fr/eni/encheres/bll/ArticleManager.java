package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {

	
	private static ArticleManager instance;
	private DAO<Article> articleDAO;
	
    private ArticleManager() {
        this.articleDAO = new DAOFactory().getArticleDAO();
    }
	
	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	 public void setArticleDAO(DAO<Article> articleDAO) {
	        this.articleDAO = articleDAO;
	    }

	
	    public void createArticle(Article article) throws BLLException {
	        validateArticleData(article);

	        try {
	            articleDAO.insert(article);
	        } catch (DALException e) {
	            throw new BLLException();
	        }
	    }
	
	    
	private void validateArticleData(Article article) throws BLLException {
	}

}
