/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Analizadores.LexicoGD;
import Analizadores.SintacticoGD;
import Principal.Menu;
import Principal.NodoError;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class Est_Array {
    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Est_Array(TablaSimbolos tabla,TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }
    
    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores){
        String path = Menu.Lista.get(num).ABpath;
        Cuerpo_op OP = new Cuerpo_op(tabla,global,num);
        NodoRespuesta tres = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        path = path+"/"+tres.resultado.toString().replace("\"", "");
        File archivo = new File(path);
        if(archivo.exists()){
           try{
                FileReader fr = new FileReader(archivo);
                LexicoGD lex = new LexicoGD(fr);
                SintacticoGD miParser = new SintacticoGD(lex);
                miParser.parse();
               // System.out.println(miParser.datos.size());
                NodoRespuesta nuevo = new NodoRespuesta(miParser.datos);
                nuevo.tipo ="array";
                return nuevo;
           }catch(Exception e){
               return new NodoRespuesta(true);
           }
        }else{
            System.out.println("El archivo no existe");
        }
        return new NodoRespuesta(false);
    }

}
