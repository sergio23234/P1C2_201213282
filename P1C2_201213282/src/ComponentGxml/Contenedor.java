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
public class Contenedor {

    public String Id;
    public String color;
    public String Borde;
    public int x, y;
    public int alto, ancho;
    public int linea, columna;
    public ArrayList<Texto> Textos;
    public ArrayList<Control> controles;
    public ArrayList<Multimedia> Multi;
    public ArrayList<Boton> Botones;

    public Contenedor() {
        Textos = new ArrayList();
        Id = "";
        Borde = "falso";
        color = "#ffffff";
        x = y = 0;
        alto = ancho = 500;
        linea = columna = 0;
        Textos = new ArrayList();
        controles = new ArrayList();
        Multi = new ArrayList();
        Botones = new ArrayList();
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
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo + " en la etiqueta contenedor";
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
                case "x":
                    this.x = Integer.valueOf(val);
                    break;
                case "y":
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

    private void Analizar_Nombres_Texto(ArrayList<NodoError> lista) {
        for (int i = 0; i < Textos.size(); i++) {
            for (int j = i + 1; j < Textos.size(); j++) {
                if (Textos.get(i).Nombre.equalsIgnoreCase(Textos.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Textos.get(j).Nombre + " en la etiquetas Texto";
                    error.linea = String.valueOf(Textos.get(j).linea);
                    error.columna = String.valueOf(Textos.get(j).columna);
                    lista.add(error);
                }
            }
            for (int j = 0; j < controles.size(); j++) {
                if (Textos.get(i).Nombre.equalsIgnoreCase(controles.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + controles.get(j).Nombre + " en la etiquetas Texto y Control";
                    error.linea = String.valueOf(controles.get(j).linea);
                    error.columna = String.valueOf(controles.get(j).columna);
                    lista.add(error);
                }
            }
            for (int j = 0; j < Multi.size(); j++) {
                if (Textos.get(i).Nombre.equalsIgnoreCase(Multi.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Multi.get(j).Nombre + " en la etiquetas Texto y Multimedia";
                    error.linea = String.valueOf(Multi.get(j).linea);
                    error.columna = String.valueOf(Multi.get(j).columna);
                    lista.add(error);
                }
            }
            for (int j = 0; j < Botones.size(); j++) {
                if (Textos.get(i).Nombre.equalsIgnoreCase(Botones.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Botones.get(j).Nombre + " en la etiquetas Texto y Enviar/Botton";
                    error.linea = String.valueOf(Botones.get(j).linea);
                    error.columna = String.valueOf(Botones.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Nombres_controles(ArrayList<NodoError> lista) {
        for (int i = 0; i < controles.size(); i++) {
            for (int j = i + 1; j < controles.size(); j++) {
                if (controles.get(i).Nombre.equalsIgnoreCase(controles.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + controles.get(j).Nombre + " en la etiquetas de control";
                    error.linea = String.valueOf(controles.get(j).linea);
                    error.columna = String.valueOf(controles.get(j).columna);
                    lista.add(error);
                }
            }
            for (int j = 0; j < Multi.size(); j++) {
                if (controles.get(i).Nombre.equalsIgnoreCase(Multi.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Multi.get(j).Nombre + " en la etiquetas Control y multimedia";
                    error.linea = String.valueOf(Multi.get(j).linea);
                    error.columna = String.valueOf(Multi.get(j).columna);
                    lista.add(error);
                }
            }
            for (int j = 0; j < Botones.size(); j++) {
                if (controles.get(i).Nombre.equalsIgnoreCase(Botones.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Botones.get(j).Nombre + " en la etiquetas Control y Enviar/Botton";
                    error.linea = String.valueOf(Botones.get(j).linea);
                    error.columna = String.valueOf(Botones.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Nombres_multimedia(ArrayList<NodoError> lista) {
        for (int i = 0; i < Multi.size(); i++) {
            for (int j = i + 1; j < Multi.size(); j++) {
                if (Multi.get(i).Nombre.equalsIgnoreCase(Multi.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Multi.get(j).Nombre + " en la etiquetas de multimedia";
                    error.linea = String.valueOf(Multi.get(j).linea);
                    error.columna = String.valueOf(Multi.get(j).columna);
                    lista.add(error);
                }
            }
            for (int j = 0; j < Botones.size(); j++) {
                if (Multi.get(i).Nombre.equalsIgnoreCase(Botones.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Botones.get(j).Nombre + " en la etiquetas Texto y Enviar/Botton";
                    error.linea = String.valueOf(Botones.get(j).linea);
                    error.columna = String.valueOf(Botones.get(j).columna);
                    lista.add(error);
                }
            }
        }

    }

    private void Analizar_Nombres_Botones(ArrayList<NodoError> lista) {
        for (int i = 0; i < Botones.size(); i++) {
            for (int j = i + 1; j < Botones.size(); j++) {
                if (Botones.get(i).Nombre.equalsIgnoreCase(Botones.get(j).Nombre)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el nombre: " + Botones.get(j).Nombre + " en la etiquetas de bottones o enviar";
                    error.linea = String.valueOf(Botones.get(j).linea);
                    error.columna = String.valueOf(Botones.get(j).columna);
                    lista.add(error);
                }
            }
        }

    }

    public void Analisis_Final(ArrayList<NodoError> lista) {
        Analizar_Nombres_Texto(lista);
        Analizar_Nombres_controles(lista);
        Analizar_Nombres_multimedia(lista);
        Analizar_Nombres_Botones(lista);

    }

    public NodoObjeto dev_contenedor() {
        ArrayList<Raiz> lista = new ArrayList();
        Raiz uno = new Raiz("color", color, "variable");
        lista.add(uno);
        uno = new Raiz("alto", alto, "variable");
        lista.add(uno);
        uno = new Raiz("ancho", ancho, "variable");
        lista.add(uno);
        uno = new Raiz("y", y, "variable");
        lista.add(uno);
        uno = new Raiz("x", x, "variable");
        lista.add(uno);
        uno = new Raiz("borde", Borde, "variable");
        lista.add(uno);
        uno = new Raiz("Id", Id, "variable");
        lista.add(uno);
        NodoObjeto nuevo = new NodoObjeto(lista);
        return nuevo;
    }

    public ArrayList<NodoObjeto> Dev_botones() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Botones.size(); i++) {
            lista.add(Botones.get(i).dev_boton());
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_textos() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Textos.size(); i++) {
            lista.add(Textos.get(i).dev_texto());
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_multi() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < Multi.size(); i++) {
            lista.add(Multi.get(i).dev_multi());
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_controloes() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < controles.size(); i++) {
            lista.add(controles.get(i).dev_Control());
        }
        return lista;
    }

    public NodoObjeto Dev_por_objeto(String nombre) {
        for (int i = 0; i < Botones.size(); i++) {
            if (Botones.get(i).Nombre.equalsIgnoreCase(nombre)) {
                return Botones.get(i).dev_boton();
            }
        }
        for (int i = 0; i < Textos.size(); i++) {
            if (Textos.get(i).Nombre.equalsIgnoreCase(nombre)) {
                return Textos.get(i).dev_texto();
            }
        }
        for (int i = 0; i < controles.size(); i++) {
            if (controles.get(i).Nombre.equalsIgnoreCase(nombre)) {
                return controles.get(i).dev_Control();
            }
        }
        for (int i = 0; i < Multi.size(); i++) {
            if (Multi.get(i).Nombre.equalsIgnoreCase(nombre)) {
                return Multi.get(i).dev_multi();
            }
        }
        return null;
    }
}
