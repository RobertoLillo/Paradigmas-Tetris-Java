public class pieza {
    // Atributos estaticos:

    // Variables piezaXY, donde X = IDPieza e Y = Rotaciones.
    // Estructura pieza: [X0, Y0, X1, Y1, X2, Y2, X3, Y3].
    // Piezas iniciales sin ningún giro:
    //   1) 11   2) 2   3)  3   4) 4    5)  5    6) 6    7)  7
    //      11      2      33      44      555      6        7
    //              2      3        4               66      77
    //              2                          

    private int[] pieza10 = {0, 0, 0, 1, 1, 0, 1, 1};

    private int[] pieza20 = {0, 0, 1, 0, 2, 0, 3, 0};
    private int[] pieza21 = {0, 0, 0, 1, 0, 2, 0, 3};

    private int[] pieza30 = {0, 1, 0, 2, 1, 0, 1, 1};
    private int[] pieza31 = {0, 0, 1, 0, 1, 1, 2, 1};

    private int[] pieza40 = {0, 0, 0, 1, 1, 1, 1, 2};
    private int[] pieza41 = {0, 1, 1, 0, 1, 1, 2, 0};

    private int[] pieza50 = {0, 0, 0, 1, 0, 2, 1, 1};
    private int[] pieza51 = {0, 1, 1, 0, 1, 1, 2, 1};
    private int[] pieza52 = {0, 1, 1, 0, 1, 1, 1, 2};
    private int[] pieza53 = {0, 0, 1, 0, 1, 1, 2, 0};

    private int[] pieza60 = {0, 0, 0, 1, 1, 0, 2, 0};
    private int[] pieza61 = {0, 0, 0, 1, 0, 2, 1, 2};
    private int[] pieza62 = {0, 1, 1, 1, 2, 0, 2, 1};
    private int[] pieza63 = {0, 0, 1, 0, 1, 1, 1, 2};

    private int[] pieza70 = {0, 0, 0, 1, 1, 1, 2, 1};
    private int[] pieza71 = {0, 2, 1, 0, 1, 1, 1, 2};
    private int[] pieza72 = {0, 0, 1, 0, 2, 0, 2, 1};
    private int[] pieza73 = {0, 0, 0, 1, 0, 2, 1, 0};
    


    // Atributos instanciables:
    public int idPieza, rotaciones;
    public int[] piezaActual;

    // Constructor:
    public pieza(int id) {
        idPieza = id;
        rotaciones = 0;
        switch(idPieza) {
            case 1:
                piezaActual = pieza10;
                break;
            case 2:
                piezaActual = pieza20;
                break;
            case 3:
                piezaActual = pieza30;
                break;
            case 4:
                piezaActual = pieza40;
                break;
            case 5:
                piezaActual = pieza50;
                break;
            case 6:
                piezaActual = pieza60;
                break;
            case 7:
                piezaActual = pieza70;
                break;
            // Sin default porque al constructor solo entran valores entre 1 y 7 para id.
        }
    }

    // Metodos:
    // Getters:

    // Setters:

    // Otros:
    public void rotarPieza() {
        switch(idPieza) {
            case 1:
                break;

            case 2:
                switch(rotaciones) {
                    case 0:
                        piezaActual = pieza21;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = pieza20;
                        rotaciones = 0;
                        break;
                }
                break;

            case 3:
                switch(rotaciones) {
                    case 0:
                        piezaActual = pieza31;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = pieza30;
                        rotaciones = 0;
                        break;
                }
                break;

            case 4:
                switch(rotaciones) {
                    case 0:
                        piezaActual = pieza41;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = pieza40;
                        rotaciones = 0;
                        break;
                }
                break;

            case 5:
                switch(rotaciones) {
                    case 0:
                        piezaActual = pieza51;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = pieza52;
                        rotaciones = 2;
                        break;
                    case 2:
                        piezaActual = pieza53;
                        rotaciones = 3;
                        break;
                    case 3:
                        piezaActual = pieza50;
                        rotaciones = 0;
                        break;
                }
                break;

            case 6:
                switch(rotaciones) {
                    case 0:
                        piezaActual = pieza61;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = pieza62;
                        rotaciones = 2;
                        break;
                    case 2:
                        piezaActual = pieza63;
                        rotaciones = 3;
                        break;
                    case 3:
                        piezaActual = pieza60;
                        rotaciones = 0;
                        break;
                }
                break;

            case 7:
                switch(rotaciones) {
                    case 0:
                        piezaActual = pieza71;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = pieza72;
                        rotaciones = 0;
                        break;
                    case 2:
                        piezaActual = pieza73;
                        rotaciones = 3;
                        break;
                    case 3:
                        piezaActual = pieza70;
                        rotaciones = 0;
                        break;
                }
                break;  
            // Sin default porque son casos limitados a esos numeros      
        }
    }   

    public void imprimirPieza() {
        int i;
        for (i = 0; i < 7; i++) {
            System.out.printf("%d, ", piezaActual[i]);
        }
        System.out.println(piezaActual[i]);
    }
}