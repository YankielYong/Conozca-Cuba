package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dto.ContractDTO;

public class ContractServices {

	public void insertContract(String contractDescription, Date contractStartDate, Date contractEndDate,
			Date contractConciliationDate, String contractType, int packageCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_insert(?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, contractDescription);
		preparedStatement.setTimestamp(2, new Timestamp(contractStartDate.getTime()));
		preparedStatement.setTimestamp(3, new Timestamp(contractEndDate.getTime()));
		preparedStatement.setTimestamp(4, new Timestamp(contractConciliationDate.getTime()));
		preparedStatement.setString(5, contractType);
		preparedStatement.setInt(6, packageCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteContract(int contractCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateContract(int contractCode, String contractDescription, Date contractStartDate,
			Date contractEndDate, Date contractConciliationDate, String contractType, int packageCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_update(?,?,?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setString(2, contractDescription);
		preparedStatement.setTimestamp(3, new Timestamp(contractStartDate.getTime()));
		preparedStatement.setTimestamp(4, new Timestamp(contractEndDate.getTime()));
		preparedStatement.setTimestamp(5, new Timestamp(contractConciliationDate.getTime()));
		preparedStatement.setString(6, contractType);
		preparedStatement.setInt(7, packageCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ContractDTO findContract(int contractCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM find_contract(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedFunction = connection.prepareStatement(query);
		preparedFunction.setInt(1, contractCode);
		preparedFunction.execute();
		ResultSet rs = preparedFunction.getResultSet();
		rs.next();
		ContractDTO contract = new ContractDTO(rs.getInt(1), rs.getString(2), new Date(rs.getTimestamp(3).getTime()),
				new Date(rs.getTimestamp(4).getTime()), new Date(rs.getTimestamp(5).getTime()), rs.getString(6), rs.getInt(7));
		rs.close();
		preparedFunction.close();
		connection.close();
		return contract;
	}
	
	public ArrayList<ContractDTO> selectAllContracts() throws SQLException, ClassNotFoundException{
		ArrayList<ContractDTO> contracts = new ArrayList<ContractDTO>();
		String function = "{?= call select_all_contract()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			contracts.add(new ContractDTO(rs.getInt(1), rs.getString(2), new Date(rs.getTimestamp(3).getTime()),
				new Date(rs.getTimestamp(4).getTime()), new Date(rs.getTimestamp(5).getTime()), rs.getString(6), rs.getInt(7)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return contracts;
	}
}
