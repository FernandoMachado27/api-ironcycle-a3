package ironcycle.api.model.exceptions;

public class ValidationException extends RuntimeException{
	
	public ValidationException(String mensagem) {
		super(mensagem);
	}

}
