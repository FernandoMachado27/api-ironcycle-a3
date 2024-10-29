package ironcycle.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ironcycle.api.model.DataDetailsMotorcycle;
import ironcycle.api.model.DataRegistrationMotorcycle;
import ironcycle.api.model.Motorcycle;
import ironcycle.api.model.MotorcycleRepository;
import jakarta.validation.Valid;

@RestController
public class MotorcycleController {
	
	@Autowired
	private MotorcycleRepository repository;

	public ResponseEntity register(@RequestBody @Valid DataRegistrationMotorcycle motorData, UriComponentsBuilder uriBuilder) {
		var motorcycle = new Motorcycle(motorData);
		repository.save(motorcycle);
		
		var uri = uriBuilder.path("/car/{id}").buildAndExpand(motorcycle.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DataDetailsMotorcycle(motorcycle));
	}

}