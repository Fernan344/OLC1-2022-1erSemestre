import Simbolo from './Simbolo';

export default class tablaSimbolo {
  private tablaAnterior: tablaSimbolo | any;
  private tablaActual: Map<String, Simbolo>;

  constructor(anterior?: tablaSimbolo) {
    this.tablaAnterior = anterior;
    this.tablaActual = new Map<String, Simbolo>();
  }

  public getValor(id: String): any{
    let valor = this.tablaActual.get(id);
    return valor;
  }

  public setValor(id: String, valor: Simbolo): any{
    this.tablaActual.set(id, valor);

    console.log(id+"="+this.tablaActual.get(id)?.getvalor())
    return null;
  }

  public getAnterior() {
    return this.tablaAnterior;
  }
  public setAnterior(anterior: tablaSimbolo) {
    this.tablaAnterior = anterior;
  }
  public getTabla() {
    return this.tablaActual;
  }
  public setTabla(Tabla: Map<String, Simbolo>) {
    this.tablaActual = Tabla;
  }
}
