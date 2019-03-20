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
        NodoRespuesta defaultsi = new NodoRespuesta(false);
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
                            } else if (condicion.es_retorno) {
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
                    } else if (condicion.es_retorno) {
                        return condicion;
                    }
                }
                return new NodoRespuesta(false, "si");
            }
        }
        return defaultsi;
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
                AS_ID id = new AS_ID(tabla, global, num);
                return id.Analizar(raiz, errores);

            case "es_sel":
                /*!Estructura seleccionar!*/
                Est_sel sel = new Est_sel(tabla, global, num);
                return sel.analizar(raiz, errores);

            case "llamadafun":
                /*!Estructura llamada a funcion!*/
                llamada_fun funcion = new llamada_fun(global, num);
                NodoRespuesta nuevo = funcion.analizar(raiz, errores, tabla);
                return nuevo;
                
            case "id_accion":
                /*!Estructura acciones ID!*/
                Accion_ID ID_A = new Accion_ID(tabla, global, num);
                return ID_A.Analizar(raiz, errores);

            case "retornar":
                /*!Estructura de retornar!*/
                if (in_global) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "no se puede retornar nada en el inicio";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
                    return new NodoRespuesta(true);
                } else {
                    Est_return retorno = new Est_return(tabla, global, num);
                    return retorno.Analizar(raiz, errores);
                }

            case "detener":
                /*!Estructura de detener!*/
                NodoError error = new NodoError("semantico");
                error.descripcion = "error no esta permitido detener en el inicio";
                error.linea = String.valueOf(raiz.linea);
                error.columna = String.valueOf(raiz.columna);
                errores.add(error);
                return new NodoRespuesta(true);
        }
        return new NodoRespuesta(true);
    }

}
