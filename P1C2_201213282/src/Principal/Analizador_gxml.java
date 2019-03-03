/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Analizadores.LexicoGxml;
import Analizadores.SintacticoGxml;
import ComponentGxml.NodoGxml;
import ComponentGxml.Ventana;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class Analizador_gxml {

    private String path;
    private String parent;
    private ArrayList<NodoGxml> raices;
    private ArrayList<String> pats;

    public Analizador_gxml(String path, String parent) {
        this.path = path;
        raices = new ArrayList();
        this.parent = parent;
        pats = new ArrayList();
    }

    public void Analizar() {
        try {
            File archivo = new File(path);
            FileReader fr = new FileReader(archivo);
            LexicoGxml lex = new LexicoGxml(fr);
            SintacticoGxml miParser = new SintacticoGxml(lex);
            miParser.parse();
            NodoGxml Raiz = miParser.RCCSS;
            Raiz.errores = com_errores(miParser.errores, lex.Elista);
            System.out.println("errores --->" + Raiz.errores.size() + "--->" + miParser.errores.size());
            raices.add(Raiz);
            pats.add(path);
            Analizar_Imports(Raiz);
            Verificar_Ventanas();
            if (!ver_errores()) {
                System.out.println("no hay errores" + raices.size());
                Generar_Archivo_FS nuevo = new Generar_Archivo_FS(raices);
                String datos[] = path.split("/");
                String nombre = path.replace(parent, "");
                nuevo.Generar_archivo(nombre, parent);

            } else {
                   imp_errores();
            }

        } catch (Exception ex) {
            Logger.getLogger(Pestania.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<NodoGxml> dev_raices() {
        return raices;
    }

    public ArrayList<NodoError> com_errores(ArrayList<NodoError> lista, ArrayList<NodoError> lista1) {
        ArrayList<NodoError> total = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            total.add(lista.get(i));
        }
        for (int i = 0; i < lista1.size(); i++) {
            total.add(lista1.get(i));
        }
        return total;
    }

    public void Analizar_Imports(NodoGxml raiz) {
        for (int i = 0; i < raiz.Importaciones.size(); i++) {
            if (raiz.Importaciones.get(i).Dato.toLowerCase().endsWith(".gxml")) {
                try {
                    String path = parent + raiz.Importaciones.get(i).Dato.trim();
                    if (comprobar_pat(path)) {
                        File archivo = new File(path);
                        FileReader fr = new FileReader(archivo);
                        LexicoGxml lex = new LexicoGxml(fr);
                        SintacticoGxml miParser = new SintacticoGxml(lex);
                        miParser.parse();
                        NodoGxml Raiz = miParser.RCCSS;
                        Raiz.errores = com_errores(miParser.errores, lex.Elista);
                        System.out.println(Raiz.Ventanas.get(0).Id + "Esto devolvio");
                        raices.add(Raiz);
                        pats.add(path);
                        Analizar_Imports(Raiz);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Pestania.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }
        }
    }

    private void Verificar_Ventanas() {
        for (int i = 0; i < raices.size(); i++) {
            for (int j = i + 1; j < raices.size(); j++) {
                analizar_ventana(raices.get(i).Ventanas, raices.get(j).Ventanas);;
            }
        }
    }

    private void analizar_ventana(ArrayList<Ventana> actual, ArrayList<Ventana> comparar) {
        for (int i = 0; i < actual.size(); i++) {
            for (int j = 0; j < comparar.size(); j++) {
                if (actual.get(i).Id.equalsIgnoreCase(comparar.get(j).Id)) {
                    System.out.println("ventana removida" + comparar.get(j).Id);
                    comparar.remove(j);
                }
            }
        }
    }

    private boolean ver_errores() {
        for (int i = 0; i < raices.size(); i++) {
            if (!raices.get(i).errores.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void imp_errores() {
        for (int i = 0; i < raices.size(); i++) {
            for(int j=0;j<raices.get(i).errores.size();j++) {
                NodoError nuevo=raices.get(i).errores.get(j);
                System.out.println(nuevo.tipo+"--"+nuevo.linea+"--"+nuevo.columna+"--"+nuevo.descripcion);
            }
        }
    }

    private boolean comprobar_pat(String direc) {
        for (int i = 0; i < pats.size(); i++) {
            if (pats.get(i).equalsIgnoreCase(direc)) {
                return false;
            }
        }
        return true;
    }

}
