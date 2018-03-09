package compiler.generated;

import java.util.Stack;

import compiler.generated.scopes.AssignScope;
import compiler.generated.scopes.FunctionScope;
import core.*;


public class Semantic {
	public static Parser parser;

	private static Semantic sAnalysis;
    private Stack<ScopedEntity> scopeStack = new Stack<ScopedEntity>();
    private Program cProgram;
    public static CodeGenerator codeGenerator;
    
	public static Semantic getInstance() {
		if (sAnalysis == null)
			sAnalysis = new Semantic();
		if (codeGenerator == null) {
			codeGenerator = new CodeGenerator();
		}
		return sAnalysis;
	}
	
	public Semantic() {
		cProgram = new Program();

	}
	
	public static CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
	
	public ScopedEntity getCurrentScope() {
        return scopeStack.peek();
    }

	
	public void initFunction(String funcName) {
		FunctionScope.getInstance().initFunction(funcName, cProgram, scopeStack);
	}
	
	public void initVariable(String varName, Object assign) {
		if (assign instanceof Expression) {
			AssignScope.getInstance().initVariable(varName, (Expression) assign, scopeStack.peek());
		} else {
			AssignScope.getInstance().initVariable(varName, assign.toString(), scopeStack.peek());
		}
		
	}
	

	
	
	
}
