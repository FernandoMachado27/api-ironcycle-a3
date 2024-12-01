package ironcycle.api.model.admin;

import jakarta.validation.constraints.NotBlank;

public record DataAuthentication(
	    @NotBlank
	    String login,  // Alterar para 'login' se preferir
	    @NotBlank
	    String senha  // Alterar para 'senha' se preferir
	) {}