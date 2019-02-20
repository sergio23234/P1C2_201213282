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
public class NodoGxml {

    public ArrayList<Ventana> Ventanas;
    public Ventana actual;

    public NodoGxml() {
        Ventanas = new ArrayList();
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
}
