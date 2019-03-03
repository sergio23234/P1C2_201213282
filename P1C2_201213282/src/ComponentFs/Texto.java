/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author sergi
 */
public class Texto extends JLabel {

    private String nombre;

    public Texto(String nombre) {
        super();
        this.nombre = nombre;
    }

    public void inicializar(String Fondo, int tamaño, int tipo, String color, int x, int y, int alto, int ancho,String texto) {
        Font fondo = new Font(Fondo, tamaño, tipo);
        color = color.replace("#", "").trim();
        setForeground(new Color(hex(color)));
        setBounds(x, y, alto,ancho);
        setFont(fondo);
        setText(texto);
        repaint();
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

}
