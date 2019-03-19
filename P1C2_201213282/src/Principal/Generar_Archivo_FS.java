/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentGxml.Ventana;
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

    //private NodoGxml raiz;
    private ArrayList<String> Datos;
    private ArrayList<NodoGxml> Raices;
    private ArrayList<String> Metodos;
    private String raiz_principal;
    
    public Generar_Archivo_FS(ArrayList<NodoGxml> raiz) {
        //this.raiz = raiz;
        Datos = new ArrayList();
        Raices = new ArrayList();
        Metodos = new ArrayList();
        Raices = raiz;
        raiz_principal="";

    }

    private void inicio(NodoGxml raiz) {
        for (int i = 0; i < raiz.Importaciones.size(); i++) {
            if (raiz.Importaciones.get(i).Dato.toLowerCase().endsWith(".gxml")) {

            } else {
                Crear_Import(raiz.Importaciones.get(i));
            }
        }

    }

    public void Generar_archivo(String nombre, String ruta) {
        for (int i = 0; i < Raices.size(); i++) {
            NodoGxml raiz = Raices.get(i);
            inicio(raiz);
        }
        for (int i = 0; i < Raices.size(); i++) {
            NodoGxml raiz = Raices.get(i);
            Recorrer_ventanas(raiz);
        }
        nombre = nombre.replace(".gxml", "").replace("GXML", "").replace("\\", "");
        Crear_archivo("\\Traduccion_" + nombre, ruta);
    }

    private void Recorrer_ventanas(NodoGxml raiz) {
        int num = -1;
        for (int i = 0; i < raiz.Ventanas.size(); i++) {
            if (raiz.Ventanas.get(i).tipo.equalsIgnoreCase("principal")) {
                num = i;
                Crear_Ventana(raiz.Ventanas.get(num),true);
                break;
            }
        }
        System.out.println("trajo este numero"+num);
        for (int i = 0; i < raiz.Ventanas.size(); i++) {
            if (i != num) {
                Crear_Ventana(raiz.Ventanas.get(i),false);
            }
        }
    }

    private void Crear_Import(EDato raiz) {
        String nuevo = "Importar(\"";
        nuevo += raiz.Dato + "\");\n";
        Datos.add(nuevo);
    }

    private void Crear_Ventana(Ventana raiz,boolean principal) {
        String nuevo = "var ven_";
        nuevo += raiz.Id + "= CrearVentana(\"" + raiz.color + "\"," + raiz.alto + "," + raiz.ancho +",\"" + raiz.Id + "\");\n";
        Datos.add(nuevo);
        for (int i = 0; i < raiz.contenedores.size(); i++) {
            Crear_Contenedor(raiz.contenedores.get(i), raiz.Id);
        }
        if (!raiz.accionI.equalsIgnoreCase("")) {
            String linea = "ven_" + raiz.Id + ".AlCargar(" + raiz.accionI.replace("{", "").replace("}", "").trim() + ");\n";
            Datos.add(linea);
        }
        if (!raiz.accionF.equalsIgnoreCase("")) {
            String linea = "ven_" + raiz.Id + ".AlCerrar(" + raiz.accionF.replace("{", "").replace("}", "").trim() + ");\n";
            Datos.add(linea);
        }
        if(principal){
            System.out.println(raiz.Id+"trajo este id");
            raiz_principal ="ven_"+raiz.Id+".AlCargar();";
        }
    }

    private void Crear_Contenedor(Contenedor raiz, String name_raiz) {
        String nuevo = "var ";
        nuevo += raiz.Id + "_" + name_raiz + "= " + "ven_" + name_raiz + ".CrearContenedor(" + raiz.alto + "," + raiz.ancho + ",\"" + raiz.color + "\"," + raiz.Borde + "," + raiz.x + "," + raiz.y + ");\n";
        Datos.add(nuevo);
        for (int i = 0; i < raiz.Textos.size(); i++) {
            Crear_Texto(raiz.Textos.get(i), raiz.Id, name_raiz);
        }
        for (int i = 0; i < raiz.controles.size(); i++) {
            Crear_Control(raiz.controles.get(i), raiz.Id, name_raiz);
        }
        for (int i = 0; i < raiz.Multi.size(); i++) {
            Crear_Multi(raiz.Multi.get(i), raiz.Id, name_raiz);
        }
        for (int i = 0; i < raiz.Botones.size(); i++) {
            Crear_Boton(raiz.Botones.get(i), raiz.Id, name_raiz);
        }
    }

    private void Crear_Control(Control raiz, String name_raiz, String raiz_padre) {
        switch (raiz.Tipo.toLowerCase()) {
            case "texto":
                Caja_Texto(raiz, name_raiz, raiz_padre);
                break;
            case "textoarea":
                Caja_AreaTexto(raiz, name_raiz, raiz_padre);
                break;
            case "numerico":
                Control_Numerico(raiz, name_raiz, raiz_padre);
                break;
            case "desplegable":
                Control_Desplegable(raiz, name_raiz, raiz_padre);
                break;
        }
    }

    private void Crear_Texto(Texto raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        nuevo += name_raiz + "_" + raiz_padre + ".CrearTexto(\"" + raiz.Fuente + "\"," + raiz.tam + ",\"" + raiz.color + "\"," + raiz.x + "," + raiz.y + "," + raiz.negrita + "," + raiz.cursiva + ", \"" + raiz.Valor + "\");\n";
        Datos.add(nuevo);
    }

    private void Caja_Texto(Control raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        String defecto = "";
        if (raiz.Defecto != null) {
            defecto = raiz.Defecto.Dato.replace("\r\n", "\\n").replace("\t", "");
        }
        nuevo += name_raiz + "_" + raiz_padre + ".CrearCajaTexto(" + raiz.alto + "," + raiz.ancho + ",\"" + raiz.Fuente + "\"," + raiz.tam + ",\"" + raiz.color + "\"," + raiz.x + "," + raiz.y + "," + raiz.negrita + "," + raiz.cursiva + ",\"" + defecto + "\",\"" + raiz.Nombre + "\");\n";
        Datos.add(nuevo);
    }

    private void Caja_AreaTexto(Control raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        String defecto = "";
        if (raiz.Defecto != null) {
            defecto = raiz.Defecto.Dato.replace("\r\n", "\\n").replace("\t", "");
        }
        nuevo += name_raiz + "_" + raiz_padre + ".CrearAreaTexto(" + raiz.alto + "," + raiz.ancho + ",\"" + raiz.Fuente + "\"," + raiz.tam + ",\"" + raiz.color + "\"," + raiz.x + "," + raiz.y + "," + raiz.negrita + "," + raiz.cursiva + ",\"" + defecto + "\",\"" + raiz.Nombre + "\");\n";
        Datos.add(nuevo);
    }

    private void Control_Numerico(Control raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        String maximo = "nulo";
        String minimo = "nulo";
        if (raiz.maximo >= 0) {
            maximo = String.valueOf(raiz.maximo);
        }
        if (raiz.minimo >= 0) {
            maximo = String.valueOf(raiz.minimo);
        }
        String defecto="";
        if(raiz.Defecto!=null){
            defecto = raiz.Defecto.Dato;
        }else{
            defecto ="nulo";
        }
        nuevo += name_raiz + "_" + raiz_padre + ".CrearControlNumerico(" + raiz.alto + "," + raiz.ancho + "," + maximo + "," + minimo + "," + raiz.x + "," + raiz.y +","+defecto +",\"" + raiz.Nombre + "\");\n";
        Datos.add(nuevo);
    }

    private void Control_Desplegable(Control raiz, String name_raiz, String raiz_padre) {
        /*NO IMPLEMENTADO AUN*/
        String nuevo = "";
        String lista = "lista_" + raiz.Nombre + "_" + name_raiz + "_" + raiz_padre;
        String variable = "var " + lista + " = " + lista_Datos(raiz.Datos.Datos);
        String defecto = "nulo";
        if (raiz.Defecto != null) {
            defecto = "\"" + raiz.Defecto.Dato + "\"";
        }
        nuevo += name_raiz + "_" + raiz_padre + ".CrearDesplegable(" + raiz.alto + "," + raiz.ancho + "," + lista + "," + raiz.x + "," + raiz.y + "," + defecto + ",\"" + raiz.Nombre + "\");\n";
        Datos.add(variable);
        Datos.add(nuevo);
    }

    private String lista_Datos(ArrayList<EDato> Datos) {
        String dato = "[";
        for (int i = 0; i < Datos.size(); i++) {
            if (i == 0) {
                dato += "\"" + Datos.get(i).Dato + "\"";
            } else {
                dato += ",\"" + Datos.get(i).Dato + "\"";
            }
        }
        dato += "];\n";
        return dato;
    }

    private void Crear_Boton(Boton raiz, String name_raiz, String super_Raiz) {
        String nuevo = "var ";
        if (raiz.referencia.equalsIgnoreCase("nulo")) {
            nuevo += raiz.Nombre + "_" + super_Raiz + "= " + name_raiz + "_" + super_Raiz + ".CrearBoton(\"" + raiz.Etexto.Fuente + "\"," + raiz.Etexto.tam + ",\"" + raiz.Etexto.color + "\"," + raiz.x + "," + raiz.y + ",nulo,\"" + raiz.Etexto.Valor.trim() + "\"," + raiz.alto + "," + raiz.ancho + ");\n";

        } else {
            //String metodo = "CargarVentana_" + super_Raiz + "()";
            nuevo += raiz.Nombre + "_" + super_Raiz + "= " + name_raiz + "_" + super_Raiz + ".CrearBoton(\"" + raiz.Etexto.Fuente + "\"," + raiz.Etexto.tam + ",\"" + raiz.Etexto.color + "\"," + raiz.x + "," + raiz.y + "," + "\"ven_" + raiz.referencia + "\",\"" + raiz.Etexto.Valor.trim() + "\"," + raiz.alto + "," + raiz.ancho + ");\n";
        }
        Datos.add(nuevo);
        if (!raiz.accion.equalsIgnoreCase("") || raiz.tipo.equalsIgnoreCase("enviar")) {
            if (raiz.tipo.equalsIgnoreCase("enviar")) {
                String metodo = "Guardar_" + super_Raiz + "()";
                String dato = raiz.Nombre + "_" + super_Raiz + ".AlClic(" + metodo + ");\n";
                Datos.add(dato);
                Crear_metodo(metodo, super_Raiz, 1);
            }
            if (!raiz.accion.equalsIgnoreCase("")) {
                String dato = raiz.Nombre + "_" + super_Raiz + ".AlClic(" + raiz.accion.replace("{", "").replace("}", "").trim() + ");\n";
                Datos.add(dato);

            }
        }
    }

    private void Crear_Multi(Multimedia raiz, String name_raiz, String raiz_padre) {
        switch (raiz.Tipo.toLowerCase()) {
            case "musica":
                Crear_Reproductor(raiz, name_raiz, raiz_padre);
                break;
            case "imagen":
                Crear_Imagen(raiz, name_raiz, raiz_padre);
                break;
            case "video":
                Crear_Video(raiz, name_raiz, raiz_padre);
                break;
        }
    }

    private void Crear_Imagen(Multimedia raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        nuevo += name_raiz + "_" + raiz_padre + ".CrearImagen(\"" + raiz.Path + "\"," + raiz.x + "," + raiz.y + "," + raiz.alto + "," + raiz.ancho + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Reproductor(Multimedia raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        nuevo += name_raiz + "_" + raiz_padre + ".CrearReproductor(\"" + raiz.Path + "\"," + raiz.x + "," + raiz.y + "," + raiz.Auto + "," + raiz.alto + "," + raiz.ancho + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_Video(Multimedia raiz, String name_raiz, String raiz_padre) {
        String nuevo = "";
        nuevo += name_raiz + "_" + raiz_padre + ".CrearVideo(\"" + raiz.Path + "\"," + raiz.x + "," + raiz.y + "," + raiz.Auto + "," + raiz.alto + "," + raiz.ancho + ");\n";
        Datos.add(nuevo);
    }

    private void Crear_archivo(String Nombre, String ruta) {
        try {
            System.out.println(ruta + Nombre + ".fs RESULTADO");
            File archivo = new File(ruta + Nombre + ".fs");
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                for (int i = 0; i < Datos.size(); i++) {
                    bw.write(Datos.get(i));
                    bw.write("\n");
                }
                for (int i = 0; i < Metodos.size(); i++) {
                    bw.write(Metodos.get(i));
                    bw.write("\n");
                }
                bw.write(raiz_principal);
                bw.write(("\n"));
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                for (int i = 0; i < Datos.size(); i++) {
                    bw.write(Datos.get(i));
                    bw.write("\n");
                }
                for (int i = 0; i < Metodos.size(); i++) {
                    bw.write(Metodos.get(i));
                    bw.write("\n");
                }
                bw.write(raiz_principal);
                bw.write(("\n"));
            }
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }

    private void Crear_metodo(String Nombre, String raiz, int tipo) {
        String metodo;
        if (tipo == 0) {
            metodo = "funcion " + Nombre + "{\nVen_" + raiz + ".AlCargar();\n}\n";
        } else {
            metodo = "funcion " + Nombre + "{\nVen_" + raiz + ".crearArrayDesdeArchivo();\n}\n";
        }
        Metodos.add(metodo);

    }

}
