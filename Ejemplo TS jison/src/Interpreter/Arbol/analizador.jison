%{
//codigo js
const inicio = require('../../controllers/indexControllers');
const nativo = require('./expresiones/Nativo');
const aritmetico = require('./expresiones/Aritmetico');
const Tipo = require('./simbolo/Tipo');
const impresion = require('./instrucciones/Imprimir');
const declaracion = require('./instrucciones/Declaracion')
%}
//definicion lexica
%lex 


%options case-insensitive 
//inicio analisis lexico
%%
"imprimir"      return 'RESPRINT';
"entero"        return 'RESINT';

";"             return 'PTCOMA';
"("             return 'PARABRE';
")"             return 'PARCIERRA';
"+"             return 'MAS';
"-"             return 'MENOS';
"="             return 'IGUAL';
"/"             return 'DIVI';
"*"             return 'POR';

[ \r\t]+ { }
\n {}
\"[^\"]*\"                  { yytext=yytext.substr(1,yyleng-2); return 'CADENA'; }
[0-9]+                      return 'ENTERO';
[A-Za-z]+["_"0-9A-Za-z]*    return 'IDENTIFICADOR';
<<EOF>>                     return 'EOF';
.                           return 'INVALID'

/*
imprimir ( "t123!@#3345exto" ) ;
imprimir(34);
imprimir(3.5);
*/

/lex
//Precedencia
%left 'POR' 'DIVI'
%left 'MAS' 'MENOS'


%start INIT
//Inicio
//Definicion de gramatica
%%

INIT: INSTRUCCIONES EOF     {return $1;}
;

INSTRUCCIONES : INSTRUCCIONES INSTRUCCION {$1.push($2); $$=$1;}
              | INSTRUCCION               {$$=[$1];}
;

INSTRUCCION : IMPRIMIR              {$$=$1;}
            | DECLARACION           {$$=$1;}
            | INVALID               {inicio.listaErrores.push(new errores.default('ERROR LEXICO',$1,@1.first_line,@1.first_column));}
            | error  PTCOMA         {inicio.listaErrores.push(new errores.default('ERROR SINTACTICO',"Se esperaba token",@1.first_line,@1.first_column));}
;

DECLARACION:
            RESINT IDENTIFICADOR IGUAL EXPRESION PTCOMA {$$=new declaracion.default($2, new Tipo.default(Tipo.tipoDato.ENTERO), $4, @1.first_line, @1.first_column);}
;

IMPRIMIR : RESPRINT PARABRE EXPRESION PARCIERRA PTCOMA {$$=new impresion.default($3,@1.first_line,@1.first_column);}
;

EXPRESION :
            EXPRESION MAS EXPRESION {$$ = new aritmetico.default(aritmetico.tipoOp.SUMA, $1, $3, @1.first_line, @1.first_column)}
          | IDENTIFICADOR {$$ = new nativo.default(new Tipo.default(Tipo.tipoDato.IDENTIFICADOR), $1, @1.first_line, @1.first_column);}
          | ENTERO {$$= new nativo.default(new Tipo.default(Tipo.tipoDato.ENTERO),$1, @1.first_line, @1.first_column);}
          | CADENA {$$= new nativo.default(new Tipo.default(Tipo.tipoDato.CADENA),$1, @1.first_line, @1.first_column);}
;