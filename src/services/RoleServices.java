package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.RoleDTO;

public class RoleServices {

	public void insertRole(String roleName, String roleDescription) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT role_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, roleName);
		preparedStatement.setString(2, roleDescription);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteRole(int roleCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT role_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roleCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateRole(int roleCode, String roleName, String roleDescription) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT user__update(?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roleCode);
		preparedStatement.setString(2, roleName);
		preparedStatement.setString(3, roleDescription);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public RoleDTO findRole(int roleCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM find_role(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedFunction = connection.prepareStatement(query);
		preparedFunction.setInt(1, roleCode);
		preparedFunction.execute();
		ResultSet rs = preparedFunction.getResultSet();
		rs.next();
		RoleDTO role = new RoleDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
		rs.close();
		preparedFunction.close();
		connection.close();
		return role;
	}
	
	public ArrayList<RoleDTO> selectAllRoles() throws SQLException, ClassNotFoundException{
		ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
		String function = "{?= call select_all_role()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			roles.add(new RoleDTO(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return roles;
	}
}
