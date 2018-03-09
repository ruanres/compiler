package exceptions;

public class AssignScopeException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 7961006974649568226L;

	public AssignScopeException(String message) {
		super("Semantic error: " + message);
	}

}