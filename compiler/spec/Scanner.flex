package compiler.generated;
/* Reference: http://www.quut.com/c/ANSI-C-grammar-l.html */
import java_cup.runtime.*;
import util.Symbol;


%%

%class Scanner
%type java_cup.runtime.Symbol
%public
%unicode
%line
%column
%cup
%cupdebug


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

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

/* Comments */

Comments = {LineComment} | {BlockComment}
LineComment = "//" {InputCharacter}* {LineTerminator}?
BlockComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"

%{
  public String lexeme;
%}
%eofval{
    return new Symbol (sym.EOF, new String("Fim do arquivo"));
%eofval}
%%

"auto"				    { return new Symbol (sym.AUTO, yychar, yyline, yytext());}
"break"					  { return new Symbol (sym.BREAK, yychar, yyline, yytext());}
"case"				    { return new Symbol (sym.CASE, yychar, yyline, yytext());}
"char"			    	{ return new Symbol (sym.CHAR, yychar, yyline, yytext());}
"const"				    { return new Symbol (sym.CONST, yychar, yyline, yytext());}
"continue"			  { return new Symbol (sym.CONTINUE, yychar, yyline, yytext());}
"default"				  { return new Symbol (sym.DEFAULT, yychar, yyline, yytext());}
"do"				      { return new Symbol (sym.DO, yychar, yyline, yytext());}
"double"			    { return new Symbol (sym.DOUBLE, yychar, yyline, yytext());}
"else"				    { return new Symbol (sym.ELSE, yychar, yyline, yytext());}
"enum"				    { return new Symbol (sym.ENUM, yychar, yyline, yytext());}
"extern"				  { return new Symbol (sym.EXTERN, yychar, yyline, yytext());}
"float"				    { return new Symbol (sym.FLOAT, yychar, yyline, yytext());}
"for"					    { return new Symbol (sym.FOR, yychar, yyline, yytext());}
"goto"				    { return new Symbol (sym.GOTO, yychar, yyline, yytext());}
"if"					    { return new Symbol (sym.IF, yychar, yyline, yytext());}
"inline"				  { return new Symbol (sym.INLINE, yychar, yyline, yytext());}
"int"					    { return new Symbol (sym.INT, yychar, yyline, yytext());}
"long"					  { return new Symbol (sym.LONG, yychar, yyline, yytext());}
"register"			  { return new Symbol (sym.REGISTER, yychar, yyline, yytext());}
"restrict"			  { return new Symbol (sym.RESTRICT, yychar, yyline, yytext());}
"return"				  { return new Symbol (sym.RETURN, yychar, yyline, yytext());}
"short"					  { return new Symbol (sym.SHORT, yychar, yyline, yytext());}
"signed"				  { return new Symbol (sym.SIGNED, yychar, yyline, yytext());}
"sizeof"				  { return new Symbol (sym.SIZEOF, yychar, yyline, yytext());}
"static"				  { return new Symbol (sym.STATIC, yychar, yyline, yytext());}
"struct"				  { return new Symbol (sym.STRUCT, yychar, yyline, yytext());}
"switch"				  { return new Symbol (sym.SWITCH, yychar, yyline, yytext());}
"typedef"				  { return new Symbol (sym.TYPEDEF, yychar, yyline, yytext());}
"union"					  { return new Symbol (sym.UNION, yychar, yyline, yytext());}
"unsigned"			  { return new Symbol (sym.UNSIGNED, yychar, yyline, yytext());}
"void"					  { return new Symbol (sym.VOID, yychar, yyline, yytext());}
"volatile"			  { return new Symbol (sym.VOLATILE, yychar, yyline, yytext());}
"while"					  { return new Symbol (sym.WHILE, yychar, yyline, yytext());}
"_Alignas"        { return new Symbol (sym.ALIGNAS, yychar, yyline, yytext());}
"_Alignof"        { return new Symbol (sym.ALIGNOF, yychar, yyline, yytext());}
"_Atomic"         { return new Symbol (sym.ATOMIC, yychar, yyline, yytext());}
"_Bool"           { return new Symbol (sym.BOOL, yychar, yyline, yytext());}
"_Complex"        { return new Symbol (sym.COMPLEX, yychar, yyline, yytext());}
"_Generic"        { return new Symbol (sym.GENERIC, yychar, yyline, yytext());}
"_Imaginary"      { return new Symbol (sym.IMAGINARY, yychar, yyline, yytext());}
"_Noreturn"       { return new Symbol (sym.NORETURN, yychar, yyline, yytext());}
"_Static_assert"  { return new Symbol (sym.STATIC_ASSERT, yychar, yyline, yytext());}
"_Thread_local"   { return new Symbol (sym.THREAD_LOCAL, yychar, yyline, yytext());}
"__func__"        { return new Symbol (sym.FUNC_NAME, yychar, yyline, yytext());}

{L}{A}*					  { return new Symbol (sym.IDENTIFIER, yychar, yyline, yytext()); }


{HP}{H}+{IS}?				{ return new Symbol (sym.I_CONSTANT, yychar, yyline, yytext());}
{NZ}{D}*{IS}?		    { return new Symbol (sym.I_CONSTANT, yychar, yyline, yytext());}
"0"{O}*{IS}?			  { return new Symbol (sym.I_CONSTANT, yychar, yyline, yytext());}
{CP}?"'"([^"'"\\\n]|{ES})+"'"		{ return new Symbol (sym.I_CONSTANT, yychar, yyline, yytext());}

{D}+{E}{FS}?				      { return new Symbol (sym.F_CONSTANT, yychar, yyline, yytext());}
{D}*"."{D}+{E}?{FS}?	    { return new Symbol (sym.F_CONSTANT, yychar, yyline, yytext());}
{D}+"."{E}?{FS}?			    { return new Symbol (sym.F_CONSTANT, yychar, yyline, yytext());}
{HP}{H}+{P}{FS}?			    { return new Symbol (sym.F_CONSTANT, yychar, yyline, yytext());}
{HP}{H}*"."{H}+{P}{FS}?		{ return new Symbol (sym.F_CONSTANT, yychar, yyline, yytext());}
{HP}{H}+"."{P}{FS}?			  { return new Symbol (sym.F_CONSTANT, yychar, yyline, yytext());}

({SP}?\"([^\"\\\n]|{ES})*\"{WS}*)+	{ return new Symbol (sym.STRING_LITERAL, yychar, yyline, yytext());}

"..."					{ return new Symbol (sym.ELLIPSIS, yychar, yyline, yytext());}
">>="					{ return new Symbol (sym.RIGHT_ASSIGN, yychar, yyline, yytext());}
"<<="					{ return new Symbol (sym.LEFT_ASSIGN, yychar, yyline, yytext());}
"+="					{ return new Symbol (sym.ADD_ASSIGN, yychar, yyline, yytext());}
"-="					{ return new Symbol (sym.SUB_ASSIGN, yychar, yyline, yytext());}
"*="					{ return new Symbol (sym.MUL_ASSIGN, yychar, yyline, yytext());}
"/="					{ return new Symbol (sym.DIV_ASSIGN, yychar, yyline, yytext());}
"%="					{ return new Symbol (sym.MOD_ASSIGN, yychar, yyline, yytext());}
"&="					{ return new Symbol (sym.AND_ASSIGN, yychar, yyline, yytext());}
"^="					{ return new Symbol (sym.XOR_ASSIGN, yychar, yyline, yytext());}
"|="					{ return new Symbol (sym.OR_ASSIGN, yychar, yyline, yytext());}


">>"					{ return new Symbol (sym.RIGHT_OP, yychar, yyline, yytext());}
"<<"					{ return new Symbol (sym.LEFT_OP, yychar, yyline, yytext());}
"++"					{ return new Symbol (sym.INC_OP, yychar, yyline, yytext());}
"--"					{ return new Symbol (sym.DEC_OP, yychar, yyline, yytext());}
"->"					{ return new Symbol (sym.PTR_OP, yychar, yyline, yytext());}
"&&"					{ return new Symbol (sym.AND_OP, yychar, yyline, yytext());}
"||"					{ return new Symbol (sym.OR_OP, yychar, yyline, yytext());}
"<="					{ return new Symbol (sym.LE_OP, yychar, yyline, yytext());}
">="					{ return new Symbol (sym.GE_OP, yychar, yyline, yytext());}
"=="					{ return new Symbol (sym.EQ_OP, yychar, yyline, yytext());}
"!="					{ return new Symbol (sym.NE_OP, yychar, yyline, yytext());}

/* ---- Atribution ---- */
"="					    { return new Symbol (sym.EQ, yychar, yyline, yytext());}

/* ---- Separators ---- */
"("					    { return new Symbol (sym.LPAREN, yychar, yyline, yytext());}
")"					    { return new Symbol (sym.RPAREN, yychar, yyline, yytext());}
"{"|"<%"			  { return new Symbol (sym.LBRACE, yychar, yyline, yytext());}
"}"|"%>"			  { return new Symbol (sym.RBRACE, yychar, yyline, yytext());}
"["|"<:"			  { return new Symbol (sym.LBRACK, yychar, yyline, yytext());}
"]"|":>"			  { return new Symbol (sym.RBRACK, yychar, yyline, yytext());}
";"				      { return new Symbol (sym.SEMICOLON, yychar, yyline, yytext());}
","					    { return new Symbol (sym.COMMA, yychar, yyline, yytext());}
"."					    { return new Symbol (sym.DOT, yychar, yyline, yytext());}

/* ---- Arithmetic operators ---- */
"+"				 	    { return new Symbol (sym.PLUS, yychar, yyline, yytext());}
"-"					    { return new Symbol (sym.MINUS, yychar, yyline, yytext());}
"*"					    { return new Symbol (sym.MULT, yychar, yyline, yytext());}
"/"					    { return new Symbol (sym.DIV, yychar, yyline, yytext());}
"%"					    { return new Symbol (sym.MOD, yychar, yyline, yytext());}

/* ---- Operator ---- */
":"					    { return new Symbol (sym.COLON, yychar, yyline, yytext());}

/* ---- Logic operators----  */
"<"					    { return new Symbol (sym.LT, yychar, yyline, yytext());}
">"					    { return new Symbol (sym.GT, yychar, yyline, yytext());}
"&"					    { return new Symbol (sym.AND, yychar, yyline, yytext());}
"!"					    { return new Symbol (sym.NOT, yychar, yyline, yytext());}
"~"					    { return new Symbol (sym.COMP, yychar, yyline, yytext());}
"|"					    { return new Symbol (sym.OR, yychar, yyline, yytext());}
"^"					    { return new Symbol (sym.XOR, yychar, yyline, yytext());}
"?"					    { return new Symbol (sym.QUESTION, yychar, yyline, yytext());}

{Comments}              { /* skip it */ }

{WS}+					{ /* whitespace separates tokens */ }
.					    { return new Symbol (sym.ERROR, yychar, yyline, yytext());}
