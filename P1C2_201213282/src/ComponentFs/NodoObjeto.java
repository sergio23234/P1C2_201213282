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

    public Object retornar_por_id(String nombre) {
        System.out.println("entro"+nombre);
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).nombre.equalsIgnoreCase(nombre)) {
                return objetos.get(i).valor;
            }else if(nombre.contains(objetos.get(i).nombre)&&nombre.contains("[")){
                String nombre_var = objetos.get(i).nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                int num = Integer.valueOf(pos_num);
                ArrayList<String> valores = (ArrayList<String>) objetos.get(i).valor;
                return valores.get(num);
            }
        }
        return "";
    }

    public boolean existe_id(String nombre) {
        for (int i = 0; i < objetos.size(); i++) {
            System.out.println("nombre: "+objetos.get(i).nombre+"///"+objetos.get(i).tipo);
            if (objetos.get(i).nombre.equalsIgnoreCase(nombre)) {
                return true;
            }else if(nombre.contains(objetos.get(i).nombre)&&nombre.contains("[")&&objetos.get(i).tipo.equalsIgnoreCase("vector")){
                String nombre_var = objetos.get(i).nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                try {
                    int num = Integer.valueOf(pos_num);
                    ArrayList<String> valores = (ArrayList<String>) objetos.get(i).valor;

                    return num < valores.size()&& num >= 0;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean set_newval(String nombre, Object val) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).nombre.equalsIgnoreCase(nombre)) {
                objetos.get(i).valor = val;
                return true;
            }else if(nombre.contains(objetos.get(i).nombre)&&nombre.contains("[")&&objetos.get(i).tipo.equalsIgnoreCase("vector")){
                String nombre_var = objetos.get(i).nombre;
                String pos_num = nombre.replace(nombre_var, "").replace("[", "").replace("]", "");
                try {
                    int num = Integer.valueOf(pos_num);
                    ArrayList<String> valores = (ArrayList<String>) objetos.get(i).valor;
                    valores.set(num, val.toString());
                    objetos.get(i).valor= valores;
                    return true;
                } catch (Exception e) {
                    return false;
                }
        }
            }
        return false;
    }

    public boolean ret_bojetos(ArrayList<Raiz> nueva) {
        for (int i = 0; i < objetos.size(); i++) {
            Raiz nuevo = new Raiz(objetos.get(i).nombre,objetos.get(i).valor,objetos.get(i).tipo);
            nueva.add(nuevo);
        }
        return true;
    }
}
