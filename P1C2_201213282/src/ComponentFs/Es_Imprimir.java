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
//  private NodoRespuesta Cuerpo_Imp(NodoFs raiz, ArrayList<NodoError> errores) {
//        NodoRespuesta nuevo;
//        switch (raiz.Tipo.toLowerCase()) {
//            case "ope_l":
//                OPA_L operal = new OPA_L(tabla, global);
//                return operal.Analizar_OPL(raiz, errores);
//                
//            case "ope_c":
//                OPA_C operac = new OPA_C(tabla, global);
//                return operac.Analizar_OPC(raiz, errores);
//
//            case "ope_a":
//                OPA_A operacon = new OPA_A(tabla, global);
//                return operacon.Analizar_OPA(raiz, errores);
//
//            case "dato":
//                nuevo = new NodoRespuesta(raiz.valor);
//                return nuevo;
//                
//            case "dato negado":
//                operacon = new OPA_A(tabla, global,num);
//                return operacon.negar_dato(raiz, errores);
//
//            case "autoincremento":
//                ES_ID retorno = new ES_ID(tabla, global);
//                return retorno.autoincrementar(raiz, errores);
//
//            case "autodecremento":
//                retorno = new ES_ID(tabla, global);
//                return retorno.autodecrementar(raiz, errores);
//
//            case "nativas":
//                
//                break;
//            case "llamadafun":
//                break;
//            case "id":
//                ES_ID id = new ES_ID(tabla, global);
//                return id.Analizar(raiz, errores);
//
//        }
//        nuevo = new NodoRespuesta(true);
//        return nuevo;
//    }

