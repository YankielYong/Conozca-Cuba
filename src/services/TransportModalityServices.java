package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.TransportModalityDTO;

public class TransportModalityServices {

	public void insertTransportModality(String modalityType) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT transport_modality_insert(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, modalityType);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteTransportModality(int modalityCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT transport_modality_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateTransportModality(int modalityCode, String modalityType) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT transport_modality_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.setString(2, modalityType);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public TransportModalityDTO findTransportModality(int modalityCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM transport_modality WHERE transport_modality.modality_code = '"+modalityCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		TransportModalityDTO transportModality = new TransportModalityDTO(rs.getInt(1), rs.getString(2));
		rs.close();
		statement.close();
		connection.close();
		return transportModality;
	}
	
	public ArrayList<TransportModalityDTO> selectAllTransportModality() throws SQLException, ClassNotFoundException{
		ArrayList<TransportModalityDTO> transportModality = new ArrayList<TransportModalityDTO>();
		String function = "{?= call select_all_transport_modality()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			transportModality.add(new TransportModalityDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return transportModality;
	}
	
	public int getLastTransportModalityCode() throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT transport_modality.modality_code FROM transport_modality ORDER BY transport_modality.modality_code DESC"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		int code = rs.getInt(1);
		rs.close();
		statement.close();
		connection.close();
		return code;
	}
}
