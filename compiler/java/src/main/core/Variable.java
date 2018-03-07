package core;

public class Variable {
	
	private Type type;
	private String name;
	private Expression expression;
	private Function associatedFunction;
	
	public Variable(String name, Type type) {
		this.type = type;
		this.name = name;
	}
	
	public Type getType() {
		return type;	
	}
	
	public String getName() {
		return name;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	
	public void setType(Type type) {
		this.type = type;	
	}
	
	public boolean existExpression() {
		if (this.expression == null) {
			return false;
		}
		
		return true;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void setFunction (Function function) {
		this.associatedFunction = function;
	}
	
	public Function getFunction() {
		return this.associatedFunction;
	}
	
	@Override
	public String toString() {
		return getName();
	}


}
