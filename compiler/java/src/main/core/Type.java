package core;

/**
 * C Type<br>
 * These types could be primitives (char, int, float, ...) and user-defined
 * using structs, unions, etc
 */
public class Type  {
	private String value;
	
	public Type(String value) {
		this.value = value; 
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Type))
			return false;
		
		Type auxObj = (Type) obj;
		String auxObjName = auxObj.getValue();
		
		return  this.getValue().equals(auxObjName) ||
				this.getValue().equals("float") && auxObjName.equals("int") || 
				this.getValue().equals("int") && auxObjName.equals("float");
	}

	public Type getType() {
		return this;
	}
	
	

}
