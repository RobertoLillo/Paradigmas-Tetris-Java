import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Tetris {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t╔═════════════════════╗");
        System.out.println("\t\t║ Bienvenido a Tetris ║");
        System.out.println("\t\t╚═════════════════════╝\n");

        int opcion, retorno;
        boolean verificadorInicial;
        System.out.println("     * Ingrese una de las siguientes opciones *");
        System.out.println("\t     1) Iniciar sesion");
        System.out.println("\t     2) Salir del juego\n");
        System.out.print("Opcion -> ");

        opcion = scanner.nextInt();
        scanner.nextLine();

        // Iniciar sesion
        if (opcion == 1) {
            System.out.println("\n\t*** Iniciar sesion ***\n");

            // Se piden todos los datos de entrada y se crea el tablero inicial vacio.
            int anchoEntrada, altoEntrada, cantidadDePiezas, seedEntrada;
            System.out.print("Ingrese el ancho del tablero: ");
            anchoEntrada = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el alto del tablero: ");
            altoEntrada = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese la cantidad de piezas iniciales: ");
            cantidadDePiezas = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese la semilla inicial para el tablero: ");
            seedEntrada = scanner.nextInt();
            scanner.nextLine();

            // *** Seccion correspondiente a la funcionalidad createBoard() ***
            // Se crea un tablero vacio de ancho NxM (anchoEntrada x altoEntrada)
            // y se colocan las piezas iniciales del tablero, haciendo uso de la semilla.
            Tablero tableroJugar = new Tablero(anchoEntrada, altoEntrada);
            if (cantidadDePiezas > 0) {
                tableroJugar.colocarPiezasIniciales(cantidadDePiezas, seedEntrada);
            }
            // ***************************************************************

            Random numeroRand = new Random();
            numeroRand.setSeed(seedEntrada);
            int idPieza, posicionEntrada, verificador;
            boolean verificadorDeJuego, verificadorDeSeleccion, flag;
            Pieza sigPieza;
            verificadorDeJuego = true;
            while (verificadorDeJuego) {
                // Se genera la siguiente pieza aleatoria.
                idPieza = 1 + numeroRand.nextInt(7);
                sigPieza = new Pieza(idPieza);
                System.out.println("\nSiguiente pieza: ");
                sigPieza.imprimirPieza();
                tableroJugar.imprimirTablero();

                // Seleccion de opciones de juego
                verificadorDeSeleccion = true;
                while (verificadorDeSeleccion) {
                    System.out.println("* Ingrese una de las siguientes opciones * \n\t 1) Colocar pieza \n\t 2) Rotar pieza\n");
                    System.out.print("Opcion -> ");

                    if (scanner.hasNextInt()) {
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        if (opcion == 1) {
                            System.out.println("\nIngrese la posicion en la que desea colocar la pieza: ");
                            System.out.print("Posicion -> ");
                            posicionEntrada = scanner.nextInt();
                            scanner.nextLine();
                            // Verificacion de que la pieza puede quedar colocada dentro del ancho maximo
                            // del tablero.
                            flag = true;
                            while (flag) {
                                if (posicionEntrada - 1 + sigPieza.getAnchoPieza() > tableroJugar.getAncho()) {
                                    System.out.println("* Esa posicion esta fuera del rango o del ancho posible *");
                                    System.out.println("Ingrese la posicion en la que desea colocar la pieza: ");
                                    System.out.print("Posicion -> ");
                                    posicionEntrada = scanner.nextInt();
                                    scanner.nextLine();
                                } else {
                                    flag = false;
                                }

                            }

                            // Se hace el proceso de colocar la pieza.
                            verificador = tableroJugar.play(sigPieza, posicionEntrada - 1);

                            if (verificador == 0) {
                                System.out.println("*** GAME OVER ***");
                                verificadorDeSeleccion = false;
                                verificadorDeJuego = false;
                            } else if (verificador == 1) {
                                tableroJugar.checkBoard();
                                verificadorDeSeleccion = false;
                            }
                        } else if (opcion == 2) { // Rotar Pieza
                            sigPieza.rotarPieza();
                            System.out.println("\nPieza:");
                            sigPieza.imprimirPieza();
                            tableroJugar.imprimirTablero();
                        } else { // Opcion fuera de rango
                            System.out.println("\n* El numero ingresado esta fuera del rango de opciones *");
                        }
                    } else { // Opcion ingresada no numerica
                        System.out.println("\t* Ingrese un valor numerico *");
                        System.out.println("\t 1) Colocar pieza \n\t 2) Rotar pieza\n\n");
                        System.out.print("Opcion -> ");
                    }
                }
            }
        } else if (opcion == 2) {
            System.out.println("\t*** Salir ***\n");
        }
        scanner.close();
    }
}
