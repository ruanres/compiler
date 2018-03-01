package core;

public class Variable extends Expression {
	
	private Type type;
	private String name;
	
	public Variable(String name, Type type) {
		super(type);
		this.type = type;
		this.name = name;
	}
	
	public Type getType() {
		return type;	
	}
	
	public String getName() {
		return name;
	};
	
	public void setType(Type type) {
		this.type = type;	
	}
		
	public void setName(String name) {
		this.name = name;
	};
	
	@Override
	public String toString() {
		return getName();
	}


}
