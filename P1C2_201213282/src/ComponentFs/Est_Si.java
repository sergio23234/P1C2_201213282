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
public class Est_Si {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Est_Si(TablaSimbolos tabla, TablaSimbolos global,int num) {
        TablaSimbolos nueva = new TablaSimbolos("Si");
        nueva.padre = tabla;
        this.tabla = nueva;
        this.num = num;
        this.global = global;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        for (int i = 0; i < raiz.hijos.size(); i++) {
            NodoFs actual = raiz.hijos.get(i);
            if (actual.Tipo.equalsIgnoreCase("si")) {
                System.out.println(actual.hijos.size());
                System.out.println(actual.hijos.get(0).Tipo);
                NodoRespuesta condicion = Analizar_Condicion(actual.hijos.get(0),errores);
                if(!condicion.error){
                    System.out.println(condicion.resultado);
                }
            } else {

            }
        }
        return null;
    }

    private void Analizar_Cuerpo(NodoFs raiz, ArrayList<NodoError> errores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private NodoRespuesta Analizar_Condicion(NodoFs raiz, ArrayList<NodoError> errores) {
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
}
