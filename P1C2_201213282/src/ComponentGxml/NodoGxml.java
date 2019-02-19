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
public class NodoGxml {
    public ArrayList<Ventana> Ventanas;
    public Ventana actual;
    public NodoGxml(){
       Ventanas = new ArrayList();
    }
    
    public void Recorrer_Ventanas(){
        for(int i=0;i<Ventanas.size();i++){
            Ventana actual = Ventanas.get(i);
            System.out.println(actual.Id+"-->"+actual.tipo+"-->"+actual.color+"-->"+actual.accionI+"-->"+actual.accionF+"-->"+actual.contenedores.size());
        }
    }
}
