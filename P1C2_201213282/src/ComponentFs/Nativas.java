/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentFs;

import ComponentGxml.NodoGxml;
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

    private NodoRespuesta Analizarv2(NodoFs raiz, ArrayList<NodoError> errores) {
        Fs_varios varios = new Fs_varios();
        boolean existe = varios.ret_Existencia_ID(raiz.valor, tabla);
        //System.out.println("el ID es:" + raiz.valor + " y si existe:" + existe);
        if (existe) {
            NodoRespuesta resultadoid = varios.ret_ID_Tabla(raiz.valor, tabla);
            //System.out.println(resultadoid.tipo + " es estetipo");
            if (resultadoid.tipo.equalsIgnoreCase("vector")) {
                return Analizarp2(raiz.valor, raiz.hijos.get(0), errores, resultadoid,raiz.linea,raiz.columna);
            } else if (resultadoid.tipo.equalsIgnoreCase("array")) {
                //System.out.println("es vector de objetos");
                return Analizarp2_A(raiz.hijos.get(0), errores, resultadoid);
            } else if (resultadoid.tipo.equalsIgnoreCase("arrayespecial")) {
                //System.out.println("es vector de objetos");
                return Analizarp2_ES(raiz.hijos.get(0), errores, resultadoid);
            } else {
                return new NodoRespuesta(true);
            }
        } else {
            existe = varios.ret_Existencia_ID(raiz.valor, global);
            if (existe) {
                NodoRespuesta resultadoid = varios.ret_ID_Tabla(raiz.valor, global);
                //System.out.println(resultadoid.tipo + " es estetipo");
                if (resultadoid.tipo.equalsIgnoreCase("vector")) {
                    return Analizarp2(raiz.valor, raiz.hijos.get(0), errores, resultadoid,raiz.linea,raiz.columna);
                } else if (resultadoid.tipo.equalsIgnoreCase("array")) {
                    //System.out.println("es vector de objetos");
                    return Analizarp2_A(raiz.hijos.get(0), errores, resultadoid);
                } else if (resultadoid.tipo.equalsIgnoreCase("arrayespecial")) {
                    //System.out.println("es vector de objetos");
                    return Analizarp2_ES(raiz.hijos.get(0), errores, resultadoid);
                } else {
                    return new NodoRespuesta(true);
                }
            }
            return new NodoRespuesta(true);
        }
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        Fs_varios varios = new Fs_varios();
        boolean existe = varios.ret_Existencia_ID(raiz.valor, tabla);
        if (existe) {//existe 
            NodoRespuesta resultadoid = varios.ret_ID_Tabla(raiz.valor, tabla);
            NodoFs actual1 = raiz.hijos.get(0);
            NodoRespuesta result = resultadoid;
            for (int i = 0; i < actual1.hijos.size(); i++) {
                NodoFs actual = actual1.hijos.get(i);
                if (result.tipo.equalsIgnoreCase("vector")) {
                    result = Analizarp2(raiz.valor, actual, errores, result,actual.linea,actual.columna);
                } else if (result.tipo.equalsIgnoreCase("array")) {
                    result = Analizarp2_A(actual, errores, result);
                } else if (result.tipo.equalsIgnoreCase("arrayespecial")) {
                    result = Analizarp2_ES(actual, errores, result);
                } else {
                    return new NodoRespuesta(true);
                }
            }
            return result;
        } else {
            existe = varios.ret_Existencia_ID(raiz.valor, global);
            if (existe) {
                NodoRespuesta resultadoid = varios.ret_ID_Tabla(raiz.valor, global);
                NodoFs actual1 = raiz.hijos.get(0);
                NodoRespuesta result = resultadoid;
                for (int i = 0; i < actual1.hijos.size(); i++) {
                    NodoFs actual = actual1.hijos.get(i);
                    if (result.tipo.equalsIgnoreCase("vector")) {
                        result = Analizarp2(raiz.valor, actual, errores, result,actual.linea,actual.columna);
                    } else if (result.tipo.equalsIgnoreCase("array")) {
                        result = Analizarp2_A(actual, errores, result);
                    } else if (result.tipo.equalsIgnoreCase("arrayespecial")) {
                        result = Analizarp2_ES(actual, errores, result);
                    } else {
                        return new NodoRespuesta(true);
                    }
                }
                return result;
            }
            return new NodoRespuesta(true);
        }
    }

    private NodoRespuesta Analizarp2(String ID, NodoFs actual, ArrayList<NodoError> errores, NodoRespuesta vector,int linea,int columna) {
        NodoRespuesta result = vector;
        if (actual.Tipo.equalsIgnoreCase("ordenamiento")) {
            int tipo = ret_tipo(vector);
            if(tipo==-1)
            {
                 NodoError error = new NodoError("semantico");
                error.descripcion = "no se puede ordenar este vector: ->"+ID+"<-";
                error.linea = String.valueOf(linea);
                error.columna = String.valueOf(columna);
                errores.add(error);
                return new NodoRespuesta(true);
            
            }
            result = Ordenamiento(actual.valor, errores, result, tipo);
            Fs_varios varios = new Fs_varios();
            if (!(actual.valor.equalsIgnoreCase("maximo") || actual.valor.equalsIgnoreCase("minimo"))) {
                varios.set_Nuevoval_ID2(result, ID, tabla);
            }
        } else if (actual.Tipo.equalsIgnoreCase("filtros")) {
            llamada_fun ord_fun = new llamada_fun(global, num);
            result = ord_fun.analizar_nati(actual, errores, (ArrayList<String>) vector.resultado);
        }
        return result;
    }

    private NodoRespuesta Analizarp2_A(NodoFs actual, ArrayList<NodoError> errores, NodoRespuesta objetos) {
        NodoRespuesta result = objetos;
        if (actual.Tipo.equalsIgnoreCase("filtros")) {
            llamada_fun ord_fun = new llamada_fun(global, num);
            result = ord_fun.analizar_nati2(actual, errores, (ArrayList<NodoObjeto>) objetos.resultado);
        }
        //System.out.println("este fue el resultado: "+result.resultado.toString()+result.tipo);
        return result;
    }

    private NodoRespuesta Analizarp2_ES(NodoFs actual, ArrayList<NodoError> errores, NodoRespuesta objetos) {
        NodoRespuesta result = objetos;
        if (actual.Tipo.equalsIgnoreCase("obtencion")) {
            llamada_fun ord_fun = new llamada_fun(global, num);
            result = ord_fun.analizar_nati3(actual, errores, (NodoGxml) objetos.resultado);
        }
        //System.out.println("este fue el resultado: "+result.resultado.toString()+result.tipo);
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
        } else if (num && !cad && dec && !bol) {
            return 3;
        } else if (!num && !cad && !dec && bol) {
            return 4;
        }
        return -1;
    }

}
