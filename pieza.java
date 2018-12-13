public class pieza {
    // Atributos estaticos:

    // Variables piezaXY, donde X = IDPieza e Y = Rotaciones.
    // Estructura pieza: [X0, Y0, X1, Y1, X2, Y2, X3, Y3, anchoPieza, altoPieza].
    // Piezas iniciales sin ningún giro:
    //   1) 11   2) 1   3)  1   4) 1    5)  1    6) 1    7)  1
    //      11      1      11      11      111      1        1
    //              1      1        1               11      11
    //              1                          

    private int[] pieza10 = {0, 0, 0, 1, 1, 0, 1, 1, 2, 2};

    private int[] pieza20 = {0, 0, 1, 0, 2, 0, 3, 0, 1, 4};
    private int[] pieza21 = {0, 0, 0, 1, 0, 2, 0, 3, 4, 1};

    private int[] pieza30 = {0, 1, 0, 2, 1, 0, 1, 1, 2, 3};
    private int[] pieza31 = {0, 0, 1, 0, 1, 1, 2, 1, 3, 2};

    private int[] pieza40 = {0, 0, 0, 1, 1, 1, 1, 2, 2, 3};
    private int[] pieza41 = {0, 1, 1, 0, 1, 1, 2, 0, 3, 2};

    private int[] pieza50 = {0, 0, 0, 1, 0, 2, 1, 1, 3, 2};
    private int[] pieza51 = {0, 1, 1, 0, 1, 1, 2, 1, 2, 3};
    private int[] pieza52 = {0, 1, 1, 0, 1, 1, 1, 2, 3, 2};
    private int[] pieza53 = {0, 0, 1, 0, 1, 1, 2, 0, 2, 3};

    private int[] pieza60 = {0, 0, 0, 1, 1, 0, 2, 0, 2, 3};
    private int[] pieza61 = {0, 0, 0, 1, 0, 2, 1, 2, 3, 2};
    private int[] pieza62 = {0, 1, 1, 1, 2, 0, 2, 1, 2, 3};
    private int[] pieza63 = {0, 0, 1, 0, 1, 1, 1, 2, 3, 2};

    private int[] pieza70 = {0, 0, 0, 1, 1, 1, 2, 1, 2, 3};
    private int[] pieza71 = {0, 2, 1, 0, 1, 1, 1, 2, 3, 2};
    private int[] pieza72 = {0, 0, 1, 0, 2, 0, 2, 1, 2, 3};
    private int[] pieza73 = {0, 0, 0, 1, 0, 2, 1, 0, 3, 2};
    


    // Atributos instanciables:
    private int idPieza, rotaciones;
    private int[] piezaActual;

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

    // Getters:
    public int getIdPieza() {
        return idPieza;
    }

    public int getRotaciones() {
        return rotaciones;
    }

    public int getAnchoPieza() {
        return piezaActual[8];
    }

    public int getAltoPieza() {
        return piezaActual[9];
    }

    // Setters:


    // Metodos:
    public int[] getPosicion(int i) {
        int[] posicionXY = new int[2];
        switch(i) {
            case 1:
                posicionXY[0] = piezaActual[0];
                posicionXY[1] = piezaActual[1];
                break;

            case 2:
                posicionXY[0] = piezaActual[2];
                posicionXY[1] = piezaActual[3];
                break;

            case 3:
                posicionXY[0] = piezaActual[4];
                posicionXY[1] = piezaActual[5];
                break;
                
            case 4:
                posicionXY[0] = piezaActual[6];
                posicionXY[1] = piezaActual[7];
                break;

            // Sin default porque solo entran los valores listados.
        }
        return posicionXY;
    }

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

            // Sin default porque son casos limitados a esos numeros.    
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