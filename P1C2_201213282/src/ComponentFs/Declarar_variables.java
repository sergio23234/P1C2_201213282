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
public class Declarar_variables {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Declarar_variables(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoFs raices = raiz.hijos.get(0);
        boolean actualizar = Analizar_ID(raices, errores);
        if (actualizar) {
            NodoRespuesta respuestas = Cuerpo_Var(raiz.hijos.get(1), errores, raices);
            return respuestas;

        }
        return new NodoRespuesta(true);
    }

    public NodoRespuesta Cuerpo_Var(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        NodoRespuesta nuevo;
        NodoRespuesta retorn;
        switch (raiz.Tipo.toLowerCase()) {
            case "vector":
                return Ana_vect(raiz, errores, raices);
            case "objetos":
                return Ana_objeto(raiz, errores, raices);

            case "ventana":
                break;
            case "boton":
                break;
            case "contenedor":
                break;
            case "nada":
                break;
            case "ope_l":
                OPA_L operal = new OPA_L(tabla, global, num);
                nuevo = operal.Analizar_OPL(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "ope_c":
                OPA_C operac = new OPA_C(tabla, global, num);
                nuevo = operac.Analizar_OPC(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "ope_a":
                OPA_A operacon = new OPA_A(tabla, global, num);
                nuevo = operacon.Analizar_OPA(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            case "dato":
                nuevo = new NodoRespuesta(raiz.valor);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            case "dato negado":
                operacon = new OPA_A(tabla, global, num);
                nuevo = operacon.negar_dato(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }

            case "autoincremento":
                ES_ID retorno = new ES_ID(tabla, global, num);
                retorn = retorno.autoincrementar(raiz, errores);
                if (!retorn.error) {
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "autodecremento":
                retorno = new ES_ID(tabla, global, num);
                retorn = retorno.autodecrementar(raiz, errores);
                if (!retorn.error) {
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            case "nativas":
                Nativas nat = new Nativas(tabla, global, num);
                retorn = nat.Analizar(raiz, errores);
                if (!retorn.error) {
                    // System.out.println("vino funcion" + retorn.tipo);
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "llamadafun":
                llamada_fun funcion = new llamada_fun(global, num);
                retorn = funcion.analizar(raiz, errores, tabla);
                if (!retorn.error) {
                    // System.out.println("vino funcion" + retorn.tipo);
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            case "id":
                ES_ID id = new ES_ID(tabla, global, num);
                nuevo = id.Analizar(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private boolean Analizar_ID(NodoFs raiz, ArrayList<NodoError> errores) {
        for (int i = 0; i < raiz.lista.size(); i++) {
            if (buscar_ID_Tabla(raiz.lista.get(i), errores)) {
                return false;
            }
        }
        return true;
    }

    private boolean buscar_ID_Tabla(String nombre, ArrayList<NodoError> errores) {
        for (int i = 0; i < tabla.Tabla.size(); i++) {
            NodoTabla actual = tabla.Tabla.get(i);
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                NodoError nuevo = new NodoError("sintactico");
                System.out.println("entro en este error");
                nuevo.descripcion = "la variable" + actual.nombre + " ya ha sido declarada";
                errores.add(nuevo);
                return true;
            }
        }
        return false;
    }

    private void Add_var_Tabla_variable(NodoFs raices, NodoRespuesta respuesta) {
        String tipos = "variable";
        if (respuesta.tipo.equalsIgnoreCase("vector")) {
            tipos = "vector";
        } else if (respuesta.tipo.equalsIgnoreCase("objeto")) {
            tipos = "objeto";
        }
        for (int i = 0; i < raices.lista.size() - 1; i++) {
            NodoTabla nodo = new NodoTabla(tipos, raices.lista.get(i));
            tabla.Tabla.add(nodo);
        }
        NodoTabla nodo = new NodoTabla(tipos, raices.lista.get(raices.lista.size() - 1));
        if (tipos.equalsIgnoreCase("vector")) {
            ArrayList<String> valores = (ArrayList<String>) respuesta.resultado;
            ArrayList<String> nuevo = new ArrayList();
            for (int i = 0; i < valores.size(); i++) {
                nuevo.add(valores.get(i));
            }
            nodo.valor = nuevo;
        } else if (tipos.equalsIgnoreCase("objeto")) {
            NodoObjeto valor = (NodoObjeto) respuesta.resultado;
            NodoObjeto nuevo = new NodoObjeto();
            valor.ret_bojetos(nuevo.objetos);
            nodo.valor = nuevo;
        } else {
            nodo.valor = respuesta.resultado;
        }
        tabla.Tabla.add(nodo);
    }

    private NodoRespuesta Ana_vect(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        ArrayList<String> valores = new ArrayList();
        for (int i = 0; i < raiz.hijos.size(); i++) {
            Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
            NodoRespuesta actual = OP.Cuerpo_G(raiz.hijos.get(i), errores);
            if (!actual.error) {
                String to_add = String.valueOf(actual.resultado);
                valores.add(to_add);
            } else {
                return new NodoRespuesta(true);
            }
        }
        Add_vect_Tabla(raices, valores);
        return new NodoRespuesta(false);
    }

    private void Add_vect_Tabla(NodoFs raices, ArrayList<String> respuestas) {
        for (int i = 0; i < raices.lista.size() - 1; i++) {
            NodoTabla nodo = new NodoTabla("variable", raices.lista.get(i));
            tabla.Tabla.add(nodo);
        }
        NodoTabla nodo = new NodoTabla("vector", raices.lista.get(raices.lista.size() - 1));
        nodo.valor = respuestas;
        tabla.Tabla.add(nodo);
    }

    private NodoRespuesta Ana_objeto(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        NodoObjeto nuevo = new NodoObjeto();
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoFs act = raiz.hijos.get(i);
            NodoFs val = act.hijos.get(0);
            Raiz to_add = new Raiz(act.Tipo, val.valor);
            nuevo.objetos.add(to_add);
        }
        Add_obj_Tabla(raices, nuevo);
        return new NodoRespuesta(false);
    }

    private void Add_obj_Tabla(NodoFs raices, NodoObjeto respuestas) {
        for (int i = 0; i < raices.lista.size() - 1; i++) {
            NodoTabla nodo = new NodoTabla("variable", raices.lista.get(i));
            tabla.Tabla.add(nodo);
        }
        NodoTabla nodo = new NodoTabla("objeto", raices.lista.get(raices.lista.size() - 1));
        nodo.valor = respuestas;
        tabla.Tabla.add(nodo);
    }
}
//  private NodoRespuesta cuerpo_Vect(NodoFs raiz, ArrayList<NodoError> errores) {
//        NodoRespuesta nuevo;
//        switch (raiz.Tipo.toLowerCase()) {
//            case "ope_l":
//                OPA_L operal = new OPA_L(tabla, global);
//                return operal.Analizar_OPL(raiz, errores);
//            case "ope_c":
//                OPA_C operac = new OPA_C(tabla, global);
//                return operac.Analizar_OPC(raiz, errores);
//            case "ope_a":
//                OPA_A operacon = new OPA_A(tabla, global);
//                return operacon.Analizar_OPA(raiz, errores);
//            case "dato":
//                nuevo = new NodoRespuesta(raiz.valor);
//                return nuevo;
//            case "dato negado":
//                operacon = new OPA_A(tabla, global);
//                return operacon.negar_dato(raiz, errores);
//                
//            case "autoincremento":
//                ES_ID retorno = new ES_ID(tabla, global);
//                return retorno.autoincrementar(raiz, errores);
//
//            case "autodecremento":
//                retorno = new ES_ID(tabla, global);
//                return retorno.autodecrementar(raiz, errores);
//            case "nativas":
//                break;
//            case "llamadafun":
//                break;
//            case "id":
//                ES_ID id = new ES_ID(tabla, global);
//                return id.Analizar(raiz, errores);
//        }
//        return new NodoRespuesta(true);
//    }
