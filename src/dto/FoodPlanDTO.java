package dto;

public class FoodPlanDTO {

	private int foodPlanCode;
	private String typeOfFoodPlan;
	
	public FoodPlanDTO(int foodPlanCode, String typeOfFoodPlan) {
		this.foodPlanCode = foodPlanCode;
		this.typeOfFoodPlan = typeOfFoodPlan;
	}

	public int getFoodPlanCode() {
		return foodPlanCode;
	}

	public void setFoodPlanCode(int foodPlanCode) {
		this.foodPlanCode = foodPlanCode;
	}

	public String getTypeOfFoodPlan() {
		return typeOfFoodPlan;
	}

	public void setTypeOfFoodPlan(String typeOfFoodPlan) {
		this.typeOfFoodPlan = typeOfFoodPlan;
	}
}
