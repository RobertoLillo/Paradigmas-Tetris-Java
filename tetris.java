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

                int i, piezaEntrada, posicionEntrada, verificador;
                tablero tableroJugar = new tablero(5, 10);
                tableroJugar.imprimirTablero();

                for (i = 0; i < 3; i++) {
                    System.out.print("Ingrese el numero de la pieza: ");
                    piezaEntrada = scanner.nextInt();
                    pieza p1 = new pieza(piezaEntrada);
                    System.out.print("Ingrese la posicion de la pieza: ");
                    posicionEntrada = scanner.nextInt();
                    verificador = tableroJugar.play(p1, posicionEntrada);
                    System.out.printf("Verificador: %d\n", verificador);
                    tableroJugar.imprimirTablero();
                    System.out.printf("Puntaje: %d\n", tableroJugar.getPuntaje());
                    System.out.printf("CantidadP: %d\n\n", tableroJugar.getCantidadDePiezas());
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
