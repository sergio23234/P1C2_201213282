/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sergio Fernando
 */
public class ventana extends JFrame{
    JPanel panel = new JPanel();
    
    public ventana(int largo,int ancho){
        this.setBounds(0,0,largo,ancho);
        panel.setBounds(0,0,largo,ancho);
        this.add(panel);
       
    }
    public void mostrar_reproductor(String path,boolean auto,int largo,int ancho){
        Reproductor_musica rep = new Reproductor_musica(path,true);
        rep.setBounds(5,5,ancho,largo);
        panel.add(rep);
        panel.repaint();
        this.repaint();
    } 
}
