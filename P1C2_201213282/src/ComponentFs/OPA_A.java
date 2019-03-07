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
public class OPA_A {

    TablaSimbolos tabla;

    public OPA_A(TablaSimbolos tabla) {
        this.tabla = tabla;
    }

    public NodoRespuesta Analizar_OPA(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta uno = Cuerpo_A(raiz.hijos.get(0), errores);
        NodoRespuesta dos = Cuerpo_A(raiz.hijos.get(1), errores);
        if (uno.error || dos.error) {
            NodoRespuesta error = new NodoRespuesta(true);
            return error;
        }
        NodoRespuesta resp = Accion_A(uno, raiz.valor, dos, errores);
        //System.out.println(uno.resultado + "<---uno " + dos.resultado + "<-----dos" + resp.resultado + "<-----res");
        return resp;
    }

    private NodoRespuesta Cuerpo_A(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "ope_l":
                break;
            case "ope_c":
                break;
            case "ope_a":
                OPA_A operacon = new OPA_A(tabla);
                return operacon.Analizar_OPA(raiz, errores);

            case "dato":
                nuevo = new NodoRespuesta(raiz.valor);
                return nuevo;
            case "dato negado":
                nuevo = new NodoRespuesta("-" + raiz.valor);
                return nuevo;

            case "autoincremento":
                ES_ID retorno = new ES_ID(tabla);
                return retorno.autoincrementar(raiz, errores);

            case "autodecremento":
                retorno = new ES_ID(tabla);
                return retorno.autodecrementar(raiz, errores);

            case "nativas":
                break;
            case "llamadafun":
                break;
            case "id":
                ES_ID id = new ES_ID(tabla);
                return id.Analizar(raiz, errores);

        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private NodoRespuesta Accion_A(NodoRespuesta Izq, String tipo, NodoRespuesta Der, ArrayList<NodoError> errores) {
        String tipo_izq = ret_tipo(Izq.resultado);
        String tipo_der = ret_tipo(Der.resultado);
        boolean relizar = ret_compatible(tipo_izq, tipo_der, tipo);
        if (relizar) {
            return realizar_OP(Izq.resultado, tipo_izq, tipo, Der.resultado, tipo_der);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "erro tipos incompatibles no se puede: "+tipo+" con: "+tipo_izq+" y "+tipo_der;
            System.out.println(error.descripcion);
            System.out.println(Izq.resultado+"---"+Der.resultado);
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
        if (tipo.equalsIgnoreCase("+")) {
            return ret_compatible_mas(tipo_izq, tipo_der);
        } else if (tipo.equalsIgnoreCase("*") || tipo.equalsIgnoreCase("/") || tipo.equalsIgnoreCase("-")) {
            return ret_compatible_menos_por_div(tipo_izq, tipo_der);
        } else {
            return ret_compatible_pot(tipo_izq, tipo_der);
        }
    }

    private boolean ret_compatible_mas(String tipo_izq, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("cadena") || tipo_der.equalsIgnoreCase("cadena")) {
            return true;
        } else if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("boleano")) {
                return false;
            }
            return true;
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            if (tipo_der.equalsIgnoreCase("boleano")) {
                return false;
            }
            return true;
        } else if (tipo_izq.equalsIgnoreCase("boleano")) {
            return false;
        }
        return false;
    }

    private boolean ret_compatible_menos_por_div(String tipo_izq, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("cadena") || tipo_der.equalsIgnoreCase("cadena")) {
            return false;
        } else if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("boleano")) {
                return false;
            }
            return true;
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            if (tipo_der.equalsIgnoreCase("boleano")) {
                return false;
            }
            return true;
        } else if (tipo_izq.equalsIgnoreCase("boleano")) {
            return false;
        }
        return false;
    }

    private boolean ret_compatible_pot(String tipo_izq, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("cadena") || tipo_der.equalsIgnoreCase("cadena")) {
            return false;
        } else if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("boleano")) {
                return false;
            }
            return true;
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            if (tipo_der.equalsIgnoreCase("boleano")) {
                return false;
            }
            return true;
        } else if (tipo_izq.equalsIgnoreCase("boleano")) {
            return true;
        }
        return false;
    }

    private NodoRespuesta realizar_OP(String izq, String tipo_i, String tipo, String der, String tipo_d) {
        NodoRespuesta nuevo;
        switch (tipo) {
            case "+":
                nuevo = new NodoRespuesta(realizar_suma(izq, tipo_i, der, tipo_d));
                return nuevo;
            case "-":
                nuevo = new NodoRespuesta(realizar_resta(izq, tipo_i, der, tipo_d));
                return nuevo;
            case "*":
                nuevo = new NodoRespuesta(realizar_mul(izq, tipo_i, der, tipo_d));
                return nuevo;
            case "/":
                nuevo = new NodoRespuesta(realizar_div(izq, tipo_i, der, tipo_d));
                return nuevo;
            case "^":
                nuevo = new NodoRespuesta(realizar_pot(izq, tipo_i, der, tipo_d));
                return nuevo;
        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private String realizar_suma(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("cadena") || tipo_der.equalsIgnoreCase("cadena")) {
            izq = izq.replace("\"", "");
            der = der.replace("\"", "");
            return "\"" + izq + der + "\"";
        } else if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("numero")) {
                double num = Double.valueOf(izq);
                int numd = Integer.valueOf(der);
                Double result = num + numd;
                return result.toString();
            } else {
                double num = Double.valueOf(izq);
                double numd = Double.valueOf(der);
                Double result = num + numd;
                return result.toString();
            }
        } else if (tipo_der.equalsIgnoreCase("decimal")) {
            double num = Double.valueOf(der);
            int numd = Integer.valueOf(izq);
            Double result = num + numd;
            return result.toString();
        } else {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            Integer result = num + numd;
            return result.toString();
        }
    }

    private String realizar_resta(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("numero")) {
                double num = Double.valueOf(izq);
                int numd = Integer.valueOf(der);
                Double result = num - numd;
                return result.toString();
            } else {
                double num = Double.valueOf(izq);
                double numd = Double.valueOf(der);
                Double result = num - numd;
                return result.toString();
            }
        } else if (tipo_der.equalsIgnoreCase("decimal")) {
            int numi = Integer.valueOf(izq);
            double num = Double.valueOf(der);
            Double result = numi - num;
            return result.toString();
        } else {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            Integer result = num - numd;
            return result.toString();
        }
    }

    private String realizar_mul(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("numero")) {
                double num = Double.valueOf(izq);
                int numd = Integer.valueOf(der);
                Double result = num * numd;
                return result.toString();
            } else {
                double num = Double.valueOf(izq);
                double numd = Double.valueOf(der);
                Double result = num * numd;
                return result.toString();
            }
        } else if (tipo_der.equalsIgnoreCase("decimal")) {
            int numi = Integer.valueOf(izq);
            double num = Double.valueOf(der);
            Double result = numi * num;
            return result.toString();
        } else {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            Integer result = num * numd;
            return result.toString();
        }
    }

    private String realizar_div(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("numero")) {
                double num = Double.valueOf(izq);
                int numd = Integer.valueOf(der);
                Double result = num / (numd + 0.0);
                return result.toString();
            } else {
                double num = Double.valueOf(izq);
                double numd = Double.valueOf(der);
                Double result = num / numd;
                return result.toString();
            }
        } else if (tipo_der.equalsIgnoreCase("decimal")) {
            int numi = Integer.valueOf(izq);
            double num = Double.valueOf(der);
            Double result = numi / num;
            return result.toString();
        } else {
            int num = Integer.valueOf(izq);
            int numd = Integer.valueOf(der);
            Double result = num / (numd + 0.0);
            return result.toString();
        }
    }

    private String realizar_pot(String izq, String tipo_izq, String der, String tipo_der) {
        if (tipo_izq.equalsIgnoreCase("decimal")) {
            if (tipo_der.equalsIgnoreCase("numero")) {
                double num = Double.valueOf(izq);
                int numd = Integer.valueOf(der);
                Double result = Math.pow(num, numd);
                return result.toString();
            } else {
                double num = Double.valueOf(izq);
                double numd = Double.valueOf(der);
                Double result = Math.pow(num, numd);
                return result.toString();
            }
        } else if (tipo_izq.equalsIgnoreCase("numero")) {
            if (tipo_izq.equalsIgnoreCase("decimal")) {
                int numi = Integer.valueOf(izq);
                double num = Double.valueOf(der);
                Double result = Math.pow(numi, num);
                return result.toString();
            } else {
                int num = Integer.valueOf(izq);
                int numd = Integer.valueOf(der);
                Integer resultado = 1;
                for (int i = 0; i < numd; i++) {
                    resultado = resultado * num;
                }
                return resultado.toString();
            }
        } else {
            int num = 0;
            if (izq.equalsIgnoreCase("verdadero")) {
                return "1";
            }
            return "0";
        }
    }

    public NodoRespuesta sumar_uno(NodoRespuesta dato, ArrayList<NodoError> errores, TablaSimbolos tabla) {
        String tipo = ret_tipo(dato.resultado);
        if (tipo.equalsIgnoreCase("decimal") || tipo.equalsIgnoreCase("numero")) {
            String resultado = realizar_suma(dato.resultado, tipo, "1", "numero");
            Fs_varios fs = new Fs_varios();
            fs.set_Nuevoval_ID(resultado, dato.dato, tabla);
            return new NodoRespuesta(false);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "no se puede autoincrementar la variable: " + dato.dato + " no es decimal o entero ";
            errores.add(error);
            NodoRespuesta uno = new NodoRespuesta(true);
            return uno;
        }
    }

    public NodoRespuesta restar_uno(NodoRespuesta dato, ArrayList<NodoError> errores, TablaSimbolos tabla) {
        String tipo = ret_tipo(dato.resultado);
        if (tipo.equalsIgnoreCase("decimal") || tipo.equalsIgnoreCase("numero")) {
            String resultado = realizar_resta(dato.resultado, tipo, "1", "numero");
            Fs_varios fs = new Fs_varios();
            fs.set_Nuevoval_ID(resultado, dato.dato, tabla);
            return new NodoRespuesta(false);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "no se puede autodecrementar la variable: " + dato.dato + " no es decimal o entero ";
            errores.add(error);
            NodoRespuesta uno = new NodoRespuesta(true);
            return uno;
        }
    }
}
