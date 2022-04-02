export default class Tipo {
  private tipo: tipoDato;

  constructor(tipo: tipoDato) {
    this.tipo = tipo;
  }
  public getTipo(): tipoDato {
    return this.tipo;
  }
  public setTipo(tipo: tipoDato): void {
    this.tipo = tipo;
  }
}

export enum tipoDato {
  ENTERO,
  CADENA,
  IDENTIFICADOR,
  SUMA,
  INDEFINIDO
}
