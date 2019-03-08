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
public class NodoTabla {

    public Object valor;
    public String tipo;
    public NodoFs nodo_raiz;
    public String nombre;


    public NodoTabla(NodoFs raiz, String tipo, String nombre) {
        valor = "";
        this.tipo = tipo;
        this.nodo_raiz = raiz;
        this.nombre = nombre;
    }

    public NodoTabla(String tipo, String nombre) {
        valor = "";
        this.tipo = tipo;
        this.nodo_raiz = new NodoFs("");
        this.nombre = nombre;
    }

}
