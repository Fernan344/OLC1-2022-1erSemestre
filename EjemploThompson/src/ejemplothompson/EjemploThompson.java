/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplothompson;

import algoritmos.Subconjuntos;
import analisis.Alfabeto;
import analisis.AnalizadorSintactico;
import estructuras.AFD;
import estructuras.AFN;
import estructuras.TablaTransicion;

/**
 *
 * @author teval
 */
public class EjemploThompson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Alfabeto alfa = new Alfabeto("abc");
        String er = "(a|b)*a+c?";
        
        AnalizadorSintactico as = new AnalizadorSintactico(alfa, er);
        
        /*
         *  CONVERSION REGEX -> AFN
         *  ALGORITMO DE THOMPSON
         */ 
       
        AFN salida = as.analizar();
        System.out.printf("AFN:\n%s", salida);
        
        /* Imprimir la Tabla transicion del AFN */
        System.out.println();
        TablaTransicion tabla = salida.getTablaTransicion();
        
               
        for (int i=0; i < tabla.getColumnCount(); i++)
            System.out.printf("%s\t", tabla.getColumnName(i));
       
        System.out.println();
        for (int i=0; i < tabla.getRowCount(); i++) {
            for (int j=0; j < tabla.getColumnCount(); j++)
                System.out.printf("%s\t", tabla.getValueAt(i, j));
           
            System.out.println();
        }
       
        System.out.printf("\nDerivaciones:\n%s", as.getLog());
        
        
        System.out.println();
        AFD afd = Subconjuntos.getAFD(salida);
        
        System.out.printf("AFD:\n%s", afd);
        System.out.printf("\nEstados AFD:\n%s", afd.estadosDtoString());
       
        /* Imprimir la Tabla transicion del AFD */
        System.out.println();
        TablaTransicion tabla2 = afd.getTablaTransicion();
       
        for (int i=0; i < tabla2.getColumnCount(); i++)
            System.out.printf("%s\t\t", tabla2.getColumnName(i));
       
        System.out.println();
        for (int i=0; i < tabla2.getRowCount(); i++) {
            for (int j=0; j < tabla2.getColumnCount(); j++)
                System.out.printf("%s\t\t", tabla2.getValueAt(i, j));
           
            System.out.println();
        }
        
        System.out.printf("\nConjuntos estados producidos:\n%s", Subconjuntos.getLog());
    }
    
}
