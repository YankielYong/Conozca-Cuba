package services;

import java.sql.SQLException;

import utils.Connection;

public class ServicesLocator {
	
	private static UserServices userServices;
	
	public static UserServices getUserServices(){
		if(userServices == null)
			userServices = new UserServices();
		return userServices;
	}

	public static java.sql.Connection getConnection(){
		Connection connection = null;
		try {
			connection = new Connection("localhost", "conozca_cuba", "postgres", "aqui va su contraseña");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection.getConnection();
	}
}
