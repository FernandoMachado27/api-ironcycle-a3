package ironcycle.api.model.users;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
		@NotNull
		Long id,
		String name,
		String cpf,
		String birth,
		String phone,
		String email){

}
