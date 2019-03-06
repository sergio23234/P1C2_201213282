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
        if (raiz.hijos.size() > 0) {

        } else if (raiz.lista.size() > 0) {

        } else {
            Fs_varios nuevo = new Fs_varios();
            System.out.println(raiz.valor);
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

}
