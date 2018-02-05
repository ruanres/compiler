package util;

/**
 *
 * @author Mafra, Ruan, Rafael
 */
public class Token extends java_cup.runtime.Symbol {


  public Token(int type, int line, int column) {
		super(type, line, column, null);
	}

	public Token(int type, int line, int column, Object value) {
	    super(type, line, column, value);
	}
	
	@Override
	public String toString() {
		  return "sym: " + sym +( value == null ? "" : ( ", value: '"+ value + "'"));
	}


}
