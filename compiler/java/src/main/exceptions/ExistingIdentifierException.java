package exceptions;

public class ExistingIdentifierException extends SemanticException{


	public ExistingIdentifierException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
