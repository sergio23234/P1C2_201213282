/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentFs.NodoFs;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author sergi
 */
public class contenedor extends JPanel {

    public String id;

    private ArrayList<Texto_Lab> Textos;
    private ArrayList<Buton> Botones;

    public contenedor(String id) {
        super();
        this.id = id;
        Botones = new ArrayList();
    }

    public void inicializar_contendor(int alto, int ancho, String color, boolean border, int x, int y) {
        this.setBounds(x, y, ancho, alto);
        color = color.replace("#", "").trim();
        this.setBackground(new Color(hex(color)));
        if(border){
            this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        }
        this.repaint();
        this.setLayout(null);
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
    
    public boolean add_boton(String id,String fuente, int tam, String color, int x, int y, NodoFs referencia, String texto, int alto, int ancho){
        Buton bot = new Buton(fuente,tam,color,x,y,referencia,texto,alto,ancho);
        boolean add = true;
        for (int i = 0; i < Botones.size(); i++) {
            if (Botones.get(i).id.equalsIgnoreCase(id)) {
                add = false;
                return add;
            }
        }
        if(add){
            Botones.add(bot);
            this.add(bot);
            return true;
        }
        return false;
    }

}
