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
public class Control {

    public String Nombre;
    public String Tipo;
    public String color;
    public String Fuente;
    public int x, y, maximo, minimo;
    public int tam, alto, ancho;
    public String negrita, cursiva, accion;
    public int linea, columna;

    public Control() {
        Nombre = Tipo = Fuente = "";
        alto = ancho = 50;
        tam = 12;
        color = "#000000";
        x = y = maximo = minimo = 0;
        negrita = cursiva = "falso";
        accion = "";
    }

    public void Analizar_Attributos(NodoSGxml Nodo, ArrayList<NodoError> lista) {
        Analizar_Attributos_repetidos(Nodo.listas, lista);
        Analizar_Attributos_obligatorios(Nodo.listas, lista);
        //Analizar_Attributo_Borde(Nodo.listas, lista);
        //Analizar_Attributo_Numeros(Nodo.listas, lista);
        //Analizar_Attributo_Color(Nodo.listas,lista);
        //Set_Attributos(Nodo.listas);
    }

    private void Analizar_Attributos_repetidos(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).tipo.equalsIgnoreCase(hijos.get(j).tipo)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo + " en la etiqueta control";
                    error.linea = String.valueOf(hijos.get(j).linea);
                    error.columna = String.valueOf(hijos.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributos_obligatorios(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        boolean tipo = false, id = false;
        boolean x = false, y = false;
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("nombre")) {
                id = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("X")) {
                x = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("Y")) {
                y = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("tipo")) {
                y = true;
            }
        }
        if (!id || !x || !y) {
            NodoError error = new NodoError("semantico");
            error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
            error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
            String tipoe;
            if (!id) {
                tipoe = "Nombre";
            } else if (!x) {
                tipoe = "X";
            } else if (!tipo) {
                tipoe = "Tipo";
            } else {
                tipoe = "Y";
            }
            error.descripcion = "No se encuentra el atributo: " + tipoe + " que es de caracter obligatorio en etiqueta Control";
            lista.add(error);
        }
    }

    private void Analizar_Attributos_Tipo(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        NodoSGxml actual = new NodoSGxml();
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("tipo")) {
                actual = hijos.get(i);
                break;
            }
        }
        switch (actual.val.replace("\"", "").toLowerCase()) {
            case "texto":
                Analizar_Attributos_Tipo_Texto(hijos, lista);
                break;
            case "numerico":
                break;
            case "textoarea":
                break;
            case "desplegable":
                break;

        }
    }

    private void Analizar_Attributos_Tipo_Texto(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            NodoSGxml actual = hijos.get(i);
            NodoError error;
            switch (actual.tipo.toLowerCase()) {
                case "maximo":
                    error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo Maximo no esta permitido dentro del cotrol tipo texto";
                    lista.add(error);
                    break;
                case "minimo":
                    error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo Maximo no esta permitido dentro del cotrol tipo texto";
                    lista.add(error);
                    break;                    
            }
        }
    }
}
