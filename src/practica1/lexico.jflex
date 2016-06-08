package practica1;
import java_cup.runtime.*;
import java.util.ArrayList;

%%
%public
%class Lexico
%unicode 
%line
%column
%cup

%{
public ArrayList<Nodo> errores = new ArrayList<>();
%}
letra=[a-zA-Z0-9]
id=[a-zA-Z0-9_]+
com=[\"]
cadena={com}([^"\""])*{com} 
caracter= [^\t\r\f\n\"\']
rgn=[~]
rango= {caracter}{rgn}{caracter}
%%
"%%"                { System.out.println("lex--> "+yytext()); return new Symbol(sym.PORC,new Nodo("PORC",yytext(), yyline,yycolumn)); }
"("                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.PARA,new Nodo("PARA",yytext(), yyline,yycolumn)); }
")"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.PARC,new Nodo("PARC",yytext(), yyline,yycolumn)); }
"."                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.PT,new Nodo("PT",yytext(), yyline,yycolumn)); }
"|"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.OR,new Nodo("OR",yytext(), yyline,yycolumn)); }
"*"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.POR,new Nodo("POR",yytext(), yyline,yycolumn)); }
"+"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.MAS,new Nodo("MAS",yytext(), yyline,yycolumn)); }
"?"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.INTER,new Nodo("INTER",yytext(), yyline,yycolumn)); }
","                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.CM,new Nodo("CM",yytext(), yyline,yycolumn)); }
":"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.DSPT,new Nodo("DSPT",yytext(), yyline,yycolumn)); }
"->"                { System.out.println("lex--> "+yytext()); return new Symbol(sym.FLECHA,new Nodo("FLECHA",yytext(), yyline,yycolumn)); }
";"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.PTCM,new Nodo("PTCM",yytext(), yyline,yycolumn)); }
"["                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.CORA,new Nodo("CORA",yytext(), yyline,yycolumn)); }
"]"                 { System.out.println("lex--> "+yytext()); return new Symbol(sym.CORC,new Nodo("CORC",yytext(), yyline,yycolumn)); }
"\\n"               { System.out.println("lex--> "+yytext()); return new Symbol(sym.SALTO,new Nodo("SALTO",yytext(), yyline,yycolumn)); }
"\\\'"              { System.out.println("lex--> "+yytext()); return new Symbol(sym.SIMPLE,new Nodo("SIMPLE",yytext(), yyline,yycolumn)); }
"\\\""              { System.out.println("lex--> "+yytext()); return new Symbol(sym.DOBLE,new Nodo("DOBLE",yytext(), yyline,yycolumn)); }
"\\t"               { System.out.println("lex--> "+yytext()); return new Symbol(sym.CARRO,new Nodo("CARRO",yytext(), yyline,yycolumn)); }
"[:blanco:]"        { System.out.println("lex--> "+yytext()); return new Symbol(sym.BLANCO,new Nodo("BLANCO",yytext(), yyline,yycolumn)); }
"[:todo:]"          { System.out.println("lex--> "+yytext()); return new Symbol(sym.TODO,new Nodo("TODO",yytext(), yyline,yycolumn)); }
"CONJ"              { System.out.println("lex--> "+yytext()); return new Symbol(sym.CONJ,new Nodo("CONJ",yytext(), yyline,yycolumn)); }
"retorno"           { System.out.println("lex--> "+yytext()); return new Symbol(sym.RETORNO,new Nodo("RETORNO",yytext(), yyline,yycolumn)); }
"RESERV"            { System.out.println("lex--> "+yytext()); return new Symbol(sym.RESERV,new Nodo("RESERV",yytext(), yyline,yycolumn)); }
"error"             { System.out.println("lex--> "+yytext()); return new Symbol(sym.ERROR,new Nodo("ERROR",yytext(), yyline,yycolumn)); }
{letra}             { System.out.println("lex--> "+yytext()); return new Symbol(sym.LETRA,new Nodo("LETRA",yytext(), yyline,yycolumn)); }
{rango}             { System.out.println("lex--> "+yytext()); return new Symbol(sym.RANGO,new Nodo("RANGO",yytext(), yyline,yycolumn)); }
{id}                { System.out.println("lex--> "+yytext()); return new Symbol(sym.ID,new Nodo("ID",yytext(), yyline,yycolumn));}
{cadena}            { System.out.println("lex--> "+yytext()); return new Symbol(sym.CAD,new Nodo("CAD",yytext(), yyline,yycolumn));}
[ \t\r\f\n]+        { /* Se ignoran */}
.                   { System.out.println("error lexico--> "+yytext()); Nodo e = new Nodo("error lexico",yytext(), yyline,yycolumn);errores.add(e);}