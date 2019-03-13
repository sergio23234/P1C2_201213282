/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author sergi
 */
public class NScroll extends JScrollPane {

    JPanel principal = new JPanel();
    int alto, ancho;
    int inial, inianch;
    private ArrayList<contenedor> contenedores;
    String color;

    public NScroll(int largo, int ancho, String color) {
        super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.alto = inial = largo;
        this.ancho = inianch = ancho;
        this.color = color.replace("#", "").trim();
        this.setBounds(0, 0, ancho,largo);
        this.setLayout(null);
        principal.setLayout(null);
        principal.setBounds(0,0,ancho,largo);
        principal.setBackground(new Color(hex(this.color)));
        contenedores = new ArrayList();
        this.setViewportView(principal);
        this.getViewport().setView(principal);
        repaint();
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    void iniciar() {
        //setLayout(null);
        /*for (int i = 0; i < contenedores.size(); i++) {
            principal.add(contenedores.get(i));
        }*/
        JLabel label = new JLabel("cualquiera");
        label.setBounds(5, 5, 60, 100);
        principal.add(label);
        principal.repaint();
        this.setViewportView(principal);
        this.getViewport().setView(principal);
        this.repaint();
        this.revalidate();
        this.repaint();
    }

    public boolean add_contenedor(String id, int alto, int ancho, String color, boolean boder, int x, int y) {
        contenedor nuevo = new contenedor(id);
        nuevo.inicializar_contendor(alto, ancho, color, boder, x, y);
        boolean add = true;
        for (int i = 0; i < contenedores.size(); i++) {
            if (contenedores.get(i).id.equalsIgnoreCase(id)) {
                add = false;
                break;
            }
        }
        if (add) {
            this.alto += alto;
            this.ancho += ancho;
            contenedores.add(nuevo);
            return true;
        }
        return false;
    }
        public void Refrescar() {
        repaint();
        principal.setPreferredSize(new Dimension(ancho, alto));
        /*if (pos_x > panel.getWidth()) {
            panel.setBounds(0, 0, pos_x, 40);
        }*/
        principal.repaint();
    }
}
