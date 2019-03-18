/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

/**
 *
 * @author sergi
 */
public class Label extends JLabel {

    public int x, y;
    public String color;

    public Label(String fuente, int tam, int x, int y, String color, int negrita, int cursiva, String nombre) {
        super();
        this.setLayout(null);
        int ancho = nombre.length()*10;
        setBounds(x, y, ancho, 40);
        this.color = color.replace("#", "");
        int total = negrita + cursiva;
        this.setText(nombre);
        this.setForeground(new Color(hex(this.color)));
        String newfuente = averiguar_tipo_letra(fuente);
        Font fuent = new Font(newfuente,total,tam);
                            //tipo,bold/italic/etc,tamío
        this.setFont(fuent);
        repaint();
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    public String averiguar_tipo_letra(String tipo) {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fontNames.length; i++) {
            if (fontNames[i].equalsIgnoreCase(tipo)) {
                return fontNames[i];
            }
        }
        return "";
    }

}
