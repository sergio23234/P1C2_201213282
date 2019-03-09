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
public class llamada_fun {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public llamada_fun(TablaSimbolos global, int num) {
        tabla = new TablaSimbolos("funcion");
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        Fs_varios fs = new Fs_varios();
        String nombre_fun = raiz.valor;
        boolean existe_func = fs.ret_Existencia_fun(nombre_fun, global);
        if (existe_func) {
            NodoFs raiz_fun = fs.ret_fun_Tabla(nombre_fun, global);
            if (raiz_fun.hijos.size() > 1) {
                NodoFs actual = raiz_fun.hijos.get(0);
                ArrayList<String> hijos = new ArrayList();
                ArrayList<NodoRespuesta> resultados = new ArrayList();
                for (int i = 0; i < actual.lista.size(); i++) {
                    hijos.add(actual.lista.get(i));
                }
                if (raiz.hijos.size() > 0) {
                    actual = raiz.hijos.get(0);
                    for (int i = 0; i < actual.hijos.size(); i++) {
                        Cuerpo_op OP = new Cuerpo_op(tabla, global,num);
                        NodoRespuesta condicion = OP.Cuerpo_G(actual.hijos.get(i), errores);
                        if (!condicion.error) {
                            resultados.add(condicion);
                        }
                    }
                    if (hijos.size() == resultados.size()) {
                        if (!id_no_repetidos(hijos)) {
                            pasar_parametros(hijos, resultados);
                            actual = raiz_fun.hijos.get(1);
                            NodoRespuesta retornara;
                            for (int i = 0; i < actual.hijos.size(); i++) {
                                retornara = Analizar_Cuerpo(actual.hijos.get(i), errores);
                                if (retornara.error) {
                                    return retornara;
                                }else if(retornara.es_retorno){
                                    System.out.println("es vector o no:"+retornara.tipo);
                                    return retornara;
                                }
                            }
                            return new NodoRespuesta(false);
                        } else {
                            NodoError error = new NodoError("semantico");
                            error.descripcion = "error la funcion:" + nombre_fun + " tiene parametros con el mismo ID";
                            errores.add(error);
                            return new NodoRespuesta(true);
                        }

                    } else {
                        NodoError error = new NodoError("semantico");
                        if (hijos.size() > resultados.size()) {
                            error.descripcion = "error la funcion:" + nombre_fun + " tiene mas parametros de los ingresados";

                        } else {
                            error.descripcion = "error la funcion:" + nombre_fun + " tiene menos parametros de los ingresados";
                        }
                        errores.add(error);
                        return new NodoRespuesta(true);
                    }
                } else {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "error la funcion:" + nombre_fun + " tiene mas parametros de los ingresados";
                    errores.add(error);
                    return new NodoRespuesta(true);
                }
            }
        }
        return null;
    }

    private NodoRespuesta Analizar_Cuerpo(NodoFs raiz, ArrayList<NodoError> errores) {
        switch (raiz.Tipo.toLowerCase()) {
            case "dec_var":
                Declarar_variables nnuevo = new Declarar_variables(tabla, global,num);
                NodoRespuesta ret = nnuevo.Analizar(raiz, errores);
                System.out.println(ret.resultado+" resultado");
                return ret;/*!Estructura variable!*/
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
                break;
            case "llamadafun":
                /*!Estructura llamada a funcion!*/
                llamada_fun funcion = new llamada_fun(global, num);
                return funcion.analizar(raiz, errores);

            case "id_accion":
                /*!Estructura acciones ID!*/
                break;
            case "retornar":
                /*!Estructura de retornar!*/
                Est_return retorno = new Est_return(tabla,global,num);
                return retorno.Analizar(raiz, errores);
               
            case "detener":
                /*!Estructura de detener!*/ break;
        }
        return new NodoRespuesta(true);
    }

    private void pasar_parametros(ArrayList<String> hijos, ArrayList<NodoRespuesta> resultados) {
        for (int i = 0; i < hijos.size(); i++) {
            Add_var_Tabla_variable(hijos.get(i), resultados.get(i));
        }
    }

    public boolean id_no_repetidos(ArrayList<String> hijos) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).equalsIgnoreCase(hijos.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void Add_var_Tabla_variable(String id, NodoRespuesta respuesta) {
        String tipos = "variable";
        if (respuesta.tipo.equalsIgnoreCase("vector")) {
            tipos = "vector";
        }
        NodoTabla nodo = new NodoTabla(tipos, id);
        if (tipos.equalsIgnoreCase("vector")) {
            ArrayList<String> valores = (ArrayList<String>) respuesta.resultado;
            ArrayList<String> nuevo = new ArrayList();
            for (int i = 0; i < valores.size(); i++) {
                nuevo.add(valores.get(i));
            }
            nodo.valor = nuevo;
        } else {
            nodo.valor = respuesta.resultado;
        }
        tabla.Tabla.add(nodo);
    }

}
