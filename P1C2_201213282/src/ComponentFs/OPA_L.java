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
public class OPA_L {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public OPA_L(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar_OPL(NodoFs raiz, ArrayList<NodoError> errores) {
        switch (raiz.hijos.size()) {
            case 2: {
                Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                NodoRespuesta dos = OP.Cuerpo_G(raiz.hijos.get(1), errores);
                if (uno.error || dos.error) {
                    NodoRespuesta error = new NodoRespuesta(true);
                    return error;
                }
                if (!uno.resultado.toString().equalsIgnoreCase("verdadero") && !uno.resultado.toString().equalsIgnoreCase("falso")) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "error el valor no es verdadero o falso";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
                    return new NodoRespuesta(true);
                }
                if (!dos.resultado.toString().equalsIgnoreCase("verdadero") && !dos.resultado.toString().equalsIgnoreCase("falso")) {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "error el valor no es verdadero o falso";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
                    return new NodoRespuesta(true);
                }
                NodoRespuesta resp = Accion_L(uno, raiz.valor, dos, errores);
                return resp;
            }
            case 1: {
                Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                if (raiz.hijos.get(0).valor.equalsIgnoreCase("not")) {
                    NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                    String respuesta = realizar_NOT(uno.resultado.toString());
                    NodoRespuesta result = new NodoRespuesta(respuesta);
                    return result;
                } else {
                    NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                    if (!uno.error) {
                        if (uno.resultado.toString().equalsIgnoreCase("verdadero")) {
                            return uno;
                        } else if (uno.resultado.toString().equalsIgnoreCase("falso")) {
                            return uno;
                        } else {
                            NodoError error = new NodoError("semantico");
                            error.descripcion = "error el valor no es verdadero o falso";
                            error.linea = String.valueOf(raiz.linea);
                            error.columna = String.valueOf(raiz.columna);
                            errores.add(error);
                            return new NodoRespuesta(true);
                        }
                    }
                    return uno;
                }
            }
            case 3:
                Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                NodoRespuesta tres = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                if (tres.error) {
                    NodoRespuesta error = new NodoRespuesta(true);
                    return error;
                }
                System.out.println("llego el resultado: " + tres.resultado.toString());
                if (tres.resultado.toString().equalsIgnoreCase("verdadero")) {
                    NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(1), errores);
                    return uno;
                } else if (tres.resultado.toString().equalsIgnoreCase("falso")) {
                    NodoRespuesta dos = OP.Cuerpo_G(raiz.hijos.get(2), errores);
                    return dos;
                } else {
                    NodoError error = new NodoError("semantico");
                    error.descripcion = "error el valor no es verdadero o falso";
                    error.linea = String.valueOf(raiz.linea);
                    error.columna = String.valueOf(raiz.columna);
                    errores.add(error);
                    return new NodoRespuesta(true);
                }
        }
        return new NodoRespuesta(true);
    }

    private NodoRespuesta Accion_L(NodoRespuesta Izq, String tipo, NodoRespuesta Der, ArrayList<NodoError> errores) {
        //System.out.println("--------------");
        //System.out.println("izq: "+Izq.resultado.toString()+" Der:"+Der.resultado.toString());
        // System.out.println("--------------");
        return realizar_OP(Izq.resultado.toString(), tipo, Der.resultado.toString());

    }

    private NodoRespuesta realizar_OP(String izq, String tipo, String der) {
        NodoRespuesta nuevo;
        switch (tipo) {
            case "OR":
                nuevo = new NodoRespuesta(realizar_OR(izq, der));
                return nuevo;
            case "AND":
                nuevo = new NodoRespuesta(realizar_AND(izq, der));
                return nuevo;
        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private String realizar_OR(String izq, String der) {
        if (izq.equalsIgnoreCase("verdadero") || der.equalsIgnoreCase("verdadero")) {
            return "verdadero";
        }
        return "falso";
    }

    private String realizar_AND(String izq, String der) {
        if (izq.equalsIgnoreCase("verdadero") && der.equalsIgnoreCase("verdadero")) {
            return "verdadero";
        }
        return "falso";
    }

    private String realizar_NOT(String izq) {
        if (izq.equalsIgnoreCase("verdadero")) {
            return "falso";
        }
        return "verdadero";

    }

}
