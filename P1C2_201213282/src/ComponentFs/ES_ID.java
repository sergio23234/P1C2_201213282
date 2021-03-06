/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Principal.NodoError;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class ES_ID {

    TablaSimbolos tabla;
    int num;
    TablaSimbolos global;

    public ES_ID(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno;
        if (raiz.hijos.size() > 0) {//es vector
            if (raiz.lista.size() > 0) {
                if (raiz.lista.get(0).equalsIgnoreCase("objeto")) {
                    Fs_varios nuevo = new Fs_varios();
                    Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                    uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                    String nombre = raiz.valor + raiz.lista.get(1) + "[" + uno.resultado.toString() + "]";
                    boolean existe = nuevo.ret_Existencia_ID(nombre, tabla);
                    if (!existe) {
                        boolean rama = nuevo.ret_Existencia_ID(nombre, global);
                        if (!rama) {
                            NodoError error = new NodoError("semantico");
                            error.descripcion = "no se encuentra la variable: " + nombre + " en el documento";
                            error.linea = String.valueOf(raiz.linea);
                            error.columna = String.valueOf(raiz.columna);
                            errores.add(error);
                            uno = new NodoRespuesta(true);
                            return uno;
                        } else {

                            return nuevo.ret_ID_Tabla(nombre, global);
                        }
                    } else {
                        // System.out.println("vino nombre: " + nombre);
                        return nuevo.ret_ID_Tabla(nombre, tabla);
                    }
                }
            } else { //vector simple
                Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                if (!uno.error) {
                    int num;
                    try {
                        String val = String.valueOf(uno.resultado);
                        num = Integer.valueOf(val);
                        String id = raiz.valor + "[" + num + "]";
                        //System.out.println("este es el ID: " + id);
                        Fs_varios nuevo = new Fs_varios();

                        boolean existe = nuevo.ret_Existencia_ID(id, tabla);
                        if (!existe) {
                            boolean rama = nuevo.ret_Existencia_ID(id, global);
                            if (!rama) {
                                NodoError error = new NodoError("semantico");
                                error.descripcion = "el vector: " + id + " no existe o el numero esta fuera del tamaño";
                                error.linea = String.valueOf(raiz.linea);
                                error.columna = String.valueOf(raiz.columna);
                                errores.add(error);
                                uno = new NodoRespuesta(true);
                                return uno;
                            } else {
                                // System.out.println(id + "resultado gb");
                                return nuevo.ret_ID_Tabla(id, global);
                            }
                        } else {
                            // System.out.println(id + "resultado");
                            return nuevo.ret_ID_Tabla(id, tabla);
                        }
                    } catch (Exception e) {
                        NodoError error = new NodoError("semantico");
                        error.descripcion = "dentro del verctor " + raiz.valor + " no se encontro un numero entero se encontro: " + uno.resultado;
                        error.linea = String.valueOf(raiz.linea);
                        error.columna = String.valueOf(raiz.columna);
                        errores.add(error);
                        return new NodoRespuesta(true);
                    }
                }
            }
        } else if (raiz.lista.size() > 0) {//es objeto
            Fs_varios nuevo = new Fs_varios();
            String nombre = raiz.valor + raiz.lista.get(0);
            // System.out.println("vino nombre: "+nombre);
            boolean existe = nuevo.ret_Existencia_ID(nombre, tabla);
            if (!existe) {
                boolean rama = nuevo.ret_Existencia_ID(nombre, global);
                if (!rama) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "no se encuentra la variable: " + nombre + " en el documento";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
                    uno = new NodoRespuesta(true);
                    return uno;
                } else {
                    return nuevo.ret_ID_Tabla(nombre, global);
                }
            } else {
                return nuevo.ret_ID_Tabla(nombre, tabla);
            }
        } else {
            Fs_varios nuevo = new Fs_varios();
            boolean existe = nuevo.ret_Existencia_ID(raiz.valor, tabla);
            if (!existe) {
                boolean rama = nuevo.ret_Existencia_ID(raiz.valor, global);
                if (!rama) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "no se encuentra la variable: " + raiz.valor + " en el documento";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
                    uno = new NodoRespuesta(true);
                    return uno;
                } else {
                    return nuevo.ret_ID_Tabla(raiz.valor, global);
                }
            } else {
                return nuevo.ret_ID_Tabla(raiz.valor, tabla);
            }
        }
        uno = new NodoRespuesta(true);
        return uno;
    }

    public NodoRespuesta autoincrementar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno;
        uno = Analizar(raiz.hijos.get(0), errores);
        //System.out.println(uno.error + "_" + uno.resultado + "<---");
        if (!uno.error) {
            OPA_A nueva = new OPA_A(tabla, global, num);
            NodoRespuesta dos = nueva.sumar_uno(uno, tabla, raiz.linea, raiz.columna);
            if (dos.error) {
                return dos;
            }
        }
        return uno;
    }

    public NodoRespuesta autodecrementar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno;
        // System.out.println(raiz.Tipo);
        uno = Analizar(raiz.hijos.get(0), errores);
        //System.out.println(uno.error + "_" + uno.resultado + "<---");
        if (!uno.error) {
            OPA_A nueva = new OPA_A(tabla, global, num);
            NodoRespuesta dos = nueva.restar_uno(uno, tabla, raiz.linea, raiz.columna);
            if (dos.error) {
                return dos;
            }
        }
        return uno;
    }

}
