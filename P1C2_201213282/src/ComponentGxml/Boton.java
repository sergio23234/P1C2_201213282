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
public class Boton {

    public String referencia, accion, Nombre, tipo;
    public int alto, ancho, x, y, linea, columna;
    public String texto;

    public Boton(String tipo) {
        referencia = Nombre = "";
        x = y = 0;
        alto = ancho = 100;
        linea = columna = 0;
        this.tipo = tipo;
    }

    public void Analizar_Attributos(NodoSGxml Nodo, ArrayList<NodoError> lista, String texto) {
        Analizar_Attributos_repetidos(Nodo.listas, lista);
        Analizar_Attributos_obligatorios(Nodo.listas, lista);
        Set_Attributos(Nodo.listas);
        this.texto = texto.replace("\"", "");
    }

    private void Analizar_Attributos_repetidos(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).tipo.equalsIgnoreCase(hijos.get(j).tipo)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo + " en la etiqueta Boton";
                    error.linea = String.valueOf(hijos.get(j).linea);
                    error.columna = String.valueOf(hijos.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributos_obligatorios(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        boolean id = false;
        boolean x = false, y = false;
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("nombre")) {
                id = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("X")) {
                x = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("Y")) {
                y = true;
            }
        }
        if (!id || !x || !y) {
            NodoError error = new NodoError("semantico");
            error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
            error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
            String tipoe;
            if (!id) {
                tipoe = "nombre";
            } else if (!x) {
                tipoe = "X";
            } else {
                tipoe = "Y";
            }
            error.descripcion = "No se encuentra el atributo: " + tipoe + " que es de caracter obligatorio en la etiqueta Multimedia";
            lista.add(error);
        }
    }

    private void Set_Attributos(ArrayList<NodoSGxml> hijos) {
        for (int i = 0; i < hijos.size(); i++) {
            String hijo = hijos.get(i).tipo;
            String val = hijos.get(i).val;
            switch (hijo.toLowerCase()) {
                case "referencia":
                    this.referencia = val.replace("\"", "");
                    break;                
                case "accion":
                    this.accion = val.replace("\"", "");
                    break;
                case "nombre":
                    this.Nombre = val.replace("\"", "");
                    this.linea = hijos.get(i).linea;
                    this.columna = hijos.get(i).columna;
                    break;
                case "y":
                    this.x = Integer.valueOf(val);
                    break;
                case "x":
                    this.y = Integer.valueOf(val);
                    break;
                case "alto":
                    this.alto = Integer.valueOf(val);
                    break;
                case "ancho":
                    this.ancho = Integer.valueOf(val);
                    break;
            }
        }
    }
}
