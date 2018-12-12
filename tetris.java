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
                System.out.println("Jugar");
            }
            else if (opcion == 2) {
                System.out.println("Cuenta jugador");
            }
            else if (opcion == 3) {
                System.out.println("Salir");
            }
        }
        /*
        ArrayList<pieza> listaPiezas = new ArrayList<pieza>();
        int i;
        Random rand = new Random();
        rand.setSeed(123);
        for (i = 0; i < 3; i++) {
            pieza piezaNueva = new pieza(1 + rand.nextInt(7));
            listaPiezas.add(piezaNueva);
        }
        for (i = 0; i < 3; i++) {
            System.out.print(listaPiezas.get(i).idPieza);
            System.out.print("-");
            System.out.println(listaPiezas.get(i).rotaciones);
        }
        */

        tablero tableroMuestra = new tablero(5,10,4,2018);
        tableroMuestra.imprimirTablero();
        scanner.close();
    }
}
