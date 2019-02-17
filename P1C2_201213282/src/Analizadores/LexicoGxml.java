/* The following code was generated by JFlex 1.6.1 */

package Analizadores;
import java_cup.runtime.*;
import java.io.Reader;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>LexicoGxml</tt>
 */
public class LexicoGxml implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\41\1\3\1\44\1\41\1\41\22\0\1\41\1\0\1\37"+
    "\1\2\1\4\10\0\1\36\1\0\1\7\12\1\2\0\1\5\1\10"+
    "\1\6\2\0\1\16\1\24\1\30\1\14\1\26\1\32\1\34\1\23"+
    "\1\13\2\0\1\22\1\17\1\25\1\21\1\20\1\0\1\27\1\35"+
    "\1\15\1\33\1\31\1\0\1\11\1\12\3\0\1\40\3\0\1\16"+
    "\1\24\1\30\1\14\1\26\1\32\1\34\1\23\1\13\2\0\1\22"+
    "\1\17\1\25\1\21\1\20\1\0\1\27\1\35\1\15\1\33\1\31"+
    "\1\0\1\11\1\12\1\0\1\42\1\41\1\43\7\0\1\44\252\0"+
    "\2\13\115\0\1\35\u1ea8\0\1\44\1\44\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\20\1\2\0\1\12\32\0\1\13"+
    "\5\0\1\14\26\0\1\15\1\16\1\0\1\17\1\0"+
    "\1\20\1\0\1\21\6\0\1\22\14\0\1\23\2\0"+
    "\1\24\1\25\6\0\1\26\1\27\4\0\1\30\7\0"+
    "\1\31\1\0\1\32\1\33\2\0\1\34\1\0\1\35"+
    "\5\0\1\36\1\0\1\37\5\0\1\40\3\0\1\41"+
    "\1\42\1\43\23\0\1\44\1\45\1\46\1\47\2\0"+
    "\1\50\1\0\1\51\2\0\1\52\4\0\1\53";

  private static int [] zzUnpackAction() {
    int [] result = new int[202];
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
    "\0\0\0\45\0\112\0\157\0\224\0\45\0\45\0\45"+
    "\0\45\0\45\0\45\0\271\0\336\0\u0103\0\u0128\0\u014d"+
    "\0\u0172\0\u0197\0\u01bc\0\u01e1\0\u0206\0\u022b\0\u0250\0\u0275"+
    "\0\u029a\0\u02bf\0\u02e4\0\u0309\0\u032e\0\45\0\u0353\0\u0378"+
    "\0\u039d\0\u03c2\0\u03e7\0\u040c\0\u0431\0\u0456\0\u047b\0\u04a0"+
    "\0\u04c5\0\u04ea\0\u050f\0\u0534\0\u0559\0\u057e\0\u05a3\0\u05c8"+
    "\0\u05ed\0\u0612\0\u0637\0\u065c\0\u0681\0\u06a6\0\u06cb\0\u06f0"+
    "\0\45\0\u0715\0\u073a\0\u075f\0\u0784\0\u07a9\0\45\0\u07ce"+
    "\0\u07f3\0\u0818\0\u083d\0\u0862\0\u0887\0\u08ac\0\u08d1\0\u08f6"+
    "\0\u091b\0\u0940\0\u0965\0\u098a\0\u09af\0\u09d4\0\u09f9\0\u0a1e"+
    "\0\u0a43\0\u0a68\0\u0a8d\0\u0ab2\0\u0ad7\0\45\0\u032e\0\u0afc"+
    "\0\45\0\u0b21\0\45\0\u0b46\0\45\0\u0b6b\0\u0b90\0\u0bb5"+
    "\0\u0bda\0\u0bff\0\u0c24\0\45\0\u0c49\0\u0c6e\0\u0c93\0\u0cb8"+
    "\0\u0cdd\0\u0d02\0\u0d27\0\u0d4c\0\u0d71\0\u0d96\0\u0dbb\0\u0de0"+
    "\0\45\0\u0e05\0\u0e2a\0\45\0\45\0\u0e4f\0\u0e74\0\u0e99"+
    "\0\u0ebe\0\u0ee3\0\u0f08\0\45\0\45\0\u0f2d\0\u0f52\0\u0f77"+
    "\0\u0f9c\0\45\0\u0fc1\0\u0fe6\0\u100b\0\u1030\0\u1055\0\u107a"+
    "\0\u109f\0\u10c4\0\u10e9\0\45\0\45\0\u110e\0\u1133\0\45"+
    "\0\u1158\0\45\0\u117d\0\u11a2\0\u11c7\0\u11ec\0\u1211\0\45"+
    "\0\u1236\0\45\0\u125b\0\u1280\0\u12a5\0\u12ca\0\u12ef\0\45"+
    "\0\u1314\0\u1339\0\u135e\0\45\0\45\0\45\0\u1383\0\u13a8"+
    "\0\u13cd\0\u13f2\0\u1417\0\u143c\0\u1461\0\u1486\0\u14ab\0\u14d0"+
    "\0\u14f5\0\u151a\0\u153f\0\u1564\0\u1589\0\u15ae\0\u15d3\0\u15f8"+
    "\0\u161d\0\45\0\45\0\45\0\45\0\u1642\0\u1667\0\45"+
    "\0\u168c\0\45\0\u16b1\0\u16d6\0\45\0\u16fb\0\u1720\0\u1745"+
    "\0\u176a\0\45";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[202];
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
    "\1\2\1\3\1\4\1\5\1\2\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\2\1\22\1\2\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\4\2\1\32\1\2\1\5\1\33"+
    "\1\2\47\0\1\3\45\0\1\34\1\0\1\35\43\0"+
    "\1\5\35\0\1\5\17\0\1\36\2\0\1\37\43\0"+
    "\1\40\7\0\1\41\31\0\1\42\2\0\1\43\7\0"+
    "\1\44\40\0\1\45\2\0\1\46\2\0\1\47\2\0"+
    "\1\50\24\0\1\51\2\0\1\52\14\0\1\53\27\0"+
    "\1\54\41\0\1\55\52\0\1\56\44\0\1\57\4\0"+
    "\1\60\43\0\1\61\45\0\1\62\37\0\1\63\11\0"+
    "\1\64\37\0\1\65\51\0\1\66\11\0\37\67\1\0"+
    "\5\67\42\70\2\0\1\70\3\34\1\71\41\34\4\35"+
    "\1\72\40\35\20\0\1\73\41\0\1\74\61\0\1\75"+
    "\32\0\1\76\43\0\1\77\36\0\1\100\50\0\1\101"+
    "\57\0\1\102\44\0\1\103\31\0\1\104\54\0\1\105"+
    "\30\0\1\106\55\0\1\107\37\0\1\110\64\0\1\111"+
    "\24\0\1\112\11\0\1\113\34\0\1\114\61\0\1\115"+
    "\41\0\1\116\45\0\1\117\34\0\1\120\2\0\1\121"+
    "\46\0\1\122\42\0\1\123\45\0\1\124\56\0\1\125"+
    "\4\0\42\70\1\0\1\126\1\70\2\35\1\127\1\35"+
    "\1\72\40\35\21\0\1\130\44\0\1\131\51\0\1\132"+
    "\37\0\1\133\40\0\1\134\50\0\1\135\46\0\1\136"+
    "\34\0\1\137\52\0\1\140\36\0\1\141\44\0\1\142"+
    "\46\0\1\143\52\0\1\144\36\0\1\145\50\0\1\146"+
    "\37\0\1\147\54\0\1\150\47\0\1\151\30\0\1\152"+
    "\57\0\1\153\37\0\1\154\40\0\1\155\64\0\1\156"+
    "\24\0\1\157\54\0\1\160\56\0\1\161\1\125\33\0"+
    "\1\162\45\0\1\163\35\0\1\164\44\0\1\165\44\0"+
    "\1\166\61\0\1\167\25\0\1\170\44\0\1\171\40\0"+
    "\1\172\47\0\1\173\53\0\1\174\45\0\1\175\45\0"+
    "\1\176\30\0\1\177\47\0\1\200\55\0\1\201\44\0"+
    "\1\202\43\0\1\203\1\204\30\0\1\205\47\0\1\206"+
    "\43\0\1\207\44\0\1\210\44\0\1\211\54\0\1\212"+
    "\46\0\1\213\36\0\1\214\44\0\1\215\42\0\1\216"+
    "\41\0\1\217\56\0\1\220\33\0\1\221\56\0\1\222"+
    "\43\0\1\223\43\0\1\224\40\0\1\225\54\0\1\226"+
    "\40\0\1\227\45\0\1\230\34\0\1\231\47\0\1\232"+
    "\36\0\1\233\16\0\1\234\40\0\1\235\44\0\1\236"+
    "\34\0\1\237\44\0\1\240\53\0\1\241\45\0\1\242"+
    "\40\0\1\243\40\0\1\244\44\0\1\245\55\0\1\246"+
    "\42\0\1\247\32\0\1\250\51\0\1\251\40\0\1\252"+
    "\45\0\1\253\57\0\1\254\30\0\1\255\46\0\1\256"+
    "\41\0\1\257\56\0\1\260\46\0\1\261\30\0\1\262"+
    "\52\0\1\263\36\0\1\264\52\0\1\265\37\0\1\266"+
    "\60\0\1\267\32\0\1\270\47\0\1\271\41\0\1\272"+
    "\63\0\1\273\25\0\1\274\55\0\1\275\36\0\1\276"+
    "\36\0\1\277\53\0\1\300\36\0\1\301\57\0\1\302"+
    "\33\0\1\303\61\0\1\304\33\0\1\305\52\0\1\306"+
    "\44\0\1\307\27\0\1\310\52\0\1\311\50\0\1\312"+
    "\17\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6031];
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
    "\1\0\1\11\3\1\6\11\20\1\2\0\1\11\32\0"+
    "\1\11\5\0\1\11\26\0\1\11\1\1\1\0\1\11"+
    "\1\0\1\11\1\0\1\11\6\0\1\11\14\0\1\11"+
    "\2\0\2\11\6\0\2\11\4\0\1\11\7\0\1\1"+
    "\1\0\2\11\2\0\1\11\1\0\1\11\5\0\1\11"+
    "\1\0\1\11\5\0\1\11\3\0\3\11\23\0\4\11"+
    "\2\0\1\11\1\0\1\11\2\0\1\11\4\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[202];
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
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
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
    private Symbol Symbol(int type){
        return new Symbol(type ,yyline, yycolumn);
    }
    private Symbol Symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    public static void errorLexico(String error,int x, int y ){
       errores+="error lexico \""+error+"\" en linea: "+Integer.toString(y)+" colulmna: "+Integer.toString(x)+"\n";
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexicoGxml(java.io.Reader in) {
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
    while (i < 206) {
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
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
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
            { return new Symbol(sym.TEXT,yyline,yycolumn,yytext());
            }
          case 44: break;
          case 2: 
            { return new Symbol(sym.NUM,yyline,yycolumn,yytext());
            }
          case 45: break;
          case 3: 
            { /*Ignore*/
            }
          case 46: break;
          case 4: 
            { return new Symbol(sym.MAY,yyline,yycolumn,yytext());
            }
          case 47: break;
          case 5: 
            { return new Symbol(sym.MEN,yyline,yycolumn,yytext());
            }
          case 48: break;
          case 6: 
            { return new Symbol(sym.DIA,yyline,yycolumn,yytext());
            }
          case 49: break;
          case 7: 
            { return new Symbol(sym.IGU,yyline,yycolumn,yytext());
            }
          case 50: break;
          case 8: 
            { return new Symbol(sym.RX,yyline,yycolumn,yytext());
            }
          case 51: break;
          case 9: 
            { return new Symbol(sym.RY,yyline,yycolumn,yytext());
            }
          case 52: break;
          case 10: 
            { return new Symbol(sym.RID,yyline,yycolumn,yytext());
            }
          case 53: break;
          case 11: 
            { /*Ignorar Comentario Simple*/
            }
          case 54: break;
          case 12: 
            { return new Symbol(sym.RTAM,yyline,yycolumn,yytext());
            }
          case 55: break;
          case 13: 
            { return new Symbol(sym.MORE,yyline,yycolumn,yytext());
            }
          case 56: break;
          case 14: 
            { /*Ignorar Comentario multiple*/
            }
          case 57: break;
          case 15: 
            { return new Symbol(sym.RDAT,yyline,yycolumn,yytext());
            }
          case 58: break;
          case 16: 
            { return new Symbol(sym.RTIP,yyline,yycolumn,yytext());
            }
          case 59: break;
          case 17: 
            { return new Symbol(sym.RALT,yyline,yycolumn,yytext());
            }
          case 60: break;
          case 18: 
            { return new Symbol(sym.RPAT,yyline,yycolumn,yytext());
            }
          case 61: break;
          case 19: 
            { return new Symbol(sym.CAD,yyline,yycolumn,yytext());
            }
          case 62: break;
          case 20: 
            { return new Symbol(sym.RTEX,yyline,yycolumn,yytext());
            }
          case 63: break;
          case 21: 
            { return new Symbol(sym.RANC,yyline,yycolumn,yytext());
            }
          case 64: break;
          case 22: 
            { return new Symbol(sym.RBOT,yyline,yycolumn,yytext());
            }
          case 65: break;
          case 23: 
            { return new Symbol(sym.RBOR,yyline,yycolumn,yytext());
            }
          case 66: break;
          case 24: 
            { return new Symbol(sym.RCOL,yyline,yycolumn,yytext());
            }
          case 67: break;
          case 25: 
            { return new Symbol(sym.RACI,yyline,yycolumn,yytext());
            }
          case 68: break;
          case 26: 
            { return new Symbol(sym.RMIN,yyline,yycolumn,yytext());
            }
          case 69: break;
          case 27: 
            { return new Symbol(sym.RMAX,yyline,yycolumn,yytext());
            }
          case 70: break;
          case 28: 
            { return new Symbol(sym.RNOM,yyline,yycolumn,yytext());
            }
          case 71: break;
          case 29: 
            { return new Symbol(sym.RENV,yyline,yycolumn,yytext());
            }
          case 72: break;
          case 30: 
            { return new Symbol(sym.RFUE,yyline,yycolumn,yytext());
            }
          case 73: break;
          case 31: 
            { return new Symbol(sym.RDEF,yyline,yycolumn,yytext());
            }
          case 74: break;
          case 32: 
            { return new Symbol(sym.RNEG,yyline,yycolumn,yytext());
            }
          case 75: break;
          case 33: 
            { return new Symbol(sym.RCUR,yyline,yycolumn,yytext());
            }
          case 76: break;
          case 34: 
            { return new Symbol(sym.RVEN,yyline,yycolumn,yytext());
            }
          case 77: break;
          case 35: 
            { return new Symbol(sym.RIMP,yyline,yycolumn,yytext());
            }
          case 78: break;
          case 36: 
            { return new Symbol(sym.RMUL,yyline,yycolumn,yytext());
            }
          case 79: break;
          case 37: 
            { return new Symbol(sym.RLDA,yyline,yycolumn,yytext());
            }
          case 80: break;
          case 38: 
            { return new Symbol(sym.REFE,yyline,yycolumn,yytext());
            }
          case 81: break;
          case 39: 
            { return new Symbol(sym.RCON,yyline,yycolumn,yytext());
            }
          case 82: break;
          case 40: 
            { return new Symbol(sym.RAFI,yyline,yycolumn,yytext());
            }
          case 83: break;
          case 41: 
            { return new Symbol(sym.RCTR,yyline,yycolumn,yytext());
            }
          case 84: break;
          case 42: 
            { return new Symbol(sym.RAIN,yyline,yycolumn,yytext());
            }
          case 85: break;
          case 43: 
            { return new Symbol(sym.RAUT,yyline,yycolumn,yytext());
            }
          case 86: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
