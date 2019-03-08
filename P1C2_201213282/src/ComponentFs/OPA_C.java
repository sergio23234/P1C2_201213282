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
    TablaSimbolos global;

    public OPA_C(TablaSimbolos tabla, TablaSimbolos global) {
        this.tabla = tabla;
        this.global = global;
    }

    public NodoRespuesta Analizar_OPC(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno = Cuerpo_C(raiz.hijos.get(0), errores);
        NodoRespuesta dos = Cuerpo_C(raiz.hijos.get(1), errores);
        if (uno.error || dos.error) {
            NodoRespuesta error = new NodoRespuesta(true);
            return error;
        }
        NodoRespuesta resp = Accion_C(uno, raiz.valor, dos, errores);
        //System.out.println(uno.resultado + "<---uno " + dos.resultado + "<-----dos" + resp.resultado + "<-----res");
        return resp;
    }

    private NodoRespuesta Cuerpo_C(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "ope_l":
                OPA_L operal = new OPA_L(tabla, global);
                return operal.Analizar_OPL(raiz, errores);
            case "ope_c":
                OPA_C operac = new OPA_C(tabla, global);
                return operac.Analizar_OPC(raiz, errores);

            case "ope_a":
                OPA_A operacon = new OPA_A(tabla, global);
                return operacon.Analizar_OPA(raiz, errores);

            case "dato":
                nuevo = new NodoRespuesta(raiz.valor);
                return nuevo;
            case "dato negado":
                nuevo = new NodoRespuesta("-" + raiz.valor);
                return nuevo;

            case "autoincremento":
                ES_ID retorno = new ES_ID(tabla, global);
                return retorno.autoincrementar(raiz, errores);

            case "autodecremento":
                retorno = new ES_ID(tabla, global);
                return retorno.autodecrementar(raiz, errores);

            case "nativas":
                break;
            case "llamadafun":
                break;
            case "id":
                ES_ID id = new ES_ID(tabla, global);
                return id.Analizar(raiz, errores);

        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private NodoRespuesta Accion_C(NodoRespuesta Izq, String tipo, NodoRespuesta Der, ArrayList<NodoError> errores) {
        String tipo_izq = ret_tipo(Izq.resultado.toString());
        String tipo_der = ret_tipo(Der.resultado.toString());
        boolean relizar = ret_compatible(tipo_izq, tipo_der, tipo);
        if (relizar) {
            return realizar_OP(Izq.resultado.toString(), tipo_izq, tipo, Der.resultado.toString());
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "erro tipos incompatibles no se puede: " + tipo + " con: " + tipo_izq + " y " + tipo_der;
            System.out.println(error.descripcion);
            System.out.println(Izq.resultado + "---" + Der.resultado);
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

    private NodoRespuesta realizar_OP(String izq, String tipo_i, String tipo, String der) {
        NodoRespuesta nuevo;
        switch (tipo) {
            case ">":
                nuevo = new NodoRespuesta(realizar_mayor(izq, tipo_i, der));
                return nuevo;
            case "<":
                nuevo = new NodoRespuesta(realizar_menor(izq, tipo_i, der));
                return nuevo;
            case ">=":
                nuevo = new NodoRespuesta(realizar_mayorigu(izq, tipo_i, der));
                return nuevo;
            case "<=":
                nuevo = new NodoRespuesta(realizar_menorigu(izq, tipo_i, der));
                return nuevo;
            case "==":
                nuevo = new NodoRespuesta(realizar_igual(izq, tipo_i, der));
                return nuevo;
            case "!=":
                nuevo = new NodoRespuesta(realizar_notigu(izq, tipo_i, der));
                return nuevo;
        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private String realizar_igual(String izq, String tipo_izq, String der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num == numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            if (num == numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("cadena")) {
            int t_izq = sumatoria_string(izq);
            int t_der = sumatoria_string(der);
            if (t_izq == t_der) {
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

    private String realizar_notigu(String izq, String tipo_izq, String der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num != numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            if (num != numd) {
                return "verdadero";
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

    private String realizar_mayor(String izq, String tipo_izq, String der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num > numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            if (num > numd) {
                return "verdadero";
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

    private String realizar_mayorigu(String izq, String tipo_izq, String der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num >= numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            if (num >= numd) {
                return "verdadero";
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

    private String realizar_menor(String izq, String tipo_izq, String der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num < numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            if (num < numd) {
                return "verdadero";
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

    private String realizar_menorigu(String izq, String tipo_izq, String der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(izq);
            double numd = Double.valueOf(der);
            if (num <= numd) {
                return "verdadero";
            }
            return "falso";
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            if (num <= numd) {
                return "verdadero";
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
}
