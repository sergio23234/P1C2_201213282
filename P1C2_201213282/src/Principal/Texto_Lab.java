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
public class Texto_Lab extends JTextField {

    public String nombre;

    public Texto_Lab(int alto, int ancho, String fuente, int tam, String color, int x, int y, int ng, String defecto, String nombre) {
        super();
        this.nombre = nombre;
        String Fondo = averiguar_tipo_letra(fuente);
        Font fondo = new Font(Fondo, ng,tam);
        if (!defecto.equalsIgnoreCase("nulo") && !defecto.equalsIgnoreCase("undefined")) {
            this.setText(defecto);
        }
        this.setBounds(x, y, ancho, alto);
        this.setFont(fondo);
        color = color.replace("#", "").trim();
        this.setForeground(new Color(hex(color)));
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
        return "Arial";
    }

}
