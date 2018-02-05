package util;

/**
 *
 * @author Mafra, Ruan, Rafael
 */
public class Token extends java_cup.runtime.Symbol {

  /*
  AUTO, BREAK, CASE, CHAR, CONST, CONTINUE, DEFAULT, DO, DOUBLE, ELSE, ENUM, EXTERN, FLOAT,
  FOR, GOTO, IF, INLINE, INT, LONG, REGISTER, RESTRICT, RETURN, SHORT, SIGNED, SIZEOF, STATIC,
  STRUCT, SWITCH, TYPEDEF, UNION, UNSIGNED, VOID, VOLATILE, WHILE, ALIGNAS, ALIGNOF,
  ATOMIC, BOOL, COMPLEX, GENERIC, IMAGINARY, NORETURN, STATIC_ASSERT, THREAD_LOCAL,
  FUNC_NAME, I_CONSTANT, F_CONSTANT, STRING_LITERA, ELLIPSIS, RIGHT_ASSIGN, LEFT_ASSIGN,
  ADD_ASSIGN, SUB_ASSIGN, MUL_ASSIGN, DIV_ASSIGN, MOD_ASSIGN, AND_ASSIGN, XOR_ASSIGN,
  OR_ASSIGN, RIGHT_OP, LEFT_OP, INC_OP, DEC_OP, PTR_OP, AND_OP, OR_OP, LE_OP, GE_OP,
  EQ_OP, NE_OP, SEMI, LEFT_KEY, RIGHT_KEY, COMMA, COLON, EQUALS, LEFT_PARENTHESES, RIGHT_PARENTESES,
  LEFT_BRACKETS, RIGHT_BRACKETS, DOT, AMPERSAND, EXCLAMATION, TILDE, HYPHEN, PLUS, TIMES, DIVISON,
  MOD, LESS_THAN_OP, GREATER_THAN_OP, POWER, BAR, QUESTION_MARK, IDENTIFIER,COMMENT; **/


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
