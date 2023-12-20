package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.TouristPackageDTO;

public class TouristPackageServices {

	public void insertTouristPackage(String promotionalName, double packagePrice, double packageCost, 
			int numberOfPeople, int numberOfDays, int numberOfNights) 
					throws SQLException, ClassNotFoundException{
		String query = "SELECT tourist_package_insert(?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, promotionalName);
		preparedStatement.setDouble(2, packagePrice);
		preparedStatement.setDouble(3, packageCost);
		preparedStatement.setInt(4, numberOfPeople);
		preparedStatement.setInt(5, numberOfDays);
		preparedStatement.setInt(6, numberOfNights);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteTouristPackage(int packageCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT tourist_package_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, packageCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateTouristPackage(int packageCode, String promotionalName, double packagePrice, double packageCost, 
			int numberOfPeople, int numberOfDays, int numberOfNights) 
					throws SQLException, ClassNotFoundException{
		String query = "SELECT tourist_package_update(?,?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, packageCode);
		preparedStatement.setString(2, promotionalName);
		preparedStatement.setDouble(3, packagePrice);
		preparedStatement.setDouble(4, packageCost);
		preparedStatement.setInt(5, numberOfPeople);
		preparedStatement.setInt(6, numberOfDays);
		preparedStatement.setInt(7, numberOfNights);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public TouristPackageDTO findTouristPackage(int packageCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM tourist_package WHERE tourist_package.package_code = '"+packageCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		TouristPackageDTO touristPackage = new TouristPackageDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
		rs.close();
		statement.close();
		connection.close();
		return touristPackage;
	}
	
	public ArrayList<TouristPackageDTO> selectAllTouristPackages() throws SQLException, ClassNotFoundException{
		ArrayList<TouristPackageDTO> touristPackages = new ArrayList<TouristPackageDTO>();
		String function = "{?= call select_all_tourist_package()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			touristPackages.add(new TouristPackageDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return touristPackages;
	}
	
	public ArrayList<TouristPackageDTO> searchPackages(String nombre) throws SQLException, ClassNotFoundException{
		ArrayList<TouristPackageDTO> touristPackages = new ArrayList<TouristPackageDTO>();
		String function = "{?= call search_tourist_package(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setString(2, nombre);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			touristPackages.add(new TouristPackageDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return touristPackages;
	}
}
