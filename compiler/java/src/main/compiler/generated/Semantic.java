package compiler.generated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import core.*;
import util.SemanticException;

public class Semantic {
	public static Parser parser;

	private static Semantic sAnalysis;
    private Stack<ScopedEntity> scopeStack = new Stack<ScopedEntity>();
    private Program cProgram;
    private HashMap<String,Variable> variables = new HashMap<String,Variable>();
    public static CodeGenerator codeGenerator;

	public static Semantic getInstance() {
		if (sAnalysis == null)
			sAnalysis = new Semantic();
		return sAnalysis;
	}
	
	public Semantic() {
		cProgram = new Program();
	}
	
	private void createNewScope(ScopedEntity scope) {
		scopeStack.push(scope);
	}
	
	public static CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
	
	public ScopedEntity getCurrentScope() {
        return scopeStack.peek();
    }
	
	public void addFunctionAndNewScope(Function f) throws Exception {
		createNewScope(f);
	}
	
	public void addVariable(Variable var) {
	
		if(scopeStack.isEmpty()){
			if (variables.get(var.toString()) != null) {
				throw new SemanticException("Variable " + var.getName() + " already exists");
			}

			variables.put(var.toString(), var);
        } else {
	    	if (checkIfVariableExistInScope(var.getName())) {
				throw new SemanticException("Variable " + var.getName() + " already exists");
			} 
	    	
            getCurrentScope().addVariable(var);
        }
		
		
	
	}
	
	public Identifier getIdentifier(String name) {
		if (!checkIfVariableExistInScope(name)) {
			throw new SemanticException("Identifier name doesn't exists: " + name);
		}
		
		return variables.get(name);
	}
	
	public boolean checkIfVariableExistInScope(String name) {
		if(!scopeStack.isEmpty() && getCurrentScope().getVariable().get(name) != null){
			return true;
	    } else if(variables.get(name) != null){
	        return true;
	    } else {
	        return false;
	    }

	}

}
