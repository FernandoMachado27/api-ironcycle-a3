package ironcycle.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataRegistrationMotorcycle(
		@NotBlank(message = "Nome é obrigatório")
		String name,
		@NotBlank(message = "Marca é obrigatório")
		String brand,
		@NotBlank(message = "Modelo é obrigatório")
		String model,
		@NotBlank(message = "Ano é obrigatório")
		@Pattern(regexp = "\\d{4}", message = "Formato do ano é inválido")
		String year) {

}
