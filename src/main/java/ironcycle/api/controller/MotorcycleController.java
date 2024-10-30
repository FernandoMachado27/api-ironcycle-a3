package ironcycle.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ironcycle.api.model.DataDetailsMotorcycle;
import ironcycle.api.model.DataRegistrationMotorcycle;
import ironcycle.api.model.Motorcycle;
import ironcycle.api.model.MotorcycleRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {
	
	@Autowired
	private MotorcycleRepository repository;

	public ResponseEntity register(@RequestBody @Valid DataRegistrationMotorcycle motorData, UriComponentsBuilder uriBuilder) {
		var motorcycle = new Motorcycle(motorData);
		repository.save(motorcycle);
		
		var uri = uriBuilder.path("/motorcycle/{id}").buildAndExpand(motorcycle.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DataDetailsMotorcycle(motorcycle));
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping
	public ResponseEntity<List<DataDetailsMotorcycle>> list(){
		var motorcycle = repository.findAll().stream().map(DataDetailsMotorcycle::new).toList();
		
		return ResponseEntity.ok(motorcycle);
	}

}