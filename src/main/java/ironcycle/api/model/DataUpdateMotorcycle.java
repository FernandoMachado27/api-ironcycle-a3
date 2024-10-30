package ironcycle.api.model;

import jakarta.validation.constraints.NotNull;

public record DataUpdateMotorcycle(
		@NotNull
		Long id,
		String name,
		String brand,
		String model,
		String year){

}
