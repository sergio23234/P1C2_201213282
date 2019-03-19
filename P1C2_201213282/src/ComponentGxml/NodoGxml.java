/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import ComponentFs.NodoObjeto;
import Principal.NodoError;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class NodoGxml {

    public ArrayList<Ventana> Ventanas;
    public ArrayList<EDato> Importaciones;
    public Ventana actual;
    public ArrayList<NodoError> errores;

    public NodoGxml() {
        Ventanas = new ArrayList();
        Importaciones = new ArrayList();
        errores = new ArrayList();
    }

    public void Recorrer_Ventanas() {
        for (int i = 0; i < Ventanas.size(); i++) {
            Ventana actual = Ventanas.get(i);
            System.out.println(actual.Id + "-->" + actual.tipo + "-->" + actual.color + "-->" + actual.accionI + "-->" + actual.accionF + "-->" + actual.contenedores.size());
        }
    }

    public void Analizar_Nombres_Ventanas(ArrayList<NodoError> lista) {
        for (int i = 0; i < Ventanas.size(); i++) {
            for (int j = i + 1; j < Ventanas.size(); j++) {
                if (Ventanas.get(i).Id.equalsIgnoreCase(Ventanas.get(j).Id)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Ventanas.get(j).Id + " en la etiquetas de ventanas";
                    error.linea = String.valueOf(Ventanas.get(j).linea);
                    error.columna = String.valueOf(Ventanas.get(j).columna);
                    lista.add(error);
                }
            }
            Ventanas.get(i).Analizar_Nombre_Contenedores(lista);
        }
    }

    public void Analizar_Importaciones(ArrayList<NodoError> lista) {
        for (int i = 0; i < Importaciones.size(); i++) {
            for (int j = i + 1; j < Importaciones.size(); j++) {
                if (Importaciones.get(i).Dato.equalsIgnoreCase(Importaciones.get(j).Dato)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Importaciones.get(j).Dato + " en la etiquetas de importaciones";
                    error.linea = String.valueOf(Importaciones.get(j).linea);
                    error.columna = String.valueOf(Importaciones.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    public ArrayList<NodoObjeto> Dev_Ventanas_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Ventanas.size(); i++) {
            lista.add(Ventanas.get(i).dev_ventana());
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_Conte_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Ventanas.size(); i++) {
            ArrayList<NodoObjeto> aux = Ventanas.get(i).Dev_Contenedores_Objeto();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(j));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_botones_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Ventanas.size(); i++) {
            ArrayList<NodoObjeto> aux = Ventanas.get(i).Dev_botones_Objeto();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(j));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_multi_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Ventanas.size(); i++) {
            ArrayList<NodoObjeto> aux = Ventanas.get(i).Dev_Multi_Objeto();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(j));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_textos_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Ventanas.size(); i++) {
            ArrayList<NodoObjeto> aux = Ventanas.get(i).Dev_textos_Objeto();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(j));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_controles_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Ventanas.size(); i++) {
            ArrayList<NodoObjeto> aux = Ventanas.get(i).Dev_controles_Objeto();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(j));
            }
        }
        return lista;
    }

    public NodoObjeto Dev_Ventanas_Objeto(String nombre, String objeto) {
        for (int i = 0; i < Ventanas.size(); i++) {
            if(Ventanas.get(i).Dev_Objeto(objeto)!=null){
                return Ventanas.get(i).Dev_Objeto(objeto);
            }
        }
        return null;
    }

    public ArrayList<Ventana> dev_ventanas(){
        return Ventanas;
    }
}
