package core;

/**
 * C Type<br>
 * These types could be primitives (char, int, float, ...) and user-defined
 * using structs, unions, etc
 */
public class Type extends NamedEntity implements Parameter {

	public Type(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Type))
			return false;
		
		Type auxObj = (Type) obj;
		String auxObjName = auxObj.getName();
		
		return  this.getName().equals(auxObjName) ||
				this.getName().equals("float") && auxObjName.equals("int") || 
				this.getName().equals("int") && auxObjName.equals("float") || 
				this.getName().equals("int") && auxObjName.equals("char") ;
	}

	public Type getType() {
		return this;
	}
	
	public boolean equalsAssign(Object obj) {
		
		// Impossivel fazer re-atribuicao a uma variavel do tipo string.
		if (this.getType().getName() == "char") {
			return false;
		}
		if (!(obj instanceof Type))
			return false;
		
		Type auxObj = (Type) obj;
		String auxObjName = auxObj.getName();
		
		return  this.getName().equals(auxObjName) ||
				this.getName().equals("float") && auxObjName.equals("int") || 
				this.getName().equals("int") && auxObjName.equals("float") || 
				this.getName().equals("int") && auxObjName.equals("char") ;
	}

	public boolean equalsRelationExpression(Object obj) {
		if (!(obj instanceof Type))
			return false;
		
		Type auxObj = (Type) obj;
		String auxObjName = auxObj.getName();
		
		return  this.getName().equals(auxObjName) ||
				this.getName().equals("float") && auxObjName.equals("int") || 
				this.getName().equals("int") && auxObjName.equals("float") || 
				this.getName().equals("int") && auxObjName.equals("char") ||
				this.getName().equals("char") && auxObjName.equals("int");
	}
}
