package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ProvincePlaceDTO;
import dto.UserDTO;

public class ProvincePlaceServices {
	
	public void insertProvincePlace(int provinceCode, int placeCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT province_place_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, provinceCode);
		preparedStatement.setInt(2, placeCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteProvincePlace(int provincePlaceCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT province_place_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, provincePlaceCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateProvincePlace(int provincePlaceCode, int provinceCode, int placeCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT province_place_update(?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, provincePlaceCode);
		preparedStatement.setInt(2, provinceCode);
		preparedStatement.setInt(3, placeCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ProvincePlaceDTO findProvincePlace(int provincePlaceCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM province_place WHERE province_place.province_place_code = '"+provincePlaceCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		ProvincePlaceDTO provincePlace = new ProvincePlaceDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3));
		rs.close();
		statement.close();
		connection.close();
		return provincePlace;
	}
	
	public ArrayList<ProvincePlaceDTO> selectAllProvincePlaces() throws SQLException, ClassNotFoundException{
		ArrayList<ProvincePlaceDTO> provincePlaces = new ArrayList<ProvincePlaceDTO>();
		String function = "{?= call select_all_province_place()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			provincePlaces.add(new ProvincePlaceDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return provincePlaces;
	}

}
