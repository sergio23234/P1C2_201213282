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
public class Pasada1 {

    TablaSimbolos tabla;
    NodoFs raiz;

    public Pasada1(NodoFs raiz) {
        this.tabla = new TablaSimbolos("global");
        this.raiz = raiz;
    }

    public TablaSimbolos analizar(ArrayList<NodoError> errores) {
        analizar(raiz, errores);
        return tabla;
    }

    private void analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        if (raiz.Tipo.equalsIgnoreCase("inicio")) {
            for (int i = 0; i < raiz.hijos.size(); i++) {
                analizar(raiz.hijos.get(i), errores);
            }
        } else if (raiz.Tipo.equalsIgnoreCase("Cuerpo")) {
            for (int i = 0; i < raiz.hijos.size(); i++) {
                analizar(raiz.hijos.get(i), errores);
            }
        } else if (raiz.Tipo.equalsIgnoreCase("funcion")) {
            if (verificar_funcion_repetida(errores, raiz.valor)) {
                NodoTabla tabla = new NodoTabla(raiz, "funcion", raiz.valor);
                this.tabla.Tabla.add(tabla);
            }
        }
    }

    private boolean verificar_funcion_repetida(ArrayList<NodoError> errores, String nombre) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            if (tabla.Tabla.get(i).nombre.equalsIgnoreCase(nombre)) {
                NodoError error = new NodoError("semantico");
                error.descripcion = "error la funcion:" + nombre + " ya se encuentra repetido en este documento";
                errores.add(error);
                return false;
            }
        }
        return true;
    }
}
