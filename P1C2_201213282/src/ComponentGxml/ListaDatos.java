/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import Principal.NodoError;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class ListaDatos {

    public ArrayList<EDato> Datos;
    public String accion;
    public int linea, columna;

    public ListaDatos() {
        Datos = new ArrayList();
    }

    public void verficar_datos_repetidos(ArrayList<NodoError> lista) {
        for (int i = 0; i < Datos.size(); i++) {
            for (int j = i + 1; j < Datos.size(); j++) {
                if (Datos.get(i).Dato.equalsIgnoreCase(Datos.get(j).Dato)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el Dato: " + Datos.get(j).Dato + " en la lista de Datos";
                    error.linea = String.valueOf(Datos.get(j).linea);
                    error.columna = String.valueOf(Datos.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    public void imprimir_lista_datos() {
        System.out.println("---DATOS----");
        for (int i = 0; i < Datos.size(); i++) {

            System.out.println(Datos.get(i).Dato);
        }
        System.out.println("---DATOS----");
    }

}
