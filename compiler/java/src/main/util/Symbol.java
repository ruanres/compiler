package util;

/**
 *
 * @author Mafra, Ruan, Rafael
 */
public class Symbol extends java_cup.runtime.Symbol {
  int line;
	
  public Symbol(int type, int column, int line) {
	  super(type, column, line, null);
	  this.line = line;
  }

  public Symbol(int type, int column, int line, Object value) {
	super(type, column, line, value);
	this.line = line;
  }
  
  public Symbol(int type, Object value) {
		super(type, value); 
  }
	
  @Override
  public String toString() {
	return "sym: " + sym +( value == null ? "" : ( ", value: '"+ value + "'")) +
	 " ,line: " + this.line;
  }


}
