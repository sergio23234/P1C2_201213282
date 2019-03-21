/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import Principal.Menu;
import Principal.NodoError;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class OPA_A {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public OPA_A(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar_OPA(NodoFs raiz, ArrayList<NodoError> errores) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        NodoRespuesta dos = OP.Cuerpo_G(raiz.hijos.get(1), errores);
        int linea = raiz.hijos.get(0).linea;
        int columna = raiz.hijos.get(0).columna;
        if (uno.error || dos.error) {
            NodoRespuesta error = new NodoRespuesta(true);
            return error;
        }
        NodoRespuesta resp = Accion_A(uno, raiz.valor, dos, errores, linea, columna);
        //System.out.println(uno.resultado + "<---uno " + dos.resultado + "<-----dos" + resp.resultado + "<-----res");
        return resp;
    }

    private NodoRespuesta Accion_A(NodoRespuesta Izq, String tipo, NodoRespuesta Der, ArrayList<NodoError> errores, int linea, int columna) {
        String tipo_izq = ret_tipo(Izq.resultado.toString());
        String tipo_der = ret_tipo(Der.resultado.toString());
        boolean relizar = ret_compatible(tipo_izq, tipo_der, tipo);
        if (relizar) {
            if (accion_posible(Izq.tipo) || accion_posible(Der.tipo)) {
                        NodoError error1 = new NodoError("semantico");
                    error1.descripcion = "no se puede realizar operaciones con ese tipo de archivos";
                    error1.linea = String.valueOf(linea);
                    error1.columna = String.valueOf(columna);
                    Menu.Lista.get(num).errores.add(error1);
                return new NodoRespuesta(true);
            }
            return realizar_OP(Izq.resultado.toString(), tipo_izq, tipo, Der.resultado.toString(), tipo_der);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "erro tipos incompatibles no se puede: " + tipo + " con: " + tipo_izq + " y " + tipo_der;
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            System.out.println("llego aqui");
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
        } else if (value.equalsIgnoreCase("undefined") || value.equalsIgnoreCase("nulo")) {
            return "undefined";
        } else {
            return "numero";
        }
    }

    private boolean accion_posible(String value) {
        switch (value.toLowerCase()) {
            case "vector":
                return true;
            case "objeto":
                return true;
            case "array":
                return true;
            case "ventana":
                return true;
            case "contenedor":
                return true;
            case "boton":
                return true;
            default:
                return false;
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
        if (tipo_izq.equalsIgnoreCase("undefined") || tipo_der.equalsIgnoreCase("undefined")) {
            return false;
        } else if (tipo_izq.equalsIgnoreCase("cadena") || tipo_der.equalsIgnoreCase("cadena")) {
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
        if (tipo_izq.equalsIgnoreCase("undefined") || tipo_der.equalsIgnoreCase("undefined")) {
            return false;
        } else if (tipo_izq.equalsIgnoreCase("cadena") || tipo_der.equalsIgnoreCase("cadena")) {
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
                nuevo.tipo = "variable";
                return nuevo;
            case "-":
                nuevo = new NodoRespuesta(realizar_resta(izq, tipo_i, der, tipo_d));
                nuevo.tipo = "variable";
                return nuevo;
            case "*":
                nuevo = new NodoRespuesta(realizar_mul(izq, tipo_i, der, tipo_d));
                nuevo.tipo = "variable";
                return nuevo;
            case "/":
                nuevo = new NodoRespuesta(realizar_div(izq, tipo_i, der, tipo_d));
                nuevo.tipo = "variable";
                return nuevo;
            case "^":
                nuevo = new NodoRespuesta(realizar_pot(izq, tipo_i, der, tipo_d));
                nuevo.tipo = "variable";
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

    public NodoRespuesta sumar_uno(NodoRespuesta dato, TablaSimbolos tabla, int linea, int columna) {
        //System.out.println(dato.dato + dato.error + dato.resultado);
        String tipo = ret_tipo(dato.resultado.toString());
        if (tipo.equalsIgnoreCase("decimal") || tipo.equalsIgnoreCase("numero")) {
            String resultado = realizar_suma(dato.resultado.toString(), tipo, "1", "numero");
            Fs_varios fs = new Fs_varios();
            fs.set_Nuevoval_ID(resultado, dato.dato, tabla);
            return new NodoRespuesta(false);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "no se puede autoincrementar la variable: " + dato.dato + " no es decimal o entero ";
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            Menu.Lista.get(num).errores.add(error);
            NodoRespuesta uno = new NodoRespuesta(true);
            return uno;
        }
    }

    public NodoRespuesta restar_uno(NodoRespuesta dato, TablaSimbolos tabla, int linea, int columna) {
        String tipo = ret_tipo(dato.resultado.toString());
        if (tipo.equalsIgnoreCase("decimal") || tipo.equalsIgnoreCase("numero")) {
            String resultado = realizar_resta(dato.resultado.toString(), tipo, "1", "numero");
            Fs_varios fs = new Fs_varios();
            fs.set_Nuevoval_ID(resultado, dato.dato, tabla);
            return new NodoRespuesta(false);
        } else {
            NodoError error = new NodoError("semantico");
            error.descripcion = "no se puede autodecrementar la variable: " + dato.dato + " no es decimal o entero ";
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            Menu.Lista.get(num).errores.add(error);
            NodoRespuesta uno = new NodoRespuesta(true);
            return uno;
        }
    }

    public NodoRespuesta negar_dato(NodoFs raiz, ArrayList<NodoError> errores) {
        Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
        NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
        // System.out.println(uno.resultado);
        if (!uno.error) {
            String tipo = ret_tipo(uno.resultado.toString());
            if (tipo.equalsIgnoreCase("decimal") || tipo.equalsIgnoreCase("numero")) {
                String resultado;
                if (uno.resultado.toString().startsWith("-")) {
                    resultado = uno.resultado.toString().replace("-", "+");
                } else {
                    resultado = "-" + uno.resultado.toString();
                }
                NodoRespuesta ret = new NodoRespuesta(resultado);
                ret.tipo = "operacion";
                return ret;
            } else {
                NodoError error = new NodoError("semantico");
                error.descripcion = "la operacion no se puede negar";
                error.linea = String.valueOf(raiz.linea);
                error.columna = String.valueOf(raiz.columna);
                errores.add(error);
            }
        }
        return new NodoRespuesta(true);
    }

    public NodoRespuesta sumar_xdato(NodoRespuesta Dato1, NodoRespuesta Dato2, int linea, int columna) {
        String tipodato1 = ret_tipo(Dato1.resultado.toString());
        String tipodato2 = ret_tipo(Dato2.resultado.toString());
        boolean compatible = ret_compatible(tipodato1, tipodato2, "+");
        if (!compatible) {
            NodoError error = new NodoError("semantico");
            error.descripcion = "la operacion no se puede sumar con tipo:" + tipodato1 + " y " + tipodato2;
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            Menu.Lista.get(num).errores.add(error);
            return new NodoRespuesta(true);
        } else {
            String resultado = realizar_suma(Dato1.resultado.toString(), tipodato1, Dato2.resultado.toString(), tipodato2);
            String tipo = "variable";
            String id = Dato1.dato;
            NodoRespuesta nuevo = new NodoRespuesta(false);
            nuevo.tipo = tipo;
            nuevo.dato = id;
            nuevo.resultado = resultado;
            return nuevo;
        }
    }

    public NodoRespuesta restar_xdato(NodoRespuesta Dato1, NodoRespuesta Dato2, int linea, int columna) {
        String tipodato1 = ret_tipo(Dato1.resultado.toString());
        String tipodato2 = ret_tipo(Dato2.resultado.toString());
        boolean compatible = ret_compatible(tipodato1, tipodato2, "-");
        if (!compatible) {
            NodoError error = new NodoError("semantico");
            error.descripcion = "la operacion no se puede resta con tipo:" + tipodato1 + " y " + tipodato2;
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            Menu.Lista.get(num).errores.add(error);
            return new NodoRespuesta(true);
        } else {
            String resultado = realizar_resta(Dato1.resultado.toString(), tipodato1, Dato2.resultado.toString(), tipodato2);
            String tipo = "variable";
            String id = Dato1.dato;
            NodoRespuesta nuevo = new NodoRespuesta(false);
            nuevo.tipo = tipo;
            nuevo.dato = id;
            nuevo.resultado = resultado;
            return nuevo;
        }
    }

    public NodoRespuesta multi_xdato(NodoRespuesta Dato1, NodoRespuesta Dato2, int linea, int columna) {
        String tipodato1 = ret_tipo(Dato1.resultado.toString());
        String tipodato2 = ret_tipo(Dato2.resultado.toString());
        boolean compatible = ret_compatible(tipodato1, tipodato2, "*");
        if (!compatible) {
            NodoError error = new NodoError("semantico");
            error.descripcion = "la operacion no se puede multiplicar con tipo:" + tipodato1 + " y " + tipodato2;
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            Menu.Lista.get(num).errores.add(error);
            return new NodoRespuesta(true);
        } else {
            String resultado = realizar_mul(Dato1.resultado.toString(), tipodato1, Dato2.resultado.toString(), tipodato2);
            String tipo = "variable";
            String id = Dato1.dato;
            NodoRespuesta nuevo = new NodoRespuesta(false);
            nuevo.tipo = tipo;
            nuevo.dato = id;
            nuevo.resultado = resultado;
            return nuevo;
        }
    }

    public NodoRespuesta divi_xdato(NodoRespuesta Dato1, NodoRespuesta Dato2, int linea, int columna) {
        String tipodato1 = ret_tipo(Dato1.resultado.toString());
        String tipodato2 = ret_tipo(Dato2.resultado.toString());
        boolean compatible = ret_compatible(tipodato1, tipodato2, "/");
        if (!compatible) {
            NodoError error = new NodoError("semantico");
            error.descripcion = "la operacion no se puede dividir con tipo:" + tipodato1 + " y " + tipodato2;
            error.linea = String.valueOf(linea);
            error.columna = String.valueOf(columna);
            Menu.Lista.get(num).errores.add(error);
            return new NodoRespuesta(true);
        } else {
            String resultado = realizar_div(Dato1.resultado.toString(), tipodato1, Dato2.resultado.toString(), tipodato2);
            String tipo = "variable";
            String id = Dato1.dato;
            NodoRespuesta nuevo = new NodoRespuesta(false);
            nuevo.tipo = tipo;
            nuevo.dato = id;
            nuevo.resultado = resultado;
            return nuevo;
        }
    }
}
