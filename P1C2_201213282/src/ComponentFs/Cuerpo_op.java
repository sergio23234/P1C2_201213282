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
public class Cuerpo_op {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Cuerpo_op(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Cuerpo_G(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "ope_l":
                OPA_L operal = new OPA_L(tabla, global, num);
                return operal.Analizar_OPL(raiz, errores);

            case "ope_c":
                OPA_C operac = new OPA_C(tabla, global, num);
                return operac.Analizar_OPC(raiz, errores);

            case "ope_a":
                OPA_A operacon = new OPA_A(tabla, global, num);
                return operacon.Analizar_OPA(raiz, errores);

            case "dato":
                if (raiz.valor.equals("")) {
                    nuevo = new NodoRespuesta("undefined");
                    return nuevo;
                }
                nuevo = new NodoRespuesta(raiz.valor);

                return nuevo;

            case "dato negado":
                operacon = new OPA_A(tabla, global, num);
                return operacon.negar_dato(raiz, errores);

            case "autoincremento":
                ES_ID retorno = new ES_ID(tabla, global, num);
                return retorno.autoincrementar(raiz, errores);

            case "autodecremento":
                retorno = new ES_ID(tabla, global, num);
                return retorno.autodecrementar(raiz, errores);

            case "nativas":
                Nativas nat = new Nativas(tabla, global, num);
                return nat.Analizar(raiz, errores);

            case "llamadafun":
                llamada_fun funcion = new llamada_fun(global, num);
                nuevo = funcion.analizar(raiz, errores, tabla);
                if (!nuevo.es_retorno) {
                    nuevo.resultado = "undefinded";
                    nuevo.tipo = "undefinded";
                    return nuevo;
                } else {
                    return nuevo;
                }

            case "id":
                ES_ID id = new ES_ID(tabla, global, num);
                return id.Analizar(raiz, errores);

        }
        nuevo = new NodoRespuesta(false);
        return nuevo;
    }

}
