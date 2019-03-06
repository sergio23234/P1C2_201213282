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

    public Inicio(TablaSimbolos tabla) {
        this.tabla = tabla;
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
                Declarar_variables nnuevo = new Declarar_variables(tabla);
                nnuevo.Analizar(raiz, errores);/*!Estructura variable!*/
                break;
            case "imprimir":/*!Estructura imprimir!*/ break;
            case "est_si":
                /*!Estructura si!*/
                break;
            case "as_id":
                /*!Estructura asignacion id!*/
                break;
            case "es_sel":
                /*!Estructura seleccionar!*/
                break;
            case "llamadafun":/*!Estructura llamada a funcion!*/ break;
            case "id_accion":
                /*!Estructura acciones ID!*/
                break;
            case "retornar":
                /*!Estructura de retornar!*/ break;
            case "detener":
                /*!Estructura de detener!*/ break;
        }

        return null;
    }
}
