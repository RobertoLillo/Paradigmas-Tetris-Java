import java.util.Scanner;

class tetris {
    public static void main(String args[]){ //Method header
        /* System.out.print("Hello ");         //Method body
        System.out.println("World");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
        scanner.close(); */

        pieza nuevaPieza = new pieza(2);

        System.out.println(nuevaPieza.idPieza);
        System.out.println(nuevaPieza.rotaciones);

        nuevaPieza.imprimirPieza();

        nuevaPieza.rotarPieza();
        nuevaPieza.imprimirPieza();

        nuevaPieza.rotarPieza();
        nuevaPieza.imprimirPieza();

        nuevaPieza.rotarPieza();
        nuevaPieza.imprimirPieza();

        nuevaPieza.rotarPieza();
        nuevaPieza.imprimirPieza();

        nuevaPieza.rotarPieza();
        nuevaPieza.imprimirPieza();
        

    }
}
