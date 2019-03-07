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
public class ES_ID {

    TablaSimbolos tabla;

    public ES_ID(TablaSimbolos tabla) {
        this.tabla = tabla;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno;
        if (raiz.hijos.size() > 0) {//es vector
            if (raiz.lista.size() > 0) {

            } else { //vector simple
                uno = Cuerpo_A(raiz.hijos.get(0), errores);
                if (!uno.error) {
                    int num;
                    try {
                        num = Integer.valueOf(uno.resultado);
                        String id = raiz.valor + "[" + num + "]";
                        System.out.println("este es el ID: "+id);
                        Fs_varios nuevo = new Fs_varios();
                        boolean existe = nuevo.ret_Existencia_ID(id, tabla);
                        if (!existe) {
                            NodoError error = new NodoError("semantico");
                            error.descripcion = "el vector: " + id + " no existe o el numero esta fuera del tamaño";
                            errores.add(error);
                            uno = new NodoRespuesta(true);
                            return uno;
                        } else {
                            return nuevo.ret_ID_Tabla(id, tabla);
                        }
                    } catch (Exception e) {
                        NodoError error = new NodoError("semantico");
                        error.descripcion = "dentro del verctor " + raiz.valor + " no se encontro un numero entero se encontro: " + uno.resultado;
                        errores.add(error);
                        return new NodoRespuesta(true);
                    }
                }
            }
        } else if (raiz.lista.size() > 0) {

        } else {
            Fs_varios nuevo = new Fs_varios();
            boolean existe = nuevo.ret_Existencia_ID(raiz.valor, tabla);
            if (!existe) {
                NodoError error = new NodoError("semantico");
                error.descripcion = "no se encuentra la variable: " + raiz.valor + " en el documento";
                errores.add(error);
                uno = new NodoRespuesta(true);
                return uno;
            } else {
                return nuevo.ret_ID_Tabla(raiz.valor, tabla);
            }
        }
        uno = new NodoRespuesta(true);
        return uno;
    }

    public NodoRespuesta autoincrementar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno;
        System.out.println(raiz.Tipo);
        uno = Analizar(raiz.hijos.get(0), errores);
        if (!uno.error) {
            OPA_A nueva = new OPA_A(tabla);
            NodoRespuesta dos = nueva.sumar_uno(uno, errores, tabla);
            if (dos.error) {
                return dos;
            }
        }
        return uno;
    }

    public NodoRespuesta autodecrementar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno;
        System.out.println(raiz.Tipo);
        uno = Analizar(raiz.hijos.get(0), errores);
        if (!uno.error) {
            OPA_A nueva = new OPA_A(tabla);
            NodoRespuesta dos = nueva.restar_uno(uno, errores, tabla);
            if (dos.error) {
                return dos;
            }
        }
        return uno;
    }

    private NodoRespuesta Cuerpo_A(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "ope_l":
                break;
            case "ope_c":
                break;
            case "ope_a":
                OPA_A operacon = new OPA_A(tabla);
                return operacon.Analizar_OPA(raiz, errores);

            case "dato":
                nuevo = new NodoRespuesta(raiz.valor);
                return nuevo;
            case "dato negado":
                nuevo = new NodoRespuesta("-" + raiz.valor);
                return nuevo;

            case "autoincremento":
                ES_ID retorno = new ES_ID(tabla);
                return retorno.autoincrementar(raiz, errores);

            case "autodecremento":
                retorno = new ES_ID(tabla);
                return retorno.autodecrementar(raiz, errores);

            case "nativas":
                break;
            case "llamadafun":
                break;
            case "id":
                ES_ID id = new ES_ID(tabla);
                return id.Analizar(raiz, errores);

        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }
}
