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

    public NodoRespuesta analizar(NodoFs raiz, ArrayList<NodoError> errores, TablaSimbolos tabla) {
        ArrayList<NodoRespuesta> resultados = new ArrayList();
        NodoFs actual = raiz.hijos.get(0);
        for (int i = 0; i < actual.hijos.size(); i++) {
            Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
            NodoRespuesta condicion = OP.Cuerpo_G(actual.hijos.get(i), errores);
            if (!condicion.error) {
                resultados.add(condicion);
            } else {
                return condicion;
            }
        }
        return analizar1(raiz, errores, resultados);
    }

    private NodoRespuesta analizar1(NodoFs raiz, ArrayList<NodoError> errores, ArrayList<NodoRespuesta> resultados) {
        Fs_varios fs = new Fs_varios();
        String nombre_fun = raiz.valor;
        boolean existe_func = fs.ret_Existencia_fun(nombre_fun, global);//para saber si existe la funcion
        if (existe_func) {
            NodoFs raiz_fun = fs.ret_fun_Tabla(nombre_fun, global); //raiz de la funcion
            if (raiz_fun.hijos.size() > 1) {        //si tienes mas hijos
                NodoFs actual = raiz_fun.hijos.get(0);
                ArrayList<String> hijos = new ArrayList();
                for (int i = 0; i < actual.lista.size(); i++) {
                    hijos.add(actual.lista.get(i));
                }//añadimos parametros
                if (raiz.hijos.size() > 0) {//resultados de hijos

                    if (hijos.size() == resultados.size()) {
                        if (!id_no_repetidos(hijos)) {
                            pasar_parametros(hijos, resultados);
                            actual = raiz_fun.hijos.get(1);
                            NodoRespuesta retornara;
                            for (int i = 0; i < actual.hijos.size(); i++) {
                                // System.out.println(actual.hijos.size() + "hijos");
                                retornara = Analizar_Cuerpo(actual.hijos.get(i), errores);
                                if (retornara.error) {
                                    return retornara;
                                } else if (retornara.es_retorno) {
                                    // System.out.println("retorno dato" + retornara.resultado);
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
                Declarar_variables nnuevo = new Declarar_variables(tabla, global, num);
                NodoRespuesta ret = nnuevo.Analizar(raiz, errores);
                //System.out.println(ret.resultado + " resultado");
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
                Est_sel sel = new Est_sel(tabla, global, num);
                return sel.analizar(raiz, errores);

            case "llamadafun":
                /*!Estructura llamada a funcion!*/
                llamada_fun funcion = new llamada_fun(global, num);
                return funcion.analizar(raiz, errores, tabla);

            case "id_accion":
                /*!Estructura acciones ID!*/
                break;
            case "retornar":
                /*!Estructura de retornar!*/
                Est_return retorno = new Est_return(tabla, global, num);
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

    public NodoRespuesta analizar_nati(NodoFs raiz, ArrayList<NodoError> errores, ArrayList<String> vector) {
        String tipo = raiz.valor;
        String funcion = raiz.lista.get(0);
        Fs_varios fs = new Fs_varios();
        boolean existe_func = fs.ret_Existencia_fun(funcion, global);//para saber si existe la funcion
        if (existe_func) {
            NodoFs raiz_fun = fs.ret_fun_Tabla(funcion, global); //raiz de la funcion
            if (raiz_fun.hijos.size() > 1) {        //si tienes mas hijos
                NodoFs actual = raiz_fun.hijos.get(0);
                ArrayList<String> hijos = new ArrayList();
                for (int i = 0; i < actual.lista.size(); i++) {
                    hijos.add(actual.lista.get(i));
                }//añadimos parametros
                if (vector.size() % hijos.size() == 0) {
                    if (!id_no_repetidos(hijos)) {
                        return analizar_nati_p2(hijos, vector, tipo, raiz_fun, errores);
                    } else {
                        NodoError error = new NodoError("semantico");
                        error.descripcion = "error la funcion:" + funcion + " tiene parametros con el mismo ID";
                        errores.add(error);
                        return new NodoRespuesta(true);
                    }
                } else {
                    System.out.println("error parametros no concuerdan");
                    return new NodoRespuesta(true);
                }
            } else {
                return new NodoRespuesta(true);
            }
        }
        return new NodoRespuesta(false);
    }

    private NodoRespuesta analizar_nati_p2(ArrayList<String> hijos, ArrayList<String> vector, String tipo, NodoFs raiz, ArrayList<NodoError> errores) {
        switch (tipo.toLowerCase()) {
            case "filtrar":
                return es_filtrar(hijos, vector, raiz, errores);
            case "buscar":
                return es_buscar(hijos, vector, raiz, errores);
            case "map":
                return es_map(hijos, vector, raiz, errores);
            case "reduce":
                break;
            case "todos":
                return es_todos(hijos, vector, raiz, errores);
            case "algunos":
                return es_alguno(hijos, vector, raiz, errores);
        }
        return new NodoRespuesta(true);
    }

    private NodoRespuesta es_filtrar(ArrayList<String> hijos, ArrayList<String> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                // System.out.println(actual.hijos.size() + "hijos");
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {
                        for (int m = 0; m < resultados.size(); m++) {
                            respuesta.add(resultados.get(m).resultado.toString());
                        }
                    } else if (retornara.resultado.toString().equalsIgnoreCase("falso")) {

                    } else {
                        return new NodoRespuesta(true);
                    }
                    break;
                }
            }
        }
        retorno = new NodoRespuesta(respuesta);
        retorno.tipo = "vector";
        return retorno;
    }

    private NodoRespuesta es_map(ArrayList<String> hijos, ArrayList<String> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    System.out.println("entro a retorno" + retornara.resultado.toString());
                    respuesta.add(retornara.resultado.toString());
                    break;
                } else {
                    System.out.println("no retorno nada");
                }
            }
        }
        retorno = new NodoRespuesta(respuesta);
        retorno.tipo = "vector";
        return retorno;
    }

    private NodoRespuesta es_buscar(ArrayList<String> hijos, ArrayList<String> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    System.out.println("entro a retorno" + retornara.resultado.toString());
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {
                        retorno = new NodoRespuesta(resultados.get(i - 1).resultado.toString());
                        retorno.tipo = "variable";
                        return retorno;
                    }
                    break;
                } else {
                    System.out.println("no retorno nada");
                }
            }
        }
        retorno = new NodoRespuesta(false);
        retorno.tipo = "undefined";
        return retorno;
    }

    private NodoRespuesta es_todos(ArrayList<String> hijos, ArrayList<String> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                // System.out.println(actual.hijos.size() + "hijos");
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {

                    } else if (retornara.resultado.toString().equalsIgnoreCase("falso")) {
                        retorno = new NodoRespuesta("falso");
                        retorno.tipo = "variable";
                        return retorno;
                    } else {
                        retorno = new NodoRespuesta("falso");
                        retorno.tipo = "variable";
                        return retorno;
                    }
                    break;
                }
            }
        }
        retorno = new NodoRespuesta("verdadero");
        retorno.tipo = "variable";
        return retorno;
    }

    private NodoRespuesta es_alguno(ArrayList<String> hijos, ArrayList<String> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                // System.out.println(actual.hijos.size() + "hijos");
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {
                        retorno = new NodoRespuesta("verdadero");
                        retorno.tipo = "variable";
                        return retorno;
                    } else if (retornara.resultado.toString().equalsIgnoreCase("falso")) {

                    } else {
                    }
                    break;
                }
            }
        }
        retorno = new NodoRespuesta("falso");
        retorno.tipo = "variable";
        return retorno;
    }

}
/*      int i=0;
      ArrayList<NodoRespuesta> resultados=new ArrayList();
      for(int j=0;j<hijos.size();j++){
          NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
          resultados.add(nuevo);
          i++;
      }
      pasar_parametros(hijos, resultados);*/

 /*                            pasar_parametros(hijos, resultados);
                            actual = raiz_fun.hijos.get(1);
                            NodoRespuesta retornara;
                            for (int i = 0; i < actual.hijos.size(); i++) {
                                // System.out.println(actual.hijos.size() + "hijos");
                                retornara = Analizar_Cuerpo(actual.hijos.get(i), errores);
                                if (retornara.error) {
                                    return retornara;
                                } else if (retornara.es_retorno) {
                                    // System.out.println("retorno dato" + retornara.resultado);
                                    return retornara;
                                }
                            }
                            return new NodoRespuesta(false);*/
