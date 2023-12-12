package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ContractTransportDTO;

public class ContractTransportServices {

	public void insertContractTransport(int contractCode, int transportCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_transport_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setInt(2, transportCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteContractTransport(int contractCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_transport_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateContractTransport(int contractCode, int transportCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT contract_transport_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, contractCode);
		preparedStatement.setInt(2, transportCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ContractTransportDTO findContractTransport(int contractCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM contract_transport WHERE contract_transport.contract_code = '"+contractCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		ContractTransportDTO contract = new ContractTransportDTO(rs.getInt(1), rs.getInt(2));
		rs.close();
		statement.close();
		connection.close();
		return contract;
	}
	
	public ArrayList<ContractTransportDTO> selectAllContractTransports() throws SQLException, ClassNotFoundException{
		ArrayList<ContractTransportDTO> contracts = new ArrayList<ContractTransportDTO>();
		String function = "{?= call select_all_contract_transport()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			contracts.add(new ContractTransportDTO(rs.getInt(1), rs.getInt(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return contracts;
	}
}
