package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.CostPerEstablishedToursDTO;

public class CostPerEstablishedToursServices {

	public void insertCostPerEstablishedTours(int modalityCode, String tourDescription, double costPerTour, double costPerTourRoundTrip) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_established_tours_insert(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,  modalityCode);
		preparedStatement.setString(2, tourDescription);
		preparedStatement.setDouble(3, costPerTour);
		preparedStatement.setDouble(4, costPerTourRoundTrip);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteCostPerEstablishedTours(int modalityCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_established_tours_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateCostPerEstablishedTours(int modalityCode, String tourDescription, double costPerTour, double costPerTourRoundTrip) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_established_tours_update(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,  modalityCode);
		preparedStatement.setString(2, tourDescription);
		preparedStatement.setDouble(3, costPerTour);
		preparedStatement.setDouble(4, costPerTourRoundTrip);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public CostPerEstablishedToursDTO findCostPerEstablishedTours(int modalityCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM cost_per_established_tours WHERE cost_per_established_tours.modality_code = '"+modalityCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		CostPerEstablishedToursDTO transportModality = new CostPerEstablishedToursDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
		rs.close();
		statement.close();
		connection.close();
		return transportModality;
	}
	
	public ArrayList<CostPerEstablishedToursDTO> selectAllCostPerEstablishedTours() throws SQLException, ClassNotFoundException{
		ArrayList<CostPerEstablishedToursDTO> transportModality = new ArrayList<CostPerEstablishedToursDTO>();
		String function = "{?= call select_all_cost_per_established_tours()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			transportModality.add(new CostPerEstablishedToursDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return transportModality;
	}
}
