import { Instruccion } from '../abstracto/Instruccion';
import Arbol from '../simbolo/Arbol';
import tablaSimbolo from '../simbolo/tablaSimbolo';
import Tipo, {tipoDato} from '../simbolo/Tipo';

export default class Operacion extends Instruccion {
  valor: any;
  private tipo: tipoDato;
  private opIz: Operacion;
  private opDer: Operacion;

  constructor(tipo: tipoDato, valor: any, fila: number, columna: number) {
    super(fila, columna);
    this.valor = valor;
    this.tipo = tipo;
  }

  interpretar(arbol: Arbol, tabla: tablaSimbolo) {
    if(this.tipo === tipoDato.IDENTIFICADOR){
      return tabla.getValor(this.valor).getvalor();
    }else{
      console.log(this.valor)
    }
    
    return this.valor;
  } 


}

