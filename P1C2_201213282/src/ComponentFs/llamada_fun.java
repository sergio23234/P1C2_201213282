/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import ComponentGxml.NodoGxml;
import Principal.Menu;
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
        if (raiz.hijos.size() > 0) {
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
        }
        return analizar1(raiz, errores, resultados);
    }

    private NodoRespuesta analizar1(NodoFs raiz, ArrayList<NodoError> errores, ArrayList<NodoRespuesta> resultados) {
        Fs_varios fs = new Fs_varios();
        String nombre_fun = raiz.valor;
        boolean existe_func = fs.ret_Existencia_fun(nombre_fun, global);//para saber si existe la funcion
        // System.out.println("llego a este punto 0"+nombre_fun);
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
            } else {
                NodoFs actual = raiz_fun.hijos.get(0);
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
            }
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "error no existe la funcion: " + nombre_fun;
            error.linea = String.valueOf(raiz.linea);
            error.columna = String.valueOf(raiz.columna);
            Menu.Lista.get(num).errores.add(error);
            return new NodoRespuesta(true);
        }

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
                // System.out.println("llego a imprimir");
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
                return funcion.analizar(raiz, errores, tabla);

            case "id_accion":
                /*!Estructura acciones ID!*/
                Accion_ID ID_A = new Accion_ID(tabla, global, num);
                return ID_A.Analizar(raiz, errores);

            case "retornar":
                /*!Estructura de retornar!*/
                Est_return retorno = new Est_return(tabla, global, num);
                return retorno.Analizar(raiz, errores);

            case "detener":
                NodoError error = new NodoError("semantico");
                error.descripcion = "error no esta permitido detener en el inicio";
                error.linea = String.valueOf(raiz.linea);
                error.columna = String.valueOf(raiz.columna);
                errores.add(error);
                return new NodoRespuesta(true);
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
        } else if (respuesta.tipo.equalsIgnoreCase("objeto")) {
            tipos = "objeto";
        }
        NodoTabla nodo = new NodoTabla(tipos, id);
        if (tipos.equalsIgnoreCase("vector")) {
            ArrayList<String> valores = (ArrayList<String>) respuesta.resultado;
            ArrayList<String> nuevo = new ArrayList();
            for (int i = 0; i < valores.size(); i++) {
                nuevo.add(valores.get(i));
            }
            nodo.valor = nuevo;
        } else if (tipos.equalsIgnoreCase("objeto")) {
            NodoObjeto valores = (NodoObjeto) respuesta.resultado;
            ArrayList<Raiz> nuevo = new ArrayList();
            for (int i = 0; i < valores.objetos.size(); i++) {
                nuevo.add(new Raiz(valores.objetos.get(i).nombre, valores.objetos.get(i).valor, valores.objetos.get(i).tipo));
            }
            NodoObjeto nuevo1 = new NodoObjeto(nuevo);
            nodo.valor = nuevo1;
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
                        error.linea = String.valueOf(raiz.linea);
                        error.columna = String.valueOf(raiz.columna);
                        errores.add(error);
                        return new NodoRespuesta(true);
                    }
                } else if (tipo.equalsIgnoreCase("reduce")) {
                    if (vector.size() % (hijos.size() - 1) == 0) {
                        if (!id_no_repetidos(hijos)) {
                            return analizar_nati_p2(hijos, vector, tipo, raiz_fun, errores);
                        } else {
                            NodoError error = new NodoError("semantico");
                            error.descripcion = "error la funcion:" + funcion + " tiene parametros con el mismo ID";
                            error.linea = String.valueOf(raiz.linea);
                            error.columna = String.valueOf(raiz.columna);
                            errores.add(error);
                            return new NodoRespuesta(true);
                        }
                    }
                } else {
                    System.out.println("error parametros no concuerdan");
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "error parametros no concuerdan";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
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
                return es_reduce(hijos, vector, raiz, errores);
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
                //System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    //  System.out.println("entro a retorno" + retornara.resultado.toString());
                    respuesta.add(retornara.resultado.toString());
                    break;
                } else {
                    //System.out.println("no retorno nada");
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
                //System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    //  System.out.println("entro a retorno" + retornara.resultado.toString());
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {
                        retorno = new NodoRespuesta(resultados.get(i - 1).resultado.toString());
                        retorno.tipo = "variable";
                        return retorno;
                    }
                    break;
                } else {
                    // System.out.println("no retorno nada");
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

    private NodoRespuesta es_reduce(ArrayList<String> hijos, ArrayList<String> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        NodoRespuesta respuesta = new NodoRespuesta(false);
        boolean primero = true;
        // NodoRespuesta retorno=new NodoRespuesta("");
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            if (primero) {
                resultados.add(new NodoRespuesta(0));
            } else {
                resultados.add(new NodoRespuesta(respuesta.resultado.toString()));
            }
            for (int j = 1; j < hijos.size(); j++) {
                //System.out.println("este resultado: "+vector.get(i)+"::"+resultados.get(0).resultado.toString());
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                resultados.add(nuevo);
                i++;
            }
            // System.out.println("hijos"+hijos.size()+" resultados"+resultados.size());
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                // System.out.println("es este tiponn"+actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    //   System.out.println("entro a retorno" + retornara.resultado.toString());
                    if (primero) {
                        //     System.out.println("entro aqui");
                        primero = false;
                        respuesta.resultado = retornara.resultado;
                    } else if (retornara.tipo.equalsIgnoreCase("variable")) {
                        //   System.out.println("entro aqui2");
                        OPA_A op = new OPA_A(tabla, global, num);
                        respuesta.resultado = retornara.resultado;

                    } else if (!(retornara.tipo.equalsIgnoreCase("objeto") || retornara.tipo.equalsIgnoreCase("vector"))) {
                        OPA_A op = new OPA_A(tabla, global, num);
                        // System.out.println("entro aqui4");
                        respuesta.resultado = retornara.resultado;
                    }
                } else {
                    System.out.println("no retorno nada");
                }
            }
        }
        respuesta.resultado = analizar_resultado(respuesta.resultado);
        respuesta.tipo = "variable";
        return respuesta;
    }

    public NodoRespuesta analizar_nati2(NodoFs raiz, ArrayList<NodoError> errores, ArrayList<NodoObjeto> vector) {
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
                        return analizar_nati_p2_2(hijos, vector, tipo, raiz_fun, errores);
                    } else {
                        NodoError error = new NodoError("semantico");
                        error.descripcion = "error la funcion:" + funcion + " tiene parametros con el mismo ID";
                        errores.add(error);
                        return new NodoRespuesta(true);
                    }
                } else if (tipo.equalsIgnoreCase("reduce")) {
                    if (vector.size() % (hijos.size() - 1) == 0) {
                        if (!id_no_repetidos(hijos)) {
                            return analizar_nati_p2_2(hijos, vector, tipo, raiz_fun, errores);
                        } else {
                            NodoError error = new NodoError("semantico");
                            error.descripcion = "error la funcion:" + funcion + " tiene parametros con el mismo ID";
                            errores.add(error);
                            return new NodoRespuesta(true);
                        }
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

    private NodoRespuesta analizar_nati_p2_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, String tipo, NodoFs raiz, ArrayList<NodoError> errores) {
        switch (tipo.toLowerCase()) {
            case "filtrar":
                return es_filtrar_2(hijos, vector, raiz, errores);
            case "buscar":
                return es_buscar_2(hijos, vector, raiz, errores);
            case "map":
                return es_map_2(hijos, vector, raiz, errores);
            case "reduce":
                return es_reduce_2(hijos, vector, raiz, errores);
            case "todos":
                return es_todos_2(hijos, vector, raiz, errores);
            case "algunos":
                return es_alguno_2(hijos, vector, raiz, errores);
        }
        return new NodoRespuesta(true);
    }

    private NodoRespuesta es_filtrar_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<NodoObjeto> respuesta = new ArrayList();
        NodoRespuesta retorno;
        ArrayList<NodoRespuesta> resultados = new ArrayList();
        while (i < vector.size()) {
            tabla.Tabla.clear();
            resultados.clear();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                nuevo.tipo = "objeto";
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                //System.out.println(actual.hijos.size() + "hijos");
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {
                        respuesta.add(vector.get(i - 1));
                    } else if (retornara.resultado.toString().equalsIgnoreCase("falso")) {

                    } else {
                        // System.out.println("hay error ->" + retornara.resultado);
                        return new NodoRespuesta(true);
                    }
                    break;
                }
            }
        }
        //System.out.println("llego aqui: " + respuesta.size());
        retorno = new NodoRespuesta(respuesta);
        retorno.tipo = "array";
        return retorno;
    }

    private NodoRespuesta es_map_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        ArrayList<NodoRespuesta> resultados = new ArrayList();
        while (i < vector.size()) {
            tabla.Tabla.clear();
            resultados.clear();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                nuevo.tipo = "objeto";
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                // System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    //System.out.println("dio un error");
                    return retornara;
                } else if (retornara.es_retorno) {
                    //System.out.println("entro a retorno" + i + ":" + retornara.resultado.toString());
                    respuesta.add(retornara.resultado.toString());
                } else {
                    //System.out.println("no retorno nada");
                }
            }
        }
        retorno = new NodoRespuesta(respuesta);
        retorno.tipo = "vector";
        return retorno;
    }

    private NodoRespuesta es_buscar_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<NodoObjeto> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                nuevo.tipo = "objeto";
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                //System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    //System.out.println("entro a retorno" + retornara.resultado.toString());
                    if (retornara.resultado.toString().equalsIgnoreCase("verdadero")) {
                        retorno = new NodoRespuesta(vector.get(i - 1));
                        retorno.tipo = "objeto";
                        return retorno;
                    }
                    break;
                } else {
                    //System.out.println("no retorno nada");
                }
            }
        }
        retorno = new NodoRespuesta(false);
        retorno.tipo = "undefined";
        return retorno;
    }

    private NodoRespuesta es_todos_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                nuevo.tipo = "objeto";
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

    private NodoRespuesta es_alguno_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        int i = 0;
        ArrayList<String> respuesta = new ArrayList();
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            for (int j = 0; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                nuevo.tipo = "objeto";
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

    private NodoRespuesta es_reduce_2(ArrayList<String> hijos, ArrayList<NodoObjeto> vector, NodoFs raiz, ArrayList<NodoError> errores) {
        //System.out.println("entro aqui reduce 2");
        int i = 0;
        NodoRespuesta respuesta = new NodoRespuesta(false);
        boolean primero = true;
        NodoRespuesta retorno;
        while (i < vector.size()) {
            tabla.Tabla.clear();
            ArrayList<NodoRespuesta> resultados = new ArrayList();
            resultados.add(new NodoRespuesta(0));
            for (int j = 1; j < hijos.size(); j++) {
                NodoRespuesta nuevo = new NodoRespuesta(vector.get(i));
                nuevo.tipo = "objeto";
                resultados.add(nuevo);
                i++;
            }
            pasar_parametros(hijos, resultados);
            NodoFs actual = raiz.hijos.get(1);
            NodoRespuesta retornara;
            for (int k = 0; k < actual.hijos.size(); k++) {
                //System.out.println(actual.hijos.get(k).Tipo);
                retornara = Analizar_Cuerpo(actual.hijos.get(k), errores);
                if (retornara.error) {
                    return retornara;
                } else if (retornara.es_retorno) {
                    //System.out.println("entro a retorno" + retornara.resultado.toString());
                    if (primero) {
                        primero = false;
                        respuesta = retornara;
                    } else if (retornara.tipo.equalsIgnoreCase("variable")) {
                        OPA_A op = new OPA_A(tabla, global, num);
                        respuesta = op.sumar_xdato(respuesta, retornara, actual.linea, actual.columna);
                    }
                    break;
                } else {
                    //System.out.println("no retorno nada");
                }
            }
        }
        respuesta.tipo = "variable";
        return respuesta;
    }

    private Object analizar_resultado(Object resultado) {
        String resultados = resultado.toString();
        if (resultados.contains("\"")) {
            resultados = resultados.replace("\"", "");
            String nuevo = "";
            char[] fac = resultados.toCharArray();
            for (int i = 1; i < fac.length; i++) {
                nuevo = nuevo + fac[i];
            }
            return "\"" + nuevo + "\"";
        }
        return resultado;
    }

    NodoRespuesta analizar_nati3(NodoFs raiz, ArrayList<NodoError> errores, NodoGxml nodo) {
        String tipo = raiz.valor;
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        if (raiz.hijos.size() == 2 && tipo.equalsIgnoreCase("obtenerpornombre")) {

            NodoRespuesta nombre = OP.Cuerpo_G(raiz.hijos.get(0), errores);
            NodoRespuesta id = OP.Cuerpo_G(raiz.hijos.get(1), errores);
            NodoObjeto resultado = nodo.Dev_Ventanas_Objeto(id.resultado.toString().replace("\"", ""), nombre.resultado.toString().replace("\"", ""));
            NodoRespuesta nuevo = new NodoRespuesta(resultado);
            nuevo.tipo = "objeto";
            return nuevo;

        } else if (raiz.hijos.size() == 1 && !tipo.equalsIgnoreCase("obtenerpornombre")) {
            NodoRespuesta nombre = OP.Cuerpo_G(raiz.hijos.get(0), errores);
            if (tipo.equalsIgnoreCase("obt_etiquieta")) {
                String tipo1 = nombre.resultado.toString().toLowerCase();
                System.out.println("entro aqui" + tipo1);
                switch (tipo1.replace("\"", "")) {
                    case "ventana":
                        ArrayList<NodoObjeto> valores = nodo.Dev_Ventanas_Objeto();
                        NodoRespuesta nuevo = new NodoRespuesta(valores);
                        nuevo.tipo = "array";
                        return nuevo;
                    case "contenedor":
                        valores = nodo.Dev_Conte_Objeto();
                        nuevo = new NodoRespuesta(valores);
                        nuevo.tipo = "array";
                        return nuevo;
                    case "texto":
                        valores = nodo.Dev_textos_Objeto();
                        nuevo = new NodoRespuesta(valores);
                        nuevo.tipo = "array";
                        return nuevo;
                    case "control":
                        valores = nodo.Dev_controles_Objeto();
                        nuevo = new NodoRespuesta(valores);
                        nuevo.tipo = "array";
                        return nuevo;
                    case "boton":
                        valores = nodo.Dev_botones_Objeto();
                        nuevo = new NodoRespuesta(valores);
                        nuevo.tipo = "array";
                        return nuevo;
                    case "multimedia":
                        valores = nodo.Dev_multi_Objeto();
                        nuevo = new NodoRespuesta(valores);
                        nuevo.tipo = "array";
                        return nuevo;
                }
                return new NodoRespuesta(true);
            } else {
                String tipo1 = nombre.resultado.toString().toLowerCase();
                NodoObjeto retorno = nodo.Dev_Ventanas_u_Objeto(tipo1);
                if (retorno != null) {
                    NodoRespuesta nuevo = new NodoRespuesta(retorno);
                    nuevo.tipo = "objeto";
                    return nuevo;
                } else {
                    return new NodoRespuesta("undefined");
                }
            }
        }
        return new NodoRespuesta(false);
    }
}
