package util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

//MASCARAS*****

public class Mascara extends MaskFormatter {
    public static final int Telefono = 0;
    public static final int Solotexto30 = 1;
    public static final int DNI = 2;
    public static final int Numero3Digitos = 3;
    public static final int movil = 4;
    public static final int unoDotUno = 5;
    public static final int tresDotUno = 6;
    public static final int hora = 7;
    public static final int alfanumerico = 8;
    public static final int unoDotDos = 9;
    public static final int menosDosDotDos = 10;
    public static final int menosUnoDotDos = 11;
    public static final int DosDotDos = 12;

    //Valores iniciales para los campos
    public static final String SANIO = "1900";
    public static final String SDNI = "00000000";
    public static final String STLF = "900000000";

    public Mascara(int tipo) throws ParseException {
        StringBuffer cadena = new StringBuffer();
        switch (tipo) {

        case (0):
            this.setMask("#########");
            break;

        case (1):
            for (int i = 0; i < 30; i++) {
                cadena.append('*');
            }
            this.setMask(cadena.toString());
            this.setInvalidCharacters("0123456789+?¿!·$%&/()=><");

            break;

        case (2):
            this.setMask("########");
            break;

        case (3):
            this.setMask("###");
            break;

        case (4):

            //this.setPlaceholderCharacter('_');
            this.setMask("6########");
            break;

        case (5):
            this.setMask("#.#");
            break;

        case (6):
            this.setMask("###.#");
            break;

        case (7):
            this.setMask("##:##:##");
            break;

        case (8):
            for (int i = 0; i < 30; i++) {
                cadena.append('*');
            }
            this.setMask(cadena.toString());

            //this.setInvalidCharacters("+?¿!·$%&/()=><");

            break;

        case (9):
            this.setMask("#.##");
            break;

        case (10):
            this.setMask("*##.##");
            this.setValidCharacters("-1234567890");
            break;

        case (11):
      this.setMask("*#.##");
      this.setValidCharacters("-1234567890");
      break;

  case (12):
        this.setMask("##.##");
        break;



        }
    }

}
