package lexico;
/* Reference: http://www.quut.com/c/ANSI-C-grammar-l.html */

%%

%class Lexer
%type Token


O = [0-7]
D = [0-9]
NZ = [1-9]
L = [a-zA-Z_]
A = [a-zA-Z_0-9]
H = [a-fA-F0-9]
HP = (0[xX])
E = ([Ee][+-]?{D}+)
P = ([Pp][+-]?{D}+)
FS = (f|F|l|L)
IS = (((u|U)(l|L|ll|LL)?)|((l|L|ll|LL)(u|U)?))
CP = (u|U|L)
SP = (u8|u|U|L)
ES = (\\(["'"\?\\abfnrtv]|[0-7]{1,3}|x[a-fA-F0-9]+))
WS = [ \t\v\n\f]

%{
  public String lexeme;

  private int yywrap() {       /* called at end of input */
      return 1;           /* terminate now */
  }

  private void comment() {
    int c;

    while ((c = input()) != 0);

    if (c == '*') {
      while ((c = input()) == '*');

       if (c == '/') { return;

              if (c == 0)
                  break;
        }
      yyerror("unterminated comment");
    }
  }

  private int check_type() {
      switch (sym_type(yytext))
      {
      case TYPEDEF_NAME:                /* previously defined */
          return TYPEDEF_NAME;
      case ENUMERATION_CONSTANT:        /* previously defined */
          return ENUMERATION_CONSTANT;
      default:                          /* includes undefined */
          return IDENTIFIER;
      }
  }

%}
%%

("auto")				  { lexeme = yytext(); return AUTO; }
("break")					{ lexeme = yytext(); return BREAK; }
("case")				  { lexeme = yytext(); return CASE; }
("char")					{ lexeme = yytext(); return CHAR; }
("const")					{ lexeme = yytext(); return CONST; }
("continue")			{ lexeme = yytext(); return CONTINUE; }
("default")				{ lexeme = yytext(); return DEFAULT; }
("do")					  { lexeme = yytext(); return DO; }
("double")			  { lexeme = yytext(); return DOUBLE; }
("else")					{ lexeme = yytext(); return ELSE; }
("enum")					{ lexeme = yytext(); return ENUM; }
("extern")				{ lexeme = yytext(); return EXTERN; }
("float")					{ lexeme = yytext(); return FLOAT; }
("for")					  { lexeme = yytext(); return FOR; }
("goto")					{ lexeme = yytext(); return GOTO; }
("if")					  { lexeme = yytext(); return IF; }
("inline")				{ lexeme = yytext(); return INLINE; }
("int")					  { lexeme = yytext(); return INT; }
("long")					{ lexeme = yytext(); return LONG; }
("register")			{ lexeme = yytext(); return REGISTER; }
("restrict")			{ lexeme = yytext(); return RESTRICT; }
("return")				{ lexeme = yytext(); return RETURN; }
("short")					{ lexeme = yytext(); return SHORT; }
("signed")				{ lexeme = yytext(); return SIGNED; }
("sizeof")				{ lexeme = yytext(); return SIZEOF; }
("static")				{ lexeme = yytext(); return STATIC; }
("struct")				{ lexeme = yytext(); return STRUCT; }
("switch")				{ lexeme = yytext(); return SWITCH; }
("typedef")				{ lexeme = yytext(); return TYPEDEF; }
("union")					{ lexeme = yytext(); return UNION; }
("unsigned")			{ lexeme = yytext(); return UNSIGNED; }
("void")					{ lexeme = yytext(); return VOID; }
("volatile")			{ lexeme = yytext(); return VOLATILE; }
("while")					{ lexeme = yytext(); return WHILE; }
("_Alignas")      { lexeme = yytext(); return ALIGNAS; }
("_Alignof")      { lexeme = yytext(); return ALIGNOF; }
("_Atomic")       { lexeme = yytext(); return ATOMIC; }
("_Bool")         { lexeme = yytext(); return BOOL; }
("_Complex")      { lexeme = yytext(); return COMPLEX; }
("_Generic")      { lexeme = yytext(); return GENERIC; }
("_Imaginary")    { lexeme = yytext(); return IMAGINARY; }
("_Noreturn")     { lexeme = yytext(); return NORETURN; }
("_Static_assert") { lexeme = yytext(); return STATIC_ASSERT; }
("_Thread_local") { lexeme = yytext(); return THREAD_LOCAL; }
("__func__")      { lexeme = yytext(); return FUNC_NAME; }

{L}{A}*					  { lexeme = yytext(); return check_type(); }

{HP}{H}+{IS}?			{ lexeme = yytext(); return I_CONSTANT; }
{NZ}{D}*{IS}?			{ lexeme = yytext(); return I_CONSTANT; }
"0"{O}*{IS}?			{ lexeme = yytext(); return I_CONSTANT; }
{CP}?"'"([^"'"\\\n]|{ES})+"'"		{ lexeme = yytext();  return I_CONSTANT; }

{D}+{E}{FS}?				{ lexeme = yytext();  return F_CONSTANT; }
{D}*"."{D}+{E}?{FS}?	{ lexeme = yytext();  return F_CONSTANT; }
{D}+"."{E}?{FS}?			{ lexeme = yytext();  return F_CONSTANT; }
{HP}{H}+{P}{FS}?			{ lexeme = yytext();  return F_CONSTANT; }
{HP}{H}*"."{H}+{P}{FS}?			{ lexeme = yytext();  return F_CONSTANT; }
{HP}{H}+"."{P}{FS}?			{ lexeme = yytext();  return F_CONSTANT; }

/* Need Fix */
/* ({SP}?\"([^"\\\n]|{ES})*\\"{WS}*)+	{ lexeme = yytext(); return STRING_LITERAL; } */

("...")					{ lexeme = yytext(); return ELLIPSIS; }
(">>=")					{ lexeme = yytext(); return RIGHT_ASSIGN; }
("<<=")					{ lexeme = yytext(); return LEFT_ASSIGN; }
("+=")					{ lexeme = yytext(); return ADD_ASSIGN; }
("-=")					{ lexeme = yytext(); return SUB_ASSIGN; }
("*=")					{ lexeme = yytext(); return MUL_ASSIGN; }
("/=")					{ lexeme = yytext(); return DIV_ASSIGN; }
("%=")					{ lexeme = yytext(); return MOD_ASSIGN; }
("&=")					{ lexeme = yytext(); return AND_ASSIGN; }
("^=")					{ lexeme = yytext(); return XOR_ASSIGN; }
("|=")					{ lexeme = yytext(); return OR_ASSIGN; }
(">>")					{ lexeme = yytext(); return RIGHT_OP; }
("<<")					{ lexeme = yytext(); return LEFT_OP; }
("++")					{ lexeme = yytext(); return INC_OP; }
("--")					{ lexeme = yytext(); return DEC_OP; }
("->")					{ lexeme = yytext(); return PTR_OP; }
("&&")					{ lexeme = yytext(); return AND_OP; }
("||")					{ lexeme = yytext(); return OR_OP; }
("<=")					{ lexeme = yytext(); return LE_OP; }
(">=")					{ lexeme = yytext(); return GE_OP; }
("==")					{ lexeme = yytext(); return EQ_OP; }
("!=")					{ lexeme = yytext(); return NE_OP; }
(";")				    { lexeme = yytext(); return SEMI; }
("{"|"<%")			{ lexeme = yytext(); return LEFT_KEY; }
("}"|"%>")			{ lexeme = yytext(); return RIGHT_KEY; }
(",")					  { lexeme = yytext(); return COMMA; }
(":")					  { lexeme = yytext(); return COLON; }
("=")					  { lexeme = yytext(); return EQUALS; }
("(")					  { lexeme = yytext(); return LEFT_PARENTHESES; }
(")")					  { lexeme = yytext(); return RIGHT_PARENTESES; }
("["|"<:")			{ lexeme = yytext(); return LEFT_BRACKETS; }
("]"|":>")			{ lexeme = yytext(); return RIGHT_BRACKETS; }
(".")					  { lexeme = yytext(); return DOT; }
("&")					  { lexeme = yytext(); return AMPERSAND; }
("!")					  { lexeme = yytext(); return EXCLAMATION; }
("~")					  { lexeme = yytext(); return TILDE; }
("-")					  { lexeme = yytext(); return HYPHEN; }
("+")				 	  { lexeme = yytext(); return PLUS; }
("*")					  { lexeme = yytext(); return TIMES; }
("/")					  { lexeme = yytext(); return DIVISON; }
("%")					  { lexeme = yytext(); return MOD; }
("<")					  { lexeme = yytext(); return LESS_THAN_OP; }
(">")					  { lexeme = yytext(); return GREATER_THAN_OP; }
("^")					  { lexeme = yytext(); return POWER; }
("|")					  { lexeme = yytext(); return BAR; }
("?")					  { lexeme = yytext(); return QUESTION_MARK; }

{WS}+					{ /* whitespace separates tokens */ }
.					    {  System.out.println("error: unexpected token |%s|\n", yytext() }
