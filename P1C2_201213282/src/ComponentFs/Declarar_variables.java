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

    public Declarar_variables(TablaSimbolos tabla, TablaSimbolos global) {
        this.tabla = tabla;
        this.global = global;
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
                Ana_vect(raiz, errores, raices);
                break;
            case "objetos":
                break;
            case "ventana":
                break;
            case "boton":
                break;
            case "contenedor":
                break;
            case "nada":
                break;
            case "ope_l":
                OPA_L operal = new OPA_L(tabla, global);
                nuevo = operal.Analizar_OPL(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "ope_c":
                OPA_C operac = new OPA_C(tabla, global);
                nuevo = operac.Analizar_OPC(raiz, errores);
                if (!nuevo.error) {
                    Add_var_Tabla_variable(raices, nuevo);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "ope_a":
                OPA_A operacon = new OPA_A(tabla, global);
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
                nuevo = new NodoRespuesta("-" + raiz.valor);
                return nuevo;
            case "autoincremento":
                ES_ID retorno = new ES_ID(tabla, global);
                retorn = retorno.autoincrementar(raiz, errores);
                if (!retorn.error) {
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "autodecremento":
                retorno = new ES_ID(tabla, global);
                retorn = retorno.autodecrementar(raiz, errores);
                if (!retorn.error) {
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);
            case "nativas":
                break;
            case "llamadafun":
                break;
            case "id":
                ES_ID id = new ES_ID(tabla, global);
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
        } else {
            nodo.valor = respuesta.resultado;
        }
        tabla.Tabla.add(nodo);
    }

    private NodoRespuesta Ana_vect(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        ArrayList<String> valores = new ArrayList();
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoRespuesta actual = cuerpo_Vect(raiz.hijos.get(i), errores);
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

    private NodoRespuesta cuerpo_Vect(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "ope_l":
                break;
            case "ope_c":
                break;
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
        return new NodoRespuesta(true);
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
}
