/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import ComponentGxml.NodoGxml;
import Analizadores.*;
import ComponentFs.*;
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
public class Pestania extends javax.swing.JPanel {

    public String path;
    public String ABpath;
    private int num;
    public ArrayList<est_ventana> ventanas;
    public TablaSimbolos tabla;
    public ArrayList<NodoError> errores;
    private int abierta_actual = -1;

    /**
     * Creates new form Pestania
     *
     * @param num
     */
    public Pestania(int num) {
        initComponents();
        path = "";
        ABpath = "";
        this.num = num;
        ventanas = new ArrayList();
        errores = new ArrayList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Editor = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Consola = new javax.swing.JTextArea();
        analizar = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(204, 204, 204));

        Editor.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(Editor);

        Consola.setBackground(new java.awt.Color(102, 102, 102));
        Consola.setColumns(20);
        Consola.setForeground(new java.awt.Color(153, 255, 153));
        Consola.setRows(5);
        jScrollPane2.setViewportView(Consola);

        analizar.setBackground(new java.awt.Color(204, 255, 204));
        analizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        analizar.setForeground(new java.awt.Color(204, 0, 0));
        analizar.setText("Analizar");
        analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(analizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(analizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarActionPerformed
        if (!path.equals("") && (path.toLowerCase().endsWith(".gxml"))) {
            errores.clear();
            Analizador_gxml analizador = new Analizador_gxml(path, ABpath);
            analizador.Analizar();
            errores = analizador.dev_errores();
            if(errores.size()>0){
                Consola.append("Hay errores en el archivo no se logro traducir");
            }else{
                Consola.append("se logro traducir el archivo");
            }
        } else if (!path.equals("") && (path.toLowerCase().endsWith(".fs"))) {
            File archivo = new File(path);
            FileReader fr;
            try {
                tabla = new TablaSimbolos();
                if (abierta_actual != -1) {
                    ventanas.get(abierta_actual).hide();
                }
                ventanas.clear();
                fr = new FileReader(archivo);
                LexicoFS lex = new LexicoFS(fr);
                SintacticoFs miParser = new SintacticoFs(lex);
                miParser.parse();
                errores.clear();
                ArrayList<NodoError> sintacticos = miParser.errores;
                ArrayList<NodoError> lexicos = lex.Elista;
                fusionar_Errores(lexicos, sintacticos);
                NodoFs nuevo = miParser.regresar_raiz();
                Consola.setText("");
                if (!(errores.size() > 0)) {
                    Pasada1 pasada = new Pasada1(nuevo);
                    tabla = pasada.analizar(errores);
                    tabla.inicia_importados();
                    Importados imp = new Importados(tabla, tabla, num);
                    ArrayList<String> lista = new ArrayList();
                    lista.add(path);
                    imp.Analizar(nuevo, errores, lista);
                    Inicio ini = new Inicio(tabla, tabla, num);
                    ini.Analizar(nuevo, errores);
                    //recorrer_Tabla(tabla);
                    //System.out.println(miParser.errores.size() + " <----cantidad de errores");
                    //imprimir_ventanas();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Pestania.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Pestania.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            /*ventana ven = new ventana(300,300);
            ven.show();
            ven.mostrar_reproductor("C:\\Users\\SergioFernando\\Desktop\\Pruebas\\Lone Digger.mp3", true,100,200);
             */
        }
    }//GEN-LAST:event_analizarActionPerformed

    private void recorrer_FS(NodoFs raiz, String tabs) {
        String linea = tabs + raiz.Tipo + "->" + raiz.valor + "\r\n";
        Consola.append(linea);
        Consola.append(tabs + "---------------------------------------------------------\r\n");

        for (int i = 0; i < raiz.hijos.size(); i++) {
            Consola.append(tabs + raiz.hijos.size() + "\r\n");
            recorrer_FS(raiz.hijos.get(i), tabs + "\t");
        }
        if (!raiz.lista.isEmpty()) {
            Consola.append(tabs + "----------------Lista-----\r\n");
            for (int i = 0; i < raiz.lista.size(); i++) {

                Consola.append(tabs + "\t" + raiz.lista.get(i) + "\r\n");
            }
        }
        Consola.append(tabs + "---------------------------------------------------------\r\n");
    }

    private void recorrer_Tabla(TablaSimbolos tabla) {
        Consola.append(tabla.ambito + ":\r\n");
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.tipo.equalsIgnoreCase("vector")) {
                String valor = "";
                ArrayList<String> valores = (ArrayList<String>) actual.valor;
                for (int j = 0; j < valores.size(); j++) {
                    valor += "---" + valores.get(j);
                }
                Consola.append(actual.nombre + "  Valor: " + valor + " tipo: " + actual.tipo + "\r\n");
            } else if (actual.tipo.equalsIgnoreCase("objeto")) {
                String valor = "";
                NodoObjeto valores = (NodoObjeto) actual.valor;
                for (int j = 0; j < valores.objetos.size(); j++) {
                    valor += "---" + valores.objetos.get(j).nombre + " : " + valores.objetos.get(j).valor;
                }
                Consola.append(actual.nombre + "  Valor: " + valor + " tipo: " + actual.tipo + "\r\n");
            } else if (actual.tipo.equalsIgnoreCase("array")) {
                String valor = "";
                ArrayList<NodoObjeto> valores = (ArrayList<NodoObjeto>) actual.valor;
                for (int j = 0; j < valores.size(); j++) {
                    valor = "";
                    NodoObjeto actualO = valores.get(j);
                    for (int k = 0; k < actualO.objetos.size(); k++) {
                        valor += "---" + actualO.objetos.get(k).nombre + " : " + actualO.objetos.get(k).valor;
                    }
                    Consola.append(actual.nombre + "  Valor: " + valor + " tipo: " + actual.tipo + "\r\n");
                }
            } else {
                Consola.append(actual.nombre + "  Valor: " + actual.valor + " tipo: " + actual.tipo + "\r\n");
            }
        }
    }

    private void Imprimir_errores(ArrayList<NodoError> errores) {
        if (errores.size() > 0) {
            System.out.println("---------- errores-------------");
            for (int i = 0; i < errores.size(); i++) {
                NodoError actual = errores.get(i);
                System.out.println(actual.descripcion + " ----  " + actual.tipo);
            }
            System.out.println("---------- errores-------------");
        }
    }

    public boolean Add_ventana(String color, int largo, int ancho, String id, String guardado) {
        String newpath = ABpath + "/" + guardado + ".gdato";
        est_ventana ven = new est_ventana(id, largo, ancho, color, num, newpath);
        boolean add = true;
        for (int i = 0; i < ventanas.size(); i++) {
            if (ventanas.get(i).id.equalsIgnoreCase(id)) {
                add = false;
                break;
            }
        }
        if (add) {
            ventanas.add(ven);
            return true;
        }
        return false;
    }

    public boolean Add_contenedor_ventana(String id, int alto, int ancho, String color, boolean boder, int x, int y, String id_ventana) {
        for (int i = 0; i < ventanas.size(); i++) {
            if (ventanas.get(i).id.equalsIgnoreCase(id_ventana)) {
                est_ventana ven = ventanas.get(i);
                //System.out.println(i+":entron en esta ventana:"+ven.id+"-->"+id_ventana);
                return ven.add_contenedor(num, id, alto, ancho, color, boder, x, y);
            }
        }
        return false;
    }

    public boolean add_boton(String id_con, String id, String fuente, int tam, String color, int x, int y, String referencia, String texto, int alto, int ancho) {
        for (int i = 0; i < ventanas.size(); i++) {
            if (ventanas.get(i).ID_Contenedor(id_con)) {
                return ventanas.get(i).add_boton(id_con, id, fuente, tam, color, x, y, referencia, texto, alto, ancho);
            }
        }
        return false;
    }

    public boolean add_FS_boton(String id, NodoFs accion) {
        for (int i = 0; i < ventanas.size(); i++) {
            est_ventana ven = ventanas.get(i);
            if (ven.ID_Contenedor_boton(id)) {
                return ven.set_FS_boton(id, accion);
            }
        }
        return false;
    }

    public void mostrar_ventana(String id_ventana) {
        for (int i = 0; i < ventanas.size(); i++) {
            if (ventanas.get(i).id.equalsIgnoreCase(id_ventana)) {

                if (ventanas.get(i).alcargar) {
                    Ejecutar_funcion(ventanas.get(i).cargar);
                }
                if (abierta_actual == -1) {
                    abierta_actual = i;
                } else {
                    est_ventana temp = ventanas.get(abierta_actual);
                    if (temp.alcerrar) {
                        Ejecutar_funcion(temp.cerrar);
                    }
                    temp.finalizarSV();
                    temp.hide();
                }
                ventanas.get(i).show();
                ventanas.get(i).inicializarSV();
                abierta_actual = i;
            }
        }

    }

    public void Ejecutar_funcion(NodoFs raiz) {
        try{
        Inicio ini = new Inicio(tabla, tabla, num);
        NodoFs nuevo = new NodoFs("cuerpo");
        nuevo.add_NodoFs(raiz);
        //System.out.println("llamo a la funcion" + raiz.Tipo);
        ini.Analizar(nuevo, errores);    
        }
        catch(Exception e){
            Consola.append(e.toString()+"\n");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea Consola;
    public javax.swing.JEditorPane Editor;
    private javax.swing.JButton analizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    public void add_cargar_Fs(NodoFs get, String id, int tipo) {
        for (int i = 0; i < ventanas.size(); i++) {
            if (ventanas.get(i).id.equalsIgnoreCase(id)) {
                if (tipo == 0) {
                    ventanas.get(i).cargar = get;
                    ventanas.get(i).alcargar = true;
                } else {
                    ventanas.get(i).cerrar = get;
                    ventanas.get(i).alcerrar = true;
                }

            }
        }
    }

    public boolean add_texto(String contenedor, String fuente, int tam, int x, int y, String color, int negrita, int cursiva, String nombre) {
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(contenedor);
            if (numero != -1) {

                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_texto(fuente, tam, x, y, color, negrita, cursiva, nombre);
            }
        }
        return false;
    }

    public boolean add_Field(String contenedor, int alto, int ancho, String fuente, int tam, String color, int x, int y, int ng, String defecto, String nombre) {
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(contenedor);
            if (numero != -1) {
                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_Field(alto, ancho, fuente, tam, color, x, y, ng, defecto, nombre);
            }
        }
        return false;
    }

    public boolean add_Area(String contenedor, int alto, int ancho, String fuente, int tam, String color, int x, int y, int ng, String defecto, String nombre) {
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(contenedor);
            if (numero != -1) {
                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_Area(alto, ancho, fuente, tam, color, x, y, ng, defecto, nombre);
            }
        }
        return false;
    }

    public boolean add_Combo(String contenedor, int alto, int ancho, ArrayList<String> lista, int x, int y, String defecto, String nombre) {
        System.out.println(contenedor + "es este contenedor");
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(contenedor);

            if (numero != -1) {
                System.out.println("entro aqui");
                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_Combo(alto, ancho, lista, x, y, defecto, nombre);
            }
        }
        return false;
    }

    public boolean add_MV(String id_ventana, String ruta, int x, int y, boolean auto, int alto, int ancho, int tipo) {
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(id_ventana);
            if (numero != -1) {
                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_MV(ruta, x, y, auto, alto, ancho, tipo);
            }
        }
        return false;
    }

    public boolean add_Numero(String id_ventana, int alto, int ancho, int maximo, int minimo, int x, int y, int defecto, String nombre) {
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(id_ventana);
            if (numero != -1) {
                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_Numero(alto, ancho, maximo, minimo, x, y, defecto, nombre);
            }
        }
        return false;
    }

    public boolean add_image(String id_ventana, String ruta, int x, int y, int alto, int ancho) {
        for (int i = 0; i < ventanas.size(); i++) {
            int numero = ventanas.get(i).ID_intContenedor(id_ventana);
            if (numero != -1) {
                contenedor con = ventanas.get(i).contenedores.get(numero);
                return con.add_Image(ruta, x, y, alto, ancho);
            }
        }
        return false;
    }

    public void Impirmir_importados() {
        for (int i = 0; i < tabla.importados.size(); i++) {
            System.out.println("--------" + i + "-----------");
            TablaSimbolos actual = tabla.importados.get(i);
            for (int j = 0; j < actual.Tabla.size(); j++) {
                System.out.println("\t" + actual.Tabla.get(j).nombre);
            }
            System.out.println("-----------------------");
        }
    }

    private void imprimir_ventanas() {
        for (int i = 0; i < ventanas.size(); i++) {
            System.out.println(i + " : " + ventanas.get(i).id);
        }
    }

    public void Add_Dato(String id_ventana) {
        for (int i = 0; i < ventanas.size(); i++) {
            if (ventanas.get(i).id.equalsIgnoreCase(id_ventana)) {
                ventanas.get(i).Guardar_datosContenedor();
            }
        }
    }

    private boolean fusionar_Errores(ArrayList<NodoError> lexicos, ArrayList<NodoError> sintacticos) {
        for (int i = 0; i < lexicos.size(); i++) {
            errores.add(lexicos.get(i));
        }
        for (int i = 0; i < sintacticos.size(); i++) {
            errores.add(sintacticos.get(i));
        }
        return true;
    }
}
