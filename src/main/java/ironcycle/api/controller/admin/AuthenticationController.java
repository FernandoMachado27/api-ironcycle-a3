package ironcycle.api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ironcycle.api.model.admin.Admin;
import ironcycle.api.model.admin.AdminRepository;
import ironcycle.api.model.admin.DataAuthentication;
import ironcycle.api.model.admin.RegisterDTO;
import ironcycle.api.security.DataTokenJWT;
import ironcycle.api.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AdminRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid DataAuthentication dataAuthentication) {
	    System.out.println("Username: " + dataAuthentication.login());  // Log do username
	    System.out.println("Password: " + dataAuthentication.senha());  // Log do password

	    try {
	        var usernamePassword = new UsernamePasswordAuthenticationToken(dataAuthentication.login(), dataAuthentication.senha());
	        var authentication = manager.authenticate(usernamePassword);
	        
	        var tokenJWT = tokenService.generateToken((Admin) authentication.getPrincipal());
	        
	        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
	        
	    } catch (UsernameNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found: " + e.getMessage());
	    } catch (BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error");
	    }
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
	    if (data.password() == null || data.password().isBlank()) {
	        return ResponseEntity.badRequest().body("Password must not be null or blank");
	    }

	    if (this.repository.findByUsername(data.login()) != null) {
	        return ResponseEntity.badRequest().body("User already exists");
	    }

	    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
	    Admin newAdmin = new Admin(data.login(), encryptedPassword, data.role());

	    this.repository.save(newAdmin);
	    return ResponseEntity.ok("User registered successfully");
	}

}