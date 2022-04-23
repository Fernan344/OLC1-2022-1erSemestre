import Arbol from '../simbolo/Arbol';
import tablaSimbolo from '../simbolo/tablaSimbolo';
import Tipo from '../simbolo/Tipo';

export abstract class Instruccion {
  public tipoDato: Tipo;
  public linea: number;
  public columna: number;

  constructor(tipo: Tipo, linea: number, columna: number) {
    this.tipoDato = tipo;
    this.linea = linea;
    this.columna = columna;
  }

  abstract interpretar(arbol: Arbol, tabla: tablaSimbolo): any;
}