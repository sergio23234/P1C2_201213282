/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class NodoObjeto {

    public ArrayList<Raiz> objetos;

    public NodoObjeto() {
        objetos = new ArrayList();
    }

    public String retornar_por_id(String nombre) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).nombre.equalsIgnoreCase(nombre)) {
                return objetos.get(i).valor;
            }
        }
        return "";
    }

    public boolean existe_id(String nombre) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean set_newval(String nombre, String val) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).nombre.equalsIgnoreCase(nombre)) {
                objetos.get(i).valor = val;
                return true;
            }
        }
        return false;
    }

    public boolean ret_bojetos(ArrayList<Raiz> nueva) {
        for (int i = 0; i < objetos.size(); i++) {
            Raiz nuevo = new Raiz(objetos.get(i).nombre,objetos.get(i).valor);
            nueva.add(nuevo);
        }
        return true;
    }
}
