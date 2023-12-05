package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.VehicleDTO;

public class VehicleServices {

	public void insertVehicle(String vehiclePlate, String vehicleBrand, int yearOfProduction, 
			int capacityWithoutLuggage, int capacityWithLuggage, int totalCapacity) 
					throws SQLException, ClassNotFoundException{
		String query = "SELECT vehicle_insert(?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, vehiclePlate);
		preparedStatement.setString(2, vehicleBrand);
		preparedStatement.setInt(3, yearOfProduction);
		preparedStatement.setInt(4, capacityWithoutLuggage);
		preparedStatement.setInt(5, capacityWithLuggage);
		preparedStatement.setInt(6, totalCapacity);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteVehicle(int vehicleCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT vehicle_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, vehicleCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateVehicle(int vehicleCode, String vehiclePlate, String vehicleBrand, int yearOfProduction, 
			int capacityWithoutLuggage, int capacityWithLuggage, int totalCapacity) 
					throws SQLException, ClassNotFoundException{
		String query = "SELECT vehicle_update(?,?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, vehicleCode);
		preparedStatement.setString(2, vehiclePlate);
		preparedStatement.setString(3, vehicleBrand);
		preparedStatement.setInt(4, yearOfProduction);
		preparedStatement.setInt(5, capacityWithoutLuggage);
		preparedStatement.setInt(6, capacityWithLuggage);
		preparedStatement.setInt(7, totalCapacity);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public VehicleDTO findVehicle(int vehicleCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM find_vehicle(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedFunction = connection.prepareStatement(query);
		preparedFunction.setInt(1, vehicleCode);
		preparedFunction.execute();
		ResultSet rs = preparedFunction.getResultSet();
		rs.next();
		VehicleDTO vehicle = new VehicleDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
		rs.close();
		preparedFunction.close();
		connection.close();
		return vehicle;
	}
	
	public ArrayList<VehicleDTO> selectAllVehicles() throws SQLException, ClassNotFoundException{
		ArrayList<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
		String function = "{?= call select_all_vehicle()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			vehicles.add(new VehicleDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return vehicles;
	}
}
