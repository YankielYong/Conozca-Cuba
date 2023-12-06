package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.PlaceDTO;

public class PlaceServices {
	
	public void insertPlace(String placeName) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT place_insert(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, placeName);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deletePlace(int placeCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT place_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, placeCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updatePlace(int placeCode, String placeName) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT place_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, placeCode);
		preparedStatement.setString(2, placeName);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public PlaceDTO findPlace(int placeCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM place WHERE place.place_code = '"+placeCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		PlaceDTO place = new PlaceDTO(rs.getInt(1), rs.getString(2));
		rs.close();
		statement.close();
		connection.close();
		return place;
	}
	
	public ArrayList<PlaceDTO> selectAllPlaces() throws SQLException, ClassNotFoundException{
		ArrayList<PlaceDTO> places = new ArrayList<PlaceDTO>();
		String function = "{?= call select_all_place()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			places.add(new PlaceDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return places;
	}

}
