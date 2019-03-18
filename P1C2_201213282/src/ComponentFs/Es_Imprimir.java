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
public class Es_Imprimir {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Es_Imprimir(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta resultado = OP.Cuerpo_G(raiz.hijos.get(0), errores);
       // System.out.println(resultado.resultado+" esto imprimira");
        if (!resultado.error) {
            String impri = resultado.resultado.toString().replace("\"", "") + "\n";
            Principal.Menu.Lista.get(num).Consola.append(impri);
        }
        return resultado;
    }

}
