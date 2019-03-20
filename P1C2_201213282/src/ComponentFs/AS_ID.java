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
 * @author Sergio Fernando
 */
public class AS_ID {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public AS_ID(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta var;
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        var = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        //System.out.println("var: " + var.dato);
        if (!var.error) {
            if (raiz.hijos.size() > 1) {
                NodoFs actual = raiz.hijos.get(1);
                NodoRespuesta res = OP.Cuerpo_G(actual, errores);
                Analizar_paso2(var, res, raiz.valor, errores,raiz.linea,raiz.columna);
            } else {

            }
        }
        return var;
    }

    private NodoRespuesta Analizar_paso2(NodoRespuesta ID, NodoRespuesta resultado, String tipo, ArrayList<NodoError> errores,int linea,int columna) {
        switch (tipo.toLowerCase()) {
            case "=":
                return es_igualar(ID, resultado, errores);
            case "+=":
                return es_sumar(ID, resultado, errores,linea,columna);
            case "-=":
                return es_restar(ID, resultado, errores,linea,columna);
            case "*=":
                return es_multi(ID, resultado, errores,linea,columna);
            case "/=":
                return es_divi(ID, resultado, errores,linea,columna);
            case "funcion":
                break;
        }
        return new NodoRespuesta(true);
    }

    private NodoRespuesta es_igualar(NodoRespuesta ID, NodoRespuesta resultado, ArrayList<NodoError> errores) {
        String tipo = resultado.tipo;
        //System.out.println(resultado.resultado + "eso ingresara");
        if (tipo.equalsIgnoreCase("vector") || tipo.equalsIgnoreCase("objeto")) {
            if (ID.dato.contains("[") || ID.dato.contains(".")) {
                return new NodoRespuesta(true);
            } else if (tipo.equalsIgnoreCase("arrayespecial")) {
                return new NodoRespuesta(true);
            }else {
                set_Nuevoval_ID(resultado, ID.dato, tabla);
            }
        } else {
            set_Nuevoval_ID(resultado, ID.dato, tabla);
        }

        return new NodoRespuesta(false);
    }

    private Boolean set_Nuevoval_ID(NodoRespuesta valor, String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {//recorrer toda la tabla
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                //System.out.println("entro en esta parte");
                if (valor.tipo.equalsIgnoreCase("")) {
                    actual.tipo = "variable";
                } else {
                    actual.tipo = valor.tipo;
                }
                actual.valor = valor.resultado;
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && (Nombre_primero_pun_cor(nombre) == 2) && actual.tipo.equalsIgnoreCase("vector")) {
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                valores.set(num, valor.toString());
                if (nombre.contains(".")) {

                } else {
                    actual.tipo = valor.tipo;
                }
                actual.valor = valores;
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains(".") && (Nombre_primero_pun_cor(nombre) == 1) && actual.tipo.equalsIgnoreCase("objeto")) {
                String id[] = nombre.split("\\.");
                NodoObjeto actobj = (NodoObjeto) actual.valor;
                actobj.set_newval(id[1], valor.resultado);
                if (nombre.contains("[")) {

                } else {
                    actual.tipo = valor.tipo;
                }
                actual.valor = actobj;
                return true;
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("objeto")) {
                actual.tipo = valor.tipo;
                if (valor.tipo.equalsIgnoreCase("vector")) {
                    ArrayList<String> valores = (ArrayList<String>) valor.resultado;
                    ArrayList<String> nuevo = new ArrayList();
                    for (int j = 0; j < valores.size(); j++) {
                        nuevo.add(valores.get(j));
                    }
                    actual.valor = nuevo;
                    return true;
                } else if (valor.tipo.equalsIgnoreCase("objeto")) {
                    NodoObjeto valor1 = (NodoObjeto) valor.resultado;
                    NodoObjeto nuevo = new NodoObjeto();
                    valor1.ret_bojetos(nuevo.objetos);
                    actual.valor = nuevo;
                    return true;
                }
                actual.valor = valor.resultado;
                return true;
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("vector")) {//es vector
                actual.tipo = valor.tipo;
                if (valor.tipo.equalsIgnoreCase("vector")) {
                    ArrayList<String> valores = (ArrayList<String>) valor.resultado;
                    ArrayList<String> nuevo = new ArrayList();
                    for (int j = 0; j < valores.size(); j++) {
                        nuevo.add(valores.get(j));
                    }
                    actual.valor = nuevo;
                    return true;
                } else if (valor.tipo.equalsIgnoreCase("objeto")) {
                    NodoObjeto valor1 = (NodoObjeto) valor.resultado;
                    NodoObjeto nuevo = new NodoObjeto();
                    valor1.ret_bojetos(nuevo.objetos);
                    actual.valor = nuevo;
                    return true;
                }
                actual.valor = valor.resultado;
                return true;
            }
        }
        if (tabla.padre != null) {
            return set_Nuevoval_ID(valor, nombre, tabla.padre);
        }
        return false;
    }

    private NodoRespuesta es_sumar(NodoRespuesta ID, NodoRespuesta resultado, ArrayList<NodoError> errores,int linea,int columna) {
        if (resultado.tipo.equalsIgnoreCase("objeto") || resultado.tipo.equalsIgnoreCase("objeto")) {
            return new NodoRespuesta(true);
        } else {
            OPA_A opa = new OPA_A(tabla, global, num);
            NodoRespuesta sumar = opa.sumar_xdato(ID, resultado,linea,columna);
            if (sumar.error) {
                return sumar;
            } else {

                Fs_varios id = new Fs_varios();
                boolean funciono = id.set_Nuevoval_ID(sumar.resultado.toString(), sumar.dato, tabla);
                if (funciono) {
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            }
        }
    }

    private NodoRespuesta es_restar(NodoRespuesta ID, NodoRespuesta resultado, ArrayList<NodoError> errores,int linea,int columna) {
        if (resultado.tipo.equalsIgnoreCase("objeto") || resultado.tipo.equalsIgnoreCase("objeto")) {
            return new NodoRespuesta(true);
        } else {
            OPA_A opa = new OPA_A(tabla, global, num);
            NodoRespuesta sumar = opa.restar_xdato(ID, resultado,linea,columna);
            if (sumar.error) {
                return sumar;
            } else {

                Fs_varios id = new Fs_varios();
                boolean funciono = id.set_Nuevoval_ID(sumar.resultado.toString(), sumar.dato, tabla);
                if (funciono) {
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            }
        }
    }

    private NodoRespuesta es_multi(NodoRespuesta ID, NodoRespuesta resultado, ArrayList<NodoError> errores,int linea,int columna) {
        if (resultado.tipo.equalsIgnoreCase("objeto") || resultado.tipo.equalsIgnoreCase("objeto")) {
            return new NodoRespuesta(true);
        } else {
            OPA_A opa = new OPA_A(tabla, global, num);
            NodoRespuesta sumar = opa.multi_xdato(ID, resultado,linea,columna);
            if (sumar.error) {
                return sumar;
            } else {

                Fs_varios id = new Fs_varios();
                boolean funciono = id.set_Nuevoval_ID(sumar.resultado.toString(), sumar.dato, tabla);
                if (funciono) {
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            }
        }
    }

    private NodoRespuesta es_divi(NodoRespuesta ID, NodoRespuesta resultado, ArrayList<NodoError> errores,int linea,int columna) {
        if (resultado.tipo.equalsIgnoreCase("objeto") || resultado.tipo.equalsIgnoreCase("objeto")) {
            return new NodoRespuesta(true);
        } else {
            OPA_A opa = new OPA_A(tabla, global, num);
            NodoRespuesta sumar = opa.divi_xdato(ID, resultado,linea,columna);
            if (sumar.error) {
                return sumar;
            } else {

                Fs_varios id = new Fs_varios();
                boolean funciono = id.set_Nuevoval_ID(sumar.resultado.toString(), sumar.dato, tabla);
                if (funciono) {
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            }
        }
    }

    private int Nombre_primero_pun_cor(String nombre) {
        char nom[] = nombre.toCharArray();
        for (int i = 0; i < nom.length; i++) {
            if (nom[i] == '.') {
                return 1;
            } else if (nom[i] == '[') {
                return 2;
            }
        }
        return -1;
    }
}
