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
public class NodoFs {

    public String Tipo;
    public ArrayList<NodoFs> hijos;
    public ArrayList<String> lista;
    public String valor;

    public NodoFs(String tipo) {
        this.Tipo = tipo;
        valor="";
        hijos = new ArrayList();
        lista = new ArrayList();
    }

    public void add_NodoFs(NodoFs hijos) {
        this.hijos.add(hijos);
    }

    public void add_ListaFs(String hijos) {
        this.lista.add(hijos);
    }
}
