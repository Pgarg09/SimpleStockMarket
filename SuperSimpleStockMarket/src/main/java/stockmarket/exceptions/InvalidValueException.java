package stockmarket.exceptions;

public class InvalidValueException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public InvalidValueException(String exceptionMessage) {
		super(exceptionMessage);
	}
	

}
