import java.util.Scanner;
import java.util.Random;

class Tetris {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t╔═════════════════════╗");
        System.out.println("\t\t║ Bienvenido a Tetris ║");
        System.out.println("\t\t╚═════════════════════╝");

        // ####################################################################################
        // ********************** Seccion de inicio o creacion de cuenta **********************
        // ####################################################################################
        int opcion;
        boolean inicioSesion;
        Jugador jugadorActual = new Jugador();

        inicioSesion = true;
        opcion = 0;
        while (inicioSesion) {
            System.out.println("\nIngrese una opcion");
            System.out.println(" 1) Iniciar sesion");
            System.out.println(" 2) Crear usuario");
            System.out.println(" 3) Salir");
            System.out.print("\nOpcion -> ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

            } else {
                opcion = 4;
            }

            if (opcion < 3) {
                if (opcion == 1) {
                    // Iniciar sesion
                    System.out.println("\nIniciar sesion");
                    jugadorActual.iniciarSesion(scanner);
                    inicioSesion = false;

                } else if (opcion == 2) {
                    // Crear usuario
                    System.out.println("\nCrear usuario");
                    jugadorActual.crearUsuario(scanner);
                }

            } else if (opcion == 3) {
                // Salir
                System.out.println("\nSalir");
                inicioSesion = false;

            } else if (opcion == 4) {
                // Dato ingresado no numerico
                System.out.println("\nEl caracter ingresado no es numerico");
                scanner.nextLine();

            } else {
                // Opcion fuera de rango
                System.out.println("\nLa opcion esta fuera de rango");
            }
        }
        // ####################################################################################
        // ####################################################################################

        // ####################################################################################
        // ********************************* Seccion de juego *********************************
        // ####################################################################################
        if (opcion == 1) {
            // *******************************************************
            // ******* Seccion correspondiente a createBoard() *******
            // *******************************************************
            int ancho, alto, cantPiezas, semilla;

            System.out.print("\nIngrese el ancho del tablero: ");
            ancho = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese el alto del tablero: ");
            alto = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese la cantidad de piezas: ");
            cantPiezas = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese la semilla: ");
            semilla = scanner.nextInt();
            scanner.nextLine();

            Tablero tableroActual = new Tablero(ancho, alto);
            tableroActual.colocarPiezasIniciales(cantPiezas, semilla);
            // *******************************************************
            // *******************************************************

            int verificador, idPieza, sigOpcion, posicion;
            boolean jugar, preguntar, preguntarPosicion;
            Pieza sigPieza;

            Random random = new Random();
            random.setSeed(semilla);

            jugar = true;
            while (jugar) {
                idPieza = 1 + random.nextInt(7); // Numero del 1 al 7
                sigPieza = new Pieza(idPieza);
                // ***********************************************************************
                // ******************** Bloque de seleccion principal ********************
                // ***********************************************************************
                preguntar = true;
                while (preguntar) {
                    System.out.println("\nSiguiente pieza a colocar\n");
                    sigPieza.imprimirPieza();
                    tableroActual.imprimirTablero();

                    System.out.println("\nIngrese una opcion:");
                    System.out.println(" 1) Colocar pieza");
                    System.out.println(" 2) Rotar pieza");
                    System.out.print("\nOpcion -> ");

                    if (scanner.hasNextInt()) {
                        sigOpcion = scanner.nextInt();
                        scanner.nextLine();
                        // ***************************************************************
                        // *************** Bloque de seleccion de posicion ***************
                        // ***************************************************************
                        if (sigOpcion == 1) {
                            posicion = 0;
                            verificador = 0;

                            preguntarPosicion = true;
                            while (preguntarPosicion) {
                                System.out.print("Ingrese la posicion: ");

                                if (scanner.hasNextInt()) {
                                    posicion = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (posicion >= 0
                                            && posicion + sigPieza.getAnchoPieza() <= tableroActual.getAncho()) {
                                        // Se intena colocar la pieza
                                        verificador = tableroActual.play(sigPieza, posicion);

                                        if (verificador == 0) { // Game Over
                                            preguntarPosicion = false;
                                            preguntar = false;
                                            jugar = false;
                                        }
                                        if (verificador == 1) { // Se coloco la pieza
                                            tableroActual.checkBoard();
                                            preguntarPosicion = false;
                                            preguntar = false;
                                        }

                                    } else {
                                        System.out.println("\nCon esa posicion la pieza queda fuera de rango");
                                    }

                                } else {
                                    System.out.println("\nEl caracter ingresado no es numerico");
                                    scanner.nextLine();
                                }
                            }
                            // ***************************************************************
                            // ***************************************************************

                        } else if (sigOpcion == 2) {
                            sigPieza.rotarPieza();

                        } else {
                            System.out.println("\nEl numero ingresado esta fuera de rango");
                        }

                    } else {
                        System.out.println("\nEl caracter ingresado no es numerico");
                        scanner.nextLine();
                    }
                }
            }
            // ***************************************************************
            // *************** Bloque de finalizacion de juego ***************
            // ***************************************************************
            int lineasEliminadas = tableroActual.getLineasEliminadas();
            int puntajeObtenido = tableroActual.getPuntaje();

            jugadorActual.setPartidasJugadas();
            jugadorActual.setLineasEliminadas(lineasEliminadas);
            jugadorActual.setPuntajesAltos(puntajeObtenido);

            String jugador = jugadorActual.getNombre();

            jugadorActual.guardarDatosCuenta(jugador);

            System.out.println("Game Over");
        }
        // ###################################################################################
        // ###################################################################################

        scanner.close();
    }
}
