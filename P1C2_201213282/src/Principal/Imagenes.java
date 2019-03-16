/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author sergi
 */
public class Imagenes extends JLabel {
    
    public Imagenes(String path,int x,int y,int alto,int ancho){
        this.setBounds(x, y,ancho,alto);
        ImageIcon imagen = new ImageIcon(path);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icono);
    }
    
}
