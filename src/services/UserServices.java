package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServices {

	public void insertUser(String name, String nick, String password, int role) throws SQLException, ClassNotFoundException{
		String query = "SELECT user_insert(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, nick);
		preparedStatement.setString(3, password);
		preparedStatement.setInt(4, role);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteUser(int codigo) throws SQLException, ClassNotFoundException{
		String query = "SELECT user_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, codigo);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
}
