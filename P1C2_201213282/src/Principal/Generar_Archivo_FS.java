/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import ComponentGxml.*;
/**
 *
 * @author Sergio Fernando
 */
public class Generar_Archivo_FS {
    private NodoGxml raiz;
    private Ventana Principal;
    private int no_principal;
    
    public Generar_Archivo_FS(NodoGxml raiz){
        this.raiz = raiz;
    }
    
    public void Generar_archivo(){
        for(int i=0;i<raiz.Ventanas.size();i++){
            if(raiz.Ventanas.get(i).tipo.equalsIgnoreCase("principal")){
                no_principal=i;
                Principal=raiz.Ventanas.get(i);
                break;
            }
        }
        
    }
}
