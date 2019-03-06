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
public class NodoRespuesta {
    public boolean error;
    public String resultado;
    public String dato;
    public NodoFs raiz;
    
    public NodoRespuesta(String resultado){
        this.resultado = resultado;
        error = false;
        dato = "";
        raiz = new NodoFs("");
    }
    public NodoRespuesta(boolean resultado){
        this.error = resultado;
        dato ="";
        this.resultado="";
        raiz = new NodoFs("");
    }
}
