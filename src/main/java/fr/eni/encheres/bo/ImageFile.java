package fr.eni.encheres.bo;

public class ImageFile {

	private int imageId;
	private byte[] data;
	private String fileName;
	private int articleId;
	
	
	public ImageFile() {
	}

	public ImageFile(int _imageId, byte[] _data, String _fileName, int _articleId) {
		this.imageId = _imageId;
		this.data=_data;
		this.fileName = _fileName;
		this.articleId = _articleId;	
	}
	
	public ImageFile(byte[] _data, String _fileName, int _articleId) {
		this.data=_data;
		this.fileName = _fileName;
		this.articleId = _articleId;
	}
	

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int _imageId) {
		this.imageId = _imageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

}
