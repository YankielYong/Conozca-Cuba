package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.RoomDTO;

public class RoomServices {
	
	public void insertRoom(String roomType, double surchargeRoom, int foodPlanCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT room_insert(?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, roomType);
		preparedStatement.setDouble(2, surchargeRoom);
		preparedStatement.setInt(3, foodPlanCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteRoom(int roomCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT room_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateRoom(int roomCode, String roomType, double surchargeRoom, int foodPlanCode) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT room_update(?,?,?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomCode);
		preparedStatement.setString(2, roomType);
		preparedStatement.setDouble(3, surchargeRoom);
		preparedStatement.setInt(4, foodPlanCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public RoomDTO findRoom(int roomCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM room WHERE room.room_code = '"+roomCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		RoomDTO room = new RoomDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
		rs.close();
		statement.close();
		connection.close();
		return room;
	}
	
	public ArrayList<RoomDTO> selectAllRooms() throws SQLException, ClassNotFoundException{
		ArrayList<RoomDTO> rooms = new ArrayList<RoomDTO>();
		String function = "{?= call select_all_room()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			rooms.add(new RoomDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return rooms;
	}

}
