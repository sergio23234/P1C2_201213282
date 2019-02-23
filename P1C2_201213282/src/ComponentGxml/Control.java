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
    public ListaDatos Datos;
    public EDato Defecto;

    public Control() {
        Nombre = Tipo =   "";
        Fuente = "Arial";
        alto = ancho = 50;
        tam = 14;
        color = "#000000";
        x = y =  0;
        maximo = minimo = -1;
        negrita = cursiva = "falso";
        accion = "";
        Datos = null;
    }

    public void Analizar_Attributos(NodoSGxml Nodo, ArrayList<NodoError> lista) {
        Analizar_Attributos_repetidos(Nodo.listas, lista);
        Analizar_Attributos_obligatorios(Nodo.listas, lista);
        Analizar_Attributos_Tipo(Nodo.listas, lista);
        //Analizar_Attributo_Numeros(Nodo.listas, lista);
        //Analizar_Attributo_Color(Nodo.listas,lista);
        Set_Attributos(Nodo.listas);
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
                Analizar_Attributos_Tipo_Texto(hijos, lista,1);
                break;
            case "numerico":
                Analizar_Attributos_Tipo_Numerico(hijos, lista);
                break;
            case "textoarea":
                Analizar_Attributos_Tipo_Texto(hijos, lista,0);
                break;
            case "desplegable":
                Analizar_Attributos_Tipo_desplegable(hijos, lista);
                break;

        }
    }

    private void Analizar_Attributos_Tipo_Texto(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista,int tipo) {
        for (int i = 0; i < hijos.size(); i++) {
            NodoSGxml actual = hijos.get(i);
            NodoError error;
            switch (actual.tipo.toLowerCase()) {
                case "maximo":
                    error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo Maximo no esta permitido dentro del cotrol tipo texto/textoarea";
                    lista.add(error);
                    break;
                case "minimo":
                    error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo Maximo no esta permitido dentro del cotrol tipo texto/textoarea";
                    lista.add(error);
                    break;
                case "accion":   
                    if(tipo==1){
                        error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo Accion no esta permitido dentro del cotrol tipo texto";
                    lista.add(error);
                    }
                    break;
            }
        }
    }

    private void Analizar_Attributos_Tipo_Numerico(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            NodoSGxml actual = hijos.get(i);
            NodoError error;
            switch (actual.tipo.toLowerCase()) {
                case "negrita":
                case "cursiva":
                case "accion":
                case "color":
                case "tam":
                case "fuente":
                    error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo " + actual.tipo + " no esta permitido dentro del cotrol tipo numerico";
                    lista.add(error);
                    break;

            }
        }
    }

    private void Analizar_Attributos_Tipo_desplegable(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            NodoSGxml actual = hijos.get(i);
            NodoError error;
            switch (actual.tipo.toLowerCase()) {
                case "negrita":
                case "minimo":
                case "maximo":
                case "cursiva":
                case "accion":
                case "color":
                case "tam":
                case "fuente":
                    error = new NodoError("semantico");
                    error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
                    error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
                    error.descripcion = "El attributo " + actual.tipo + " no esta permitido dentro del cotrol tipo desplegable";
                    lista.add(error);
                    break;

            }
        }
    }

    public void Analizar_Lista_Datos(ArrayList<NodoError> lista) {
        if (!Tipo.equalsIgnoreCase("desplegable")) {
            if (Datos != null) {
                NodoError error;
                error = new NodoError("semantico");
                error.linea = String.valueOf(linea);
                error.columna = String.valueOf(columna);
                error.descripcion = "la etiqueta desplegable no esta permitido dentro del cotrol tipo: " + Tipo;
                lista.add(error);
            }
        }
        if (Datos != null) {
            Datos.verficar_datos_repetidos(lista);
        }
       if(Defecto!=null){
           Analizar_etiqueta_Defecto(lista);
       } 
    }

    private void Analizar_etiqueta_Defecto(ArrayList<NodoError> lista){
        switch(Tipo.toLowerCase()) {
            case "desplegable":
                if(!Datos.buscar_lista_datos(Defecto.Dato)){
                NodoError error;
                error = new NodoError("semantico");
                error.linea = String.valueOf(Defecto.linea);
                error.columna = String.valueOf(Defecto.columna);
                error.descripcion = "la etiqueta Defecto no tiene un valor valido dentro de los datos de la lista";
                lista.add(error);
                }
                break;
            case "numerico":
                if(!Defecto.match_numero()){
                NodoError error;
                error = new NodoError("semantico");
                error.linea = String.valueOf(Defecto.linea);
                error.columna = String.valueOf(Defecto.columna);
                error.descripcion = "la etiqueta Defecto tiene solo numeros dentro del tipo numerico";
                lista.add(error);
                }
                break;
            case "texto":   
                if(Defecto.Dato.contains("\n")){
                NodoError error;
                error = new NodoError("semantico");
                error.linea = String.valueOf(Defecto.linea);
                error.columna = String.valueOf(Defecto.columna);
                error.descripcion = "la etiqueta Defecto tiene un salto de linea, no valido en tipo Texto";
                lista.add(error);
                }
                break;
            case "textoArea": 
                break;
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
                    this.negrita = val.replace("\"", "");
                    break;
                case "accion":
                    this.accion = val.replace("\"", "");
                    break;                    
                case "nombre":
                    this.Nombre= val.replace("\"", "");
                    this.linea = hijos.get(i).linea;
                    this.columna = hijos.get(i).columna;
                    break;
                case "tipo":
                    this.Tipo= val.replace("\"", "");
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
                case "tam":
                    this.tam = Integer.valueOf(val);
                    break; 
                case "maximo":
                    this.maximo = Integer.valueOf(val);
                    break;
                case "minimo":
                    this.minimo = Integer.valueOf(val);
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
