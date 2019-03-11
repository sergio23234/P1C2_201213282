/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

/**
 *
 * @author sergi
 */
public class Raiz {

    public String nombre;
    public Object valor;
    public String tipo;

    public Raiz() {
        nombre = "";
        valor = "";
        tipo ="variable";
    }

    public Raiz(String nombre, Object valor,String tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
    }

}
