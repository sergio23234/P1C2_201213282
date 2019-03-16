/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import javax.swing.*;
/**
 *
 * @author sergi
 */
public class Numberfield extends JSpinner{
    
    public String nombre;
    
    public Numberfield(int alto,int ancho,int max,int min,int x,int y,int defecto,String nombre){
        this.nombre = nombre;
        this.setBounds(x, y, ancho,alto);
        SpinnerModel model = new SpinnerNumberModel(defecto,min,max,1);
        this.setModel(model);
        JComponent editor = new JSpinner.NumberEditor(this);
        this.setEditor(editor);
    }
}
