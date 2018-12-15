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
            }
            else if (opcion == 2) {
                System.out.println("Cuenta jugador\n");
            }
            else if (opcion == 3) {
                System.out.println("Salir\n");
            }
        }

        System.out.println("1 para crear tablero");
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
            if (opcion == 1) {
                tablero tableroJugar = new tablero(5, 10);
                tableroJugar.imprimirTablero();
                pieza p1 = new pieza(1);
                tableroJugar.play(p1, 0);
                System.out.println();
                tableroJugar.imprimirTablero();
            }
        }
        /*
        // Verificacion de que la pieza puede quedar colocada dentro del ancho maximo
        // del tablero.
        flag1 = true;
        while (flag1) {
            if (posicionEntrada + anchoPieza > ancho) {
                posicionEntrada = numeroRandom.nextInt(ancho);
            } else {
                flag1 = false;
            }
        }
        */

        scanner.close();
    }
}
