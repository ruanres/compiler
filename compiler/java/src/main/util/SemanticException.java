package util;

import compiler.generated.Scanner;

public class SemanticException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 7961006974649568226L;

	public SemanticException(String message) {
		super("Error at " + Scanner.lexeme + "\n" + message);
	}

}