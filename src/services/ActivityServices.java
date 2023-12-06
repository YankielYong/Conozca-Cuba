package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dto.ActivityDTO;

public class ActivityServices {

	public void insertActivity(Date activityDate, double activityPrice, String activityDescription) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT activity_insert(?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setTimestamp(1, new Timestamp(activityDate.getTime()));
		preparedStatement.setDouble(2, activityPrice);
		preparedStatement.setString(3, activityDescription);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteActivity(int activityCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT activity_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, activityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateActivity(int activityCode, Date activityDate, double activityPrice, String activityDescription) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT activity_update(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, activityCode);
		preparedStatement.setTimestamp(2, new Timestamp(activityDate.getTime()));
		preparedStatement.setDouble(3, activityPrice);
		preparedStatement.setString(4, activityDescription);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public ActivityDTO findActivity(int activityCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM activity WHERE activity.activity_code = '"+activityCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		ActivityDTO province = new ActivityDTO(rs.getInt(1), new Date(rs.getTimestamp(2).getTime()), rs.getDouble(3), rs.getString(4));
		rs.close();
		statement.close();
		connection.close();
		return province;
	}
	
	public ArrayList<ActivityDTO> selectAllActivity() throws SQLException, ClassNotFoundException{
		ArrayList<ActivityDTO> province = new ArrayList<ActivityDTO>();
		String function = "{?= call select_all_activity()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			province.add(new ActivityDTO(rs.getInt(1), new Date(rs.getTimestamp(2).getTime()), rs.getDouble(3), rs.getString(4)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return province;
	}
}
