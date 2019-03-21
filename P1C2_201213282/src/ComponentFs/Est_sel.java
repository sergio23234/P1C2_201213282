/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Principal.Menu;
import Principal.NodoError;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class Est_sel {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;
    boolean in_seleccionar;
    boolean in_global;

    public Est_sel(TablaSimbolos tabla, TablaSimbolos global, int num) {
        TablaSimbolos nueva = new TablaSimbolos("Seleccionar");
        nueva.padre = tabla;
        this.tabla = nueva;
        this.num = num;
        this.global = global;
        in_seleccionar = false;
        in_global = false;
    }

    public NodoRespuesta analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        //System.out.println(raiz.hijos.size() + "hijos selecionar");
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        // System.out.println("suma de seleccionar ");
        if (accion_posible(uno.tipo)) {
            NodoError error1 = new NodoError("semantico");
            error1.descripcion = "un caso no puede ser:" + uno.tipo;
            error1.linea = String.valueOf(raiz.linea);
            error1.columna = String.valueOf(raiz.columna);
            Menu.Lista.get(num).errores.add(error1);
            return new NodoRespuesta(true);
        } else {
            return analizar_casos(raiz.hijos.get(1), errores, uno.resultado.toString());
        }
    }

    private boolean accion_posible(String value) {
        switch (value.toLowerCase()) {
            case "vector":
                return true;
            case "objeto":
                return true;
            case "array":
                return true;
            case "ventana":
                return true;
            case "contenedor":
                return true;
            case "boton":
                return true;
            default:
                return false;
        }
    }

    private NodoRespuesta analizar_casos(NodoFs raiz, ArrayList<NodoError> errores, String respuesta) {
        //System.out.println(raiz.hijos.size());
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        boolean confirmado = false;
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoFs actual = raiz.hijos.get(i);
            if (actual.Tipo.equalsIgnoreCase("caso")) {
                NodoRespuesta Respuesta = OP.Cuerpo_G(actual.hijos.get(0), errores);
                OPA_C compa = new OPA_C(tabla, global, num);
                NodoRespuesta caso = compa.Comparar_Caso(Respuesta.resultado.toString(), respuesta, raiz.linea, raiz.columna);
                if (caso.resultado.toString().equalsIgnoreCase("verdadero") || confirmado) {
                    NodoFs cuerpo = actual.hijos.get(1);
                    confirmado = true;
                    for (int j = 0; j < cuerpo.hijos.size(); j++) {
                        NodoRespuesta ret = Analizar_Cuerpo(cuerpo.hijos.get(j), errores);
                        if (ret.error) {
                            return new NodoRespuesta(true);
                        } else if (ret.tipo.equalsIgnoreCase("detener")) {
                            return ret;
                        } else if (ret.es_retorno) {
                            //System.out.println("entro en retorno");
                            return ret;
                        }
                    }
                }
            } else {
                NodoFs cuerpo = actual.hijos.get(0);
                for (int j = 0; j < cuerpo.hijos.size(); j++) {
                    NodoRespuesta ret = Analizar_Cuerpo(cuerpo.hijos.get(j), errores);
                    if (ret.error) {
                        return new NodoRespuesta(true);
                    } else if (ret.tipo.equalsIgnoreCase("detener")) {
                        return ret;
                    } else if (ret.es_retorno) {

                        return ret;
                    }
                }
            }
        }
        return new NodoRespuesta(false);
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
                Est_return retorno = new Est_return(tabla, global, num);
                return retorno.Analizar(raiz, errores);

            case "detener":
                /*!Estructura de detener!*/
                NodoRespuesta actualsel = new NodoRespuesta(false);
                actualsel.tipo = "detener";
                return actualsel;
        }
        return new NodoRespuesta(true);
    }
}
