package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.LodgingDTO;

public class LodgingServices {

	public void insertLodging(int hotelCode, int seasonCode, int roomCode, double lodgingPrice) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT lodging_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, hotelCode);
		preparedStatement.setInt(2, seasonCode);
		preparedStatement.setInt(3, roomCode);
		preparedStatement.setDouble(4, lodgingPrice);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteLodging(int lodgingCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT lodging_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, lodgingCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateLodging(int lodgingCode, int hotelCode, int seasonCode, int roomCode, double lodgingPrice) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT lodging_update(?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, lodgingCode);
		preparedStatement.setInt(2, hotelCode);
		preparedStatement.setInt(3, seasonCode);
		preparedStatement.setInt(4, roomCode);
		preparedStatement.setDouble(5, lodgingPrice);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public LodgingDTO findLodging(int lodgingCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM lodging WHERE lodging.lodging_code = '"+lodgingCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		LodgingDTO lodging = new LodgingDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));
		rs.close();
		statement.close();
		connection.close();
		return lodging;
	}
	
	public ArrayList<LodgingDTO> selectAllLodgings() throws SQLException, ClassNotFoundException{
		ArrayList<LodgingDTO> lodgings = new ArrayList<LodgingDTO>();
		String function = "{?= call select_all_lodging()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			lodgings.add(new LodgingDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return lodgings;
	}
}
