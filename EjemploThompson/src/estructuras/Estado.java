/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import analisis.Alfabeto;
import java.util.HashMap;

/**
 *
 * @author teval
 */
public class Estado implements Comparable<Estado>{
    
    private int identificador;
    private boolean esFinal;
    private String etiqueta;
    private Conjunto<Transicion> transiciones;
    private boolean visitado;
    
    public Estado(int identificador) {
         this(identificador, false);
    }
    
    public Estado(int identificador, boolean esFinal) {
        setIdentificador(identificador);
        setEsFinal(esFinal);
        setEtiqueta(String.valueOf(identificador));
        transiciones = new Conjunto<Transicion>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.identificador;
        return hash;
    }

    public int compareTo(Estado obj) {
        return this.identificador - obj.identificador;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isEsFinal() {
        return esFinal;
    }

    public void setEsFinal(boolean esFinal) {
        this.esFinal = esFinal;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Conjunto<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(Conjunto<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
    public boolean getEsInicial() {
        return identificador == 0;
    }
    
    public boolean getVisitado() {
        return visitado;
    }
    
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
     public boolean getEsIdentidad() {
        for (Transicion trans : getTransiciones())
            if (!this.equals(trans.getEstado()))
                return false;
       
        return true;
    }
     
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
       
        if (getClass() != obj.getClass())
            return false;
       
        final Estado other = (Estado) obj;
        if (this.identificador == other.identificador)
            return true;
       
        return false;
    }
    
    @Override
    public String toString() {
        String valor;
       
        if (getEtiqueta().equals(""))
            valor = String.valueOf(identificador);
        else
            valor = getEtiqueta();
       
        if (getEsInicial())
            valor += "i";
       
        if (isEsFinal())
            valor += "f";
               
        return valor;
    }
    
    public HashMap<String, Estado> getTransicionesSegunAlfabeto(Alfabeto alfabeto) {
        HashMap<String, Estado> trans = new HashMap<String, Estado>();
       
        /* Rellenamos todo con null */
        for (String s : alfabeto)
            trans.put(s, null);
       
        /* Reemplazamos las transiciones existentes */
        for (Transicion t : getTransiciones())
            trans.put(t.getSimbolo(), t.getEstado());
       
        return trans;
    }

    public boolean getEsFinal() {
        return esFinal;
    }
}
