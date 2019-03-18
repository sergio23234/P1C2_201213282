/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author sergi
 */
public class hilomusica extends Thread {

    Player player;
    String path;

    public hilomusica(String path) {
        this.path = path;
    }

    public void run() {
        iniciar();
    }
    public void detener(){
        player.close();
    }
    public void iniciar(){
        try {
            FileInputStream input = new FileInputStream(path);
            player = new Player(input);
            player.play();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
