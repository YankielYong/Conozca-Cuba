package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ContractEventDTO;

public class ContractEventServices {

	public void insertContractEvent(int contractCode, int eventCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_event_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setInt(2, eventCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteContractEvent(int contractCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_event_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateContractEvent(int contractCode, int eventCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_event_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setInt(2, eventCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ContractEventDTO findContractEvent(int contractCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM contract_event WHERE contract_event.contract_code = '"+contractCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		ContractEventDTO contract = new ContractEventDTO(rs.getInt(1), rs.getInt(2));
		rs.close();
		statement.close();
		connection.close();
		return contract;
	}
	
	public ArrayList<ContractEventDTO> selectAllContractEvents() throws SQLException, ClassNotFoundException{
		ArrayList<ContractEventDTO> contracts = new ArrayList<ContractEventDTO>();
		String function = "{?= call select_all_contract_event()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			contracts.add(new ContractEventDTO(rs.getInt(1), rs.getInt(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return contracts;
	}
}
