/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class TablaSimbolos {
    
    public ArrayList<NodoTabla> Tabla;
    public TablaSimbolos padre;
    public String ambito;
    
 public TablaSimbolos(String ambito){
     Tabla = new ArrayList();
     padre = null;
     this.ambito = ambito;
 }
}
