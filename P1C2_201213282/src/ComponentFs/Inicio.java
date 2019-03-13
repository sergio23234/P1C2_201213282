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
public class Inicio {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Inicio(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        if (raiz.Tipo.equalsIgnoreCase("inicio")) {
            for (int i = 0; i < raiz.hijos.size(); i++) {
                Analizar(raiz.hijos.get(i), errores);
            }
        } else if (raiz.Tipo.equalsIgnoreCase("Cuerpo")) {
            for (int i = 0; i < raiz.hijos.size(); i++) {
                Analizar_Cuerpo(raiz.hijos.get(i), errores);
            }
        }
        return null;
    }

    private NodoRespuesta Analizar_Cuerpo(NodoFs raiz, ArrayList<NodoError> errores) {
        switch (raiz.Tipo.toLowerCase()) {
            case "dec_var":
                /*!Estructura Declaracion de variables!*/
                Declarar_variables nnuevo = new Declarar_variables(tabla, global, num);
                nnuevo.Analizar(raiz, errores);/*!Estructura variable!*/
                break;
            case "imprimir":
                /*!Estructura imprimir!*/
                Es_Imprimir nuevoi = new Es_Imprimir(tabla, global, num);
                nuevoi.Analizar(raiz, errores);
                break;
            case "est_si":
                /*!Estructura si!*/
                Est_Si est = new Est_Si(tabla, global, num);
                est.in_global = true;
                est.Analizar(raiz, errores);
                break;
            case "as_id":
                /*!Estructura asignacion id!*/
                AS_ID id = new AS_ID(tabla,global,num);
                return id.Analizar(raiz, errores);
                
            case "es_sel":
                /*!Estructura seleccionar!*/
                Est_sel sel = new Est_sel(tabla, global, num);
                return sel.analizar(raiz, errores);

            case "llamadafun":/*!Estructura llamada a funcion!*/
                llamada_fun funcion = new llamada_fun(global, num);
                return funcion.analizar(raiz, errores,tabla);

            case "id_accion":
                /*!Estructura acciones ID!*/
                Accion_ID ID_A =new Accion_ID(tabla,global,num);
                return ID_A.Analizar(raiz, errores);
                
            case "retornar":
                /*!Estructura de retornar!*/
                Est_return retorno = new Est_return(tabla, global, num);
                return new NodoRespuesta(true);

            case "detener":
                /*!Estructura de detener!*/ break;
        }

        return null;
    }
}
