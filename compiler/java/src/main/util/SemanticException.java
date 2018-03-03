package util;

public class SemanticException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 7961006974649568226L;

	public SemanticException(String message) {
		super("Semantic error: " + message);
	}

}