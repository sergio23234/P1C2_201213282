/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Analizadores.LexicoFS;
import Analizadores.SintacticoFs;
import Principal.Menu;
import Principal.NodoError;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class Importados {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Importados(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores,ArrayList<String> lista) {
        if (raiz.Tipo.equalsIgnoreCase("inicio")) {
            for (int i = 0; i < raiz.hijos.size(); i++) {
               return Analizar(raiz.hijos.get(i), errores,lista);
            }
        } else if (raiz.Tipo.equalsIgnoreCase("EST_IMP")) {
            for (int i = 0; i < raiz.hijos.size(); i++) {
               NodoRespuesta nuevo= Analizar_imp(lista,raiz.hijos.get(i), errores);
               if(nuevo.error){
                   return nuevo;
               }
            }
        }
        return new NodoRespuesta(false);
    }
    private NodoRespuesta Analizar_imp(ArrayList<String> lista, NodoFs raiz, ArrayList<NodoError> errores) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta condicion = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        if (condicion.error) {
            return new NodoRespuesta(true);
        } else {
            try {
                String path = Menu.Lista.get(num).ABpath + "//" + condicion.resultado.toString().replace("\"","").trim();
                boolean existe = false;
                for (int i = 0; i < lista.size(); i++) {
                    if (path.equalsIgnoreCase(lista.get(i))) {
                        existe = true;
                    }
                }
                if (!existe) {
                    File archivo = new File(path);
                    FileReader fr;
                    fr = new FileReader(archivo);
                    LexicoFS lex = new LexicoFS(fr);
                    SintacticoFs miParser = new SintacticoFs(lex);
                    miParser.parse();
                    ArrayList<NodoError> sintacticos = miParser.errores;
                    ArrayList<NodoError> lexicos = lex.Elista;
                    NodoFs nuevo = miParser.regresar_raiz();
                    Pasada1 pasada = new Pasada1(nuevo);
                    tabla = pasada.analizar(errores);
                    Importados imp = new Importados(tabla,global,num);
                    global.add_importado(tabla);
                    lista.add(path);
                    imp.Analizar(nuevo, errores, lista);                    
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Importados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Importados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new NodoRespuesta(false);
    }

}
