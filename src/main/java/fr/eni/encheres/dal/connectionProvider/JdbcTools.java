package fr.eni.encheres.dal.connectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcTools {
	
//TODO gestion des erreurs

	private static Connection cnx = null;
	

	public static Connection getConnection() {
		
		try {
			String urlDB = Settings.getProperty("urlDB");
			String userDB = Settings.getProperty("userDB");
			String passwordDB = Settings.getProperty("passwordDB");
		
			cnx = DriverManager.getConnection(urlDB, userDB, passwordDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnx;
	}

}

