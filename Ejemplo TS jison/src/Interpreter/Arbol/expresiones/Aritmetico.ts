import { Instruccion } from '../abstracto/Instruccion';
import Arbol from '../simbolo/Arbol';
import tablaSimbolo from '../simbolo/tablaSimbolo';
import Tipo, {tipoDato} from '../simbolo/Tipo';

export default class Aritmetico extends Instruccion {
  operacionIzq: Instruccion;
  operacionDer: Instruccion;
  tipo: tipoOp;
  

  constructor(tipo: tipoOp, opIzq: Instruccion, opDer: Instruccion, fila: number, columna: number) {
    super(new Tipo(tipoDato.INDEFINIDO), fila, columna);
    this.tipo = tipo;
    this.operacionIzq = opIzq;
    this.operacionDer = opDer;
  }

  interpretar(arbol: Arbol, tabla: tablaSimbolo) {
        if(this.tipo===tipoOp.SUMA){

            let valueIzq = this.operacionIzq.interpretar(arbol, tabla);
            let valueDer = this.operacionDer.interpretar(arbol, tabla);
            
            if(this.operacionIzq.tipoDato.getTipo() === tipoDato.ENTERO){
                if(this.operacionDer.tipoDato.getTipo() === tipoDato.ENTERO){
                    return (Number(valueIzq)+Number(valueDer));
                }
            }
        }
        this.tipoDato.setTipo(tipoDato.ENTERO);
        return null;
  }
}

export enum tipoOp{
    SUMA,
    RESTA,
    DIVISION,
    MULTIPLICACION
}