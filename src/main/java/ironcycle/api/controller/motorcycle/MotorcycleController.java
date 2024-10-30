package ironcycle.api.controller.motorcycle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ironcycle.api.model.motorcycle.DataDetailsMotorcycle;
import ironcycle.api.model.motorcycle.DataRegistrationMotorcycle;
import ironcycle.api.model.motorcycle.DataUpdateMotorcycle;
import ironcycle.api.model.motorcycle.Motorcycle;
import ironcycle.api.model.motorcycle.MotorcycleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {
	
	@Autowired
	private MotorcycleRepository repository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	@Transactional
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
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping
	@Transactional
	public ResponseEntity update(@RequestBody @Valid DataUpdateMotorcycle dataUpdateMotorcycle) {
		var motorcycle = repository.getReferenceById(dataUpdateMotorcycle.id());
		motorcycle.updateMotorcycle(dataUpdateMotorcycle);
		
		return ResponseEntity.ok(new DataDetailsMotorcycle(motorcycle));
	}

}