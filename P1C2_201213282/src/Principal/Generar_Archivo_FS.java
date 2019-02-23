/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentGxml.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergio Fernando
 */
public class Generar_Archivo_FS {

    private NodoGxml raiz;
    private ArrayList<String> Datos;

    public Generar_Archivo_FS(NodoGxml raiz) {
        this.raiz = raiz;
        Datos = new ArrayList();
    }

    public void Generar_archivo(String nombre, String ruta) {
        for (int i = 0; i < raiz.Importaciones.size(); i++) {
            Crear_Import(raiz.Importaciones.get(i));
        }
        int num = 0;
        for (int i = 0; i < raiz.Ventanas.size(); i++) {
            if (raiz.Ventanas.get(i).tipo.equalsIgnoreCase("principal")) {
                num = i;
                break;
            }
        }
        Crear_Ventana(raiz.Ventanas.get(num));
        for (int i = 0; i < raiz.Ventanas.size(); i++) {
            if (i != num) {
                Crear_Ventana(raiz.Ventanas.get(i));
            }
        }
        nombre = nombre.replace(".gxml", "").replace("GXML", "");
        Crear_archivo(nombre, ruta);
    }

    private void Crear_Import(EDato raiz) {
        String nuevo = "Importar(";
        nuevo += raiz.Dato + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Ventana(Ventana raiz) {
        String nuevo = "var ";
        nuevo += raiz.Id + "= CrearVentana(" + raiz.color + ");\n";
        Datos.add(nuevo);
        for (int i = 0; i < raiz.contenedores.size(); i++) {
            Crear_Contenedor(raiz.contenedores.get(i), raiz.Id);
        }
    }

    private void Crear_Contenedor(Contenedor raiz, String name_raiz) {
        String nuevo = "var ";
        nuevo += raiz.Id + "= " + name_raiz + ".CrearContenedor(" + raiz.alto + "," + raiz.ancho + "," + raiz.color + "," + raiz.Borde + "," + raiz.x + "," + raiz.y + ");\n";
        Datos.add(nuevo);
        for (int i = 0; i < raiz.Textos.size(); i++) {
            Crear_Texto(raiz.Textos.get(i), raiz.Id);
        }
        for (int i = 0; i < raiz.controles.size(); i++) {
            Crear_Control(raiz.controles.get(i), raiz.Id);
        }
        for (int i = 0; i < raiz.Multi.size(); i++) {
            Crear_Multi(raiz.Multi.get(i), raiz.Id);
        }
        for (int i = 0; i < raiz.Botones.size(); i++) {
            Crear_Boton(raiz.Botones.get(i), raiz.Id);
        }
    }

    private void Crear_Control(Control raiz, String name_raiz) {
        switch (raiz.Tipo.toLowerCase()) {
            case "texto":
                Caja_Texto(raiz, name_raiz);
                break;
            case "textoarea":
                Caja_AreaTexto(raiz, name_raiz);
                break;
            case "numerico":
                Control_Numerico(raiz, name_raiz);
                break;
            case "desplegable":
                Control_Desplegable(raiz, name_raiz);
                break;
        }
    }

    private void Crear_Texto(Texto raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearTexto(" + raiz.Fuente + "," + raiz.tam + "," + raiz.color + "," + raiz.x + "," + raiz.y + "," + raiz.negrita + "," + raiz.cursiva + ");\n";
        Datos.add(nuevo);
    }

    private void Caja_Texto(Control raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearTexto(" + raiz.alto + "," + raiz.ancho + "," + raiz.Fuente + "," + raiz.tam + "," + raiz.color + "," + raiz.x + "," + raiz.y + "," + raiz.negrita + "," + raiz.cursiva + ");\n";
        Datos.add(nuevo);
    }

    private void Caja_AreaTexto(Control raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearAreaTexto(" + raiz.alto + "," + raiz.ancho + "," + raiz.Fuente + "," + raiz.tam + "," + raiz.color + "," + raiz.x + "," + raiz.y + "," + raiz.negrita + "," + raiz.cursiva + ");\n";
        Datos.add(nuevo);
    }

    private void Control_Numerico(Control raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearControlNumerico(" + raiz.alto + "," + raiz.ancho + "," + raiz.maximo + "," + raiz.minimo + "," + raiz.x + "," + raiz.y + ");\n";
        Datos.add(nuevo);
    }

    private void Control_Desplegable(Control raiz, String name_raiz) {
        /*NO IMPLEMENTADO AUN*/
        String nuevo = "";
        nuevo += name_raiz + ".CrearControlDesplegable(" + raiz.alto + "," + raiz.ancho + "," + raiz.maximo + "," + raiz.minimo + "," + raiz.x + "," + raiz.y + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Boton(Boton raiz, String name_raiz) {
        String nuevo = "var ";
        nuevo += raiz.Nombre + "= " + name_raiz + ".CrearBoton(" + raiz.Etexto.Fuente + "," + raiz.Etexto.tam + "," + raiz.Etexto.color + "," + raiz.x + "," + raiz.y + "," + raiz.accion + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Multi(Multimedia raiz, String name_raiz) {
        switch (raiz.Tipo.toLowerCase()) {
            case "musica":
                Crear_Reproductor(raiz, name_raiz);
                break;
            case "imagen":
                Crear_Imagen(raiz, name_raiz);
                break;
            case "video":
                Crear_Video(raiz, name_raiz);
                break;
        }
    }

    private void Crear_Imagen(Multimedia raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearImagen(" + raiz.Path + "," + raiz.y + "," + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Reproductor(Multimedia raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearReproductor(" + raiz.Path + "," + raiz.y + "," + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Video(Multimedia raiz, String name_raiz) {
        String nuevo = "";
        nuevo += name_raiz + ".CrearVideo(" + raiz.Path + "," + raiz.y + "," + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_archivo(String Nombre, String ruta) {
        try {
            File archivo = new File(ruta + Nombre + ".fs");
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                for (int i = 0; i < Datos.size(); i++) {
                    bw.write(Datos.get(i));
                }
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                for (int i = 0; i < Datos.size(); i++) {
                    bw.write(Datos.get(i));
                }
            }
            bw.close();
        } catch (IOException e) {

        }
    }
}
