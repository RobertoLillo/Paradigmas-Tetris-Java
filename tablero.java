import java.util.Random;
import java.util.ArrayList;

class tablero {
    // Atributos estaticos:

    // Atributos instanciables:
    public int ancho, alto, cantidadDePiezas, puntaje;
    private ArrayList<int[]> tableroActual = new ArrayList<int[]>();
    private ArrayList<pieza> listaPiezas = new ArrayList<pieza>();
    private int[] filaCompleta;

    // Constructor:
    // Este constructor corresponde a la funcionalidad createBoard().
    public tablero(int anchoEntrada, int altoEntrada, int cantPiezas, int seedEntrada) {

        // Asignacion de los atributos instanciables y creacion del tablero vacio.
        ancho = anchoEntrada;
        alto = altoEntrada;
        cantidadDePiezas = cantPiezas;
        puntaje = 0;

        int i, j;
        for (i = 0; i < alto; i++) {
            int[] fila = new int[ancho];
            for (j = 0; j < ancho; j++) {
                fila[j] = 0;
            }
            tableroActual.add(fila);
        }
        
        int[] filaAuxiliar = new int[ancho];
        for (i = 0; i < ancho; i++) {
            filaAuxiliar[i] = 1;
        }
        filaCompleta = filaAuxiliar;

        // Insercion de piezas al tablero.
        int numeroIdPieza, numeroPosicion, contador;
        int[] copiaFila;
        boolean flag;
        Random numeroRandom = new Random();
        numeroRandom.setSeed(seedEntrada);
        for (i = 0; i < cantidadDePiezas; i++) {
            numeroIdPieza = 1 + numeroRandom.nextInt(7);
            numeroPosicion = numeroRandom.nextInt(ancho);
            pieza piezaActual = new pieza(numeroIdPieza);

            flag = true;
            contador = alto - 1;
            while (flag && contador >= 0) {
                copiaFila = tableroActual.get(contador);
                if (copiaFila[numeroPosicion] == 1) {
                    copiaFila = tableroActual.get(contador + 1);
                    flag = false;
                }
                else {
                    contador--;
                }
            }

            

            listaPiezas.add(piezaActual);

        }

    }

    // Metodos:
    public void imprimirTablero() {
        int i, j;
        int[] auxiliar = new int[ancho];
        for (i = 0; i < alto; i++) {
            auxiliar = tableroActual.get(i);
            for (j = 0; j < ancho; j++) {
                System.out.print(auxiliar[j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    

}