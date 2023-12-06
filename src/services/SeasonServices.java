package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dto.SeasonDTO;

public class SeasonServices {

	public void insertSeason(String seasonName, String seasonDescription, Date seasonStartDate, Date seasonEndDate) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT season_insert(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, seasonName);
		preparedStatement.setString(2, seasonDescription);
		preparedStatement.setTimestamp(3, new Timestamp(seasonStartDate.getTime()));
		preparedStatement.setTimestamp(4, new Timestamp(seasonEndDate.getTime()));
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteSeason(int seasonCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT season_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, seasonCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateSeason(int seasonCode, String seasonName, String seasonDescription, Date seasonStartDate, Date seasonEndDate) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT season_update(?,?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, seasonCode);
		preparedStatement.setString(2, seasonName);
		preparedStatement.setString(3, seasonDescription);
		preparedStatement.setTimestamp(4, new Timestamp(seasonStartDate.getTime()));
		preparedStatement.setTimestamp(5, new Timestamp(seasonEndDate.getTime()));
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public SeasonDTO findSeason(int seasonCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM season WHERE season.season_code = '"+seasonCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		SeasonDTO lodging = new SeasonDTO(rs.getInt(1), rs.getString(2), rs.getString(3), new Date(rs.getTimestamp(4).getTime()), new Date(rs.getTimestamp(5).getTime()));
		rs.close();
		statement.close();
		connection.close();
		return lodging;
	}
	
	public ArrayList<SeasonDTO> selectAllSeasons() throws SQLException, ClassNotFoundException{
		ArrayList<SeasonDTO> lodgings = new ArrayList<SeasonDTO>();
		String function = "{?= call select_all_season()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			lodgings.add(new SeasonDTO(rs.getInt(1), rs.getString(2), rs.getString(3), new Date(rs.getTimestamp(4).getTime()), new Date(rs.getTimestamp(5).getTime())));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return lodgings;
	}
}
