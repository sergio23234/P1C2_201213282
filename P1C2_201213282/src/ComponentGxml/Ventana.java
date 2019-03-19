/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import ComponentFs.NodoObjeto;
import ComponentFs.Raiz;
import ComponentGxml.Contenedor;
import ComponentGxml.NodoSGxml;
import java.util.ArrayList;
import Principal.NodoError;
import java.awt.Color;

/**
 *
 * @author sergi
 */
public class Ventana {

    public String Id;
    public String tipo;
    public String color;
    public String accionI;
    public String accionF;
    public ArrayList<Contenedor> contenedores;
    public int linea, columna;
    public int alto, ancho;

    public Ventana() {
        contenedores = new ArrayList();
        Id = "";
        tipo = "";
        color = "#ffffff";
        accionI = "";
        accionF = "";
        alto = ancho = 500;
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
        Analizar_Attributo_Principal(Nodo.listas, lista);
        Analizar_Attributo_Color(Nodo.listas, lista);
        Set_Attributos(Nodo.listas);
    }

    private void Analizar_Attributos_repetidos(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            for (int j = i + 1; j < hijos.size(); j++) {
                if (hijos.get(i).tipo.equalsIgnoreCase(hijos.get(j).tipo)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el attributo: " + hijos.get(i).tipo;
                    error.linea = String.valueOf(hijos.get(j).linea);
                    error.columna = String.valueOf(hijos.get(j).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributos_obligatorios(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        boolean id = false;
        boolean tipo = false;
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("id")) {
                id = true;
            } else if (hijos.get(i).tipo.equalsIgnoreCase("tipo")) {
                tipo = true;
            }
        }
        if (!id || !tipo) {
            NodoError error = new NodoError("semantico");
            error.linea = String.valueOf(hijos.get(hijos.size() - 1).linea);
            error.columna = String.valueOf(hijos.get(hijos.size() - 1).columna);
            String tipoe;
            if (!id) {
                tipoe = "ID";
            } else {
                tipoe = "Tipo";
            }
            error.descripcion = "No se encuentra el atributo: " + tipoe + " que es de caracter obligatorio en esta etiqueta";
            lista.add(error);
        }
    }

    private void Analizar_Attributo_Principal(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("tipo")) {
                String hijo = hijos.get(i).val.replace("\"", "");
                if (!(hijo.equalsIgnoreCase("principal") || hijo.equalsIgnoreCase("secundaria"))) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "El tipo definido no es ni Secundario ni Principal es: " + hijos.get(i).val;
                    error.linea = String.valueOf(hijos.get(i).linea);
                    error.columna = String.valueOf(hijos.get(i).columna);
                    lista.add(error);
                }
            }
        }
    }

    private void Analizar_Attributo_Color(ArrayList<NodoSGxml> hijos, ArrayList<NodoError> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).tipo.equalsIgnoreCase("color")) {
                String hijo = hijos.get(i).val.replace("\"", "");
                try {
                    Color color = Color.decode(hijo);
                } catch (Exception e) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "El color definido:" + hijo + " no es un color valido tiene que extar en hexadecimal";
                    error.linea = String.valueOf(hijos.get(i).linea);
                    error.columna = String.valueOf(hijos.get(i).columna);
                    lista.add(error);
                }
                /*if(){
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "El tipo definido no es ni Secundario ni Principal es: " + hijos.get(i).val;
                    error.linea = String.valueOf(hijos.get(i).linea);
                    error.columna = String.valueOf(hijos.get(i).columna);
                    lista.add(error);
                    }*/
            }
        }
    }

    private void Set_Attributos(ArrayList<NodoSGxml> hijos) {
        for (int i = 0; i < hijos.size(); i++) {
            String hijo = hijos.get(i).tipo;
            String val = hijos.get(i).val;
            switch (hijo.toLowerCase()) {
                case "tipo":
                    this.tipo = val.replace("\"", "");
                    break;
                case "id":
                    this.Id = val.replace("\"", "");
                    linea = hijos.get(i).linea;
                    columna = hijos.get(i).columna;
                    break;
                case "color":
                    this.color = val.replace("\"", "");
                    break;
                case "inicial":
                    this.accionI = val;
                    break;
                case "final":
                    this.accionF = val;
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

    public void Analizar_Nombre_Contenedores(ArrayList<NodoError> lista) {
        for (int i = 0; i < contenedores.size(); i++) {
            for (int j = i + 1; j < contenedores.size(); j++) {
                if (contenedores.get(i).Id.equalsIgnoreCase(contenedores.get(j).Id)) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "Se encuentra repetido el id del contenedor: " + contenedores.get(i).Id;
                    error.linea = String.valueOf(contenedores.get(j).linea);
                    error.columna = String.valueOf(contenedores.get(j).columna);
                    lista.add(error);
                }
            }
            contenedores.get(i).Analisis_Final(lista);
        }
    }

    public NodoObjeto dev_ventana() {
        ArrayList<Raiz> lista = new ArrayList();
        Raiz uno = new Raiz("color", color, "variable");
        lista.add(uno);
        uno = new Raiz("alto", alto, "variable");
        lista.add(uno);
        uno = new Raiz("ancho", ancho, "variable");
        lista.add(uno);
        uno = new Raiz("Id", Id, "variable");
        lista.add(uno);
        NodoObjeto nuevo = new NodoObjeto(lista);
        return nuevo;
    }

    public ArrayList<NodoObjeto> Dev_Contenedores_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < contenedores.size(); i++) {
            lista.add(contenedores.get(i).dev_contenedor());
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_botones_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < contenedores.size(); i++) {
            ArrayList<NodoObjeto> aux = contenedores.get(i).Dev_botones();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(i));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_Multi_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < contenedores.size(); i++) {
            ArrayList<NodoObjeto> aux = contenedores.get(i).Dev_multi();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(i));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_textos_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < contenedores.size(); i++) {
            ArrayList<NodoObjeto> aux = contenedores.get(i).Dev_textos();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(i));
            }
        }
        return lista;
    }

    public ArrayList<NodoObjeto> Dev_controles_Objeto() {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < contenedores.size(); i++) {
            ArrayList<NodoObjeto> aux = contenedores.get(i).Dev_controloes();
            for (int j = 0; j < aux.size(); j++) {
                lista.add(aux.get(i));
            }
        }
        return lista;
    }

    public NodoObjeto Dev_Objeto(String objeto) {
        ArrayList<NodoObjeto> lista = new ArrayList();
        for (int i = 0; i < contenedores.size(); i++) {
           if(contenedores.get(i).Dev_por_objeto(objeto)!=null){
               return contenedores.get(i).Dev_por_objeto(objeto);
           } 
        }
        return null;
    }
}
