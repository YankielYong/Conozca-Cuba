package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.CostPerKilometerDTO;

public class CostPerKilometerServices {

	public void insertCostPerKilometer(int modalityCode, double costPerKm, double costPerKmRoundTrip, double costPerWaitingHours) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_kilometer_insert(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.setDouble(2, costPerKm);
		preparedStatement.setDouble(3, costPerKmRoundTrip);
		preparedStatement.setDouble(4, costPerWaitingHours);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteCostPerKilometer(int modalityCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_kilometer_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateCostPerKilometer(int modalityCode, double costPerKm, double costPerKmRoundTrip, double costPerWaitingHours) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_kilometer_update(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.setDouble(2, costPerKm);
		preparedStatement.setDouble(3, costPerKmRoundTrip);
		preparedStatement.setDouble(4, costPerWaitingHours);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public CostPerKilometerDTO findCostPerKilometer(int modalityCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM cost_per_kilometer WHERE cost_per_kilometer.modality_code = '"+modalityCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		CostPerKilometerDTO transportModality = new CostPerKilometerDTO(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4));
		rs.close();
		statement.close();
		connection.close();
		return transportModality;
	}
	
	public ArrayList<CostPerKilometerDTO> selectAllCostPerKilometer() throws SQLException, ClassNotFoundException{
		ArrayList<CostPerKilometerDTO> transportModality = new ArrayList<CostPerKilometerDTO>();
		String function = "{?= call select_all_cost_per_kilometer()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			transportModality.add(new CostPerKilometerDTO(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return transportModality;
	}
}
