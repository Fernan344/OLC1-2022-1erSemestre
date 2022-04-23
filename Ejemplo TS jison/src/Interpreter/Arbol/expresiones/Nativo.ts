import { Instruccion } from '../abstracto/Instruccion';
import Arbol from '../simbolo/Arbol';
import tablaSimbolo from '../simbolo/tablaSimbolo';
import Tipo, { tipoDato } from '../simbolo/Tipo';

export default class Nativo extends Instruccion {
  valor: any;

  constructor(tipo: Tipo, valor: any, fila: number, columna: number) {
    super(tipo, fila, columna);
    this.valor = valor;
  }

  interpretar(arbol: Arbol, tabla: tablaSimbolo) {
    if(this.tipoDato.getTipo() === tipoDato.ENTERO){
        return this.valor;
    }else if(this.tipoDato.getTipo() === tipoDato.CADENA){
      return this.valor.toString();
    }else if(this.tipoDato.getTipo() === tipoDato.IDENTIFICADOR){
      let value = tabla.getValor(this.valor)
      console.log(value)
    }
    
  }
}

