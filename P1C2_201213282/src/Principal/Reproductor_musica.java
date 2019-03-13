/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

/**
 *
 * @author Sergio Fernando
 */
public class Reproductor_musica extends JButton implements ActionListener {

    Player player;
    boolean actual;

    public Reproductor_musica(String path, boolean auto) {
        actual = auto;     
        try {
            FileInputStream in;
            in = new FileInputStream(path);
            player = new Player(in);
            if (auto) {
                iniciar();
            }
            else{
                this.setText("reproducir");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reproductor_musica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Reproductor_musica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciar() {
        try {
            actual = false;
            player.play();
            this.setText("pausar");
        } catch (JavaLayerException ex) {
            Logger.getLogger(Reproductor_musica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parar() {
        player.close();
        actual = true;
        this.setText("iniciar");
    }

    public void clic() {
        if (actual) {
            iniciar();
        } else {
            parar();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("no hizo clic");
       
        clic(); //To change body of generated methods, choose Tools | Templates.
    }
}
