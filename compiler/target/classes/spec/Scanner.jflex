package compiler.generated;

import java_cup.runtime.*;

%%

%public
%standalone
%unicode
%line
%column
%char
%class Scanner

%eof{
    System.out.println("End of file.");
%eof}

NUMBER = [0-9]+
SPACE = [ \t\r\n\f]

%%
{NUMBER} { System.out.println(String.format("Found the number %s at line %d, column %d, char %d", yytext(), yyline, yycolumn, yychar)); }
{SPACE}	 { /* ignore white space. */ }
. { System.err.println(String.format("Illegal character %s at line %d, column %d, char %d", yytext(), yyline, yycolumn, yychar)); }