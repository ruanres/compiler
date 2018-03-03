
package core;

/**
 *	A C Operation... Nodes are the parameters of the operation...
 */
public enum Operation {
	PLUS("SUM"), MINUS("SUBTRACTION"), MULT("MULTIPLICATION"), DIV("DIVISION"), MOD("MOD"), LE_OP("LESS_OR_EQUAL"),
    GE_OP("GREATER_OR_EQUAL"), LESS_THAN("LESS_THAN"), MORE_THAN("MORE_THAN"), EQ_OP("EQUAL"), NE_OP("NOT_EQUAL"),
    AND_OP("AND"), OR_OP("OR"), NOT_OP("NOT");

	private String value;

	Operation(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString(){
		return value;
	}
}
