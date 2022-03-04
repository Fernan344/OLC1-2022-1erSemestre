/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author teval
 */
public class Transicion implements Comparable<Transicion> {
    /**
     * <code>Estado</code> destino de esta <code>Transicion</code>.
     */
    private Estado estado;
   
    /**
     * Simbolo del alfabeto para esta <code>Transicion</code>.
     */
    private String simbolo;

    /**
     * Construye una <code>Transicion</code> especificando los dos
     * atributos de la misma.
     *
     * @param estado El <code>Estado</code> destino para esta <code>Transicion</code>.
     * @param simbolo El simbolo para esta <code>Transicion</code>.
     */
    public Transicion(Estado estado, String simbolo) {
        this.estado  = estado;
        this.simbolo = simbolo;
    }

    /**
     * Contruye una <code>Transicion</code> sin <code>Estado</code>
     * ni simbolo.
     */
    public Transicion() {
        this(null, null);
    }

    /**
     * Obtiene el <code>Estado</code> destino de esta <code>Transicion</code>.
     * @return El <code>Estado</code> destino de esta <code>Transicion</code>.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el <code>Estado</code> destino de esta <code>Transicion</code>.
     * @param estado El nuevo <code>Estado</code> destino de esta <code>Transicion</code>.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
   
    /**
     * Obtiene el simbolo para esta <code>Transicion</code>.
     * @return El simbolo para esta <code>Transicion</code>.
     */
    public String getSimbolo() {
        return simbolo;
    }
   
    /**
     * Establece el simbolo para esta <code>Transicion</code>.
     * @param simbolo El nuevo simbolo para esta <code>Transicion</code>.
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
   
    @Override
    public String toString() {
        return "(" + getEstado() + ", " + getSimbolo() + ")";
    }

    public int compareTo(Transicion obj) {
        Estado e1 = this.getEstado();
        Estado e2 = obj.getEstado();
       
        int diferencia = e1.getIdentificador() - e2.getIdentificador();
       
        if (diferencia != 0)
            return diferencia;
       
        String s1 = this.getSimbolo();
        String s2 = obj.getSimbolo();
       
        return s1.compareTo(s2);
    }
}
