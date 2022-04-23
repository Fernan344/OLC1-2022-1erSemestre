import { Instruccion } from '../abstracto/Instruccion';
import Operacion from '../expresiones/Nativo';
import Arbol from '../simbolo/Arbol';
import Simbolo from '../simbolo/Simbolo';
import tablaSimbolo from '../simbolo/tablaSimbolo';
import Tipo, {tipoDato} from '../simbolo/Tipo';

export default class Declaracion extends Instruccion {
    private id: String;
    private tipo: Tipo;
    private valor: Operacion;

    constructor(id: String, tipo: Tipo, valor: Operacion, linea: number, columna: number) {
        super(new Tipo(tipoDato.INDEFINIDO), linea, columna);
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    public interpretar(arbol: Arbol, tabla: tablaSimbolo) {
        tabla.setValor(this.id, new Simbolo(this.tipo, this.id, this.valor.interpretar(arbol, tabla)));
        return null;
    }
}