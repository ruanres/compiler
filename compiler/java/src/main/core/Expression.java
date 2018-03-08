package core;


public class Expression  {
	
	private Type type;
	private String value;
	
	public Expression() {
		type = new Type("null");
	}
	
	public Expression(Type t) {
		this.type = t;
	}
	
	public Expression(Type t, String value) {
		this.type = t;
		this.value = value;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(Expression exp) {
		this.value = exp.getValue();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	public String toString() {
		if (getValue() == null) {
			return "{ Expression: " + getType() + "}";
		}
		return "{ Expression: " + getType() + " " + getValue() + "  }";
	}
}
