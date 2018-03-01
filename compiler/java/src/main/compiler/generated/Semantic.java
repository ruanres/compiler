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
	
	public static CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
	
	public ScopedEntity getCurrentScope() {
        return scopeStack.peek();
    }
	
	public void addVariable(Variable var) {
		if (variables.get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}

		variables.put(var.toString(), var);
	}
	
	public Identifier getIdentifier(String name) {
		if (variables.get(name) == null) {
			throw new SemanticException("Identifier name doesn't exists: " + name);
		}
		
		return variables.get(name);
	}
	

	public boolean isRelationalExpression(Expression le, Expression re) throws SemanticException {
		if(!le.getType().equals(re.getType())){
            throw new SemanticException("ERRO: A expressão formada pelas subexpressões de valor " + le.getValue() + " do tipo "
                    + le.getType().getName()+" e de valor " + re.getValue() + " do tipo " + re.getType().getName()+ " não é uma expressão relacional!");
        }

		return true;
    }

}
