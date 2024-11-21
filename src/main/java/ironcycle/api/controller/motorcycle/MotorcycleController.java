package ironcycle.api.controller.motorcycle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import ironcycle.api.model.motorcycle.list.MotorcycleLinkedList;
import ironcycle.api.model.motorcycle.validations.ValidationRegisterMotorcycle;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {
	
	@Autowired
	private MotorcycleRepository repository;
	
	@Autowired
	private List<ValidationRegisterMotorcycle> validations;


	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	@Transactional
	public ResponseEntity register(@RequestBody @Valid DataRegistrationMotorcycle motorData, UriComponentsBuilder uriBuilder) {
		var motorcycle = new Motorcycle(motorData);
		
		validations.forEach(v -> v.validate(motorData));

		repository.save(motorcycle);
		
		var uri = uriBuilder.path("/motorcycle/{id}").buildAndExpand(motorcycle.getId()).toUri();
		return ResponseEntity.created(uri).body(new DataDetailsMotorcycle(motorcycle));
	}
	
    //lista encadeada
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<DataDetailsMotorcycle[]> list() {
        // Cria uma nova lista encadeada e a preenche com dados do banco
        MotorcycleLinkedList motorcycleList = new MotorcycleLinkedList();
        repository.findAll().forEach(motorcycleList::add);

        // Converte a lista encadeada em array para a resposta
        DataDetailsMotorcycle[] motorcycles = motorcycleList.toArray();
        return ResponseEntity.ok(motorcycles);
    }
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping
	@Transactional
	public ResponseEntity update(@RequestBody @Valid DataUpdateMotorcycle dataUpdateMotorcycle) {
		var motorcycle = repository.getReferenceById(dataUpdateMotorcycle.id());
		motorcycle.updateMotorcycle(dataUpdateMotorcycle);
		
		return ResponseEntity.ok(new DataDetailsMotorcycle(motorcycle));
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id) {
		var motorcycle = repository.getReferenceById(id);
		repository.delete(motorcycle);
		return ResponseEntity.noContent().build();
	}

}