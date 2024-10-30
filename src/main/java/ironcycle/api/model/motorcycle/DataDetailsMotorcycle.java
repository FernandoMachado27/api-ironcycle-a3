package ironcycle.api.model.motorcycle;

public record DataDetailsMotorcycle (Long id, String name, String brand, String model, String year){
	
	public DataDetailsMotorcycle(Motorcycle motorcycle) {
		this(motorcycle.getId(), motorcycle.getName(), motorcycle.getBrand(), motorcycle.getModel(), motorcycle.getYear());
	}

}
