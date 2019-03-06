/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

/**
 *
 * @author sergi
 */
public class NodoTabla {

public String valor;
public String tipo;
public NodoFs nodo_raiz;
public String nombre;

public NodoTabla(NodoFs raiz,String tipo,String nombre){
    valor="";
    this.tipo = tipo;
    this.nodo_raiz = raiz;
    this.nombre = nombre;
}
    
}
