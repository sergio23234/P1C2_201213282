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

/**
 *
 * @author sergi
 */
public class Multimedia {

    public String Path, Nombre, Tipo, Auto;
    public int alto, ancho, x, y;
    public int linea, columna;
    //public String texto;

    public Multimedia() {
        Path = Nombre = Tipo = "";
        Auto = "verdadero";
        x = y = 0;
        alto = ancho = 200;
        linea = columna = 0;
    }

    private void Analizar_Attributos_repetidos(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).tipo.equalsIgnoreCase(hijos.get(j).tipo)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo + " en la etiqueta multimedia";
                    error.linea = String.valueOf(hijos.get(j).linea);
                    error.columna = String.valueOf(hijos.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributos_obligatorios(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        boolean id = false, tipo = false;
        boolean x = false, y = false, path=false;
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("nombre")) {
                id = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("X")) {
                x = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("Y")) {
                y = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("Tipo")) {
                tipo = true;
            }else if (hijos.get(i).tipo.equalsIgnoreCase("Path")) {
                path = true;
            }
        }
        if (!id || !x || !y || !tipo|| !path) {
            NodoError error = new NodoError("semantico");
            error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
            error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
            String tipoe;
            if (!id) {
                tipoe = "nombre";
            } else if (!x) {
                tipoe = "X";
            } else if (!y) {
                tipoe = "Y";
            } else if (!path) {
                tipoe = "Path";
            }  else {
                tipoe = "Tipo";
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
                case "path":
                    this.Path = val.replace("\"", "");
                    break;
                case "nombre":
                    this.Nombre = val.replace("\"", "");
                    this.linea = hijos.get(i).linea;
                    this.columna = hijos.get(i).columna;
                    break;
                case "tipo":
                    this.Tipo = val.replace("\"", "");
                    break;
                case "auto":
                    this.Auto = val.replace("\"", "");
                    break;
                case "y":
                    this.y = Integer.valueOf(val);
                    break;
                case "x":
                    this.x = Integer.valueOf(val);
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

    public void Analizar_Attributos(NodoSGxml Nodo, ArrayList<NodoError> lista) {
        Analizar_Attributos_repetidos(Nodo.listas, lista);
        Analizar_Attributos_obligatorios(Nodo.listas, lista);
        Set_Attributos(Nodo.listas);
       // this.texto = texto.replace("\"","");
    }

 public NodoObjeto dev_multi(){
        ArrayList<Raiz> lista = new ArrayList();
        Raiz uno = new Raiz("path",Path,"variable");
        lista.add(uno);
        uno = new Raiz("alto",alto,"variable");
        lista.add(uno);
        uno = new Raiz("ancho",ancho,"variable");
        lista.add(uno);
         uno = new Raiz("y",y,"variable");
        lista.add(uno);
        uno = new Raiz("x",x,"variable");
        lista.add(uno);
        uno = new Raiz("Auto",Auto,"variable");
        lista.add(uno);
        uno = new Raiz("Nombre",Nombre,"variable");
        lista.add(uno);
        NodoObjeto nuevo = new NodoObjeto(lista);
        return nuevo;
    }
}
