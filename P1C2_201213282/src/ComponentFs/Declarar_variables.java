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

    public Declarar_variables(TablaSimbolos tabla) {
        this.tabla = tabla;
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
                break;
            case "ope_c":
                break;
            case "ope_a":
                OPA_A operacon = new OPA_A(tabla);
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
                ES_ID retorno = new ES_ID(tabla);
                retorn = retorno.autoincrementar(raiz, errores);
                if (!retorn.error) {
                    Add_var_Tabla_variable(raices, retorn);
                    return new NodoRespuesta(false);
                }
                return new NodoRespuesta(true);

            case "autodecremento":
                retorno = new ES_ID(tabla);
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
                ES_ID id = new ES_ID(tabla);
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
        for (int i = 0; i < raices.lista.size() - 1; i++) {
            NodoTabla nodo = new NodoTabla("variable", raices.lista.get(i));
            tabla.Tabla.add(nodo);
        }
        NodoTabla nodo = new NodoTabla("variable", raices.lista.get(raices.lista.size() - 1));
        nodo.valor = respuesta.resultado;
        tabla.Tabla.add(nodo);
    }

    private NodoRespuesta Ana_vect(NodoFs raiz, ArrayList<NodoError> errores, NodoFs raices) {
        ArrayList<String> valores = new ArrayList();
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoRespuesta actual = cuerpo_Vect(raiz.hijos.get(i), errores);
            if (!actual.error) {
                valores.add(actual.resultado);
            } else {
                return new NodoRespuesta(true);
            }
        }
        Add_vect_Tabla(raices,valores);
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
        return new NodoRespuesta(true);
    }

    private void Add_vect_Tabla(NodoFs raices, ArrayList<String> respuestas) {
        for (int i = 0; i < raices.lista.size() - 1; i++) {
            NodoTabla nodo = new NodoTabla("variable", raices.lista.get(i));
            tabla.Tabla.add(nodo);
        }
        NodoTabla nodo = new NodoTabla("vector", raices.lista.get(raices.lista.size() - 1));
        for (int i = 0; i < respuestas.size(); i++) {
            nodo.valores.add(respuestas.get(i));
        }
        tabla.Tabla.add(nodo);
    }
}
