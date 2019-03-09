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
    public boolean  error;
    public Object   resultado;
    public String   tipo;
    public String   dato;
    public boolean es_retorno;
    public NodoFs   raiz;
    
    public NodoRespuesta(Object resultado){
        this.resultado = resultado;
        error = false;
        dato = "";
        raiz = new NodoFs("");
        tipo="";
        es_retorno=false;
    }
    public NodoRespuesta(boolean resultado){
        this.error = resultado;
        dato ="";
        this.resultado="";
         tipo="";
         es_retorno=false;
        raiz = new NodoFs("");
    }
        public NodoRespuesta(boolean resultado,String tipo){
        this.error = resultado;
        dato ="";
        this.resultado="";
        this.tipo=tipo;
        es_retorno=false;
        raiz = new NodoFs("");
    }
}
