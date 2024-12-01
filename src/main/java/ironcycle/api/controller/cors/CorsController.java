package ironcycle.api.controller.cors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorsController {

	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<?> handleOptions() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}