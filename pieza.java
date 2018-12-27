class Pieza {
    // *** Atributos ***

    // Variables piezaXY, donde X = IDPieza e Y = Rotaciones.
    // Estructura pieza: [X0, Y0, X1, Y1, X2, Y2, X3, Y3, anchoPieza, altoPieza].
    // Piezas iniciales sin ningún giro:
    //   1) 11   2) 1   3)  1   4) 1    5)  1    6) 1    7)  1
    //      11      1      11      11      111      1        1
    //              1      1        1               11      11
    //              1                          

    private int[] piezaR0;
    private int[] piezaR1;
    private int[] piezaR2;
    private int[] piezaR3;

    private int idPieza, rotaciones;
    private int[] piezaActual;

    // *** Constructor ***
    public Pieza(int id) {
        idPieza = id;
        rotaciones = 0;
        switch(idPieza) {
            case 1:
                int[] pieza10 = {0, 0, 0, 1, 1, 0, 1, 1, 2, 2};
                this.piezaR0 = pieza10;
                piezaActual = this.piezaR0;
                break;
            case 2:
                int[] pieza20 = {0, 0, 1, 0, 2, 0, 3, 0, 1, 4};
                int[] pieza21 = {0, 0, 0, 1, 0, 2, 0, 3, 4, 1};
                this.piezaR0 = pieza20;
                this.piezaR1 = pieza21;
                piezaActual = this.piezaR0;
                break;
            case 3:
                int[] pieza30 = {0, 0, 1, 0, 1, 1, 2, 1, 2, 3};
                int[] pieza31 = {0, 1, 0, 2, 1, 0, 1, 1, 3, 2};
                this.piezaR0 = pieza30;
                this.piezaR1 = pieza31;
                piezaActual = this.piezaR0;
                break;
            case 4:
                int[] pieza40 = {0, 1, 1, 0, 1, 1, 2, 0, 2, 3};
                int[] pieza41 = {0, 0, 0, 1, 1, 1, 1, 2, 3, 2};
                this.piezaR0 = pieza40;
                this.piezaR1 = pieza41;
                piezaActual = this.piezaR0;
                break;
            case 5:
                int[] pieza50 = {0, 0, 0, 1, 0, 2, 1, 1, 3, 2};
                int[] pieza51 = {0, 0, 1, 0, 1, 1, 2, 0, 2, 3};
                int[] pieza52 = {0, 1, 1, 0, 1, 1, 1, 2, 3, 2};
                int[] pieza53 = {0, 1, 1, 0, 1, 1, 2, 1, 2, 3};
                this.piezaR0 = pieza50;
                this.piezaR1 = pieza51;
                this.piezaR2 = pieza52;
                this.piezaR3 = pieza53;
                piezaActual = this.piezaR0;
                break;
            case 6:
                int[] pieza60 = {0, 0, 0, 1, 1, 0, 2, 0, 2, 3};
                int[] pieza61 = {0, 0, 1, 0, 1, 1, 1, 2, 3, 2};
                int[] pieza62 = {0, 1, 1, 1, 2, 0, 2, 1, 2, 3};
                int[] pieza63 = {0, 0, 0, 1, 0, 2, 1, 2, 3, 2};
                this.piezaR0 = pieza60;
                this.piezaR1 = pieza61;
                this.piezaR2 = pieza62;
                this.piezaR3 = pieza63;
                piezaActual = this.piezaR0;
                break;
            case 7:
                int[] pieza70 = {0, 0, 0, 1, 1, 1, 2, 1, 2, 3};
                int[] pieza71 = {0, 0, 0, 1, 0, 2, 1, 0, 3, 2};
                int[] pieza72 = {0, 0, 1, 0, 2, 0, 2, 1, 2, 3};
                int[] pieza73 = {0, 2, 1, 0, 1, 1, 1, 2, 3, 2};
                this.piezaR0 = pieza70;
                this.piezaR1 = pieza71;
                this.piezaR2 = pieza72;
                this.piezaR3 = pieza73;
                piezaActual = this.piezaR0;
                break;
            // Sin default porque al constructor solo entran valores entre 1 y 7 para id.
        }
    }
    
    // *** Setters ***

    // *** Getters ***
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

    // Metodos:
    public void rotarPieza() {
        switch(idPieza) {
            case 1:
                break;

            case 2:
                switch(rotaciones) {
                    case 0:
                        piezaActual = this.piezaR1;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = this.piezaR0;
                        rotaciones = 0;
                        break;
                }
                break;

            case 3:
                switch(rotaciones) {
                    case 0:
                        piezaActual = this.piezaR1;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = this.piezaR0;
                        rotaciones = 0;
                        break;
                }
                break;

            case 4:
                switch(rotaciones) {
                    case 0:
                        piezaActual = this.piezaR1;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = this.piezaR0;
                        rotaciones = 0;
                        break;
                }
                break;

            case 5:
                switch(rotaciones) {
                    case 0:
                        piezaActual = this.piezaR1;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = this.piezaR2;
                        rotaciones = 2;
                        break;
                    case 2:
                        piezaActual = this.piezaR3;
                        rotaciones = 3;
                        break;
                    case 3:
                        piezaActual = this.piezaR0;
                        rotaciones = 0;
                        break;
                }
                break;

            case 6:
                switch(rotaciones) {
                    case 0:
                        piezaActual = this.piezaR1;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = this.piezaR2;
                        rotaciones = 2;
                        break;
                    case 2:
                        piezaActual = this.piezaR3;
                        rotaciones = 3;
                        break;
                    case 3:
                        piezaActual = this.piezaR0;
                        rotaciones = 0;
                        break;
                }
                break;

            case 7:
                switch(rotaciones) {
                    case 0:
                        piezaActual = this.piezaR1;
                        rotaciones = 1;
                        break;
                    case 1:
                        piezaActual = this.piezaR2;
                        rotaciones = 2;
                        break;
                    case 2:
                        piezaActual = this.piezaR3;
                        rotaciones = 3;
                        break;
                    case 3:
                        piezaActual = this.piezaR0;
                        rotaciones = 0;
                        break;
                }
                break;  

            // Sin default porque son casos limitados a esos numeros.    
        }
    }   

    public void imprimirPieza() {
        switch (idPieza) {
            case 1:
                System.out.println("\t##\n\t##");
                break;
            
            case 2:
                if (rotaciones == 0) {
                    System.out.println("\t#\n\t#\n\t#\n\t#");
                }
                else if (rotaciones == 1) {
                    System.out.println("\t####");
                }
                break;

            case 3:
                if (rotaciones == 0) {
                    System.out.println("\t #\n\t##\n\t#");
                }
                else if (rotaciones == 1) {
                    System.out.println("\t##\n\t ##");
                }
                break;

            case 4:
                if (rotaciones == 0) {
                    System.out.println("\t#\n\t##\n\t #");
                }
                else if (rotaciones == 1) {
                    System.out.println("\t ##\n\t##");
                }
                break;

            case 5:
                if (rotaciones == 0) {
                    System.out.println("\t #\n\t###");
                }
                else if (rotaciones == 1) {
                    System.out.println("\t#\n\t##\n\t#");
                }
                else if (rotaciones == 2) {
                    System.out.println("\t###\n\t #");
                }
                else if (rotaciones == 3) {
                    System.out.println("\t #\n\t##\n\t #");
                }
                break;

            case 6:
                if (rotaciones == 0) {
                    System.out.println("\t#\n\t#\n\t##");
                }
                else if (rotaciones == 1) {
                    System.out.println("\t###\n\t#");
                }
                else if (rotaciones == 2) {
                    System.out.println("\t##\n\t #\n\t #");
                }
                else if (rotaciones == 3) {
                    System.out.println("\t  #\n\t###");
                }
                break;
            
            case 7:
                if (rotaciones == 0) {
                    System.out.println("\t #\n\t #\n\t##");
                }
                else if (rotaciones == 1) {
                    System.out.println("\t#\n\t###");
                }
                else if (rotaciones == 2) {
                    System.out.println("\t##\n\t#\n\t#");
                }
                else if (rotaciones == 3) {
                    System.out.println("\t###\n\t  #");
                }
                break;
            
        }
    }
}