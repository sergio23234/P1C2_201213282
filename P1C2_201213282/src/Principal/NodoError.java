/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author Sergio Fernando
 */
public class NodoError {
    public String tipo;
    public String linea;
    public String columna;
    public String descripcion;
    
    public NodoError(String tipo){
        linea=columna=descripcion="";
        this.tipo=tipo;
    }
}
