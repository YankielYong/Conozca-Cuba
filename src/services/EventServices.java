package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.EventDTO;

public class EventServices {

	public void insertEvent(int placeCode, int activityCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT event_insert(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, placeCode);
		preparedStatement.setInt(2, activityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteEvent(int eventCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT event_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, eventCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateEvent(int eventCode, int placeCode, int activityCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT event_update(?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, eventCode);
		preparedStatement.setInt(2, placeCode);
		preparedStatement.setInt(3, activityCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public EventDTO findEvent(int eventCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM event WHERE event.event_code = '"+eventCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		EventDTO event = new EventDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3));
		rs.close();
		statement.close();
		connection.close();
		return event;
	}
	
	public ArrayList<EventDTO> selectAllEvents() throws SQLException, ClassNotFoundException{
		ArrayList<EventDTO> events = new ArrayList<EventDTO>();
		String function = "{?= call select_all_event()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			events.add(new EventDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return events;
	}
	
	public ArrayList<EventDTO> searchEvents(String lugar) throws SQLException, ClassNotFoundException{
		ArrayList<EventDTO> events = new ArrayList<EventDTO>();
		String function = "{?= call search_event(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setString(2, lugar);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			events.add(new EventDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return events;
	}
}
