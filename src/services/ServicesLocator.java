package services;

import java.sql.SQLException;

import utils.Connection;

public class ServicesLocator {
	
	private static ActivityServices activityServices = null;
	private static ContractEventServices contractEventServices = null;
	private static ContractLodgingServices contractLodgingServices = null;
	private static ContractServices contractServices = null;
	private static ContractTransportServices contractTransportServices = null;
	private static CostPerEstablishedToursServices costPerEstablishedToursServices = null;
	private static CostPerHourKilometerServices costPerHourKilometerServices = null;
	private static CostPerKilometerServices costPerKilometerServices = null;
	private static EventServices eventServices = null;
	private static FoodPlanServices foodPlanServices = null;
	private static HotelChainServices hotelChainServices = null;
	private static HotelServices hotelServices = null;
	private static LodgingServices lodgingServices = null;
	private static PlaceServices placeServices = null;
	private static ProvincePlaceServices provincePlaceServices = null;
	private static ProvinceServices provinceServices = null;
	private static RoleServices roleServices = null;
	private static RoomServices roomServices = null;
	private static SeasonServices seasonServices = null;
	private static TouristPackageServices touristPackageServices = null;
	private static TransportModalityServices transportModalityServices = null;
	private static TransportServices transportServices = null;
	private static UserServices userServices = null;
	private static VehicleServices vehicleServices = null;
	
	public static ActivityServices getActivityServices() {
		if(activityServices == null)
			activityServices = new ActivityServices();
		return activityServices;
	}

	public static ContractEventServices getContractEventServices() {
		if(contractEventServices == null)
			contractEventServices = new ContractEventServices();
		return contractEventServices;
	}

	public static ContractLodgingServices getContractLodgingServices() {
		if(contractLodgingServices == null)
			contractLodgingServices = new ContractLodgingServices();
		return contractLodgingServices;
	}

	public static ContractServices getContractServices() {
		if(contractServices == null)
			contractServices = new ContractServices();
		return contractServices;
	}

	public static ContractTransportServices getContractTransportServices() {
		if(contractTransportServices == null)
			contractTransportServices = new ContractTransportServices();
		return contractTransportServices;
	}

	public static CostPerEstablishedToursServices getCostPerEstablishedToursServices() {
		if(costPerEstablishedToursServices == null)
			costPerEstablishedToursServices = new CostPerEstablishedToursServices();
		return costPerEstablishedToursServices;
	}

	public static CostPerHourKilometerServices getCostPerHourKilometerServices() {
		if(costPerHourKilometerServices == null)
			costPerHourKilometerServices = new CostPerHourKilometerServices();
		return costPerHourKilometerServices;
	}

	public static CostPerKilometerServices getCostPerKilometerServices() {
		if(costPerKilometerServices == null)
			costPerKilometerServices = new CostPerKilometerServices();
		return costPerKilometerServices;
	}

	public static EventServices getEventServices() {
		if(eventServices == null)
			eventServices = new EventServices();
		return eventServices;
	}

	public static FoodPlanServices getFoodPlanServices() {
		if(foodPlanServices == null)
			foodPlanServices = new FoodPlanServices();
		return foodPlanServices;
	}

	public static HotelChainServices getHotelChainServices() {
		if(hotelChainServices == null)
			hotelChainServices = new HotelChainServices();
		return hotelChainServices;
	}

	public static HotelServices getHotelServices() {
		if(hotelServices == null)
			hotelServices = new HotelServices();
		return hotelServices;
	}

	public static LodgingServices getLodgingServices() {
		if(lodgingServices == null)
			lodgingServices = new LodgingServices();
		return lodgingServices;
	}

	public static PlaceServices getPlaceServices() {
		if(placeServices == null)
			placeServices = new PlaceServices();
		return placeServices;
	}

	public static ProvincePlaceServices getProvincePlaceServices() {
		if(provincePlaceServices == null)
			provincePlaceServices = new ProvincePlaceServices();
		return provincePlaceServices;
	}

	public static ProvinceServices getProvinceServices() {
		if(provinceServices == null)
			provinceServices = new ProvinceServices();
		return provinceServices;
	}

	public static RoleServices getRoleServices() {
		if(roleServices == null)
			roleServices = new RoleServices();
		return roleServices;
	}

	public static RoomServices getRoomServices() {
		if(roomServices == null)
			roomServices = new RoomServices();
		return roomServices;
	}

	public static SeasonServices getSeasonServices() {
		if(seasonServices == null)
			seasonServices = new SeasonServices();
		return seasonServices;
	}

	public static TouristPackageServices getTouristPackageServices() {
		if(touristPackageServices == null)
			touristPackageServices = new TouristPackageServices();
		return touristPackageServices;
	}

	public static TransportModalityServices getTransportModalityServices() {
		if(transportModalityServices == null)
			transportModalityServices = new TransportModalityServices();
		return transportModalityServices;
	}

	public static TransportServices getTransportServices() {
		if(transportServices == null)
			transportServices = new TransportServices();
		return transportServices;
	}

	public static UserServices getUserServices(){
		if(userServices == null)
			userServices = new UserServices();
		return userServices;
	}

	public static VehicleServices getVehicleServices() {
		if(vehicleServices == null)
			vehicleServices = new VehicleServices();
		return vehicleServices;
	}

	public static java.sql.Connection getConnection(){
		Connection connection = null;
		try {
			connection = new Connection("localhost", "conozca_cuba", "postgres", "yankiel2002");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection.getConnection();
	}
}
