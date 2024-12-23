//package fr.eni.encheres.bll;
//
//import java.util.List;
//
//
//import fr.eni.encheres.bll.error.ErrorManager;
//import fr.eni.encheres.bo.ImageFile;
//import fr.eni.encheres.dal.DALException;
//import fr.eni.encheres.dal.DAO;
//import fr.eni.encheres.dal.DAOFactory;
//
//public class ImageFileManager {
//
//	private static ImageFileManager instance;
//	private DAO<ImageFile> imageFileDAO;
//	private ErrorManager errorManager;
//	
//
//	private ImageFileManager() {
//		this.imageFileDAO = new DAOFactory().getImageFileDAO();
//        this.errorManager = new ErrorManager();
//	}
//
//	public static ImageFileManager getInstance() {
//		if (instance == null) {
//			instance = new ImageFileManager();
//		}
//		return instance;
//	}
//
//	public void setImageFileDAO(DAO<ImageFile> imageFileDAO) {
//		this.imageFileDAO = imageFileDAO;
//	}
//
//	public void createImageFile(ImageFile image) throws BLLException {
//
//		try {
//			imageFileDAO.insert(image);
//		} catch (DALException e) {
//			throw new BLLException(errorManager.getErrorMessage("20102"), "20102");
//		}
//	}
//
//	public List<ImageFile> getImages() throws BLLException {
//		try {
//	    	return imageFileDAO.selectAll();
//	    } catch (DALException e) {
//			throw new BLLException(errorManager.getErrorMessage("20102"), "20102");
//		}
//
//	}
//	
//	public List<ImageFile> getArticleImages(int articleID) throws BLLException {
//		try {
//			return imageFileDAO.selectAll().stream().filter(i -> i.getArticleId()==articleID).toList();
//		} catch (DALException e) {
//			throw new BLLException(errorManager.getErrorMessage("20102"), "20102");
//		}
//	}
//
//
//}
