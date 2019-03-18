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
public class OPA_C {

    TablaSimbolos tabla;
    int num;
    TablaSimbolos global;

    public OPA_C(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar_OPC(NodoFs raiz, ArrayList<NodoError> errores) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        NodoRespuesta dos = OP.Cuerpo_G(raiz.hijos.get(1), errores);
        if (uno.error || dos.error) {
            NodoRespuesta error = new NodoRespuesta(true);
            return error;
        }
        NodoRespuesta resp = Accion_C(uno, raiz.valor, dos, errores);
        //System.out.println(uno.resultado + "<---uno " + dos.resultado + "<-----dos" + resp.resultado + "<-----res");
        return resp;
    }

    private NodoRespuesta Accion_C(NodoRespuesta Izq, String tipo, NodoRespuesta Der, ArrayList<NodoError> errores) {
        String tipo_izq = ret_tipo(Izq.resultado.toString());
        String tipo_der = ret_tipo(Der.resultado.toString());
        boolean relizar = ret_compatible(tipo_izq, tipo_der, tipo);
        if (relizar) {
            return realizar_OP(Izq.resultado.toString(), tipo_izq, tipo, Der.resultado.toString(), tipo_der);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "erro tipos incompatibles no se puede: " + tipo + " con: " + tipo_izq + " y " + tipo_der;
            //System.out.println(error.descripcion);
            //System.out.println(Izq.resultado + "---" + Der.resultado);
            errores.add(error);
            return new NodoRespuesta(true);
        }
    }

    private String ret_tipo(String value) {
        if (value.contains("\"")) {
            return "cadena";
        } else if (value.equalsIgnoreCase("verdadero") || value.equalsIgnoreCase("falso")) {
            return "boleano";
        } else if (value.contains(".")) {
            return "decimal";
        } else {
            return "numero";
        }
    }

    private boolean ret_compatible(String tipo_izq, String tipo_der, String tipo) {
        if (tipo_izq.equalsIgnoreCase("cadena") && tipo_der.equalsIgnoreCase("cadena")) {
            return true;
        } else if (tipo_izq.equalsIgnoreCase("boleano") && tipo_der.equalsIgnoreCase("boleano")) {
            return true;
        } else if ((tipo_izq.equalsIgnoreCase("decimal") || tipo_izq.equalsIgnoreCase("numero")) && (tipo_der.equalsIgnoreCase("decimal") || tipo_der.equalsIgnoreCase("numero"))) {
            return true;
        }
        return false;
    }

    private NodoRespuesta realizar_OP(String izq, String tipo_i, String tipo, String der, String tipo_der) {
        NodoRespuesta nuevo;
        switch (tipo) {
            case ">":
                nuevo = new NodoRespuesta(realizar_mayor(izq, tipo_i, der, tipo_der));
                return nuevo;
            case "<":
                nuevo = new NodoRespuesta(realizar_menor(izq, tipo_i, der, tipo_der));
                return nuevo;
            case ">=":
                nuevo = new NodoRespuesta(realizar_mayorigu(izq, tipo_i, der, tipo_der));
                return nuevo;
            case "<=":
                nuevo = new NodoRespuesta(realizar_menorigu(izq, tipo_i, der, tipo_der));
                return nuevo;
            case "==":
                nuevo = new NodoRespuesta(realizar_igual(izq, tipo_i, der, tipo_der));
                return nuevo;
            case "!=":
                nuevo = new NodoRespuesta(realizar_notigu(izq, tipo_i, der, tipo_der));
                return nuevo;
        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private String realizar_igual(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num == numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            if (tipo_der.equalsIgnoreCase("numero")) {
                int numd = Integer.valueOf(der);
                if (num == numd) {
                    return "verdadero";
                }
            } else {
                double numd = Double.valueOf(der);
                if (num == numd) {
                    return "verdadero";
                }
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            String t_izq = (izq.replace("\"", ""));
            String t_der = (der.replace("\"", ""));
            if (t_izq.equalsIgnoreCase(t_der)) {
                return "verdadero";
            }
            return "falso";
        } else {
            if (izq.equalsIgnoreCase(der)) {
                return "verdadero";
            }
            return "falso";
        }
    }

    private String realizar_notigu(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num != numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            if (tipo_der.equalsIgnoreCase("numero")) {
                int numd = Integer.valueOf(der);
                if (num != numd) {
                    return "verdadero";
                }
            } else {
                double numd = Double.valueOf(der);
                if (num != numd) {
                    return "verdadero";
                }
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            int t_izq = sumatoria_string(izq);
            int t_der = sumatoria_string(der);
            if (t_izq != t_der) {
                return "verdadero";
            }
            return "falso";
        } else {
            if (izq.equalsIgnoreCase(der)) {
                return "falso";
            }
            return "verdadero";
        }
    }

    private String realizar_mayor(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num > numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            if (tipo_der.equalsIgnoreCase("numero")) {
                int numd = Integer.valueOf(der);
                if (num > numd) {
                    return "verdadero";
                }
            } else {
                double numd = Double.valueOf(der);
                if (num > numd) {
                    return "verdadero";
                }
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            int t_izq = sumatoria_string(izq);
            int t_der = sumatoria_string(der);
            if (t_izq > t_der) {
                return "verdadero";
            }
            return "falso";
        } else {
            int num = 0;
            int numd = 0;
            if (izq.equalsIgnoreCase("verdadero")) {
                num = 1;
            }
            if (der.equalsIgnoreCase("verdadero")) {
                numd = 1;
            }
            if (num > numd) {
                return "verdadero";
            }
            return "falso";
        }
    }

    private String realizar_mayorigu(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num >= numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            if (tipo_der.equalsIgnoreCase("numero")) {
                int numd = Integer.valueOf(der);
                if (num >= numd) {
                    return "verdadero";
                }
            } else {
                double numd = Double.valueOf(der);
                if (num >= numd) {
                    return "verdadero";
                }
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            int t_izq = sumatoria_string(izq);
            int t_der = sumatoria_string(der);
            if (t_izq >= t_der) {
                return "verdadero";
            }
            return "falso";
        } else {
            int num = 0;
            int numd = 0;
            if (izq.equalsIgnoreCase("verdadero")) {
                num = 1;
            }
            if (der.equalsIgnoreCase("verdadero")) {
                numd = 1;
            }
            if (num >= numd) {
                return "verdadero";
            }
            return "falso";
        }
    }

    private String realizar_menor(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num < numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            if (tipo_der.equalsIgnoreCase("numero")) {
                int numd = Integer.valueOf(der);
                if (num < numd) {
                    return "verdadero";
                }
            } else {
                double numd = Double.valueOf(der);
                if (num < numd) {
                    return "verdadero";
                }
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            int t_izq = sumatoria_string(izq);
            int t_der = sumatoria_string(der);
            if (t_izq < t_der) {
                return "verdadero";
            }
            return "falso";
        } else {
            int num = 0;
            int numd = 0;
            if (izq.equalsIgnoreCase("verdadero")) {
                num = 1;
            }
            if (der.equalsIgnoreCase("verdadero")) {
                numd = 1;
            }
            if (num < numd) {
                return "verdadero";
            }
            return "falso";
        }
    }

    private String realizar_menorigu(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num <= numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            if (tipo_der.equalsIgnoreCase("numero")) {
                int numd = Integer.valueOf(der);
                if (num <= numd) {
                    return "verdadero";
                }
            } else {
                double numd = Double.valueOf(der);
                if (num <= numd) {
                    return "verdadero";
                }
            }

            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            int t_izq = sumatoria_string(izq);
            int t_der = sumatoria_string(der);
            if (t_izq <= t_der) {
                return "verdadero";
            }
            return "falso";
        } else {
            int num = 0;
            int numd = 0;
            if (izq.equalsIgnoreCase("verdadero")) {
                num = 1;
            }
            if (der.equalsIgnoreCase("verdadero")) {
                numd = 1;
            }
            if (num <= numd) {
                return "verdadero";
            }
            return "falso";
        }
    }

    private int sumatoria_string(String cadena) {
        char[] cad = cadena.toCharArray();
        int resultado = 0;
        for (int i = 0; i < cad.length; i++) {
            resultado += (int) cad[i];
        }
        return resultado;
    }

    public NodoRespuesta Comparar_Caso(String dato1,String dato2){
        String tipo1 = ret_tipo(dato1);
        String tipo2 = ret_tipo(dato2);
        boolean compatible = ret_compatible(tipo1,tipo2,"==");
        if(compatible){
            String resultado = realizar_igual(dato1,tipo1,dato2,tipo2);
            return new NodoRespuesta(resultado);
        }else{
            return new NodoRespuesta(true);
        }
    }
    
}

