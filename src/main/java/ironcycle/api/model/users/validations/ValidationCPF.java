package ironcycle.api.model.users.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ironcycle.api.model.exceptions.ValidationException;
import ironcycle.api.model.users.DataRegistrationUser;
import ironcycle.api.model.users.UserRepository;

@Component
public class ValidationCPF implements ValidationRegisterUser{
	
	@Autowired
	private UserRepository repository;
	@Override
	public void validate(DataRegistrationUser userData) {
		var cpfExisting = repository.existsByCpf(userData.cpf());
		
		if(cpfExisting) {
			throw new ValidationException("CPF já cadastrado");
		}
	}

}
