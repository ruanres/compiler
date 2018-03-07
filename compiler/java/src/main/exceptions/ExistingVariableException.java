package exceptions;

public class ExistingVariableException extends SemanticException{

	public ExistingVariableException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
