/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JPanel;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author sergi
 */
public class Videoview extends javax.swing.JFrame {
 MediaPlayerFactory mpf;
 EmbeddedMediaPlayer emp;
    public Videoview(String ruta,int x,int y,int alto,int ancho) {
        this.setBounds(x,y,ancho,alto);
        Canvas c = new Canvas();
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(c);
        add(p);
        setVisible(true);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        mpf = new MediaPlayerFactory();
        emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
        emp.setVideoSurface(mpf.newVideoSurface(c));
        //emp.toggleFullScreen();
        //deshabilitar el teclado
        emp.setEnableKeyInputHandling(false);
        emp.prepareMedia(ruta.replace("/", "\\\\"));
        emp.play();
    }
    
    public void cerrar(){
        emp.pause();
        emp.stop();
        this.setVisible(false);
    }
    
    

}
