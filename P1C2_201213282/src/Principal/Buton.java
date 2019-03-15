/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentFs.NodoFs;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;

/**
 *
 * @author sergi
 */
public class Buton extends JButton {

    String referencia;
    NodoFs accion;
    String id, color;
    int largo, ancho;
    int tam;
    String texto;

    public Buton(String id,String fuente, int tam, String color, int x, int y, String referencia, String texto, int alto, int ancho) {
        this.tam = tam;
        this.id =id;
        this.setLayout(null);
        this.color = color.replace("#", "").trim();
        this.referencia = referencia;
        this.setText(texto);
        this.setBounds(x, y, ancho, alto);
        String tipofu = averiguar_tipo_letra(fuente);
        Font font = new Font(tipofu, 0, tam);
        this.setFont(font);
        this.setForeground(new Color(hex(this.color)));
        this.accion = null;
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    public boolean add_accion(NodoFs accion){
        this.accion = accion;
        return true;
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
