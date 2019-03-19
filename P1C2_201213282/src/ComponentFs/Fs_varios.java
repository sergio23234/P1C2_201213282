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
            } else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("arrayespecial")) {
                NodoRespuesta retorno = new NodoRespuesta(actual.valor);
                retorno.dato = actual.nombre;
                retorno.tipo = actual.tipo;
                return retorno;
            } else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("array")) {
                NodoRespuesta retorno = new NodoRespuesta(actual.valor);
                retorno.dato = actual.nombre;
                retorno.tipo = actual.tipo;
                return retorno;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && (Nombre_primero_pun_cor(nombre) == 2) && actual.tipo.equalsIgnoreCase("vector")) {
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
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("objeto")) {
                String super_name = nombre;
                NodoObjeto valores = (NodoObjeto) actual.valor;
                NodoRespuesta retorno = new NodoRespuesta(valores);
                retorno.dato = super_name;
                retorno.tipo = actual.tipo;
                return retorno;
            } else if (nombre.contains(actual.nombre) && nombre.contains(".") && (Nombre_primero_pun_cor(nombre) == 1) && actual.tipo.equalsIgnoreCase("objeto")) {
                String id[] = nombre.split("\\.");
                NodoObjeto actobj = (NodoObjeto) actual.valor;
                NodoRespuesta retorno = new NodoRespuesta(actobj.retornar_por_id(id[1]));
                retorno.dato = nombre;
                retorno.tipo = actobj.retornar_tipo_por_id(id[1]);
                return retorno;
            }
        }
        if (tabla.padre != null) {
            return ret_ID_Tabla(nombre, tabla.padre);
        }
        return null;
    }

    public Boolean ret_Existencia_ID(String nombre, TablaSimbolos tabla) {
        System.out.println(nombre+" <---nombre");
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                return true;
            } else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("ventana")) {
                return true;
            }  else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("arrayespecial")) {
                return true;
            }else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("contenedor")) {
                return true;
            } else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("boton")) {
                return true;
            } else if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("array")) {
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && (Nombre_primero_pun_cor(nombre) == 2) && actual.tipo.equalsIgnoreCase("vector")) {
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
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("objeto")) {
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains(".") && (Nombre_primero_pun_cor(nombre) == 1) && actual.tipo.equalsIgnoreCase("objeto")) {
                String id[] = nombre.split("\\.");
                // System.out.println("es:---> "+id[1]);
                NodoObjeto actobj = (NodoObjeto) actual.valor;
                return actobj.existe_id(id[1]);
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
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && (Nombre_primero_pun_cor(nombre) == 2) && actual.tipo.equalsIgnoreCase("vector")) {
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                valores.set(num, valor);
                actual.valor = valores;
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains(".") && (Nombre_primero_pun_cor(nombre) == 1) && actual.tipo.equalsIgnoreCase("objeto")) {
                String id[] = nombre.split("\\.");
                NodoObjeto actobj = (NodoObjeto) actual.valor;
                actobj.set_newval(id[1], valor);
                actual.valor = actobj;
                return true;
            }
        }
        if (tabla.padre != null) {
            return set_Nuevoval_ID(valor, nombre, tabla.padre);
        }
        return false;
    }

    public Boolean set_Nuevoval_ID2(NodoRespuesta valor, String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre) && actual.tipo.equalsIgnoreCase("variable")) {
                actual.valor = valor.resultado;
                actual.tipo = valor.tipo;
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains("[") && (Nombre_primero_pun_cor(nombre) == 2) && actual.tipo.equalsIgnoreCase("vector")) {
                String nombre_var = actual.nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                valores.set(num, valor.resultado.toString());
                actual.valor = valores;
                actual.tipo = valor.tipo;
                return true;
            } else if (nombre.contains(actual.nombre) && nombre.contains(".") && (Nombre_primero_pun_cor(nombre) == 1) && actual.tipo.equalsIgnoreCase("objeto")) {
                String id[] = nombre.split("\\.");
                NodoObjeto actobj = (NodoObjeto) actual.valor;
                actobj.set_newval(id[1], valor.resultado);
                actual.valor = actobj;
                return true;
            } else if (nombre.equalsIgnoreCase(actual.nombre) && actual.tipo.equalsIgnoreCase("vector")) {
                actual.valor = valor.resultado;
                actual.tipo = valor.tipo;
                return true;

            }
        }
        if (tabla.padre != null) {
            return set_Nuevoval_ID2(valor, nombre, tabla.padre);
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
        } else if (tabla.importados != null) {
            for (int i = 0; i < tabla.importados.size(); i++) {
                boolean existe = ret_Existencia_fun(nombre, tabla.importados.get(i));
                if (existe) {
                    return true;
                }
            }
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
        } else if (tabla.importados != null) {
            for (int i = 0; i < tabla.importados.size(); i++) {
                NodoFs existe = ret_fun_Tabla(nombre, tabla.importados.get(i));
                if (existe != null) {
                    return existe;
                }
            }
        }
        return null;
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

    public String ret_Tipo_ID(String nombre, TablaSimbolos tabla) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                return actual.tipo;
            }
        }
        if (tabla.padre != null) {
            return ret_Tipo_ID(nombre, tabla.padre);
        }
        return "";
    }
}
