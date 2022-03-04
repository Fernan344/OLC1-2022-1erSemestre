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
public abstract class Automata {
    
    public abstract TablaTransicion getTablaTransicion();
    /**
     * Conjunto de estados del automata.
     */
    protected Conjunto<Estado> estados;
   
    /**
     * Expresion regular para este automata.
     */
    protected String exprReg;
   
    /**
     * Alfabeto para este automata.
     */
    protected Alfabeto alfabeto;
   
    /**
     * Alfabeto para este automata.
     */
    protected String LogProceso;
   
    /**
     * Constructor por defecto.
     */
    protected Automata() {
       this(null, "");
    }
    
    protected Automata(Alfabeto alfabeto, String exprReg) {
        estados = new Conjunto<Estado>();
        setAlfabeto(alfabeto);
        setExprReg(exprReg);
    }

    public Conjunto<Estado> getEstados() {
        return estados;
    }

    public void setEstados(Conjunto<Estado> estados) {
        this.estados = estados;
    }

    public String getExprReg() {
        return exprReg;
    }

    public void setExprReg(String exprReg) {
        this.exprReg = exprReg;
    }

    public Alfabeto getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getLogProceso() {
        return LogProceso;
    }

    public void setLogProceso(String LogProceso) {
        this.LogProceso = LogProceso;
    }
    
     public Estado getEstadoInicial() {
        return estados.obtenerPrimero();
    }
     
     public Conjunto<Estado> getEstadosFinales() {
        Conjunto<Estado> finales = new Conjunto<Estado>();
       
        for (Estado tmp : estados)
            if (tmp.isEsFinal())
                finales.agregar(tmp);
       
        return finales;
    }
     
     public Conjunto<Estado> getEstadosNoFinales() {
        Conjunto<Estado> noFinales = new Conjunto<Estado>();
       
        for (Estado tmp : estados)
            if (!tmp.isEsFinal())
                noFinales.agregar(tmp);
       
        return noFinales;
    }
     
     public void agregarEstado(Estado estado) {
        estados.agregar(estado);
    }
     
    public Estado getEstado(int pos) {
        return estados.obtener(pos);
    }
    
    public int cantidadEstados() {
        return estados.cantidad();
    }
    
    public void iniciarRecorrido() {
        for (Estado tmp : estados)
            tmp.setVisitado(false);
    }
    
    @SuppressWarnings("unchecked")
    protected TablaTransicion cargarTablaTransicion(int cantFil, int cantCol, int colDesde) {
        /* Cabeceras de las columnas de la tabla de transiciones */
        String[] cabecera = new String[cantCol];
       
        /* Datos de la tabla de transiciones */
        Object[][] datos = new Object[cantFil][cantCol];
       
        /* Titulo para los Estados */
        cabecera[colDesde] = "Estados";
       
        /* Cargamos la cabecera con simbolos del Alfabeto */
        for (int i=colDesde + 1; i < cantCol; i++)
            cabecera[i] = getAlfabeto().getSimbolo(i - colDesde - 1);
       
        /* Cargamos la primera columna de datos con Estados */
        for (int i=0; i < cantFil; i++)
            datos[i][colDesde] = getEstado(i);
       
        /* Cargamos las transiciones */
        for (Estado e : getEstados()) {
            int fil = e.getIdentificador();
           
            for (Transicion t : e.getTransiciones()) {
                int col = getAlfabeto().obtenerPosicion(t.getSimbolo());
               
                if (datos[fil][col + colDesde + 1] == null)
                    datos[fil][col + colDesde + 1] = new Conjunto<Integer>();
               
                int id = t.getEstado().getIdentificador();
                ((Conjunto<Integer>) datos[fil][col + colDesde + 1]).agregar(id);
            }
        }
       
        /* Cambiamos las celdas "null" por cadenas vacias */
        String vacio = "";
        for (int i=0; i < cantFil; i++) {
            for (int j=colDesde + 1; j < cantCol; j++) {
                if (datos[i][j] == null)
                    datos[i][j] = vacio;
                else {
                    @SuppressWarnings("rawtypes")
					Conjunto c = (Conjunto) datos[i][j];
                    if (c.cantidad() == 1)
                        datos[i][j] = c.obtenerPrimero();
                }
            }
        }
       
        return new TablaTransicion(cabecera, datos);
    }
        
    @Override
    public String toString() {
        String str = "";
       
        for (Estado tmp : getEstados()) {
            str += tmp.toString();
           
            for (Transicion trans : tmp.getTransiciones())
                str += " --> " + trans.getEstado() + "{" + trans.getSimbolo() + "}";
           
            str += "\n";
        }
       
        return str;
    }
    
    public static void copiarEstados(Automata afOrigen, Automata afDestino, int incremento) {
        copiarEstados(afOrigen, afDestino, incremento, 0);
    }
    
    public static void copiarEstados(Automata afOrigen, Automata afDestino,
                    int incrementoTrans, int omitidos) {
       
        /*
         * Cantidad que hay que incrementar al identificador
         * de un estado de afnOrigen para convertirlo en el
         * correspondiente estado de afnDestino.
         */
        int incrementoEst = incrementoTrans; // TODO:
                                             // Teoricamente, aqui deberia asignarse
                                             // afnDestino.cantidadEstados(), pero
                                             // el valor asignado actualmente tambien
                                             // funciona correctamente, por lo que se
                                             // se deja asi ya que no se pudo analizar
                                             // el impacto del cambio.
       
        /* Agregamos los nuevos estados para afnDestino */
        for (int i=omitidos; i < afOrigen.cantidadEstados(); i++)
            afDestino.agregarEstado(new Estado(afDestino.cantidadEstados()));
       
        /* Contador de omitidos */
        int contador = 0;
       
        /* Agregamos las transiciones de cada estado */
        for (Estado tmp : afOrigen.getEstados()) {
           
            if (omitidos > contador++)
                continue;
           
            /* Estado de afnDestino al cual se agregaran las transiciones */
            Estado objetivo = afDestino.getEstado(tmp.getIdentificador() + incrementoEst);
           
            /* Para cada estado, agregamos las transiciones */
            copiarTransiciones(afDestino, tmp.getTransiciones(), objetivo, incrementoTrans);
        }
    }
    
    public static void copiarTransiciones(Automata afDestino, Conjunto<Transicion> transiciones,
                        Estado objetivo, int incrementoTrans) {
       
        for (Transicion trans : transiciones) {
            int idDestino = trans.getEstado().getIdentificador();
            String simbolo = trans.getSimbolo();

            Estado estadoDestino = afDestino.getEstado(idDestino + incrementoTrans);
            Transicion nuevaTrans = new Transicion(estadoDestino, simbolo);

            objetivo.getTransiciones().agregar(nuevaTrans);
        }
    }
}
