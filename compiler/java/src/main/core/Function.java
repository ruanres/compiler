package core;


import java.util.ArrayList;
import java.util.List;

import util.SemanticException;


public class Function{
	
	private Type returnType = new Type(null); // Default Return Type
	private List<Expression> expressions;
	private List<String> params;
	private boolean initialized;
	private Type returnedType = new Type(null); // Default Return Type
	private int checked = 0;
	private String name;
	
	public Function(String name, List<String> params) {
		this.name = name;
		this.params = params; 
		initialized = false;
	}
	
	public void initializeFunction() {
		this.initialized = true;
	}
	
	public void incrementCheck() {
		this.checked = this.checked + 1;
	}
	
	public int getChecked() {
		return checked;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Expression> getFunctionParamaters() {
		return expressions;
	}
	
	public void setFunctionParamaters(List<Expression> expressions) {
		this.expressions = expressions;
	}
	
	public boolean isFunctionInitialized() {
		return this.initialized;
	}
	
	
	public Function(String name) {
		this(name, null);
	}
	
	public Type getReturnType() {
		return returnType;
	}
	
	public List<Expression> getExpressions() {
		List<Expression> exps = new ArrayList<Expression>();
		
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
