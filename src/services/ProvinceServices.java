package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ProvinceDTO;
import dto.UserDTO;

public class ProvinceServices {

	public void insertProvince(String proviceName) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT province_insert(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, proviceName);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteProvince(int provinceCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT province_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, provinceCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateProvince(int provinceCode, String proviceName) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT province_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, provinceCode);
		preparedStatement.setString(2, proviceName);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ProvinceDTO findProvince(int provinceCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM province WHERE province.province_code = '"+provinceCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		ProvinceDTO province = new ProvinceDTO(rs.getInt(1), rs.getString(2));
		rs.close();
		statement.close();
		connection.close();
		return province;
	}
	
	public ArrayList<ProvinceDTO> selectAllProvinces() throws SQLException, ClassNotFoundException{
		ArrayList<ProvinceDTO> province = new ArrayList<ProvinceDTO>();
		String function = "{?= call select_all_province_()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			province.add(new ProvinceDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return province;
	}
	
}
