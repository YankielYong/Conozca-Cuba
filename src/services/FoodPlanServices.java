package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.FoodPlanDTO;

public class FoodPlanServices {
	
	public void insertFoodPlan(String typeOfFoodPlan) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT food_plan_insert(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, typeOfFoodPlan);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteFoodPlan(int foodPlanCode) throws SQLException, ClassNotFoundException{
		String query = "SELECT food_plan_delete(?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, foodPlanCode);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public void updateFoodPlan(int foodPlanCode, String typeOfFoodPlan) 
			throws SQLException, ClassNotFoundException{
		String query = "SELECT food_plan_update(?,?)";
		java.sql.Connection connection = ServicesLocator.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, foodPlanCode);
		preparedStatement.setString(2, typeOfFoodPlan);
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public FoodPlanDTO findFoodPlan(int foodPlanCode) throws SQLException, ClassNotFoundException{
		java.sql.Connection connection = ServicesLocator.getConnection();
		Statement statement = connection.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		String query = "SELECT * FROM food_plan WHERE food_plan.food_plan_code = '"+foodPlanCode+"'"; 
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		FoodPlanDTO foodPlan = new FoodPlanDTO(rs.getInt(1), rs.getString(2));
		rs.close();
		statement.close();
		connection.close();
		return foodPlan;
	}
	
	public ArrayList<FoodPlanDTO> selectAllFoddPlans() throws SQLException, ClassNotFoundException{
		ArrayList<FoodPlanDTO> foodPlans = new ArrayList<FoodPlanDTO>();
		String function = "{?= call select_all_food_plan()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet rs = (ResultSet) preparedFunction.getObject(1);
		while (rs.next()){
			foodPlans.add(new FoodPlanDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		preparedFunction.close();
		connection.close();
		return foodPlans;
	}

}