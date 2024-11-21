package ironcycle.api.model.users;

import jakarta.validation.constraints.NotBlank;

public record DataRegistrationUser(
		@NotBlank(message = "Nome é obrigatório")
		String name,
		@NotBlank(message = "CPF é obrigatório")
		String cpf,
		@NotBlank(message = "Data de nascimento é obrigatório")
		String birth,
		@NotBlank(message = "Telefone é obrigatório")
		String phone,
		@NotBlank(message = "E-mail é obrigatório")
        String email) {

}
