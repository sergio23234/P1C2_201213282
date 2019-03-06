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
        NodoRespuesta respuestas = Cuerpo_Var(raiz.hijos.get(1), errores);
        boolean actualizar = Analizar_ID(raices, errores);
        if (actualizar) {
            Add_var_Tabla(raices, respuestas);
        }
        return respuestas;
    }

    public NodoRespuesta Cuerpo_Var(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "vector":
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
                return operacon.Analizar_OPA(raiz, errores);
            case "dato":
                System.out.println("entro");
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

    private void Add_var_Tabla(NodoFs raices, NodoRespuesta respuesta) {
        for (int i = 0; i < raices.lista.size() - 1; i++) {
            NodoTabla nodo = new NodoTabla("variable", raices.lista.get(i));
            tabla.Tabla.add(nodo);
        }
        NodoTabla nodo = new NodoTabla("variable", raices.lista.get(raices.lista.size() - 1));
        nodo.valor = respuesta.resultado;
        tabla.Tabla.add(nodo);
    }

}
