package ironcycle.api.model.motorcycle.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ironcycle.api.model.exceptions.ValidationException;
import ironcycle.api.model.motorcycle.DataRegistrationMotorcycle;
import ironcycle.api.model.motorcycle.MotorcycleRepository;

@Component
public class ValidationMotorAlreadyRegistered implements ValidationRegisterMotorcycle{
	
	@Autowired
	private MotorcycleRepository repository;
	@Override
	public void validate(DataRegistrationMotorcycle motorData) {
		var plateExisting = repository.existsByPlate(motorData.plate());
		
		if(plateExisting) {
			throw new ValidationException("Moto com mesma placa já cadastrado");
		}
	}

}
