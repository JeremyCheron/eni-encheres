package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
	private static DataSource datasource;
	
	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.datasource =(DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossile d'accéder à la base de données");
		}
	}
		public static Connection getConnection() throws SQLException{
			return ConnectionProvider.datasource.getConnection();
		}
}
