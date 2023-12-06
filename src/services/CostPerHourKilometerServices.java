package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.CostPerHourKilometerDTO;

public class CostPerHourKilometerServices {

	public void insertCostPerHourKilometer(int modalityCode, double costPerKmTraveled, double costPerHour, double costForExtraKm, double costForExtraHours) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_hour_kilometer_insert(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.setDouble(2, costPerKmTraveled);
		preparedStatement.setDouble(3, costPerHour);
		preparedStatement.setDouble(4, costForExtraKm);
		preparedStatement.setDouble(5, costForExtraHours);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteCostPerHourKilometer(int modalityCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_hour_kilometer_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateCostPerHourKilometer(int modalityCode, double costPerKmTraveled, double costPerHour, double costForExtraKm, double costForExtraHours) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT cost_per_hour_kilometer_update(?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, modalityCode);
		preparedStatement.setDouble(2, costPerKmTraveled);
		preparedStatement.setDouble(3, costPerHour);
		preparedStatement.setDouble(4, costForExtraKm);
		preparedStatement.setDouble(5, costForExtraHours);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public CostPerHourKilometerDTO findCostPerHourKilometer(int modalityCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM cost_per_hour_kilometer WHERE cost_per_hour_kilometer.modality_code = '"+modalityCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		CostPerHourKilometerDTO transportModality = new CostPerHourKilometerDTO(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5));
		rs.close();
		statement.close();
		connection.close();
		return transportModality;
	}
	
	public ArrayList<CostPerHourKilometerDTO> selectAllCostPerHourKilometer() throws SQLException, ClassNotFoundException{
		ArrayList<CostPerHourKilometerDTO> transportModality = new ArrayList<CostPerHourKilometerDTO>();
		String function = "{?= call select_all_cost_per_hour_kilometer()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			transportModality.add(new CostPerHourKilometerDTO(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return transportModality;
	}
}
