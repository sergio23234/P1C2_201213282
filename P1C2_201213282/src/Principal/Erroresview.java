/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import static Principal.Menu.Lista;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

/**
 *
 * @author sergi
 */
public class Erroresview extends javax.swing.JFrame {

    ArrayList<NodoError> errores;

    /**
     * Creates new form Erroresview
     */
    public Erroresview(ArrayList<NodoError> errores) {
        initComponents();
        this.errores = new ArrayList();
        this.errores = errores;
        Scroll.setViewportView(Texto);
        Scroll.repaint();
        Scroll.revalidate();
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
        Scroll = new javax.swing.JScrollPane();
        Texto = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Texto.setEditable(false);
        Texto.setContentType("text/html"); // NOI18N
        Scroll.setViewportView(Texto);

        jButton1.setBackground(new java.awt.Color(153, 255, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Descargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jButton1)
                .addGap(0, 311, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Scroll)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GuardarComo() ;
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JEditorPane Texto;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void Set_errores() {
        String texto = "<html>\n" + "<head><title>Errores</title></head>\n" + "<body>\n" + "<h1>Errores</h1><br>" + "<table border=1>\n";
        texto += "<tr>";
        texto += "<th>tipo</th>";
        texto += "<th>linea</th>";
        texto += "<th>columna</th>";
        texto += "<th>descripcion</th>";
        texto += "</tr>";
        for (int i = 0; i < errores.size(); i++) {
            NodoError actual = errores.get(i);
            texto += "<tr>";
            texto += "<td>" + actual.tipo + "</td>\n";
            texto += "<td>" + actual.linea + "</td>\n";
            texto += "<td>" + actual.columna + "</td>\n";
            texto += "<td>" + actual.descripcion + "</td>\n";
            texto += "</tr>";
        }
        texto += "</table>\n</body>\n</html>\n";
        Texto.setText(texto);
    }
    private void GuardarComo() {
        if (Lista.size() > 0) {
            String texto = Texto.getText();
            if (texto.matches("[[ ]*[\n]*[\t]]*")) {//compara si en el JTextArea hay texto sino muestrtra un mensaje en pantalla
                JOptionPane.showMessageDialog(null, "No hay texto para guardar!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
            } else {
                
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.html", "html", "HTML"));//filtro para ver solo archivos .edu
                int seleccion = fileChooser.showSaveDialog(null);
                try {
                    if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                        File JFC = fileChooser.getSelectedFile();
                        String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                        if (!(PATH.toLowerCase().endsWith(".html")) && !(PATH.toLowerCase().endsWith(".HTML"))) {//comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                            JOptionPane.showMessageDialog(null, "No hay una extension fijada", "Error al guardar el archivo!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String JTextArea = texto;
                            PrintWriter printwriter = new PrintWriter(JFC);
                            printwriter.print(JTextArea);//escribe en el archivo todo lo que se encuentre en el JTextArea
                            printwriter.close();//cierra el archivo
                            JOptionPane.showMessageDialog(null, "Guardado exitoso!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                } catch (Exception e) {//por alguna excepcion salta un mensaje de error
                    JOptionPane.showMessageDialog(null, "Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe existir por lo menos una pestaña para abrir un archivo", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
