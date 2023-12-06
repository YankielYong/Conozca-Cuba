package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.HotelChainDTO;

public class HotelChainServices {
	
	public void insertHotelChain(String hotelChainName) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT hotel_chain_insert(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, hotelChainName);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteHotelChain(int hotelChainCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT hotel_chain_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, hotelChainCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateHotelChain(int hotelChainCode, String hotelChainName) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT hotel_chain_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, hotelChainCode);
		preparedStatement.setString(2, hotelChainName);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public HotelChainDTO findHotelChain(int hotelChainCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM hotel_chain WHERE hotel_chain.hotel_chain_code = '"+hotelChainCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		HotelChainDTO hotelChain = new HotelChainDTO(rs.getInt(1), rs.getString(2));
		rs.close();
		statement.close();
		connection.close();
		return hotelChain;
	}
	
	public ArrayList<HotelChainDTO> selectAllHotelChains() throws SQLException, ClassNotFoundException{
		ArrayList<HotelChainDTO> hotelChains = new ArrayList<HotelChainDTO>();
		String function = "{?= call select_all_hotel_chain()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			hotelChains.add(new HotelChainDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return hotelChains;
	}

}

