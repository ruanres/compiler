package compiler.generated.scopes;

import java.util.Stack;

import core.Function;
import core.Program;
import core.ScopedEntity;

public class FunctionScope {
	
	private static FunctionScope func;
	
	public FunctionScope() {
	
	}
	
	public static FunctionScope getInstance() {
		if (func == null) {
			func = new FunctionScope();
		}
		
		return func;
	}
	
	public void initFunction(String funcName, Program cProgram, Stack<ScopedEntity> scopeStack) {
		Function func = cProgram.getFunctions().get(funcName);
		
		if (func == null) {
			Function function = new Function(funcName);
			ScopedEntity scoped = new ScopedEntity(funcName);
			
			scopeStack.push(scoped);
			cProgram.addFunction(function);
		}
		
	}
	
}
