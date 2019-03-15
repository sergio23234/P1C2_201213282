/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Principal.Menu;
import Principal.NodoError;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class Componentes_FS {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Componentes_FS(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar_Ventana(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta dato1 = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        NodoRespuesta dato2 = OP.Cuerpo_G(raiz.hijos.get(1), errores);
        NodoRespuesta dato3 = OP.Cuerpo_G(raiz.hijos.get(2), errores);
        /*ERROES*/
        if (dato1.error || dato2.error || dato3.error) {
            return new NodoRespuesta(true);
        } else {
            String tipo1 = ret_tipo(dato1.resultado.toString());
            String tipo2 = ret_tipo(dato2.resultado.toString());
            String tipo3 = ret_tipo(dato3.resultado.toString());
            /*System.out.println(dato1.resultado.toString() + " es tipo: " + tipo1);
            System.out.println(dato2.resultado.toString() + " es tipo: " + tipo2);
            System.out.println(dato3.resultado.toString() + " es tipo: " + tipo3);*/
            if (!tipo1.equalsIgnoreCase("cadena") || !tipo2.equalsIgnoreCase("numero") || !tipo3.equalsIgnoreCase("numero")) {
                return new NodoRespuesta(true);
            }
            String id = raices.lista.get(raices.lista.size() - 1);
            int largo = Integer.valueOf(dato2.resultado.toString());
            int ancho = Integer.valueOf(dato3.resultado.toString());
            String color = dato1.resultado.toString().replace("\"", "");
            boolean resultado = Menu.Lista.get(num).Add_ventana(color, largo, ancho, id);
            if (resultado) {
                return new NodoRespuesta(false);
            } else {
                return new NodoRespuesta(true);
            }
        }
    }

    public NodoRespuesta Analizar_Contenedor(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        String id_ventana = raiz.valor;
        NodoRespuesta dato1 = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        NodoRespuesta dato2 = OP.Cuerpo_G(raiz.hijos.get(1), errores);
        NodoRespuesta dato3 = OP.Cuerpo_G(raiz.hijos.get(2), errores);
        NodoRespuesta dato4 = OP.Cuerpo_G(raiz.hijos.get(3), errores);
        NodoRespuesta dato5 = OP.Cuerpo_G(raiz.hijos.get(4), errores);
        NodoRespuesta dato6 = OP.Cuerpo_G(raiz.hijos.get(5), errores);
        /*ERROES*/
        if (dato1.error || dato2.error || dato3.error || dato4.error || dato5.error || dato6.error) {
            //System.out.println("hay error en estos");
            return new NodoRespuesta(true);
        } else {
            String tipos[] = new String[6];
            tipos[0] = ret_tipo(dato1.resultado.toString());
            tipos[1] = ret_tipo(dato2.resultado.toString());
            tipos[2] = ret_tipo(dato3.resultado.toString());
            tipos[3] = ret_tipo(dato4.resultado.toString());
            tipos[4] = ret_tipo(dato5.resultado.toString());
            tipos[5] = ret_tipo(dato6.resultado.toString());
            boolean error = false;
            for (int i = 0; i < tipos.length; i++) {
                if (!tipos[i].equalsIgnoreCase("cadena") && i == 2) {
                    //  System.out.println("no es cadena");
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("boleano") && i == 3) {
                    //System.out.println("no es boleano");
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("numero") && i != 2 && i != 3) {
                    //System.out.println("no es numero" + i);
                    error = true;
                    break;
                }
            }
            if (error) {
                return new NodoRespuesta(true);
            }
            String id = raices.lista.get(raices.lista.size() - 1);
            int alto = Integer.valueOf(dato1.resultado.toString());
            int ancho = Integer.valueOf(dato2.resultado.toString());
            String color = dato3.resultado.toString().replace("\"", "");
            boolean borde = false;
            if (dato4.resultado.toString().equalsIgnoreCase("verdadero")) {
                borde = true;
            }
            // System.out.println(id_ventana);
            int x = Integer.valueOf(dato5.resultado.toString());
            int y = Integer.valueOf(dato6.resultado.toString());
            boolean resultado = Menu.Lista.get(num).Add_contenedor_ventana(id, alto, ancho, color, borde, x, y, id_ventana);
            if (resultado) {

                return new NodoRespuesta(false);
            } else {

                return new NodoRespuesta(true);
            }
        }
    }

    public NodoRespuesta Analizar_Boton(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        String id_ventana = raiz.valor;
        NodoRespuesta dato1 = OP.Cuerpo_G(raiz.hijos.get(0), errores);//fuente
        NodoRespuesta dato2 = OP.Cuerpo_G(raiz.hijos.get(1), errores);//tamaño
        NodoRespuesta dato3 = OP.Cuerpo_G(raiz.hijos.get(2), errores);//color
        NodoRespuesta dato4 = OP.Cuerpo_G(raiz.hijos.get(3), errores);//x
        NodoRespuesta dato5 = OP.Cuerpo_G(raiz.hijos.get(4), errores);//y
        NodoRespuesta dato6 = OP.Cuerpo_G(raiz.hijos.get(5), errores);//referencia
        NodoRespuesta dato7 = OP.Cuerpo_G(raiz.hijos.get(6), errores);//valor
        NodoRespuesta dato8 = OP.Cuerpo_G(raiz.hijos.get(7), errores);//alto
        NodoRespuesta dato9 = OP.Cuerpo_G(raiz.hijos.get(8), errores);//ancho
        /*ERROES*/
        if (dato1.error || dato2.error || dato3.error || dato4.error || dato5.error || dato7.error || dato8.error || dato9.error) {
            return new NodoRespuesta(true);
        } else {
            String tipos[] = new String[9];
            tipos[0] = ret_tipo(dato1.resultado.toString());
            tipos[1] = ret_tipo(dato2.resultado.toString());
            tipos[2] = ret_tipo(dato3.resultado.toString());
            tipos[3] = ret_tipo(dato4.resultado.toString());
            tipos[4] = ret_tipo(dato5.resultado.toString());
            tipos[5] = ret_tipo(dato6.resultado.toString());
            tipos[6] = ret_tipo(dato7.resultado.toString());
            tipos[7] = ret_tipo(dato8.resultado.toString());
            tipos[8] = ret_tipo(dato9.resultado.toString());
            boolean error = false;
            for (int i = 0; i < tipos.length; i++) {
                if ((!tipos[i].equalsIgnoreCase("cadena") && i == 5) && (!tipos[i].equalsIgnoreCase("undefined") && i == 5)) {
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("cadena") && (i == 0 || i == 2 || i == 6)) {
                    //  System.out.println("no es cadena");
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("numero") && i != 0 && i != 2 && i != 5 && i != 6) {
                    // System.out.println("no es numero" + i);
                    error = true;
                    break;
                }
            }

            /*System.out.println(dato1.resultado.toString() + " es tipo: " + tipo1);
            System.out.println(dato2.resultado.toString() + " es tipo: " + tipo2);
            System.out.println(dato3.resultado.toString() + " es tipo: " + tipo3);*/
            if (error) {
                return new NodoRespuesta(true);
            }
            String id = raices.lista.get(raices.lista.size() - 1);
            String fuente = dato1.resultado.toString().replace("\"", "");
            int tam = Integer.valueOf(dato2.resultado.toString());
            String color = dato3.resultado.toString().replace("\"", "");
            int x = Integer.valueOf(dato4.resultado.toString());
            int y = Integer.valueOf(dato5.resultado.toString());
            String referencia = dato6.resultado.toString().replace("\"", "");
            String valor = dato7.resultado.toString().replace("\"", "");
            int alto = Integer.valueOf(dato8.resultado.toString());
            int ancho = Integer.valueOf(dato9.resultado.toString());

            boolean resultado = Menu.Lista.get(num).add_boton(id_ventana, id, fuente, tam, color, x, y, referencia, valor, alto, ancho);
            if (resultado) {
                NodoRespuesta retorno = new NodoRespuesta(raiz.valor);
                return retorno;
            } else {
                //System.out.println("no se añadio");
                return new NodoRespuesta(true);
            }
        }
    }

    private String ret_tipo(String value) {
        if (value.contains("\"")) {
            return "cadena";
        } else if (value.equalsIgnoreCase("verdadero") || value.equalsIgnoreCase("falso")) {
            return "boleano";
        } else if (value.contains(".")) {
            return "decimal";
        } else if (value.equalsIgnoreCase("undefined") || value.equalsIgnoreCase("nulo")) {
            return "undefined";
        } else {
            return "numero";
        }
    }

    public boolean averiguar_tipo_letra(String tipo) {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fontNames.length; i++) {
            if (fontNames[i].equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    public NodoRespuesta Analizar_Label(NodoFs raiz, ArrayList<NodoError> errores, NodoRespuesta id) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        String id_ventana = id.resultado.toString();
        NodoRespuesta dato1 = OP.Cuerpo_G(raiz.hijos.get(0), errores);//fuente
        NodoRespuesta dato2 = OP.Cuerpo_G(raiz.hijos.get(1), errores);//tamaño
        NodoRespuesta dato3 = OP.Cuerpo_G(raiz.hijos.get(2), errores);//color
        NodoRespuesta dato4 = OP.Cuerpo_G(raiz.hijos.get(3), errores);//x
        NodoRespuesta dato5 = OP.Cuerpo_G(raiz.hijos.get(4), errores);//y
        NodoRespuesta dato6 = OP.Cuerpo_G(raiz.hijos.get(5), errores);//negrita
        NodoRespuesta dato7 = OP.Cuerpo_G(raiz.hijos.get(6), errores);//cursiva
        NodoRespuesta dato8 = OP.Cuerpo_G(raiz.hijos.get(7), errores);//valor
        /*ERROES*/
        if (dato1.error || dato2.error || dato3.error || dato4.error || dato5.error || dato7.error || dato8.error || dato6.error) {
            return new NodoRespuesta(true);
        } else {
            String tipos[] = new String[8];
            tipos[0] = ret_tipo(dato1.resultado.toString());
            tipos[1] = ret_tipo(dato2.resultado.toString());
            tipos[2] = ret_tipo(dato3.resultado.toString());
            tipos[3] = ret_tipo(dato4.resultado.toString());
            tipos[4] = ret_tipo(dato5.resultado.toString());
            tipos[5] = ret_tipo(dato6.resultado.toString());
            tipos[6] = ret_tipo(dato7.resultado.toString());
            tipos[7] = ret_tipo(dato8.resultado.toString());
            boolean error = false;
            for (int i = 0; i < tipos.length; i++) {
                if ((!tipos[i].equalsIgnoreCase("boleano") && i == 5 && i == 6)) {
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("cadena") && (i == 0 || i == 2 || i == 7)) {
                    // System.out.println("no es cadena");
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("numero") && (i == 1 || i == 3 || i == 4)) {
                    // System.out.println("no es numero" + i);
                    error = true;
                    break;
                }
            }

            /*System.out.println(dato1.resultado.toString() + " es tipo: " + tipo1);
            System.out.println(dato2.resultado.toString() + " es tipo: " + tipo2);
            System.out.println(dato3.resultado.toString() + " es tipo: " + tipo3);*/
            if (error) {
                return new NodoRespuesta(true);
            }
            String fuente = dato1.resultado.toString().replace("\"", "");
            int tam = Integer.valueOf(dato2.resultado.toString());
            String color = dato3.resultado.toString().replace("\"", "");
            int x = Integer.valueOf(dato4.resultado.toString());
            int y = Integer.valueOf(dato5.resultado.toString());
            String negri = dato6.resultado.toString();
            String cursi = dato7.resultado.toString();
            int negrita = 0;
            int cursiva = 0;
            if (negri.equalsIgnoreCase("verdadero")) {
                negrita = 1;
            }
            if (cursi.equalsIgnoreCase("verdadero")) {
                cursiva = 2;
            }
            String valor = dato8.resultado.toString().replace("\"", "");

            boolean resultado = Menu.Lista.get(num).add_texto(id_ventana, fuente, tam, x, y, color, negrita, cursiva, valor);
            if (resultado) {
                NodoRespuesta retorno = new NodoRespuesta(raiz.valor);
                return retorno;
            } else {
                //  System.out.println("no se añadio");
                return new NodoRespuesta(true);
            }
        }
    }

    public NodoRespuesta Analizar_Texto(NodoFs raiz, ArrayList<NodoError> errores, NodoRespuesta id) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        String id_ventana = id.resultado.toString();
        NodoRespuesta dato1 = OP.Cuerpo_G(raiz.hijos.get(0), errores);//alto
        NodoRespuesta dato2 = OP.Cuerpo_G(raiz.hijos.get(1), errores);//ancho
        NodoRespuesta dato3 = OP.Cuerpo_G(raiz.hijos.get(2), errores);//Fuente
        NodoRespuesta dato4 = OP.Cuerpo_G(raiz.hijos.get(3), errores);//tam
        NodoRespuesta dato5 = OP.Cuerpo_G(raiz.hijos.get(4), errores);//Color
        NodoRespuesta dato6 = OP.Cuerpo_G(raiz.hijos.get(5), errores);//x
        NodoRespuesta dato7 = OP.Cuerpo_G(raiz.hijos.get(6), errores);//y
        NodoRespuesta dato8 = OP.Cuerpo_G(raiz.hijos.get(7), errores);//negrita
        NodoRespuesta dato9 = OP.Cuerpo_G(raiz.hijos.get(8), errores);//cursiva
        NodoRespuesta dato10 = OP.Cuerpo_G(raiz.hijos.get(9), errores);//defecto
        NodoRespuesta dato11 = OP.Cuerpo_G(raiz.hijos.get(10), errores);//nombre
        /*ERROES*/
        if (dato10.error || dato11.error || dato9.error || dato1.error || dato2.error || dato3.error || dato4.error || dato5.error || dato7.error || dato8.error || dato6.error) {
            return new NodoRespuesta(true);
        } else {
            String tipos[] = new String[11];
            tipos[0] = ret_tipo(dato1.resultado.toString());
            tipos[1] = ret_tipo(dato2.resultado.toString());
            tipos[2] = ret_tipo(dato3.resultado.toString());
            tipos[3] = ret_tipo(dato4.resultado.toString());
            tipos[4] = ret_tipo(dato5.resultado.toString());
            tipos[5] = ret_tipo(dato6.resultado.toString());
            tipos[6] = ret_tipo(dato7.resultado.toString());
            tipos[7] = ret_tipo(dato8.resultado.toString());
            tipos[8] = ret_tipo(dato9.resultado.toString());
            tipos[9] = ret_tipo(dato10.resultado.toString());
            tipos[10] = ret_tipo(dato11.resultado.toString());
            boolean error = false;
            for (int i = 0; i < tipos.length; i++) {
                if ((!tipos[i].equalsIgnoreCase("cadena") && i == 9) && (!tipos[i].equalsIgnoreCase("undefined") && i == 9)) {
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("cadena") && (i == 4 || i == 2 || i == 10)) {
                    // System.out.println("no es cadena");
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("numero") && (i == 0 || i == 1 || i == 5 || i == 6)) {
                    // System.out.println("no es numero" + i);
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("boleano") && (i == 8 || i == 7)) {
                    // System.out.println("no es numero" + i);
                    error = true;
                    break;
                }
            }

            /*System.out.println(dato1.resultado.toString() + " es tipo: " + tipo1);
            System.out.println(dato2.resultado.toString() + " es tipo: " + tipo2);
            System.out.println(dato3.resultado.toString() + " es tipo: " + tipo3);*/
            if (error) {
                return new NodoRespuesta(true);
            }
            int alto = Integer.valueOf(dato1.resultado.toString());
            int ancho = Integer.valueOf(dato2.resultado.toString());
            String fuente = dato3.resultado.toString().replace("\"", "");
            int tam = Integer.valueOf(dato4.resultado.toString());
            String color = dato5.resultado.toString().replace("\"", "");
            int x = Integer.valueOf(dato6.resultado.toString());
            int y = Integer.valueOf(dato7.resultado.toString());
            String negri = dato8.resultado.toString();
            String cursi = dato9.resultado.toString();
            int negrita = 0;
            if (negri.equalsIgnoreCase("verdadero")) {
                negrita += 1;
            }
            if (cursi.equalsIgnoreCase("verdadero")) {
                negrita += 2;
            }
            String defecto = dato10.resultado.toString().replace("\"", "");
            String valor = dato11.resultado.toString().replace("\"", "");

            boolean resultado = Menu.Lista.get(num).add_Field(id_ventana, alto, ancho, fuente, tam, color, x, y, negrita, defecto,valor);
            if (resultado) {
                NodoRespuesta retorno = new NodoRespuesta(raiz.valor);
                return retorno;
            } else {
                //  System.out.println("no se añadio");
                return new NodoRespuesta(true);
            }
        }
    }
    public NodoRespuesta Analizar_Area(NodoFs raiz, ArrayList<NodoError> errores, NodoRespuesta id) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        String id_ventana = id.resultado.toString();
        NodoRespuesta dato1 = OP.Cuerpo_G(raiz.hijos.get(0), errores);//alto
        NodoRespuesta dato2 = OP.Cuerpo_G(raiz.hijos.get(1), errores);//ancho
        NodoRespuesta dato3 = OP.Cuerpo_G(raiz.hijos.get(2), errores);//Fuente
        NodoRespuesta dato4 = OP.Cuerpo_G(raiz.hijos.get(3), errores);//tam
        NodoRespuesta dato5 = OP.Cuerpo_G(raiz.hijos.get(4), errores);//Color
        NodoRespuesta dato6 = OP.Cuerpo_G(raiz.hijos.get(5), errores);//x
        NodoRespuesta dato7 = OP.Cuerpo_G(raiz.hijos.get(6), errores);//y
        NodoRespuesta dato8 = OP.Cuerpo_G(raiz.hijos.get(7), errores);//negrita
        NodoRespuesta dato9 = OP.Cuerpo_G(raiz.hijos.get(8), errores);//cursiva
        NodoRespuesta dato10 = OP.Cuerpo_G(raiz.hijos.get(9), errores);//defecto
        NodoRespuesta dato11 = OP.Cuerpo_G(raiz.hijos.get(10), errores);//nombre
        /*ERROES*/
        if (dato10.error || dato11.error || dato9.error || dato1.error || dato2.error || dato3.error || dato4.error || dato5.error || dato7.error || dato8.error || dato6.error) {
            return new NodoRespuesta(true);
        } else {
            String tipos[] = new String[11];
            tipos[0] = ret_tipo(dato1.resultado.toString());
            tipos[1] = ret_tipo(dato2.resultado.toString());
            tipos[2] = ret_tipo(dato3.resultado.toString());
            tipos[3] = ret_tipo(dato4.resultado.toString());
            tipos[4] = ret_tipo(dato5.resultado.toString());
            tipos[5] = ret_tipo(dato6.resultado.toString());
            tipos[6] = ret_tipo(dato7.resultado.toString());
            tipos[7] = ret_tipo(dato8.resultado.toString());
            tipos[8] = ret_tipo(dato9.resultado.toString());
            tipos[9] = ret_tipo(dato10.resultado.toString());
            tipos[10] = ret_tipo(dato11.resultado.toString());
            boolean error = false;
            for (int i = 0; i < tipos.length; i++) {
                if ((!tipos[i].equalsIgnoreCase("cadena") && i == 9) && (!tipos[i].equalsIgnoreCase("undefined") && i == 9)) {
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("cadena") && (i == 4 || i == 2 || i == 10)) {
                    // System.out.println("no es cadena");
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("numero") && (i == 0 || i == 1 || i == 5 || i == 6)) {
                    // System.out.println("no es numero" + i);
                    error = true;
                    break;
                } else if (!tipos[i].equalsIgnoreCase("boleano") && (i == 8 || i == 7)) {
                    // System.out.println("no es numero" + i);
                    error = true;
                    break;
                }
            }

            /*System.out.println(dato1.resultado.toString() + " es tipo: " + tipo1);
            System.out.println(dato2.resultado.toString() + " es tipo: " + tipo2);
            System.out.println(dato3.resultado.toString() + " es tipo: " + tipo3);*/
            if (error) {
                return new NodoRespuesta(true);
            }
            int alto = Integer.valueOf(dato1.resultado.toString());
            int ancho = Integer.valueOf(dato2.resultado.toString());
            String fuente = dato3.resultado.toString().replace("\"", "");
            int tam = Integer.valueOf(dato4.resultado.toString());
            String color = dato5.resultado.toString().replace("\"", "");
            int x = Integer.valueOf(dato6.resultado.toString());
            int y = Integer.valueOf(dato7.resultado.toString());
            String negri = dato8.resultado.toString();
            String cursi = dato9.resultado.toString();
            int negrita = 0;
            if (negri.equalsIgnoreCase("verdadero")) {
                negrita += 1;
            }
            if (cursi.equalsIgnoreCase("verdadero")) {
                negrita += 2;
            }
            String defecto = dato10.resultado.toString().replace("\"", "").replace("\\n","\n");
            String valor = dato11.resultado.toString().replace("\"", "");

            boolean resultado = Menu.Lista.get(num).add_Area(id_ventana, alto, ancho, fuente, tam, color, x, y, negrita, defecto,valor);
            if (resultado) {
                NodoRespuesta retorno = new NodoRespuesta(raiz.valor);
                return retorno;
            } else {
                //  System.out.println("no se añadio");
                return new NodoRespuesta(true);
            }
        }
    }
}
