package core;


import java.util.ArrayList;
import java.util.List;

import exceptions.*;


public class Function extends ScopedEntity implements Identifier {
	
	private Type returnType = new Type(null); // Default Return Type
	private ArrayList<Variable> variables;
	private List<String> params;
	private boolean initialized;
	private Type returnedType = new Type(null); // Default Return Type

	public Function(String name, List<String> params) {
		super(name);
		this.params = params; 
		initialized = false;
	}
	
	public void initializeFunction() {
		this.initialized = true;
	}
	
	public ArrayList<Variable> getFunctionParamaters() {
		return variables;
	}
	
	@Override
	public List<String> getParams() {
		return this.params;
	}
	

	public Function(String name) {
		this(name, null);
	}
	
	public Type getReturnType() {
		return returnType;
	}
	
	public List<Expression> getExpressions() {
		List<Expression> exps = new ArrayList<Expression>();
		
		for (Variable var : variables) {
			exps.add(var.getExpression());
		}
		
		return exps;
	}
	

	public void setReturnType(Type type) {
		this.returnType = type;
	}
	
	
	@Override
	public String toString() {
		return "{ Function: " + getName() + " "  + " }";
	}


	public void validateReturnedType() { // Checks if the function returned what it was supposed to..
		if (!returnedType.equals(returnType))
			throw new SemanticException("Function " + getName() + " was supposed to return " + returnType);
	}


	public void setReturnedType(Type type) {
		this.returnedType = type;
	}


	
}
