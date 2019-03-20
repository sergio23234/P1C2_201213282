/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Analizadores.LexicoGxml;
import Analizadores.SintacticoGxml;
import ComponentGxml.NodoGxml;
import ComponentGxml.Ventana;
import Principal.Menu;
import Principal.NodoError;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class Est_gxml {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;
    ArrayList<String> paths;
    String abpath;

    public Est_gxml(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
        paths = new ArrayList();
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        String path = Menu.Lista.get(num).ABpath;
        abpath = path;
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta tres = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        path = path + "/" + tres.resultado.toString().replace("\"", "");
        if (path.toLowerCase().endsWith(".gxml")) {
            paths.add(path);
            File archivo = new File(path);
            if (archivo.exists()) {
                try {
                    FileReader fr = new FileReader(archivo);
                    LexicoGxml lex = new LexicoGxml(fr);
                    SintacticoGxml miParser = new SintacticoGxml(lex);
                    miParser.parse();
                    NodoGxml resultado = miParser.RCCSS;
                    ArrayList<Ventana> analizado = Analizar_Import(resultado);
                    if (analizado != null) {
                        for (int j = 0; j < analizado.size(); j++) {
                            resultado.Ventanas.add(analizado.get(j));
                        }
                    }
                    NodoRespuesta nuevo = new NodoRespuesta(miParser.RCCSS);
                    nuevo.tipo = "arrayespecial";
                    return nuevo;
                } catch (Exception e) {
                    return new NodoRespuesta(true);
                }
            } else {
                System.out.println("El archivo no existe");
                NodoError error = new NodoError("semantico");
                error.descripcion = "error la direccion de archivo no existe: " + path;
                error.linea = String.valueOf(raiz.linea);
                error.columna = String.valueOf(raiz.columna);
                errores.add(error);
                return new NodoRespuesta(true);
            }
        }else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "error no tiene extencion gxml: " + path;
            error.linea = String.valueOf(raiz.linea);
            error.columna = String.valueOf(raiz.columna);
            errores.add(error);
            return new NodoRespuesta(true);
        }

    }

    public ArrayList<Ventana> Analizar_Import(NodoGxml resultado) {
        for (int i = 0; i < resultado.Importaciones.size(); i++) {
            String subpath = resultado.Importaciones.get(i).Dato.replace("/", "");
            if (subpath.endsWith(".gxml")) {
                String path = abpath + "\\" + subpath;
                System.out.println(path);
                File archivo = new File(path);
                if (archivo.exists()) {
                    try {
                        FileReader fr = new FileReader(archivo);
                        LexicoGxml lex = new LexicoGxml(fr);
                        SintacticoGxml miParser = new SintacticoGxml(lex);
                        miParser.parse();
                        NodoGxml resultado1 = miParser.RCCSS;
                        ArrayList<Ventana> analizado = Analizar_Import(resultado1);
                        if (analizado != null) {
                            for (int j = 0; j < analizado.size(); j++) {
                                resultado1.Ventanas.add(analizado.get(j));
                            }
                        }
                        return resultado1.Ventanas;
                    } catch (Exception e) {

                    }
                }
            }
        }
        return null;
    }
}
