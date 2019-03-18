/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentFs.NodoFs;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author sergi
 */
public class contenedor extends JPanel implements ActionListener {

    public String id;
    int num_pest;
    /*------------------------Donde podemos obtener informacion--------------------------*/
    private ArrayList<Texto_Lab> Fields;
    private ArrayList<AreaTexto> Areas;
    private ArrayList<Desplegable> Combos;
    private ArrayList<Numberfield> Numeros;
    /*------------------------Donde no se obtiene nada--------------------------*/
    private ArrayList<Musica> Reproductor;
    private ArrayList<Label> Labels;
    private ArrayList<Buton> Botones;
    private ArrayList<Imagenes> imagenes;

    public contenedor(String id) {
        super();
        this.setLayout(null);
        this.id = id;
        Botones = new ArrayList();
        Labels = new ArrayList();
        Fields = new ArrayList();
        Areas = new ArrayList();
        Combos = new ArrayList();
        Reproductor = new ArrayList();
        Numeros = new ArrayList();
        imagenes = new ArrayList();
    }

    public void inicializar_contendor(int num, int alto, int ancho, String color, boolean border, int x, int y) {
        this.num_pest = num;
        this.setBounds(x, y, ancho, alto);
        color = color.replace("#", "").trim();
        this.setBackground(new Color(hex(color)));
        if (border) {
            this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        }
        this.repaint();
        this.setLayout(null);
    }

    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    public boolean add_texto(String fuente, int tam, int x, int y, String color, int negrita, int cursiva, String nombre) {
        Label Tex = new Label(fuente, tam, x, y, color, negrita, cursiva, nombre);
        Labels.add(Tex);
        this.add(Tex);
        return true;
    }

    public boolean add_boton(String id, String fuente, int tam, String color, int x, int y, String referencia, String texto, int alto, int ancho) {
        Buton bot = new Buton(id, fuente, tam, color, x, y, referencia, texto, alto, ancho);
        bot.addActionListener(this);
        boolean add = true;
        for (int i = 0; i < Botones.size(); i++) {
            if (Botones.get(i).id.equalsIgnoreCase(id)) {
                add = false;
                return add;
            }
        }
        if (add) {
            System.out.println(bot.id + " se añadio a:" + this.id);
            Botones.add(bot);
            this.add(bot);
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        //   System.out.println("entro aqui en este envento");
        if (!Botones.isEmpty()) {
            for (int i = 0; i < Botones.size(); i++) {
                if (fuente == Botones.get(i)) {
                    if (Botones.get(i).accion != null) {
                        System.out.println("entro a la accion" + Botones.get(i).accion.Tipo);
                        Menu.Lista.get(num_pest).Ejecutar_funcion(Botones.get(i).accion);
                    }
                    if ((!Botones.get(i).referencia.trim().equalsIgnoreCase("nulo") && !Botones.get(i).referencia.trim().equalsIgnoreCase("undefined"))) {
                        if (Botones.get(i).referencia.equalsIgnoreCase("nulo")) {

                        } else if (Botones.get(i).referencia.equalsIgnoreCase("nulo")) {

                        } else {

                            Menu.Lista.get(num_pest).mostrar_ventana(Botones.get(i).referencia);
                        }
                    }
                }
            }
        }
    }

    boolean Buscar_boton(String id) {
        for (int i = 0; i < Botones.size(); i++) {
            if (Botones.get(i).id.equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    boolean set_FS_boton(String id, NodoFs accion) {
        for (int i = 0; i < Botones.size(); i++) {
            if (Botones.get(i).id.equalsIgnoreCase(id)) {
                Botones.get(i).accion = accion;
                return true;
            }
        }
        return false;
    }

    boolean add_Field(int alto, int ancho, String fuente, int tam, String color, int x, int y, int ng, String defecto, String nombre) {
        for (int i = 0; i < Fields.size(); i++) {
            if (Fields.get(i).nombre.equalsIgnoreCase(nombre)) {
                return false;
            }
        }
        Texto_Lab field = new Texto_Lab(alto, ancho, fuente, tam, color, x, y, ng, defecto, nombre);
        Fields.add(field);
        this.add(field);
        return true;
    }

    boolean add_Area(int alto, int ancho, String fuente, int tam, String color, int x, int y, int ng, String defecto, String nombre) {
        for (int i = 0; i < Areas.size(); i++) {
            if (Areas.get(i).nombre.equalsIgnoreCase(nombre)) {
                return false;
            }
        }
        AreaTexto field = new AreaTexto(alto, ancho, fuente, tam, color, x, y, ng, defecto, nombre);
        Areas.add(field);
        this.add(field);
        return true;
    }

    boolean add_Combo(int alto, int ancho, ArrayList<String> lista, int x, int y, String defecto, String nombre) {
        for (int i = 0; i < Combos.size(); i++) {
            if (Combos.get(i).nombre.equalsIgnoreCase(nombre)) {
                System.out.println("repetido");
                return false;
            }
        }
        Desplegable field = new Desplegable(alto, ancho, lista, x, y, defecto, nombre);
        Combos.add(field);
        System.out.println("se añadio");
        this.add(field);
        return true;
    }

    boolean add_MV(String ruta, int x, int y, boolean auto, int alto, int ancho, int tipo) {
        if (tipo == 1) {
            String nuevopat = Menu.Lista.get(num_pest).ABpath + "\\" + ruta;
            System.out.println(nuevopat + "este es el nuevopat");
            Musica music = new Musica(nuevopat, x, y, auto, alto, ancho);
            Reproductor.add(music);
            this.add(music);
            return true;
        }
        return false;
    }

    boolean add_Numero(int alto, int ancho, int maximo, int minimo, int x, int y, int defecto, String nombre) {
        for (int i = 0; i < Numeros.size(); i++) {
            if (Numeros.get(i).nombre.equalsIgnoreCase(nombre)) {
                System.out.println("son iguales");
                return false;
            }
        }
        Numberfield field = new Numberfield(alto, ancho, maximo, minimo, x, y, defecto, nombre);
        Numeros.add(field);
        this.add(field);
        return true;
    }

    boolean add_Image(String ruta, int x, int y, int alto, int ancho) {
        String nuevopat = Menu.Lista.get(num_pest).ABpath + "\\" + ruta;
        System.out.println(nuevopat + "este es el nuevopat");
        Imagenes music = new Imagenes(nuevopat, x, y, alto, ancho);
        imagenes.add(music);
        this.add(music);
        return true;
    }

    public String obtener_datos() {
        String datos = "";
        for(int i=0;i<Fields.size();i++){
            Texto_Lab actual = Fields.get(i);
            String dato = "<"+actual.nombre+">\""+actual.getText()+"\"</"+actual.nombre+">";
            datos += dato;
        }
        for(int i=0;i<Areas.size();i++){
            AreaTexto actual = Areas.get(i);
            String dato = "<"+actual.nombre+">\""+actual.getText().replace("\n","\\n")+"\"</"+actual.nombre+">";
            datos += dato;
        }
        for(int i=0;i<Combos.size();i++){
            Desplegable actual = Combos.get(i);
            String dato = "<"+actual.nombre+">\""+actual.getSelectedItem().toString()+"\"</"+actual.nombre+">";
            datos += dato;
        }
        for(int i=0;i<Numeros.size();i++){
            Numberfield actual = Numeros.get(i);
            String dato = "<"+actual.nombre+">"+actual.getValue()+"</"+actual.nombre+">";
            datos += dato;
        }
        return datos;
    }
}
