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
		return getName().equals(((Type) obj).getName())
				|| (((Type) obj).getName().equals("float") && this.getType().getName().equals("int"))
				|| (((Type) obj).getName().equals("int") && this.getType().getName().equals("float"))
				|| (((Type) obj).getName().equals("int") && this.getType().getName().equals("char"))
				|| (((Type) obj).getName().equals("char") && this.getType().getName().equals("i"));
	}

	public Type getType() {
		return this;
	}
}
