/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Principal.NodoError;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class Est_Array {
    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Est_Array(TablaSimbolos tabla,TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }
    
    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores){
        return new NodoRespuesta(true);
    }
}
