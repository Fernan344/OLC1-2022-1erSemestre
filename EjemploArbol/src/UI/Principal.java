/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author teval
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaEntrada = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSalida = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaEntrada.setColumns(20);
        jTextAreaEntrada.setRows(5);
        jScrollPane1.setViewportView(jTextAreaEntrada);

        jTextAreaSalida.setColumns(20);
        jTextAreaSalida.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSalida);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Parser");

        jMenuItem1.setText("Analizar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);       
        
        // indica cual fue la accion de usuario sobre el jfilechooser
        int resultado = selectorArchivos.showOpenDialog(this);
        
        File archivo = selectorArchivos.getSelectedFile(); // obtiene el archivo seleccionado

        // muestra error si es inválido
        if ((archivo == null) || (archivo.getName().equals(""))) {
            JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
        }else{        
            try {
                Scanner obj = new Scanner(archivo);
                String entrada = "";
                while (obj.hasNextLine()){
                    entrada+=obj.nextLine()+'\n';                
                }
                jTextAreaEntrada.setText(entrada);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        interpretar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void interpretar() {
        File file = new File("./public/parse.txt");  
        crearArchivo(file, jTextAreaEntrada.getText());
        
        analizadores.Sintactico pars;
        LinkedList<Instruccion> AST_arbolSintaxisAbstracta=null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(file)));
            pars.parse();        
            AST_arbolSintaxisAbstracta=pars.getAST();
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
        ejecutarAST(AST_arbolSintaxisAbstracta);
    }
    
    private static void ejecutarAST(LinkedList<Instruccion> ast) {
        if(ast==null){
            System.out.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
            return;
        }
        //Se crea una tabla de símbolos global para ejecutar las instrucciones.
        TablaDeSimbolos ts=new TablaDeSimbolos();
        //Se ejecuta cada instruccion en el ast, es decir, cada instruccion de 
        //la lista principal de instrucciones.
        for(Instruccion ins:ast){
            //Si existe un error léxico o sintáctico en cierta instrucción esta
            //será inválida y se cargará como null, por lo tanto no deberá ejecutarse
            //es por esto que se hace esta validación.
            if(ins!=null)
                ins.ejecutar(ts);
        }
    }
    
    private void crearArchivo(File file, String contenido){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);
            String [] lineas = contenido.split("\n");

            for (String linea: lineas)
                pw.println(linea);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaEntrada;
    private javax.swing.JTextArea jTextAreaSalida;
    // End of variables declaration//GEN-END:variables
}
