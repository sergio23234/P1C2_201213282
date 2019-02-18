/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import java.util.ArrayList;

/**
 *
 * @author Sergio Fernando
 */
public class NodoSGxml {

    public String tipo;
    public String val;
    public int value;
    public ArrayList<NodoSGxml> listas;
    public int linea,columna;
    public NodoSGxml() {
        tipo ="";
        val="";
        value=0;
    }
    public NodoSGxml(String nombre){
        listas = new ArrayList();
        val="";
        value=0;
        tipo = "raiz";
    }
}
