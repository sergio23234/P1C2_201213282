
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analizadores;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import ComponentFs.*;
import Principal.NodoError;
import java_cup.runtime.*;
import java.util.ArrayList;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class SintacticoGD extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return symd.class;
}

  /** Default constructor. */
  @Deprecated
  public SintacticoGD() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public SintacticoGD(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public SintacticoGD(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\017\000\002\002\004\000\002\004\005\000\002\002" +
    "\004\000\002\002\003\000\002\003\005\000\002\005\004" +
    "\000\002\005\003\000\002\007\012\000\002\006\003\000" +
    "\002\006\003\000\002\006\003\000\002\006\004\000\002" +
    "\006\004\000\002\006\003\000\002\006\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\035\000\004\004\005\001\002\000\004\002\037\001" +
    "\002\000\004\006\007\001\002\000\006\005\035\006\007" +
    "\001\002\000\004\012\012\001\002\000\006\005\ufffe\006" +
    "\ufffe\001\002\000\006\007\ufffb\012\ufffb\001\002\000\004" +
    "\015\016\001\002\000\006\007\015\012\012\001\002\000" +
    "\006\007\ufffc\012\ufffc\001\002\000\006\005\ufffd\006\ufffd" +
    "\001\002\000\004\011\017\001\002\000\016\012\024\013" +
    "\025\014\020\016\023\017\021\020\026\001\002\000\004" +
    "\012\ufff3\001\002\000\004\012\ufff8\001\002\000\004\012" +
    "\031\001\002\000\004\012\ufff7\001\002\000\006\016\030" +
    "\017\027\001\002\000\004\012\ufff4\001\002\000\004\012" +
    "\ufff9\001\002\000\004\012\ufff6\001\002\000\004\012\ufff5" +
    "\001\002\000\004\010\032\001\002\000\004\015\033\001" +
    "\002\000\004\011\034\001\002\000\006\007\ufffa\012\ufffa" +
    "\001\002\000\004\002\000\001\002\000\006\005\uffff\006" +
    "\uffff\001\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\035\000\004\004\003\001\001\000\002\001\001\000" +
    "\006\002\005\003\007\001\001\000\004\003\035\001\001" +
    "\000\006\005\012\007\010\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\007\013\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\006\021\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$SintacticoGD$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$SintacticoGD$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$SintacticoGD$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    protected int error_sync_size () {
       return 1;   
    }

    public void syntax_error(Symbol s){
        System.out.println("Error sintactico lexema \"" + s.value+"\" en la Columna: "+(s.right+1) +", Linea: "+(s.left+1)+ " no esperado"+s.sym);
		NodoError Nerror = new NodoError("sintactico");
		Nerror.descripcion = "caracter no reconocido: "+ s.value;
		Nerror.linea = String.valueOf(s.left+1);
		Nerror.columna = String.valueOf(s.right+1);
		errores.add(Nerror);
    }

    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{
        System.err.println("Error sintactico \"" + s.value+"\" en la Linea: "+(s.right+1) +", Columna: "+(s.left+1)+ "");
        /*Nodo_error nuevo = new Nodo_error();
        nuevo.dato = String.valueOf(s.value);
        nuevo.linea = (s.left+1);
        nuevo.columna=(s.right);
        Errores.add(nuevo);
        errores=true;*/
    }
	 public ArrayList<NodoError> errores = new ArrayList();
	  public ArrayList<NodoObjeto> datos = new ArrayList();
	 

/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$SintacticoGD$actions {


    public String estado="";

  private final SintacticoGD parser;

  /** Constructor */
  CUP$SintacticoGD$actions(SintacticoGD parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$SintacticoGD$do_action_part00000000(
    int                        CUP$SintacticoGD$act_num,
    java_cup.runtime.lr_parser CUP$SintacticoGD$parser,
    java.util.Stack            CUP$SintacticoGD$stack,
    int                        CUP$SintacticoGD$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$SintacticoGD$result;

      /* select the action based on the action number */
      switch (CUP$SintacticoGD$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Start EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).value;
		RESULT = start_val;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$SintacticoGD$parser.done_parsing();
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Start ::= ili ini fli 
            {
              Object RESULT =null;

              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Start",2, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-2)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // ini ::= ini Est_Pri 
            {
              Object RESULT =null;

              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("ini",0, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // ini ::= Est_Pri 
            {
              Object RESULT =null;

              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("ini",0, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Est_Pri ::= ipr Cuerpo_pri fpr 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).right;
		NodoObjeto e1 = (NodoObjeto)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).value;
		datos.add(e1);
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Est_Pri",1, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-2)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Cuerpo_pri ::= Cuerpo_pri Est_ID 
            {
              NodoObjeto RESULT =null;
		int e2left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).right;
		NodoObjeto e2 = (NodoObjeto)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)).value;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		Raiz e1 = (Raiz)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		e2.objetos.add(e1); RESULT=e2;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Cuerpo_pri",3, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Cuerpo_pri ::= Est_ID 
            {
              NodoObjeto RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		Raiz e1 = (Raiz)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		NodoObjeto nuevo = new NodoObjeto(); nuevo.objetos.add(e1); RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Cuerpo_pri",3, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Est_ID ::= men idva may Dato_pri men dia idva may 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-6)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-6)).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-6)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-4)).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-4)).right;
		Raiz e2 = (Raiz)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-4)).value;
		e2.nombre=e1; RESULT=e2;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Est_ID",5, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-7)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Dato_pri ::= cade 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz(e1);	RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Dato_pri ::= nume 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz(e1);	RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Dato_pri ::= deci 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz(e1);	RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Dato_pri ::= men nume 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz("-"+e1);RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Dato_pri ::= men deci 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz("-"+e1);RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.elementAt(CUP$SintacticoGD$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Dato_pri ::= verd 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz(e1);	RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Dato_pri ::= fals 
            {
              Raiz RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()).right;
		String e1 = (String)((java_cup.runtime.Symbol) CUP$SintacticoGD$stack.peek()).value;
		Raiz nuevo = new Raiz(e1);	RESULT=nuevo;
              CUP$SintacticoGD$result = parser.getSymbolFactory().newSymbol("Dato_pri",4, ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), ((java_cup.runtime.Symbol)CUP$SintacticoGD$stack.peek()), RESULT);
            }
          return CUP$SintacticoGD$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$SintacticoGD$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$SintacticoGD$do_action(
    int                        CUP$SintacticoGD$act_num,
    java_cup.runtime.lr_parser CUP$SintacticoGD$parser,
    java.util.Stack            CUP$SintacticoGD$stack,
    int                        CUP$SintacticoGD$top)
    throws java.lang.Exception
    {
              return CUP$SintacticoGD$do_action_part00000000(
                               CUP$SintacticoGD$act_num,
                               CUP$SintacticoGD$parser,
                               CUP$SintacticoGD$stack,
                               CUP$SintacticoGD$top);
    }
}

}
