/* The following code was generated by JFlex 1.7.0 */

package Analizadores;
import java_cup.runtime.*;
import java.io.Reader;
import Principal.NodoError;
import java.util.ArrayList;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>LexicoGD</tt>
 */
public class LexicoGD implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int Stado = 2;
  public static final int LLave = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  0,  0,  0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\24\5\0\16\24\6\0\1\26\1\0\1\23\11\0\1\25\1\11"+
    "\12\1\2\0\1\2\1\0\1\10\2\0\1\7\1\23\1\15\1\20"+
    "\1\17\1\22\2\23\1\4\2\23\1\3\1\23\1\14\1\21\1\12"+
    "\1\23\1\13\1\5\1\6\1\23\1\16\4\23\4\0\1\23\1\0"+
    "\1\7\1\23\1\15\1\20\1\17\1\22\2\23\1\4\2\23\1\3"+
    "\1\23\1\14\1\21\1\12\1\23\1\13\1\5\1\6\1\23\1\16"+
    "\4\23\4\0\41\24\2\0\4\23\4\0\1\23\2\0\1\24\7\0"+
    "\1\23\4\0\1\23\5\0\27\23\1\0\37\23\1\0\70\23\2\4"+
    "\115\23\1\5\u0142\23\4\0\14\23\16\0\5\23\7\0\1\23\1\0"+
    "\1\23\21\0\160\24\5\23\1\0\2\23\2\0\4\23\10\0\1\23"+
    "\1\0\3\23\1\0\1\23\1\0\24\23\1\0\123\23\1\0\213\23"+
    "\1\0\5\24\2\0\236\23\11\0\46\23\2\0\1\23\7\0\47\23"+
    "\7\0\1\23\1\0\55\24\1\0\1\24\1\0\2\24\1\0\2\24"+
    "\1\0\1\24\10\0\33\23\5\0\3\23\15\0\5\24\6\0\1\23"+
    "\4\0\13\24\5\0\53\23\37\24\4\0\2\23\1\24\143\23\1\0"+
    "\1\23\10\24\1\0\6\24\2\23\2\24\1\0\4\24\2\23\12\24"+
    "\3\23\2\0\1\23\17\0\1\24\1\23\1\24\36\23\33\24\2\0"+
    "\131\23\13\24\1\23\16\0\12\24\41\23\11\24\2\23\4\0\1\23"+
    "\5\0\26\23\4\24\1\23\11\24\1\23\3\24\1\23\5\24\22\0"+
    "\31\23\3\24\104\0\1\23\1\0\13\23\67\0\33\24\1\0\4\24"+
    "\66\23\3\24\1\23\22\24\1\23\7\24\12\23\2\24\2\0\12\24"+
    "\1\0\7\23\1\0\7\23\1\0\3\24\1\0\10\23\2\0\2\23"+
    "\2\0\26\23\1\0\7\23\1\0\1\23\3\0\4\23\2\0\1\24"+
    "\1\23\7\24\2\0\2\24\2\0\3\24\1\23\10\0\1\24\4\0"+
    "\2\23\1\0\3\23\2\24\2\0\12\24\4\23\7\0\1\23\5\0"+
    "\3\24\1\0\6\23\4\0\2\23\2\0\26\23\1\0\7\23\1\0"+
    "\2\23\1\0\2\23\1\0\2\23\2\0\1\24\1\0\5\24\4\0"+
    "\2\24\2\0\3\24\3\0\1\24\7\0\4\23\1\0\1\23\7\0"+
    "\14\24\3\23\1\24\13\0\3\24\1\0\11\23\1\0\3\23\1\0"+
    "\26\23\1\0\7\23\1\0\2\23\1\0\5\23\2\0\1\24\1\23"+
    "\10\24\1\0\3\24\1\0\3\24\2\0\1\23\17\0\2\23\2\24"+
    "\2\0\12\24\1\0\1\23\17\0\3\24\1\0\10\23\2\0\2\23"+
    "\2\0\26\23\1\0\7\23\1\0\2\23\1\0\5\23\2\0\1\24"+
    "\1\23\7\24\2\0\2\24\2\0\3\24\10\0\2\24\4\0\2\23"+
    "\1\0\3\23\2\24\2\0\12\24\1\0\1\23\20\0\1\24\1\23"+
    "\1\0\6\23\3\0\3\23\1\0\4\23\3\0\2\23\1\0\1\23"+
    "\1\0\2\23\3\0\2\23\3\0\3\23\3\0\14\23\4\0\5\24"+
    "\3\0\3\24\1\0\4\24\2\0\1\23\6\0\1\24\16\0\12\24"+
    "\11\0\1\23\7\0\3\24\1\0\10\23\1\0\3\23\1\0\27\23"+
    "\1\0\12\23\1\0\5\23\3\0\1\23\7\24\1\0\3\24\1\0"+
    "\4\24\7\0\2\24\1\0\2\23\6\0\2\23\2\24\2\0\12\24"+
    "\22\0\2\24\1\0\10\23\1\0\3\23\1\0\27\23\1\0\12\23"+
    "\1\0\5\23\2\0\1\24\1\23\7\24\1\0\3\24\1\0\4\24"+
    "\7\0\2\24\7\0\1\23\1\0\2\23\2\24\2\0\12\24\1\0"+
    "\2\23\17\0\2\24\1\0\10\23\1\0\3\23\1\0\51\23\2\0"+
    "\1\23\7\24\1\0\3\24\1\0\4\24\1\23\10\0\1\24\10\0"+
    "\2\23\2\24\2\0\12\24\12\0\6\23\2\0\2\24\1\0\22\23"+
    "\3\0\30\23\1\0\11\23\1\0\1\23\2\0\7\23\3\0\1\24"+
    "\4\0\6\24\1\0\1\24\1\0\10\24\22\0\2\24\15\0\60\23"+
    "\1\24\2\23\7\24\4\0\10\23\10\24\1\0\12\24\47\0\2\23"+
    "\1\0\1\23\2\0\2\23\1\0\1\23\2\0\1\23\6\0\4\23"+
    "\1\0\7\23\1\0\3\23\1\0\1\23\1\0\1\23\2\0\2\23"+
    "\1\0\4\23\1\24\2\23\6\24\1\0\2\24\1\23\2\0\5\23"+
    "\1\0\1\23\1\0\6\24\2\0\12\24\2\0\4\23\40\0\1\23"+
    "\27\0\2\24\6\0\12\24\13\0\1\24\1\0\1\24\1\0\1\24"+
    "\4\0\2\24\10\23\1\0\44\23\4\0\24\24\1\0\2\24\5\23"+
    "\13\24\1\0\44\24\11\0\1\24\71\0\53\23\24\24\1\23\12\24"+
    "\6\0\6\23\4\24\4\23\3\24\1\23\3\24\2\23\7\24\3\23"+
    "\4\24\15\23\14\24\1\23\17\24\2\0\46\23\1\0\1\23\5\0"+
    "\1\23\2\0\53\23\1\0\u014d\23\1\0\4\23\2\0\7\23\1\0"+
    "\1\23\1\0\4\23\2\0\51\23\1\0\4\23\2\0\41\23\1\0"+
    "\4\23\2\0\7\23\1\0\1\23\1\0\4\23\2\0\17\23\1\0"+
    "\71\23\1\0\4\23\2\0\103\23\2\0\3\24\40\0\20\23\20\0"+
    "\125\23\14\0\u026c\23\2\0\21\23\1\0\32\23\5\0\113\23\3\0"+
    "\3\23\17\0\15\23\1\0\4\23\3\24\13\0\22\23\3\24\13\0"+
    "\22\23\2\24\14\0\15\23\1\0\3\23\1\0\2\24\14\0\64\23"+
    "\40\24\3\0\1\23\3\0\2\23\1\24\2\0\12\24\41\0\3\24"+
    "\2\0\12\24\6\0\130\23\10\0\51\23\1\24\1\23\5\0\106\23"+
    "\12\0\35\23\3\0\14\24\4\0\14\24\12\0\12\24\36\23\2\0"+
    "\5\23\13\0\54\23\4\0\21\24\7\23\2\24\6\0\12\24\46\0"+
    "\27\23\5\24\4\0\65\23\12\24\1\0\35\24\2\0\13\24\6\0"+
    "\12\24\15\0\1\23\130\0\5\24\57\23\21\24\7\23\4\0\12\24"+
    "\21\0\11\24\14\0\3\24\36\23\15\24\2\23\12\24\54\23\16\24"+
    "\14\0\44\23\24\24\10\0\12\24\3\0\3\23\12\24\44\23\122\0"+
    "\3\24\1\0\25\24\4\23\1\24\4\23\3\24\2\23\11\0\300\23"+
    "\47\24\25\0\4\24\u0116\23\2\0\6\23\2\0\46\23\2\0\6\23"+
    "\2\0\10\23\1\0\1\23\1\0\1\23\1\0\1\23\1\0\37\23"+
    "\2\0\65\23\1\0\7\23\1\0\1\23\3\0\3\23\1\0\7\23"+
    "\3\0\4\23\2\0\6\23\4\0\15\23\5\0\3\23\1\0\7\23"+
    "\16\0\5\24\32\0\5\24\20\0\2\23\23\0\1\23\13\0\5\24"+
    "\5\0\6\24\1\0\1\23\15\0\1\23\20\0\15\23\3\0\33\23"+
    "\25\0\15\24\4\0\1\24\3\0\14\24\21\0\1\23\4\0\1\23"+
    "\2\0\12\23\1\0\1\23\3\0\5\23\6\0\1\23\1\0\1\23"+
    "\1\0\1\23\1\0\4\23\1\0\13\23\2\0\4\23\5\0\5\23"+
    "\4\0\1\23\21\0\51\23\u0a77\0\57\23\1\0\57\23\1\0\205\23"+
    "\6\0\4\23\3\24\2\23\14\0\46\23\1\0\1\23\5\0\1\23"+
    "\2\0\70\23\7\0\1\23\17\0\1\24\27\23\11\0\7\23\1\0"+
    "\7\23\1\0\7\23\1\0\7\23\1\0\7\23\1\0\7\23\1\0"+
    "\7\23\1\0\7\23\1\0\40\24\57\0\1\23\u01d5\0\3\23\31\0"+
    "\11\23\6\24\1\0\5\23\2\0\5\23\4\0\126\23\2\0\2\24"+
    "\2\0\3\23\1\0\132\23\1\0\4\23\5\0\51\23\3\0\136\23"+
    "\21\0\33\23\65\0\20\23\u0200\0\u19b6\23\112\0\u51cd\23\63\0\u048d\23"+
    "\103\0\56\23\2\0\u010d\23\3\0\20\23\12\24\2\23\24\0\57\23"+
    "\1\24\4\0\12\24\1\0\31\23\7\0\1\24\120\23\2\24\45\0"+
    "\11\23\2\0\147\23\2\0\4\23\1\0\4\23\14\0\13\23\115\0"+
    "\12\23\1\24\3\23\1\24\4\23\1\24\27\23\5\24\20\0\1\23"+
    "\7\0\64\23\14\0\2\24\62\23\21\24\13\0\12\24\6\0\22\24"+
    "\6\23\3\0\1\23\4\0\12\24\34\23\10\24\2\0\27\23\15\24"+
    "\14\0\35\23\3\0\4\24\57\23\16\24\16\0\1\23\12\24\46\0"+
    "\51\23\16\24\11\0\3\23\1\24\10\23\2\24\2\0\12\24\6\0"+
    "\27\23\3\0\1\23\1\24\4\0\60\23\1\24\1\23\3\24\2\23"+
    "\2\24\5\23\2\24\1\23\1\24\1\23\30\0\3\23\2\0\13\23"+
    "\5\24\2\0\3\23\2\24\12\0\6\23\2\0\6\23\2\0\6\23"+
    "\11\0\7\23\1\0\7\23\221\0\43\23\10\24\1\0\2\24\2\0"+
    "\12\24\6\0\u2ba4\23\14\0\27\23\4\0\61\23\u2104\0\u016e\23\2\0"+
    "\152\23\46\0\7\23\14\0\5\23\5\0\1\23\1\24\12\23\1\0"+
    "\15\23\1\0\5\23\1\0\1\23\1\0\2\23\1\0\2\23\1\0"+
    "\154\23\41\0\u016b\23\22\0\100\23\2\0\66\23\50\0\15\23\3\0"+
    "\20\24\20\0\7\24\14\0\2\23\30\0\3\23\31\0\1\23\6\0"+
    "\5\23\1\0\207\23\2\0\1\24\4\0\1\23\13\0\12\24\7\0"+
    "\32\23\4\0\1\23\1\0\32\23\13\0\131\23\3\0\6\23\2\0"+
    "\6\23\2\0\6\23\2\0\3\23\3\0\2\23\3\0\2\23\22\0"+
    "\3\24\4\0\14\23\1\0\32\23\1\0\23\23\1\0\2\23\1\0"+
    "\17\23\2\0\16\23\42\0\173\23\105\0\65\23\210\0\1\24\202\0"+
    "\35\23\3\0\61\23\57\0\37\23\21\0\33\23\65\0\36\23\2\0"+
    "\44\23\4\0\10\23\1\0\5\23\52\0\236\23\2\0\12\24\u0356\0"+
    "\6\23\2\0\1\23\1\0\54\23\1\0\2\23\3\0\1\23\2\0"+
    "\27\23\252\0\26\23\12\0\32\23\106\0\70\23\6\0\2\23\100\0"+
    "\1\23\3\24\1\0\2\24\5\0\4\24\4\23\1\0\3\23\1\0"+
    "\33\23\4\0\3\24\4\0\1\24\40\0\35\23\203\0\66\23\12\0"+
    "\26\23\12\0\23\23\215\0\111\23\u03b7\0\3\24\65\23\17\24\37\0"+
    "\12\24\20\0\3\24\55\23\13\24\2\0\1\24\22\0\31\23\7\0"+
    "\12\24\6\0\3\24\44\23\16\24\1\0\12\24\100\0\3\24\60\23"+
    "\16\24\4\23\13\0\12\24\u04a6\0\53\23\15\24\10\0\12\24\u0936\0"+
    "\u036f\23\221\0\143\23\u0b9d\0\u042f\23\u33d1\0\u0239\23\u04c7\0\105\23\13\0"+
    "\1\23\56\24\20\0\4\24\15\23\u4060\0\2\23\u2163\0\5\24\3\0"+
    "\26\24\2\0\7\24\36\0\4\24\224\0\3\24\u01bb\0\125\23\1\0"+
    "\107\23\1\0\2\23\2\0\1\23\2\0\2\23\2\0\4\23\1\0"+
    "\14\23\1\0\1\23\1\0\7\23\1\0\101\23\1\0\4\23\2\0"+
    "\10\23\1\0\7\23\1\0\34\23\1\0\4\23\1\0\5\23\1\0"+
    "\1\23\3\0\7\23\1\0\u0154\23\2\0\31\23\1\0\31\23\1\0"+
    "\37\23\1\0\31\23\1\0\37\23\1\0\31\23\1\0\37\23\1\0"+
    "\31\23\1\0\37\23\1\0\31\23\1\0\10\23\2\0\62\24\u1600\0"+
    "\4\23\1\0\33\23\1\0\2\23\1\0\1\23\2\0\1\23\1\0"+
    "\12\23\1\0\4\23\1\0\1\23\1\0\1\23\6\0\1\23\4\0"+
    "\1\23\1\0\1\23\1\0\1\23\1\0\3\23\1\0\2\23\1\0"+
    "\1\23\2\0\1\23\1\0\1\23\1\0\1\23\1\0\1\23\1\0"+
    "\1\23\1\0\2\23\1\0\1\23\2\0\4\23\1\0\7\23\1\0"+
    "\4\23\1\0\4\23\1\0\1\23\1\0\12\23\1\0\21\23\5\0"+
    "\3\23\1\0\5\23\1\0\21\23\u1144\0\ua6d7\23\51\0\u1035\23\13\0"+
    "\336\23\u3fe2\0\u021e\23\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05ee\0"+
    "\1\24\36\0\140\24\200\0\360\24\uffff\0\uffff\0\ufe12\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\2\3\5\0"+
    "\2\3\1\6\1\7\4\0\2\3\4\0\2\3\4\0"+
    "\1\3\1\10\4\0\1\3\1\11\3\0\1\3\1\12"+
    "\2\0\1\3\2\0\1\13\3\0\1\14\1\15";

  private static int [] zzUnpackAction() {
    int [] result = new int[57];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\27\0\56\0\105\0\134\0\134\0\163\0\212"+
    "\0\241\0\270\0\317\0\346\0\375\0\u0114\0\u012b\0\134"+
    "\0\270\0\u0142\0\u0159\0\u0170\0\u0187\0\u019e\0\u01b5\0\u01cc"+
    "\0\u01e3\0\u01fa\0\u0211\0\u0228\0\u023f\0\u0256\0\u026d\0\u0284"+
    "\0\u029b\0\u02b2\0\105\0\u02c9\0\u02e0\0\u02f7\0\u030e\0\u0325"+
    "\0\134\0\u033c\0\u0353\0\u036a\0\u0381\0\134\0\u0398\0\u03af"+
    "\0\u03c6\0\u03dd\0\u03f4\0\105\0\u040b\0\u0422\0\u0439\0\134"+
    "\0\134";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[57];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\1\3\5\4\1\5\1\6\4\4\1\7"+
    "\3\4\1\10\1\4\2\0\1\11\1\0\1\2\23\0"+
    "\1\12\4\0\1\13\5\0\1\14\1\15\15\0\1\4"+
    "\1\0\5\4\2\0\13\4\32\0\1\4\1\0\5\4"+
    "\2\0\5\4\1\16\5\4\3\0\1\4\1\0\4\4"+
    "\1\17\2\0\13\4\2\0\26\11\1\20\1\0\1\21"+
    "\31\0\1\22\25\0\1\23\6\0\1\24\27\0\1\25"+
    "\14\0\1\4\1\0\5\4\2\0\1\4\1\26\11\4"+
    "\3\0\1\4\1\0\1\27\4\4\2\0\13\4\7\0"+
    "\1\30\25\0\1\31\35\0\1\32\17\0\1\33\23\0"+
    "\1\4\1\0\5\4\2\0\6\4\1\34\4\4\3\0"+
    "\1\4\1\0\2\4\1\35\2\4\2\0\13\4\10\0"+
    "\1\36\25\0\1\37\25\0\1\40\36\0\1\41\13\0"+
    "\1\4\1\0\4\4\1\42\2\0\13\4\3\0\1\4"+
    "\1\0\5\4\2\0\7\4\1\43\3\4\11\0\1\44"+
    "\25\0\1\45\34\0\1\46\27\0\1\47\12\0\1\4"+
    "\1\0\5\4\2\0\6\4\1\50\4\4\12\0\1\51"+
    "\25\0\1\52\34\0\1\53\15\0\1\54\23\0\1\4"+
    "\1\0\5\4\2\0\5\4\1\55\5\4\12\0\1\56"+
    "\22\0\1\57\34\0\1\60\15\0\1\4\1\0\5\4"+
    "\2\0\1\4\1\61\11\4\14\0\1\62\23\0\1\63"+
    "\20\0\1\4\1\0\5\4\2\0\7\4\1\64\3\4"+
    "\11\0\1\65\22\0\1\66\26\0\1\67\33\0\1\70"+
    "\26\0\1\71\16\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1104];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\3\1\2\11\2\1\5\0\2\1\1\11\1\1"+
    "\4\0\2\1\4\0\2\1\4\0\2\1\4\0\1\1"+
    "\1\11\3\0\1\1\1\11\2\0\1\1\2\0\1\1"+
    "\3\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[57];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
public static String errores="";
 public ArrayList<NodoError> Elista = new ArrayList();
    private Symbol Symbol(int type){
        return new Symbol(type ,yyline, yycolumn);
    }
    private Symbol Symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    private void errorLexico(String error,int x, int y ){
       errores+="error lexico \""+error+"\" en linea: "+Integer.toString(y)+" colulmna: "+Integer.toString(x)+"\n";
	   NodoError Nerror = new NodoError("lexico");
       Nerror.descripcion = "caracter no reconocido: "+error;
       Nerror.linea = String.valueOf(y);
       Nerror.columna = String.valueOf(x);
       Elista.add(Nerror);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexicoGD(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2846) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return new Symbol(symd.nume,yyline,yycolumn,yytext());
            } 
            // fall through
          case 14: break;
          case 2: 
            { return new Symbol(symd.men,yyline,yycolumn,yytext());
            } 
            // fall through
          case 15: break;
          case 3: 
            { return new Symbol(symd.idva,yyline,yycolumn,yytext());
            } 
            // fall through
          case 16: break;
          case 4: 
            { return new Symbol(symd.may,yyline,yycolumn,yytext());
            } 
            // fall through
          case 17: break;
          case 5: 
            { return new Symbol(symd.dia,yyline,yycolumn,yytext());
            } 
            // fall through
          case 18: break;
          case 6: 
            { return new Symbol(symd.cade,yyline,yycolumn,yytext());
            } 
            // fall through
          case 19: break;
          case 7: 
            { return new Symbol(symd.deci,yyline,yycolumn,yytext());
            } 
            // fall through
          case 20: break;
          case 8: 
            { return new Symbol(symd.fals,yyline,yycolumn,yytext());
            } 
            // fall through
          case 21: break;
          case 9: 
            { return new Symbol(symd.ili,yyline,yycolumn,yytext());
            } 
            // fall through
          case 22: break;
          case 10: 
            { return new Symbol(symd.fli,yyline,yycolumn,yytext());
            } 
            // fall through
          case 23: break;
          case 11: 
            { return new Symbol(symd.verd,yyline,yycolumn,yytext());
            } 
            // fall through
          case 24: break;
          case 12: 
            { return new Symbol(symd.ipr,yyline,yycolumn,yytext());
            } 
            // fall through
          case 25: break;
          case 13: 
            { return new Symbol(symd.fpr,yyline,yycolumn,yytext());
            } 
            // fall through
          case 26: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
