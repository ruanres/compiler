package core;


import java.util.ArrayList;


import util.SemanticException;


public class Function extends ScopedEntity implements Identifier {
	
	private Type returnType = new Type(null); // Default Return Type
	private ArrayList<Expression> functionParameter;
	private Type returnedType = new Type(null); // Default Return Type

	public Function(String name, ArrayList<Expression> parameters) {
		super(name);
		if (parameters != null) {
			functionParameter = parameters;
		} else {
			functionParameter = new ArrayList<Expression>();
		}
	}
	

	public Function(String name) {
		this(name, null);
	}
	
	public Type getReturnType() {
		return returnType;
	}
	
	public Type[] getParameterTypes() {
		if (functionParameter == null)
			return new Type[]{};
		
		Type[] pTypes = new Type[functionParameter.size()];
		for (int i = 0 ; i < pTypes.length ; i++)
			pTypes[i] = functionParameter.get(i).getType();
		return pTypes;
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


	public void addParameter(Expression exp) {
		functionParameter.add(exp);
	}
	
}
