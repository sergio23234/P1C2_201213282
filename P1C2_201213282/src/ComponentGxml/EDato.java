/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import java.util.regex.Pattern;

/**
 *
 * @author sergi
 */
public class EDato {
    public String Dato;
    public String referencia;
    public int linea,columna;
    public EDato(){
        Dato="";
        referencia="";
        linea=columna=0;
    }
    public EDato(String Dato,int linea,int columna){
        this.Dato = Dato.trim();
        this.linea = linea;
        this.columna= columna;
    }
     
    public boolean match_numero(){
        String regexp = "(-|\"+\")?[0-9]+";
        return Pattern.matches(regexp,Dato);
    }
}
