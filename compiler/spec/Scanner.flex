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
      public String current_lexeme() {
        int l = yyline + 1;
        int c = yycolumn + 1;
        return " (line: " + l + " , column: " + c + " , lexeme: '" + yytext() + "')";
      }
%}
%eofval{
    return new Symbol (sym.EOF, new String("Fim do arquivo"));
%eofval}
%%

"auto"				    { return new Symbol (sym.AUTO, yycolumn, yyline, yytext());}
"break"					  { return new Symbol (sym.BREAK, yycolumn, yyline, yytext());}
"case"				    { return new Symbol (sym.CASE, yycolumn, yyline, yytext());}
"char"			    	{ return new Symbol (sym.CHAR, yycolumn, yyline, yytext());}
"const"				    { return new Symbol (sym.CONST, yycolumn, yyline, yytext());}
"continue"			  { return new Symbol (sym.CONTINUE, yycolumn, yyline, yytext());}
"default"				  { return new Symbol (sym.DEFAULT, yycolumn, yyline, yytext());}
"do"				      { return new Symbol (sym.DO, yycolumn, yyline, yytext());}
"double"			    { return new Symbol (sym.DOUBLE, yycolumn, yyline, yytext());}
"else"				    { return new Symbol (sym.ELSE, yycolumn, yyline, yytext());}
"enum"				    { return new Symbol (sym.ENUM, yycolumn, yyline, yytext());}
"extern"				  { return new Symbol (sym.EXTERN, yycolumn, yyline, yytext());}
"float"				    { return new Symbol (sym.FLOAT, yycolumn, yyline, yytext());}
"for"					    { return new Symbol (sym.FOR, yycolumn, yyline, yytext());}
"goto"				    { return new Symbol (sym.GOTO, yycolumn, yyline, yytext());}
"if"					    { return new Symbol (sym.IF, yycolumn, yyline, yytext());}
"inline"				  { return new Symbol (sym.INLINE, yycolumn, yyline, yytext());}
"int"					    { return new Symbol (sym.INT, yycolumn, yyline, yytext());}
"long"					  { return new Symbol (sym.LONG, yycolumn, yyline, yytext());}
"register"			  { return new Symbol (sym.REGISTER, yycolumn, yyline, yytext());}
"restrict"			  { return new Symbol (sym.RESTRICT, yycolumn, yyline, yytext());}
"return"				  { return new Symbol (sym.RETURN, yycolumn, yyline, yytext());}
"short"					  { return new Symbol (sym.SHORT, yycolumn, yyline, yytext());}
"signed"				  { return new Symbol (sym.SIGNED, yycolumn, yyline, yytext());}
"sizeof"				  { return new Symbol (sym.SIZEOF, yycolumn, yyline, yytext());}
"static"				  { return new Symbol (sym.STATIC, yycolumn, yyline, yytext());}
"struct"				  { return new Symbol (sym.STRUCT, yycolumn, yyline, yytext());}
"switch"				  { return new Symbol (sym.SWITCH, yycolumn, yyline, yytext());}
"typedef"				  { return new Symbol (sym.TYPEDEF, yycolumn, yyline, yytext());}
"union"					  { return new Symbol (sym.UNION, yycolumn, yyline, yytext());}
"unsigned"			  { return new Symbol (sym.UNSIGNED, yycolumn, yyline, yytext());}
"void"					  { return new Symbol (sym.VOID, yycolumn, yyline, yytext());}
"volatile"			  { return new Symbol (sym.VOLATILE, yycolumn, yyline, yytext());}
"while"					  { return new Symbol (sym.WHILE, yycolumn, yyline, yytext());}
"_Alignas"        { return new Symbol (sym.ALIGNAS, yycolumn, yyline, yytext());}
"_Alignof"        { return new Symbol (sym.ALIGNOF, yycolumn, yyline, yytext());}
"_Atomic"         { return new Symbol (sym.ATOMIC, yycolumn, yyline, yytext());}
"_Bool"           { return new Symbol (sym.BOOL, yycolumn, yyline, yytext());}
"_Complex"        { return new Symbol (sym.COMPLEX, yycolumn, yyline, yytext());}
"_Generic"        { return new Symbol (sym.GENERIC, yycolumn, yyline, yytext());}
"_Imaginary"      { return new Symbol (sym.IMAGINARY, yycolumn, yyline, yytext());}
"_Noreturn"       { return new Symbol (sym.NORETURN, yycolumn, yyline, yytext());}
"_Static_assert"  { return new Symbol (sym.STATIC_ASSERT, yycolumn, yyline, yytext());}
"_Thread_local"   { return new Symbol (sym.THREAD_LOCAL, yycolumn, yyline, yytext());}
"__func__"        { return new Symbol (sym.FUNC_NAME, yycolumn, yyline, yytext());}

{L}{A}*					  { return new Symbol (sym.IDENTIFIER, yycolumn, yyline, yytext()); }


{HP}{H}+{IS}?				{ return new Symbol (sym.I_CONSTANT, yycolumn, yyline, yytext());}
{NZ}{D}*{IS}?		    { return new Symbol (sym.I_CONSTANT, yycolumn, yyline, yytext());}
"0"{O}*{IS}?			  { return new Symbol (sym.I_CONSTANT, yycolumn, yyline, yytext());}
{CP}?"'"([^"'"\\\n]|{ES})+"'"		{ return new Symbol (sym.I_CONSTANT, yycolumn, yyline, yytext());}

{D}+{E}{FS}?				      { return new Symbol (sym.F_CONSTANT, yycolumn, yyline, yytext());}
{D}*"."{D}+{E}?{FS}?	    { return new Symbol (sym.F_CONSTANT, yycolumn, yyline, yytext());}
{D}+"."{E}?{FS}?			    { return new Symbol (sym.F_CONSTANT, yycolumn, yyline, yytext());}
{HP}{H}+{P}{FS}?			    { return new Symbol (sym.F_CONSTANT, yycolumn, yyline, yytext());}
{HP}{H}*"."{H}+{P}{FS}?		{ return new Symbol (sym.F_CONSTANT, yycolumn, yyline, yytext());}
{HP}{H}+"."{P}{FS}?			  { return new Symbol (sym.F_CONSTANT, yycolumn, yyline, yytext());}

({SP}?\"([^\"\\\n]|{ES})*\"{WS}*)+	{ return new Symbol (sym.STRING_LITERAL, yycolumn, yyline, yytext());}

"..."					{ return new Symbol (sym.ELLIPSIS, yycolumn, yyline, yytext());}
">>="					{ return new Symbol (sym.RIGHT_ASSIGN, yycolumn, yyline, yytext());}
"<<="					{ return new Symbol (sym.LEFT_ASSIGN, yycolumn, yyline, yytext());}
"+="					{ return new Symbol (sym.ADD_ASSIGN, yycolumn, yyline, yytext());}
"-="					{ return new Symbol (sym.SUB_ASSIGN, yycolumn, yyline, yytext());}
"*="					{ return new Symbol (sym.MUL_ASSIGN, yycolumn, yyline, yytext());}
"/="					{ return new Symbol (sym.DIV_ASSIGN, yycolumn, yyline, yytext());}
"%="					{ return new Symbol (sym.MOD_ASSIGN, yycolumn, yyline, yytext());}
"&="					{ return new Symbol (sym.AND_ASSIGN, yycolumn, yyline, yytext());}
"^="					{ return new Symbol (sym.XOR_ASSIGN, yycolumn, yyline, yytext());}
"|="					{ return new Symbol (sym.OR_ASSIGN, yycolumn, yyline, yytext());}


">>"					{ return new Symbol (sym.RIGHT_OP, yycolumn, yyline, yytext());}
"<<"					{ return new Symbol (sym.LEFT_OP, yycolumn, yyline, yytext());}
"++"					{ return new Symbol (sym.INC_OP, yycolumn, yyline, yytext());}
"--"					{ return new Symbol (sym.DEC_OP, yycolumn, yyline, yytext());}
"->"					{ return new Symbol (sym.PTR_OP, yycolumn, yyline, yytext());}
"&&"					{ return new Symbol (sym.AND_OP, yycolumn, yyline, yytext());}
"||"					{ return new Symbol (sym.OR_OP, yycolumn, yyline, yytext());}
"<="					{ return new Symbol (sym.LE_OP, yycolumn, yyline, yytext());}
">="					{ return new Symbol (sym.GE_OP, yycolumn, yyline, yytext());}
"=="					{ return new Symbol (sym.EQ_OP, yycolumn, yyline, yytext());}
"!="					{ return new Symbol (sym.NE_OP, yycolumn, yyline, yytext());}

/* ---- Atribution ---- */
"="					    { return new Symbol (sym.EQ, yycolumn, yyline, yytext());}

/* ---- Separators ---- */
"("					    { return new Symbol (sym.LPAREN, yycolumn, yyline, yytext());}
")"					    { return new Symbol (sym.RPAREN, yycolumn, yyline, yytext());}
"{"|"<%"			  { return new Symbol (sym.LBRACE, yycolumn, yyline, yytext());}
"}"|"%>"			  { return new Symbol (sym.RBRACE, yycolumn, yyline, yytext());}
"["|"<:"			  { return new Symbol (sym.LBRACK, yycolumn, yyline, yytext());}
"]"|":>"			  { return new Symbol (sym.RBRACK, yycolumn, yyline, yytext());}
";"				      { return new Symbol (sym.SEMICOLON, yycolumn, yyline, yytext());}
","					    { return new Symbol (sym.COMMA, yycolumn, yyline, yytext());}
"."					    { return new Symbol (sym.DOT, yycolumn, yyline, yytext());}

/* ---- Arithmetic operators ---- */
"+"				 	    { return new Symbol (sym.PLUS, yycolumn, yyline, yytext());}
"-"					    { return new Symbol (sym.MINUS, yycolumn, yyline, yytext());}
"*"					    { return new Symbol (sym.MULT, yycolumn, yyline, yytext());}
"/"					    { return new Symbol (sym.DIV, yycolumn, yyline, yytext());}
"%"					    { return new Symbol (sym.MOD, yycolumn, yyline, yytext());}

/* ---- Operator ---- */
":"					    { return new Symbol (sym.COLON, yycolumn, yyline, yytext());}

/* ---- Logic operators----  */
"<"					    { return new Symbol (sym.LT, yycolumn, yyline, yytext());}
">"					    { return new Symbol (sym.GT, yycolumn, yyline, yytext());}
"&"					    { return new Symbol (sym.AND, yycolumn, yyline, yytext());}
"!"					    { return new Symbol (sym.NOT, yycolumn, yyline, yytext());}
"~"					    { return new Symbol (sym.COMP, yycolumn, yyline, yytext());}
"|"					    { return new Symbol (sym.OR, yycolumn, yyline, yytext());}
"^"					    { return new Symbol (sym.XOR, yycolumn, yyline, yytext());}
"?"					    { return new Symbol (sym.QUESTION, yycolumn, yyline, yytext());}

{Comments}              { /* skip it */ }

{WS}+					{ /* whitespace separates tokens */ }
.					    { return new Symbol (sym.error, yycolumn, yyline, yytext());}
