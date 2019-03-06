/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

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
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                NodoRespuesta retorno = new NodoRespuesta(actual.valor);
                retorno.dato = actual.nombre;
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
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        if (tabla.padre != null) {
            return ret_Existencia_ID(nombre, tabla.padre);
        }
        return false;
    }

    public Boolean set_Nuevoval_ID(String valor,String nombre,TablaSimbolos tabla){
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                actual.valor = valor;
                return true;
            }
        }
        if (tabla.padre != null) {
            return ret_Existencia_ID(nombre, tabla.padre);
        }
        return false;
    }
}
