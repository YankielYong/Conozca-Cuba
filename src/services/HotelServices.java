package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.HotelDTO;

public class HotelServices {

	public void insertHotel(String hotelName,
			String hotelAddress,
			int hotelCategory,
			String hotelPhone,
			String hotelFax,
			String hotelMail, 
			String hotelLocation, 
			String hotelModality, 
			int numberOfRooms,
			int numberOfFloors, 
			double nearbyCityDistance, 
			double airportDistance, 
			int hotelChainCode, 
			int provinceCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT hotel_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, hotelName);
		preparedStatement.setString(2, hotelAddress);
		preparedStatement.setInt(3, hotelCategory);
		preparedStatement.setString(4, hotelPhone);
		preparedStatement.setString(5, hotelFax);
		preparedStatement.setString(6, hotelMail);
		preparedStatement.setString(7, hotelLocation);
		preparedStatement.setString(8, hotelModality);
		preparedStatement.setInt(9, numberOfRooms);
		preparedStatement.setInt(10, numberOfFloors);
		preparedStatement.setDouble(11, nearbyCityDistance);
		preparedStatement.setDouble(12, airportDistance);
		preparedStatement.setInt(13, hotelChainCode);
		preparedStatement.setInt(14, provinceCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}

	public void deleteHotel(int hotelCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT hotel_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, hotelCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}

	public void updateHotel(int hotelCode,
			String hotelName,
			String hotelAddress,
			int hotelCategory,
			String hotelPhone,
			String hotelFax,
			String hotelMail, 
			String hotelLocation, 
			String hotelModality, 
			int numberOfRooms,
			int numberOfFloors, 
			double nearbyCityDistance, 
			double airportDistance, 
			int hotelChainCode, 
			int provinceCode) 
					throws SQLException, ClassNotFoundException{
		String query = "SELECT hotel_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, hotelCode);
		preparedStatement.setString(2, hotelName);
		preparedStatement.setString(3, hotelAddress);
		preparedStatement.setInt(4, hotelCategory);
		preparedStatement.setString(5, hotelPhone);
		preparedStatement.setString(6, hotelFax);
		preparedStatement.setString(7, hotelMail);
		preparedStatement.setString(8, hotelLocation);
		preparedStatement.setString(9, hotelModality);
		preparedStatement.setInt(10, numberOfRooms);
		preparedStatement.setInt(11, numberOfFloors);
		preparedStatement.setDouble(12, nearbyCityDistance);
		preparedStatement.setDouble(13, airportDistance);
		preparedStatement.setInt(14, hotelChainCode);
		preparedStatement.setInt(15, provinceCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}

	public HotelDTO findHotel(int hotelCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM hotel WHERE hotel.hotel_code = '"+hotelCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		HotelDTO hotel = new HotelDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), 
				rs.getDouble(12), rs.getDouble(13), rs.getInt(14), rs.getInt(15));
		rs.close();
		statement.close();
		connection.close();
		return hotel;
	}

	public ArrayList<HotelDTO> selectAllHotels() throws SQLException, ClassNotFoundException{
		ArrayList<HotelDTO> hotels = new ArrayList<HotelDTO>();
		String function = "{?= call select_all_hotel()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			hotels.add(new HotelDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), 
					rs.getDouble(12), rs.getDouble(13), rs.getInt(14), rs.getInt(15)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return hotels;
	}
	
	public ArrayList<HotelDTO> searchHotels(String nombre, String provincia, String cadena) throws SQLException, ClassNotFoundException{
		ArrayList<HotelDTO> hotels = new ArrayList<HotelDTO>();
		String function = "{?= call search_hotel(?,?,?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setString(2, nombre);
		preparedFunction.setString(3, provincia);
		preparedFunction.setString(4, cadena);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			hotels.add(new HotelDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), 
					rs.getDouble(12), rs.getDouble(13), rs.getInt(14), rs.getInt(15)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return hotels;
	}
}
