package fr.eni.encheres.dal;

public class DALException extends Exception {
    private static final long serialVersionUID = 1L;
	private String errorCode;

    public DALException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
