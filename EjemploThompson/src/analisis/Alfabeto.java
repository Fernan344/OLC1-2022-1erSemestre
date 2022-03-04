/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author teval
 */
public class Alfabeto implements Iterable<String>{
    
    public static final String VACIO = "é";
    private Vector<String> simbolos;

    public Alfabeto(String caracteres) {
        String[] arregloTemp = new String[caracteres.length()];
        
        for (int i=0; i < caracteres.length(); i++)
            arregloTemp[i] = "" + caracteres.charAt(i);
       
        Arrays.sort(arregloTemp);
       
        simbolos = new Vector<String>(arregloTemp.length);
        for(int i=0; i<arregloTemp.length; i++){
            String temp = arregloTemp[i];
            if(!simbolos.contains(temp))
                simbolos.add(temp);
        }
    }   
    
    public int getCantidad() {
        return simbolos.size();
    }
    
    @Override
    public String toString() {
        String salida = "{";
       
        for (int i=0; i < this.getCantidad(); i++) {
            salida += simbolos.get(i);
           
            if (i < this.getCantidad()-1)
                salida += ", ";
        }
       
        return salida;
    }
    
    public boolean contiene(String caracter) {
        return simbolos.contains(caracter);
    }
    
    public String getSimbolo(int pos) {
        if (pos == getCantidad())
            return Alfabeto.VACIO;
        else
            return simbolos.get(pos);
    }

    @Override
    public Iterator<String> iterator() {
        return simbolos.iterator();
    }    
    
    public int obtenerPosicion(String caracter) {
        if (caracter.equals(Alfabeto.VACIO))
            return getCantidad();
        else
            return simbolos.indexOf(caracter);
    }
    
    
}
