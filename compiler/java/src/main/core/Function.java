package core;


import java.util.ArrayList;
import java.util.List;

import util.SemanticException;


public class Function extends ScopedEntity implements Identifier {
	
	private Type returnType = new Type(null); // Default Return Type
	private ArrayList<Variable> functionParameter;

	private Type returnedType = new Type(null); // Default Return Type

	public Function(String name, ArrayList<Variable> parameters) {
		super(name);
		if (parameters != null) {
			functionParameter = parameters;
		} else {
			functionParameter = new ArrayList<Variable>();
		}
	}
	
	public ArrayList<Variable> getFunctionParamaters() {
		return functionParameter;
	}
	

	public Function(String name) {
		this(name, null);
	}
	
	public Type getReturnType() {
		return returnType;
	}
	
	public List<Expression> getExpressions() {
		List<Expression> exps = new ArrayList<Expression>();
		
		for (Variable var : functionParameter) {
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
