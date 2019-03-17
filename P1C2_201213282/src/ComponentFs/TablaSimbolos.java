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
    public ArrayList<TablaSimbolos> importados;

    public TablaSimbolos(String ambito) {
        Tabla = new ArrayList();
        padre = null;
        this.ambito = ambito;
        this.importados = null;
    }

    public void inicia_importados() {
        this.importados = new ArrayList();
    }

    public void add_importado(TablaSimbolos nueva) {
        this.importados.add(nueva);
    }
    public TablaSimbolos(){
        
    }
}
