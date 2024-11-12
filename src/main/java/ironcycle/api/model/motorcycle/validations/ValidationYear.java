package ironcycle.api.model.motorcycle.validations;

import org.springframework.stereotype.Component;

import ironcycle.api.model.exceptions.ValidacaoException;
import ironcycle.api.model.motorcycle.DataRegistrationMotorcycle;

@Component
public class ValidationYear implements ValidationRegisterMotorcycle{
	
	public void validate(DataRegistrationMotorcycle motorData) {
		var yearString = motorData.year();
		int year = Integer.parseInt(yearString);
		
		int yearLimit = 2000;
		
		if(year < yearLimit) {
			throw new ValidacaoException("Permitido cadastrar apenas motos fabricadas a partir de 2000.");
		}
	}

}
