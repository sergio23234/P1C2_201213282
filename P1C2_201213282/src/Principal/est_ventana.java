/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentFs.NodoFs;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class est_ventana extends javax.swing.JFrame {

    public String id;
    private String color;
    public boolean alcerrar,alcargar;
    public NodoFs cerrar,cargar;
    public ArrayList<contenedor> contenedores;
    private boolean primero = true;
    /**
     * Creates new form ventana1
     */
    private int max_alto, max_ancho;

    public est_ventana(String id, int alto, int ancho, String color) {
        initComponents();
        this.id = id;
        contenedores = new ArrayList();
        setBounds(0, 0, ancho, alto);
        Scrollpane.setBounds(0, 0, alto, ancho);
        max_alto = alto;
        max_ancho = ancho;
        this.setResizable(false);
        this.color = color.replace("#", "").trim();
        master.setBackground(new Color(hex(this.color)));
        Refrescar();
        alcerrar=alcargar=false;
        cerrar = null;
        cargar=null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Scrollpane = new javax.swing.JScrollPane();
        master = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        master.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout masterLayout = new javax.swing.GroupLayout(master);
        master.setLayout(masterLayout);
        masterLayout.setHorizontalGroup(
            masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        masterLayout.setVerticalGroup(
            masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        Scrollpane.setViewportView(master);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Scrollpane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Scrollpane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Refrescar() {
        master.setPreferredSize(new Dimension(max_ancho, max_alto));
        master.repaint();
    }

    public boolean add_contenedor(int nump,String id, int alto, int ancho, String color, boolean boder, int x, int y) {
        contenedor nuevo = new contenedor(id);
        nuevo.inicializar_contendor(nump,alto, ancho, color, boder, x, y);
        boolean add = true;
        for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).id.equalsIgnoreCase(id)) {
                add = false;
                break;
            }
        }
        if (add) {
            if (primero) {
                primero = false;
                if (alto > this.max_alto) {
                    max_alto = alto;
                }
                if (ancho > this.max_ancho) {
                    max_ancho = ancho;
                }
            } else {
                this.max_alto += alto;
                this.max_ancho += ancho;
            }
            contenedores.add(nuevo);
            master.add(nuevo);
            Refrescar();
            return true;
        }
        return false;
    }

    public boolean add_boton(String id_con, String id, String fuente, int tam, String color, int x, int y, String referencia, String texto, int alto, int ancho) {
        for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).id.equalsIgnoreCase(id_con)) {
                return contenedores.get(i).add_boton(id, fuente, tam, color, x, y, referencia, texto, alto, ancho);
            }
        }
        return false;
    }

    public boolean ID_Contenedor(String id_con) {
        for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).id.equalsIgnoreCase(id_con)) {
                return true;
            }
        }
        return false;
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scrollpane;
    private javax.swing.JPanel master;
    // End of variables declaration//GEN-END:variables

    boolean ID_Contenedor_boton(String id) {
        for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).Buscar_boton(id)) {
                return true;
            }
        }
        return false;
    }

    boolean set_FS_boton(String id, NodoFs accion) {
         for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).Buscar_boton(id)) {
                return contenedores.get(i).set_FS_boton(id,accion);
            }
        }
        return false;
    }

    public int ID_intContenedor(String id_con) {
        for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).id.equalsIgnoreCase(id_con)) {
                return i;
            }
        }
        return -1;
    }

    
}
