package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.TransportDTO;

public class TransportServices {

	public void insertTransport(int vehicleCode, int modalityCode, String transportBorrower) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT transport_insert(?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, vehicleCode);
		preparedStatement.setInt(2, modalityCode);
		preparedStatement.setString(3, transportBorrower);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteTransport(int transportCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT transport_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, transportCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateTransport(int transportCode, int vehicleCode, int modalityCode, String transportBorrower) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT transport_update(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, transportCode);
		preparedStatement.setInt(2, vehicleCode);
		preparedStatement.setInt(3, modalityCode);
		preparedStatement.setString(4, transportBorrower);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public TransportDTO findTransport(int transportCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM transport WHERE transport.transport_code = '"+transportCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		TransportDTO role = new TransportDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
		rs.close();
		statement.close();
		connection.close();
		return role;
	}
	
	public ArrayList<TransportDTO> selectAllTransports() throws SQLException, ClassNotFoundException{
		ArrayList<TransportDTO> roles = new ArrayList<TransportDTO>();
		String function = "{?= call select_all_transport()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			roles.add(new TransportDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return roles;
	}
}
