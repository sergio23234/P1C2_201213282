/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentGxml;

import java.util.ArrayList;

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
    ArrayList<Contenedor> contenedores;
    public Ventana(){
        contenedores = new ArrayList();
        Id ="";
        tipo="";
        color="";
        accionI="";
        accionF="";
    }
    public boolean set_Id(String Id){
        if(this.Id.equalsIgnoreCase("")){
           this.Id = Id;
           return true;
        }
        return false;
    }
}
