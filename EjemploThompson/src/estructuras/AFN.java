/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import analisis.Alfabeto;

/**
 *
 * @author teval
 */
public class AFN extends Automata{
    public AFN(){
        super();
    }

    public AFN(Alfabeto alfabeto, String exprReg) {
        super(alfabeto, exprReg);
    }
   
    /**
     * Retorna la tabla de transicion de estados.
     * @return La tabla de transicion de estados.
     */
    public TablaTransicion getTablaTransicion() {
        int cantFil = getEstados().cantidad();
        int cantCol = getAlfabeto().getCantidad() + 2;
       
        return cargarTablaTransicion(cantFil, cantCol, 0);
    }
}
