/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author sergi
 */
public class contenedor extends JPanel {

    private String id;

    private ArrayList<Texto_Lab> Textos;

    public contenedor(String id) {
        super();
        this.id = id;
    }

    public void inicializar_contendor(int x, int y, int largo, int ancho, String color) {
        setBounds(x, y, ancho, largo);
        color = color.replace("#", "").trim();
        setBackground(new Color(hex(color)));
        repaint();
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    public void add_texto(int x,int y,int alto,int ancho,String color,String nombre,String fondo,int tamañio,int tipo,String texto)
    {
        Texto_Lab tex = new Texto_Lab(nombre);
        tex.inicializar(fondo, tamañio, tipo, color, x, y, alto, ancho,texto);
        Textos.add(tex);
        this.add(tex);
    }
    

}
