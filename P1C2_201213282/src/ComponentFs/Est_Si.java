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
public class Est_Si {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;
    boolean in_seleccionar;
    boolean in_global;

    public Est_Si(TablaSimbolos tabla, TablaSimbolos global, int num) {
        TablaSimbolos nueva = new TablaSimbolos("Si");
        nueva.padre = tabla;
        this.tabla = nueva;
        this.num = num;
        this.global = global;
        in_seleccionar = false;
        in_global = false;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoFs actual = raiz.hijos.get(i);
            if (actual.Tipo.equalsIgnoreCase("si")) {
                Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                NodoRespuesta condicion = OP.Cuerpo_G(actual.hijos.get(0), errores);
                if (!condicion.error) {
                    //System.out.println(condicion.resultado + "resultado");
                    if (condicion.resultado.toString().equalsIgnoreCase("verdadero")) {
                        NodoFs newactual = actual.hijos.get(1);
                        for (int j = 0; j < newactual.hijos.size(); j++) {
                            condicion = Analizar_Cuerpo(newactual.hijos.get(j), errores);
                            if (condicion.error) {
                                condicion.tipo = "si";
                                return condicion;
                            }else if(condicion.es_retorno){
                                return condicion;
                            }
                        }
                        return new NodoRespuesta(false, "si");
                    }
                }
            } else {
                NodoFs newactual = actual.hijos.get(0);
                NodoRespuesta condicion;
                for (int j = 0; j < newactual.hijos.size(); j++) {
                    condicion = Analizar_Cuerpo(newactual.hijos.get(j), errores);
                    if (condicion.error) {
                        return condicion;
                    }else if(condicion.es_retorno){
                                return condicion;
                            }
                }
                return new NodoRespuesta(false, "si");
            }
        }
        return null;
    }

    private NodoRespuesta Analizar_Cuerpo(NodoFs raiz, ArrayList<NodoError> errores) {
        switch (raiz.Tipo.toLowerCase()) {
            case "dec_var":
                Declarar_variables nnuevo = new Declarar_variables(tabla, global, num);
                return nnuevo.Analizar(raiz, errores);/*!Estructura variable!*/
            case "imprimir":
                /*!Estructura imprimir!*/
                Es_Imprimir nuevoi = new Es_Imprimir(tabla, global, num);
                return nuevoi.Analizar(raiz, errores);
            case "est_si":
                /*!Estructura si!*/
                Est_Si est = new Est_Si(tabla, global, num);
                return est.Analizar(raiz, errores);
            case "as_id":
                /*!Estructura asignacion id!*/
                break;
            case "es_sel":
                /*!Estructura seleccionar!*/
                Est_sel sel = new Est_sel(tabla,global,num);
                return sel.analizar(raiz, errores);

            case "llamadafun":
                /*!Estructura llamada a funcion!*/
                llamada_fun funcion = new llamada_fun(global, num);
                NodoRespuesta nuevo = funcion.analizar(raiz, errores);
                return nuevo;
            case "id_accion":
                /*!Estructura acciones ID!*/
                break;
            case "retornar":
                /*!Estructura de retornar!*/
                Est_return retorno = new Est_return(tabla, global, num);
                return retorno.Analizar(raiz, errores);

            case "detener":
                /*!Estructura de detener!*/ 
                    return new NodoRespuesta(true);
        }
        return new NodoRespuesta(true);
    }

}

//    private NodoRespuesta Analizar_Condicion(NodoFs raiz, ArrayList<NodoError> errores) {
//        NodoRespuesta nuevo;
//        switch (raiz.Tipo.toLowerCase()) {
//            case "ope_l":
//                OPA_L operal = new OPA_L(tabla, global);
//                return operal.Analizar_OPL(raiz, errores);
//            case "ope_c":
//                OPA_C operac = new OPA_C(tabla, global);
//                return operac.Analizar_OPC(raiz, errores);
//            case "ope_a":
//                OPA_A operacon = new OPA_A(tabla, global);
//                return operacon.Analizar_OPA(raiz, errores);
//
//            case "dato":
//                nuevo = new NodoRespuesta(raiz.valor);
//                return nuevo;
//            case "dato negado":
//                operacon = new OPA_A(tabla, global);
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
