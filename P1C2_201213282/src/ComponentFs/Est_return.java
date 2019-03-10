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
public class Est_return {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Est_return(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        //System.out.println("entro");
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta resultado = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        if (!resultado.error) {
           // System.out.println("retornara: " + resultado.resultado);
            resultado.es_retorno = true;
            return resultado;
        } else {
            System.out.println("hay error");
        }
        return resultado;
    }
    
}
