/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import ComponentFs.NodoObjeto;
import ComponentFs.Raiz;
import Principal.NodoError;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author sergi
 */
public class Texto {

    public String Nombre;
    public String color;
    public String Fuente;
    public int x, y;
    public int tam;
    public String negrita, cursiva;
    public int linea, columna;
    public String Valor;

    public Texto() {
        Nombre = "";
        Fuente = "Arial";
        color = "#000000";
        x = y = 0;
        tam = 14;
        negrita = cursiva = "falso";
        linea = columna = 0;
        Valor = "";
    }

    public void Analizar_Attributos(NodoSGxml Nodo, ArrayList<NodoError> lista, int tipo) {
        Analizar_Attributos_repetidos(Nodo.listas, lista);
        Analizar_Attributos_obligatorios(Nodo.listas, lista, tipo);
        //Analizar_Attributo_Borde(Nodo.listas, lista);
        //Analizar_Attributo_Numeros(Nodo.listas, lista);
        //Analizar_Attributo_Color(Nodo.listas,lista);
        Set_Attributos(Nodo.listas);

    }

    public void Analizar_Valor(EDato Valor, ArrayList<NodoError> lista) {
        if (Valor.Dato.trim().contains("\n")) {
            NodoError error;
            error = new NodoError("semantico");
            error.linea = String.valueOf(Valor.linea);
            error.columna = String.valueOf(Valor.columna);
            error.descripcion = "la etiqueta tiene un salto de linea no valido en la etiqueta Texto";
            lista.add(error);
        } else {
            this.Valor = Valor.Dato.trim();
        }
    }

    private void Analizar_Attributos_repetidos(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).tipo.equalsIgnoreCase(hijos.get(j).tipo)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo + " en la etiqueta texto";
                    error.linea = String.valueOf(hijos.get(j).linea);
                    error.columna = String.valueOf(hijos.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributos_obligatorios(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista, int tipo) {
        if (tipo == 1) {
            Analizar_Attributos_obligatorios_especial(hijos, lista);
        } else {
            Analizar_Attributos_obligatorios_normal(hijos, lista);
        }
    }

    private void Analizar_Attributos_obligatorios_especial(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        boolean id = false;
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("nombre")) {
                id = true;
            }
        }
        if (!id) {
            NodoError error = new NodoError("semantico");
            error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
            error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
            String tipoe;
            tipoe = "Nombre";
            error.descripcion = "No se encuentra el atributo: " + tipoe + " que es de caracter obligatorio en etiqueta Texto";
            lista.add(error);
        }
    }

    private void Analizar_Attributos_obligatorios_normal(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
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
                tipoe = "Nombre";
            } else if (!x) {
                tipoe = "X";
            } else {
                tipoe = "Y";
            }
            error.descripcion = "No se encuentra el atributo: " + tipoe + " que es de caracter obligatorio en etiqueta Texto";
            lista.add(error);
        }
    }

    private void Set_Attributos(ArrayList<NodoSGxml> hijos) {
        for (int i = 0; i < hijos.size(); i++) {
            String hijo = hijos.get(i).tipo;
            String val = hijos.get(i).val;
            switch (hijo.toLowerCase()) {
                case "negrita":
                    this.negrita = val.replace("\"", "");
                    break;
                case "cursiva":
                    this.cursiva = val.replace("\"", "");
                    break;
                case "nombre":
                    this.Nombre = val.replace("\"", "");
                    this.linea = hijos.get(i).linea;
                    this.columna = hijos.get(i).columna;
                    break;
                case "color":
                    this.color = val.replace("\"", "");
                    break;
                case "y":
                    this.y = Integer.valueOf(val);
                    break;
                case "x":
                    this.x = Integer.valueOf(val);
                    break;
                case "fuente":
                    this.Fuente = val.replace("\"", "");
                    break;
                case "tam":
                    this.tam = Integer.valueOf(val);
                    break;
            }
        }
    }

    public NodoObjeto dev_texto() {
        ArrayList<Raiz> lista = new ArrayList();
        Raiz uno = new Raiz("color", color, "variable");
        lista.add(uno);
        uno = new Raiz("Fuente", Fuente, "variable");
        lista.add(uno);
        uno = new Raiz("tamañio", tam, "variable");
        lista.add(uno);
        uno = new Raiz("y", y, "variable");
        lista.add(uno);
        uno = new Raiz("x", x, "variable");
        lista.add(uno);
        uno = new Raiz("negrita", negrita, "variable");
        lista.add(uno);
        uno = new Raiz("cursiva", cursiva, "variable");
        lista.add(uno);
        uno = new Raiz("nombre", Nombre, "variable");
        lista.add(uno);
        uno = new Raiz("valor", Valor, "variable");
        lista.add(uno);
        NodoObjeto nuevo = new NodoObjeto(lista);
        return nuevo;
    }
}
