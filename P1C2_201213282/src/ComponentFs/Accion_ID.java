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
public class Accion_ID {

    TablaSimbolos tabla;
    TablaSimbolos global;
    int num;

    public Accion_ID(TablaSimbolos tabla, TablaSimbolos global, int num) {
        this.tabla = tabla;
        this.global = global;
        this.num = num;
    }

    public NodoRespuesta Analizar(NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta id = ID_analisis(raiz.hijos.get(0), errores);
        return Cuerpo_Accion(id, raiz.hijos.get(1), errores);
    }

    private NodoRespuesta Cuerpo_Accion(NodoRespuesta id, NodoFs raiz, ArrayList<NodoError> errores) {
        NodoRespuesta nuevo;
        switch (raiz.Tipo.toLowerCase()) {
            case "funciones":
                NodoFs prueba = new NodoFs("Nativas");
                prueba.valor = id.resultado.toString();
                prueba.add_NodoFs(raiz);
                Nativas nat = new Nativas(tabla, global, num);
                nuevo = nat.Analizar(prueba, errores);
                if (nuevo.error) {
                    return new NodoRespuesta(true);
                }
                break;
            case "cargar":
                if (raiz.hijos.size() > 0) {
                    Menu.Lista.get(num).add_cargar_Fs(raiz.hijos.get(0), id.resultado.toString(), 0);
                } else {
                    System.out.println("llego a cargar: "+id.resultado.toString());
                    Menu.Lista.get(num).mostrar_ventana(id.resultado.toString());
                }
                break;
            case "cerrar":
                if (raiz.hijos.size() > 0) {
                    Menu.Lista.get(num).add_cargar_Fs(raiz.hijos.get(0), id.resultado.toString(), 1);
                } else {
                    Menu.Lista.get(num).mostrar_ventana(id.resultado.toString());
                }
                break;
            case "click":
                boolean resultado = Menu.Lista.get(num).add_FS_boton(id.resultado.toString(), raiz.hijos.get(0));
                //System.out.println(resultado + " si lo añadio " + id.resultado.toString());
                break;
            case "label":
                Componentes_FS fs = new Componentes_FS(tabla, global, num);
                fs.Analizar_Label(raiz, errores, id);
                break;
            case "texto":
                Componentes_FS field = new Componentes_FS(tabla, global, num);
                field.Analizar_Texto(raiz, errores, id);
                break;
            case "area":
                Componentes_FS area = new Componentes_FS(tabla, global, num);
                area.Analizar_Area(raiz, errores, id);
                break;
            case "desplegable":
                area = new Componentes_FS(tabla, global, num);
                area.Analizar_Combo(raiz, errores, id);
                break;
            case "reproductor":
                area = new Componentes_FS(tabla, global, num);
                area.Analizar_IMV(raiz, errores, id, 1);
                break;
            case "imagen":
                area = new Componentes_FS(tabla, global, num);
                area.Analizar_Imagen(raiz, errores, id);
                break;   case "numero":
                area = new Componentes_FS(tabla, global, num);
                area.Analizar_Numero(raiz, errores, id);
                break;
        }
        return new NodoRespuesta(false);
    }

    private NodoRespuesta ID_analisis(NodoFs raiz, ArrayList<NodoError> errores) {
        String base = raiz.valor;
        if (raiz.lista.size() > 0) {
            if (raiz.hijos.size() > 0) {
                Cuerpo_op OP = new Cuerpo_op(tabla, global, num);
                NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
                String dos = raiz.lista.get(1);
                if (uno.error) {
                    return new NodoRespuesta(true);
                } else if (raiz.lista.get(0).equalsIgnoreCase("objeto")) {
                    base = base + dos + "[" + uno.resultado.toString() + "]";
                } else {
                    base = base + "[" + uno.resultado.toString() + "]" + dos;
                }
            } else {
                base = base + raiz.lista.get(0);
            }
        } else if (raiz.hijos.size() > 0) {
            Cuerpo_op OP = new Cuerpo_op(tabla, global, num);

            NodoRespuesta uno = OP.Cuerpo_G(raiz.hijos.get(0), errores);
            if (uno.error) {
                return new NodoRespuesta(true);
            }
            base = base + uno.resultado.toString();
        }
        NodoRespuesta retorno = new NodoRespuesta(base);
        return retorno;
    }
}
