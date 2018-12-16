import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class tetris {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t-----------------------");
        System.out.println("\t| Bienvenido a Tetris |");
        System.out.println("\t-----------------------\n");

        System.out.print("Ingrese la opcion que desea: ");

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
                        
                        System.out.println("Piezas colocadas:");
                        sigPieza.imprimirPieza();

                        verificador = tableroJugar.play(sigPieza, posicion);
                        if (verificador == 2) {
                            cantidadDePiezas--;
                        }
                    }
                    tableroJugar.imprimirTablero();
                }
                /*
                int posicionEntrada;
                boolean verificadorDeJuego;
                verificadorDeJuego = true;
                while (verificadorDeJuego) {
                    // Se genera la siguiente pieza aleatoria.

                    // Se pide la posicion donde colocar la pieza.
                    System.out.print("Ingrese la posicion de la pieza: ");
                    posicionEntrada = scanner.nextInt();

                    // Se hace el proceso de intentar colocar la pieza.
                    verificador = tableroJugar.play(p1, posicionEntrada - 1);

                    System.out.printf("Verificador: %d\n", verificador);
                    tableroJugar.imprimirTablero();
                    System.out.printf("Puntaje: %d\n", tableroJugar.getPuntaje());
                    System.out.printf("CantidadP: %d\n\n", tableroJugar.getCantidadDePiezas());
                }
                */
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
