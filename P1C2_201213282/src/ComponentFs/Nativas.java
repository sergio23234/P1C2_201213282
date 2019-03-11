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
 * @author Sergio Fernando
 */
public class Nativas {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Nativas(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        Fs_varios varios = new Fs_varios();
        boolean existe = varios.ret_Existencia_ID(raiz.valor, tabla);
        if (existe) {
            NodoRespuesta resultadoid = varios.ret_ID_Tabla(raiz.valor, tabla);
            //System.out.println(resultadoid.tipo + " es estetipo");
            if (resultadoid.tipo.equalsIgnoreCase("vector")) {
                return Analizarp2(raiz.hijos.get(0), errores, resultadoid);
            } else {
                return new NodoRespuesta(true);
            }
        } else {
            existe = varios.ret_Existencia_ID(raiz.valor, global);
            if (existe) {
                NodoRespuesta resultadoid = varios.ret_ID_Tabla(raiz.valor, global);
                // System.out.println(resultadoid.tipo + " es estetipo");
                if (resultadoid.tipo.equalsIgnoreCase("vector")) {
                    return Analizarp2(raiz.hijos.get(0), errores, resultadoid);
                } else {
                    return new NodoRespuesta(true);
                }
            }
            return new NodoRespuesta(true);
        }
    }

    private NodoRespuesta Analizarp2(NodoFs raiz, ArrayList<NodoError> errores, NodoRespuesta vector) {
        NodoRespuesta result = vector;
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoFs actual = raiz.hijos.get(i);
            if (actual.Tipo.equalsIgnoreCase("ordenamiento")) {
                int tipo = ret_tipo(vector);
                result = Ordenamiento(actual.valor, errores, result, tipo);
            } else if (actual.Tipo.equalsIgnoreCase("filtros")) {
                llamada_fun ord_fun = new llamada_fun(global, num);
                return ord_fun.analizar_nati(actual, errores, (ArrayList<String>) vector.resultado);
            }
        }
        return result;
    }

    private NodoRespuesta Ordenamiento(String tipo, ArrayList<NodoError> errores, NodoRespuesta vector, int tipop) {
        switch (tipo.toLowerCase()) {
            case "invertir":
                return invertir(vector);
            case "descendente":
                return descendente(vector, tipop);
            case "ascendente":
                return ascendente(vector, tipop);
            case "minimo":
                return max_min(ascendente(vector, tipop), 0);
            case "maximo":
                return max_min(ascendente(vector, tipop), 1);
        }
        return new NodoRespuesta(true);
    }

    private NodoRespuesta invertir(NodoRespuesta vector) {
        ArrayList<String> nuevalista = new ArrayList();
        ArrayList<String> actual = (ArrayList<String>) vector.resultado;
        for (int i = actual.size() - 1; i >= 0; i--) {
            nuevalista.add(actual.get(i));
        }
        NodoRespuesta nuevo = new NodoRespuesta(nuevalista);
        nuevo.tipo = "vector";
        return nuevo;
    }

    private NodoRespuesta ascendente(NodoRespuesta vector, int tipo) {
        ArrayList<String> actual = (ArrayList<String>) vector.resultado;
        Object[] Svector = actual.toArray();
        switch (tipo) {
            case 2:
            case 4:
                for (int i = 0; i < Svector.length; i++) {
                    for (int j = i + 1; j < Svector.length; j++) {
                        String uno = Svector[i].toString();
                        String dos = Svector[j].toString();
                        if (uno.compareToIgnoreCase(dos) > 0) {
                            Object tres = Svector[j];
                            Svector[j] = Svector[i];
                            Svector[i] = tres;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < Svector.length; i++) {
                    for (int j = i + 1; j < Svector.length; j++) {
                        int uno = Integer.valueOf(Svector[i].toString());
                        int dos = Integer.valueOf(Svector[j].toString());
                        if (uno > dos) {
                            Object tres = Svector[j];
                            Svector[j] = Svector[i];
                            Svector[i] = tres;
                        }
                    }
                }
                break;
            default:
                for (int i = 0; i < Svector.length; i++) {
                    for (int j = i + 1; j < Svector.length; j++) {
                        Double uno = Double.valueOf(Svector[i].toString());
                        Double dos = Double.valueOf(Svector[j].toString());
                        if (uno > dos) {
                            Object tres = Svector[j];
                            Svector[j] = Svector[i];
                            Svector[i] = tres;
                        }
                    }
                }
                break;
        }
        ArrayList<String> nuevalista = new ArrayList();
        for (int i = 0; i < Svector.length; i++) {
            nuevalista.add(Svector[i].toString());
        }
        NodoRespuesta nuevo = new NodoRespuesta(nuevalista);
        nuevo.tipo = "vector";
        return nuevo;
    }

    private NodoRespuesta descendente(NodoRespuesta vector, int tipo) {
        ArrayList<String> actual = (ArrayList<String>) vector.resultado;
        Object[] Svector = actual.toArray();
        switch (tipo) {
            case 2:
            case 4:
                for (int i = 0; i < Svector.length; i++) {
                    for (int j = i + 1; j < Svector.length; j++) {
                        String uno = Svector[i].toString();
                        String dos = Svector[j].toString();
                        if (uno.compareToIgnoreCase(dos) < 0) {
                            Object tres = Svector[j];
                            Svector[j] = Svector[i];
                            Svector[i] = tres;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < Svector.length; i++) {
                    for (int j = i + 1; j < Svector.length; j++) {
                        int uno = Integer.valueOf(Svector[i].toString());
                        int dos = Integer.valueOf(Svector[j].toString());
                        if (uno < dos) {
                            Object tres = Svector[j];
                            Svector[j] = Svector[i];
                            Svector[i] = tres;
                        }
                    }
                }
                break;
            default:
                for (int i = 0; i < Svector.length; i++) {
                    for (int j = i + 1; j < Svector.length; j++) {
                        Double uno = Double.valueOf(Svector[i].toString());
                        Double dos = Double.valueOf(Svector[j].toString());
                        if (uno < dos) {
                            Object tres = Svector[j];
                            Svector[j] = Svector[i];
                            Svector[i] = tres;
                        }
                    }
                }
                break;
        }
        ArrayList<String> nuevalista = new ArrayList();
        for (int i = 0; i < Svector.length; i++) {
            nuevalista.add(Svector[i].toString());
        }
        NodoRespuesta nuevo = new NodoRespuesta(nuevalista);
        nuevo.tipo = "vector";
        return nuevo;
    }

    private NodoRespuesta max_min(NodoRespuesta vector, int tipo) {
        ArrayList<String> actual = (ArrayList<String>) vector.resultado;
        NodoRespuesta ret;
        if (tipo == 0) {
            ret = new NodoRespuesta(actual.get(0));
            ret.tipo = "variable";
            return ret;
        } else {
            ret = new NodoRespuesta(actual.get(actual.size() - 1));
            ret.tipo = "variable";
            return ret;
        }
    }

    private int ret_tipo(NodoRespuesta vector) {
        ArrayList<String> actual = (ArrayList<String>) vector.resultado;
        boolean num = false;
        boolean cad = false;
        boolean dec = false;
        boolean bol = false;
        for (int i = 0; i < actual.size(); i++) {
            String dato = actual.get(i);
            if (dato.contains("\"")) {
                cad = true;
            } else if (dato.contains(".")) {
                dec = true;
            } else if (dato.equalsIgnoreCase("verdadero") || dato.equalsIgnoreCase("falso")) {
                return -1;
            } else {
                num = true;
            }
        }
        if (num && !cad && !dec && !bol) {
            return 1;
        } else if (!num && cad && !dec && !bol) {
            return 2;
        } else if (!num && !cad && dec && !bol) {
            return 3;
        }else if (num && !cad && dec && !bol) {
            return 3;
        } else if (!num && !cad && !dec && bol) {
            return 4;
        }
        return -1;
    }
}
