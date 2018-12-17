import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class tetris {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t╔═════════════════════╗");
        System.out.println("\t\t║ Bienvenido a Tetris ║");
        System.out.println("\t\t╚═════════════════════╝\n");

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
                Random numeroRand = new Random();
                numeroRand.setSeed(seedEntrada);

                // Se colocan las piezas iniciales.
                int idPieza, cantGiros, posicion, verificador;
                boolean flag;
                pieza sigPieza;
                if (cantidadDePiezas > 0) {
                    while (cantidadDePiezas > 0) {
                        idPieza = 1 + numeroRand.nextInt(7);
                        cantGiros = numeroRand.nextInt(4);
                        posicion = numeroRand.nextInt(tableroJugar.getAncho());

                        sigPieza = new pieza(idPieza);
                        while (cantGiros > 0) {
                            sigPieza.rotarPieza();
                            cantGiros--;
                        }

                        // Verificacion de que la pieza puede quedar colocada dentro del ancho maximo del tablero.
                        flag = true;
                        while (flag) {
                            if (posicion + sigPieza.getAnchoPieza() > tableroJugar.getAncho()) {
                                posicion = numeroRand.nextInt(tableroJugar.getAncho());
                                System.out.println(posicion);
                                
                            }
                            else {
                                verificador = tableroJugar.play(sigPieza, posicion);
                                tableroJugar.checkBoard();
                                cantidadDePiezas--;
                                flag = false;
                            }
                        }
                    }
                }
                
                int posicionEntrada;
                boolean verificadorDeJuego, verificadorDeSeleccion;
                verificadorDeJuego = true;
                while (verificadorDeJuego) {
                    // Se genera la siguiente pieza aleatoria.
                    idPieza = 1 + numeroRand.nextInt(7);
                    sigPieza = new pieza(idPieza);
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
                            if (opcion == 1) {
                                System.out.println("\nIngrese la posicion en la que desea colocar la pieza: ");
                                System.out.print("Posicion -> ");
                                posicionEntrada = scanner.nextInt();
                                // Verificacion de que la pieza puede quedar colocada dentro del ancho maximo del tablero.
                                flag = true;
                                while (flag) {
                                    if (posicionEntrada - 1 + sigPieza.getAnchoPieza() > tableroJugar.getAncho()) {
                                        System.out.println("* Esa posicion esta fuera del rango o del ancho posible *");
                                        System.out.println("Ingrese la posicion en la que desea colocar la pieza: ");
                                        System.out.print("Posicion -> ");
                                        posicionEntrada = scanner.nextInt();
                                    } 
                                    else {
                                        flag = false;
                                    }
                                    
                                }

                                // Se hace el proceso de colocar la pieza.
                                verificador = tableroJugar.play(sigPieza, posicionEntrada - 1);

                                if (verificador == 0) {
                                    System.out.println("*** GAME OVER ***");
                                    verificadorDeSeleccion = false;
                                    verificadorDeJuego = false;
                                }
                                else if (verificador == 1) {
                                    tableroJugar.checkBoard();
                                    verificadorDeSeleccion = false;
                                }
                            }
                            // Rotar Pieza
                            else if (opcion == 2) {
                                sigPieza.rotarPieza();
                                System.out.println("\nPieza:");
                                sigPieza.imprimirPieza();
                                tableroJugar.imprimirTablero();
                            }
                            // Opcion fuera de rango
                            else {
                                System.out.println("\n* El numero ingresado esta fuera del rango de opciones *");
                            }
                        }
                        // Opcion ingresada no numerica
                        else {
                            System.out.println("*Ingrese un valor numerico*");
                            System.out.println("* Ingrese una de las siguientes opciones * \n\t 1) Colocar pieza \n\t 2) Rotar pieza\n\n");
                            System.out.print("Opcion -> ");
                        }
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
