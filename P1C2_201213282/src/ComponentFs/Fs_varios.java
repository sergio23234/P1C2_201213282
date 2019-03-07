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
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                NodoRespuesta retorno = new NodoRespuesta(actual.valor);
                retorno.dato = actual.nombre;
                return retorno;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && actual.tipo.equalsIgnoreCase("vector")) {
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                return new NodoRespuesta(actual.valores.get(num));
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
                    return num < actual.valores.size()&&num>=0;
                } catch (Exception e) {
                    return false;
                }
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
                actual.valores.set(num, valor);
                return true;
            }
        }
        if (tabla.padre != null) {
            return ret_Existencia_ID(nombre, tabla.padre);
        }
        return false;
    }
}
