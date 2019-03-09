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
public class Fs_varios {

    public Fs_varios() {

    }

    public NodoRespuesta ret_ID_Tabla(String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                NodoRespuesta retorno = new NodoRespuesta(actual.valor);
                retorno.dato = actual.nombre;
                retorno.tipo = actual.tipo;
                return retorno;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && actual.tipo.equalsIgnoreCase("vector")) {
                String super_name = nombre;
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                NodoRespuesta retorno = new NodoRespuesta(valores.get(num));
                retorno.dato = super_name;
                retorno.tipo = "variable";
                return retorno;
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("vector")) {
                String super_name = nombre;
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                NodoRespuesta retorno = new NodoRespuesta(valores);
                retorno.dato = super_name;
                retorno.tipo = actual.tipo;
                return retorno;
            }
        }
        if (tabla.padre != null) {
            return ret_ID_Tabla(nombre, tabla.padre);
        }
        return null;
    }

    public Boolean ret_Existencia_ID(String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && actual.tipo.equalsIgnoreCase("vector")) {
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                try {
                    int num = Integer.valueOf(pos_num);
                    ArrayList<String> valores = (ArrayList<String>) actual.valor;
                    return num < valores.size() && num >= 0;
                } catch (Exception e) {
                    return false;
                }
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("vector")) {
                return true;
            }
        }
        if (tabla.padre != null) {
            return ret_Existencia_ID(nombre, tabla.padre);
        }
        return false;
    }

    public Boolean set_Nuevoval_ID(String valor, String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                actual.valor = valor;
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && actual.tipo.equalsIgnoreCase("vector")) {
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                valores.set(num, valor);
                actual.valor = valores;
                return true;
            }
        }
        if (tabla.padre != null) {
            return ret_Existencia_ID(nombre, tabla.padre);
        }
        return false;
    }

    public Boolean ret_Existencia_fun(String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("funcion")) {
                return true;
            }
        }
        if (tabla.padre != null) {
            return ret_Existencia_ID(nombre, tabla.padre);
        }
        return false;
    }

    public NodoFs ret_fun_Tabla(String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("funcion")) {
                return actual.nodo_raiz;
            }
        }
        if (tabla.padre != null) {
            return ret_fun_Tabla(nombre, tabla.padre);
        }
        return null;
    }

}
