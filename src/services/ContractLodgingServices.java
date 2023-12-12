package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ContractLodgingDTO;

public class ContractLodgingServices {

	public void insertContractLodging(int contractCode, int lodgingCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_lodging_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setInt(2, lodgingCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteContractLodging(int contractCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_lodging_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateContractLodging(int contractCode, int lodgingCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_lodging_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setInt(2, lodgingCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ContractLodgingDTO findContractLodging(int contractCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM contract_lodging WHERE contract_lodging.contract_code = '"+contractCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		ContractLodgingDTO contract = new ContractLodgingDTO(rs.getInt(1), rs.getInt(2));
		rs.close();
		statement.close();
		connection.close();
		return contract;
	}
	
	public ArrayList<ContractLodgingDTO> selectAllContractLodging() throws SQLException, ClassNotFoundException{
		ArrayList<ContractLodgingDTO> contracts = new ArrayList<ContractLodgingDTO>();
		String function = "{?= call select_all_contract_lodging()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			contracts.add(new ContractLodgingDTO(rs.getInt(1), rs.getInt(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return contracts;
	}
}
