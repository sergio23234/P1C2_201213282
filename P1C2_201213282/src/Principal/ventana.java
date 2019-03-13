/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sergio Fernando
 */
public class ventana extends JFrame {

    private String id;
    private ArrayList<contenedor> Contenedores;
    
    public ventana(String id) {
        super();
        this.id = id;
        Contenedores = new ArrayList();
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    public void inicializar_ventana(int largo, int ancho, String color) {
        setBounds(0, 0, ancho, largo);
        color = color.replace("#", "").trim();
        setBackground(new Color(hex(color)));
        repaint();
    }
   
    public void add_contenedor(String id,int x,int y,int ancho,int largo,String color){
        contenedor nuevo = new contenedor(id);
        nuevo.inicializar_contendor(x, y, largo, ancho, color);
        Contenedores.add(nuevo);
        this.add(nuevo);
    }
   
}
