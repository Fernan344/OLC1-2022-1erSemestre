import Arbol from '../simbolo/Arbol';
import tablaSimbolo from '../simbolo/tablaSimbolo';
import Tipo from '../simbolo/Tipo';

export abstract class Instruccion {
  public linea: number;
  public columna: number;

  constructor(linea: number, columna: number) {
    this.linea = linea;
    this.columna = columna;
  }

  abstract interpretar(arbol: Arbol, tabla: tablaSimbolo): any;
}
