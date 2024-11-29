package ironcycle.api.model.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
		@NotBlank String login, 
	    @NotBlank String password, 
	    @NotNull AdminRole role) {

}
