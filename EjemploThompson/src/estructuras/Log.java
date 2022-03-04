package estructuras;


import java.util.Vector;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author teval
 */
public class Log {
     private Vector<String> cadenas;
   
    /**
     * Construye un nuevo <code>Log</code> que
     * no contiene ningun texto.
     */
    public Log() {
        cadenas = new Vector<String>();
    }
   
    /**
     * Agrega una linea al <code>Log</code>.
     * @param linea La linea agregada al <code>Log</code>.
     * @return this (para encadenamiento de metodos).
     */
    public Log agregar(String linea) {
        cadenas.add(linea);
        return this;
    }
   
    /**
     * Agrega un caracter de fin de linea al <code>Log</code>.
     * @return this (para encadenamiento de metodos).
     */
    public Log nuevaLinea() {
        cadenas.add("\n");
        return this;
    }
   
    /**
     * Vacia las cadenas de este <code>Log</code>.
     * @return this (para encadenamiento de metodos).
     */
    public Log vaciar() {
        cadenas.clear();
        return this;
    }
   
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for (String s : cadenas)
            str.append(s);
       
        return str.toString();
    }
}
