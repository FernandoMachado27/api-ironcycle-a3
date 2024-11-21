package ironcycle.api.controller.users;

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

import ironcycle.api.model.users.DataDetailsUser;
import ironcycle.api.model.users.DataRegistrationUser;
import ironcycle.api.model.users.DataUpdateUser;
import ironcycle.api.model.users.User;
import ironcycle.api.model.users.UserRepository;
import ironcycle.api.model.users.validations.ValidationRegisterUser;
import ironcycle.api.model.users.list.UserStack;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private List<ValidationRegisterUser> validations;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	@Transactional
	public ResponseEntity register(@RequestBody @Valid DataRegistrationUser userData, UriComponentsBuilder uriBuilder) {
		var user = new User(userData);

		validations.forEach(v -> v.validate(userData));

		repository.save(user);

		var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new DataDetailsUser(user));
	}

	// Pilha
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping
	public ResponseEntity<DataDetailsUser[]> list() {
		UserStack userStack = new UserStack();
		repository.findAll().forEach(userStack::push);

		DataDetailsUser[] users = userStack.toArray();
		return ResponseEntity.ok(users);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping
	@Transactional
	public ResponseEntity update(@RequestBody @Valid DataUpdateUser dataUpdateUser) {
		var user = repository.getReferenceById(dataUpdateUser.id());
		user.updateUser(dataUpdateUser);

		return ResponseEntity.ok(new DataDetailsUser(user));
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id) {
		var user = repository.getReferenceById(id);
		repository.delete(user);
		return ResponseEntity.noContent().build();
	}

}
