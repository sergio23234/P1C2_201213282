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
public class OPA_L {

    TablaSimbolos tabla;
    TablaSimbolos global;

    public OPA_L(TablaSimbolos tabla, TablaSimbolos global) {
        this.tabla = tabla;
        this.global = global;
    }

    public NodoRespuesta Analizar_OPL(NodoFs raiz, ArrayList<NodoError> errores) {
        if(raiz.hijos.size()==2){
           NodoRespuesta uno = Cuerpo_L(raiz.hijos.get(0), errores);
            NodoRespuesta dos = Cuerpo_L(raiz.hijos.get(1), errores);
        if (uno.error || dos.error) {
            NodoRespuesta error = new NodoRespuesta(true);
            return error;
        }
        NodoRespuesta resp = Accion_L(uno, raiz.valor, dos, errores);
        return resp; 
        }else if(raiz.hijos.size()==1){
            NodoRespuesta uno = Cuerpo_L(raiz.hijos.get(0), errores);
            String respuesta = realizar_NOT(uno.resultado.toString());
            NodoRespuesta result = new NodoRespuesta(respuesta);
            return result;
        }
        return new NodoRespuesta(true);
    }

    private NodoRespuesta Cuerpo_L(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "ope_l":
                break;
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

    private NodoRespuesta Accion_L(NodoRespuesta Izq, String tipo, NodoRespuesta Der, ArrayList<NodoError> errores) {
        return realizar_OP(Izq.resultado.toString(), tipo, Der.resultado.toString());

    }

    private NodoRespuesta realizar_OP(String izq, String tipo, String der) {
        NodoRespuesta nuevo;
        switch (tipo) {
            case "OR":
                nuevo = new NodoRespuesta(realizar_OR(izq, der));
                return nuevo;
            case "AND":
                nuevo = new NodoRespuesta(realizar_AND(izq, der));
                return nuevo;
        }
        nuevo = new NodoRespuesta(true);
        return nuevo;
    }

    private String realizar_OR(String izq, String der) {
        if (izq.equalsIgnoreCase("verdadero") || der.equalsIgnoreCase("verdadero")) {
            return "verdadero";
        }
        return "falso";
    }

    private String realizar_AND(String izq, String der) {
        if (izq.equalsIgnoreCase("verdadero") && der.equalsIgnoreCase("verdadero")) {
            return "verdadero";
        }
        return "falso";
    }

    private String realizar_NOT(String izq) {
        if (izq.equalsIgnoreCase("verdadero")) {
            return "falso";
        }
        return "verdadero";

    }

}
