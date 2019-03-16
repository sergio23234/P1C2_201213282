/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author sergi
 */
public class Desplegable extends JComboBox {

    public String nombre;

    public Desplegable(int alto, int ancho, ArrayList<String> lista, int x, int y, String defecto, String nombre) {
        for (int i = 0; i < lista.size(); i++) {
            this.addItem(lista.get(i).replace("\"",""));
        }
        if (!defecto.equalsIgnoreCase("nulo") && !defecto.equalsIgnoreCase("undefined")) {
            System.out.println("llego");
            this.setSelectedItem(defecto);
        }
        this.nombre = nombre;
        this.setBounds(x, y, ancho, alto);
        
    }
}
