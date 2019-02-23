/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentGxml.NodoGxml;
import Analizadores.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class Pestania extends javax.swing.JPanel {

    public String path;
    private ArrayList<NodoError> errores;
    private ArrayList<NodoError> lexicos;

    /**
     * Creates new form Pestania
     */
    public Pestania() {
        initComponents();
        path = "";
        errores = new ArrayList();
        lexicos = new ArrayList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Editor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Consola = new javax.swing.JTextArea();
        analizar = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(153, 153, 255));

        Editor.setColumns(20);
        Editor.setRows(5);
        jScrollPane1.setViewportView(Editor);

        Consola.setBackground(new java.awt.Color(102, 102, 102));
        Consola.setColumns(20);
        Consola.setForeground(new java.awt.Color(153, 255, 153));
        Consola.setRows(5);
        jScrollPane2.setViewportView(Consola);

        analizar.setBackground(new java.awt.Color(204, 255, 204));
        analizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        analizar.setForeground(new java.awt.Color(204, 0, 0));
        analizar.setText("Analizar");
        analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(analizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(analizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarActionPerformed
        if (!path.equals("")) {
            try {
                errores.clear();
                lexicos.clear();
                File archivo = new File(path);
                FileReader fr = new FileReader(archivo);
                LexicoGxml lex = new LexicoGxml(fr);
                SintacticoGxml miParser = new SintacticoGxml(lex);
                miParser.parse();
                NodoGxml Raiz = miParser.RCCSS;
                errores = miParser.errores;
                lexicos = lex.Elista;
                System.out.println("errores --->" + errores.size() + "--->" + miParser.errores.size());
                System.out.println("lexicos--------->" + lex.Elista.size());
                if (!(errores.size() > 0 || lexicos.size() > 0)) {
                    System.out.println("no hay errores");
                    Generar_Archivo_FS nuevo = new Generar_Archivo_FS(Raiz);
                    String datos[] = path.split("/");
                    String nombre = datos[datos.length-1];
                    nuevo.Generar_archivo(nombre,path.replace(nombre,""));
                }
                //Raiz.Recorrer_Ventanas();
            } catch (Exception ex) {
                Logger.getLogger(Pestania.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_analizarActionPerformed

    public ArrayList<NodoError> dev_errores() {
        ArrayList<NodoError> total = new ArrayList();
        for (int i = 0; i < errores.size(); i++) {
            total.add(errores.get(i));
        }
        for (int i = 0; i < lexicos.size(); i++) {
            total.add(lexicos.get(i));
        }
        return total;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea Consola;
    public javax.swing.JTextArea Editor;
    private javax.swing.JButton analizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
