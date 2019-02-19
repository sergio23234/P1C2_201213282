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
public class Contenedor {

    public String Id;
    public String color;
    public String Borde;
    public int x, y;
    public int alto, ancho;
    public int linea, columna;
    public ArrayList<Texto> Textos;

    public Contenedor() {
        Textos = new ArrayList();
        Id = "";
        Borde = "falso";
        color = "#ffffff";
        x = y = 0;
        alto = ancho = 500;
        linea = columna = 0;
    }

    public void Imprimir_NodoS(NodoSGxml nodo) {
        System.out.println("----------------------***------------------");
        for (int i = 0; i < nodo.listas.size(); i++) {
            NodoSGxml actual = nodo.listas.get(i);
            System.out.println(actual.tipo + "--->" + actual.val);
        }
        System.out.println("----------------------***------------------");
    }

    public void Analizar_Attributos(NodoSGxml Nodo, ArrayList<NodoError> lista) {
        Analizar_Attributos_repetidos(Nodo.listas, lista);
        Analizar_Attributos_obligatorios(Nodo.listas, lista);
        Analizar_Attributo_Borde(Nodo.listas, lista);
        Analizar_Attributo_Numeros(Nodo.listas, lista);
        //Analizar_Attributo_Color(Nodo.listas,lista);
        Set_Attributos(Nodo.listas);
    }

    private void Analizar_Attributos_repetidos(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).tipo.equalsIgnoreCase(hijos.get(j).tipo)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo+" en la etiqueta contenedor";
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
            if (hijos.get(i).tipo.equalsIgnoreCase("id")) {
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
                tipoe = "ID";
            } else if (!x) {
                tipoe = "X";
            } else {
                tipoe = "Y";
            }
            error.descripcion = "No se encuentra el atributo: " + tipoe + " que es de caracter obligatorio en la etiqueta contenedor";
            lista.add(error);
        }
    }

    private void Analizar_Attributo_Borde(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("borde")) {
                String hijo = hijos.get(i).val.replace("\"", "");
                if (!(hijo.equalsIgnoreCase("verdadero") || hijo.equalsIgnoreCase("falso"))) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "El tipo definido solo puede ser verdadero o falso no: " + hijos.get(i).val;
                    error.linea = String.valueOf(hijos.get(i).linea);
                    error.columna = String.valueOf(hijos.get(i).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributo_Numeros(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            String hijoss = hijos.get(i).tipo;
            if (hijoss.equalsIgnoreCase("x") || hijoss.equalsIgnoreCase("y") || hijoss.equalsIgnoreCase("ancho") || hijoss.equalsIgnoreCase("alto")) {
                int hijo = Integer.valueOf(hijos.get(i).val);
                if (hijo < 0) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "El tipo definido solo puede cotener numeros mayores a cero el numero ingresados es: " + hijos.get(i).val;
                    error.linea = String.valueOf(hijos.get(i).linea);
                    error.columna = String.valueOf(hijos.get(i).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Set_Attributos(ArrayList<NodoSGxml> hijos) {
        for (int i = 0; i < hijos.size(); i++) {
            String hijo = hijos.get(i).tipo;
            String val = hijos.get(i).val;
            switch (hijo.toLowerCase()) {
                case "borde":
                    this.Borde = val.replace("\"", "");
                    break;
                case "id":
                    this.Id = val.replace("\"", "");
                    this.linea = hijos.get(i).linea;
                    this.columna = hijos.get(i).columna;
                    break;
                case "color":
                    this.color = val.replace("\"", "");
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
