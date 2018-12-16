import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class tetris {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t-----------------------");
        System.out.println("\t\t| Bienvenido a Tetris |");
        System.out.println("\t\t-----------------------\n");

        System.out.print("     * Ingrese una de las siguientes opciones * \n\t 1) Jugar \n\t 2) Crear perfil de jugador \n\t 3) Salir del juego\n\n");
        System.out.print("Opcion -> ");
        int opcion;
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
            if (opcion == 1) {
                System.out.println("Jugar\n");

                // Se piden todos los datos de entrda. y se crea el tablero inicial vacio.
                int anchoEntrada, altoEntrada, cantidadDePiezas, seedEntrada;

                System.out.print("Ingrese el ancho del tablero: ");
                anchoEntrada = scanner.nextInt();
                System.out.print("Ingrese el alto del tablero: ");
                altoEntrada = scanner.nextInt();
                System.out.print("Ingrese la cantidad de piezas iniciales: ");
                cantidadDePiezas = scanner.nextInt();
                System.out.print("Ingrese la semilla inicial para el tablero: ");
                seedEntrada = scanner.nextInt();

                tablero tableroJugar = new tablero(anchoEntrada, altoEntrada);

                // Se colocan las piezas iniciales.
                Random numeroRand = new Random();
                int idPieza, cantGiros, posicion, verificador;
                pieza sigPieza;
                if (cantidadDePiezas > 0) {
                    numeroRand.setSeed(seedEntrada);

                    while (cantidadDePiezas > 0) {
                        idPieza = 1 + numeroRand.nextInt(7);
                        cantGiros = numeroRand.nextInt(4);
                        posicion = numeroRand.nextInt(tableroJugar.getAncho());

                        sigPieza = new pieza(idPieza);
                        while (cantGiros > 0) {
                            sigPieza.rotarPieza();
                            cantGiros--;
                        }

                        verificador = tableroJugar.play(sigPieza, posicion);
                        if (verificador == 2) {
                            cantidadDePiezas--;
                        }
                    }
                    tableroJugar.imprimirTablero();
                }
                
                int posicionEntrada;
                boolean verificadorDeJuego;
                verificadorDeJuego = true;
                while (verificadorDeJuego) {
                    // Se genera la siguiente pieza aleatoria.
                    idPieza = 1 + numeroRand.nextInt(7);
                    sigPieza = new pieza(idPieza);
                    System.out.println("Siguiente pieza: ");
                    sigPieza.imprimirPieza();

                    // Se pide la posicion donde colocar la pieza.
                    System.out.print("Ingrese la posicion en la que colocar la pieza: ");
                    posicionEntrada = scanner.nextInt();

                    // Se hace el proceso de intentar colocar la pieza.
                    verificador = tableroJugar.play(sigPieza, posicionEntrada - 1);

                    if (verificador == 0) {
                        System.out.println("*** GAME OVER ***");
                        verificadorDeJuego = false;
                    }
                    else if (verificador ==  1) {
                        System.out.println("*** NO SE PUEDE COLOCAR LA PIEZA DE ESA FORMA");
                    }
                    else if (verificador == 2) {
                        tableroJugar.imprimirTablero();
                    }
                }
                
            }
            else if (opcion == 2) {
                System.out.println("Cuenta jugador\n");
            }
            else if (opcion == 3) {
                System.out.println("Salir\n");
            }
        }        

        scanner.close();
    }
}
