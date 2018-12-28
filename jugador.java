import java.io.*;
import java.util.Scanner;

/**
 * Clase Jugador Clase en la que se encuentran todos los datos y manejos
 * respectivos al jugador y al manejo de archivos de este
 * 
 * @author: Roberto Lillo Toloza
 * @version: 27/12/2018
 */
public class Jugador {
    // Atributos
    private String nombre;
    private int partidasJugadas, lineasEliminadas;
    private int[] puntajesAltos = new int[3];

    /**
     * Constructor de la clase jugador, mantiene un nombre que solo es asignable
     * hasta que se inicia sesion.
     */
    public Jugador() {
        this.nombre = "placeholder";
    }

    // Setters
    public void setPartidasJugadas() {
        partidasJugadas++;
    }

    public void setLineasEliminadas(int lineasEliminadas) {
        this.lineasEliminadas += lineasEliminadas;
    }

    public void setPuntajesAltos(int puntaje) {
        if (puntaje > puntajesAltos[0]) {
            int auxiliar1, auxiliar2;
            auxiliar1 = puntajesAltos[0];
            puntajesAltos[0] = puntaje;
            auxiliar2 = puntajesAltos[1];
            puntajesAltos[1] = auxiliar1;
            puntajesAltos[2] = auxiliar2;

        } else if (puntaje > puntajesAltos[1]) {
            int auxiliar1;
            auxiliar1 = puntajesAltos[1];
            puntajesAltos[1] = puntaje;
            puntajesAltos[2] = auxiliar1;

        } else if (puntaje > puntajesAltos[2]) {
            puntajesAltos[2] = puntaje;
        }
    }

    // Getters
    public String getNombre() {
        return this.nombre;
    }

    public int getPartidasJugadas() {
        return this.partidasJugadas;
    }

    public int getLineasEliminadas() {
        return this.lineasEliminadas;
    }

    public int[] getPuntajesAltos() {
        return this.puntajesAltos;
    }

    // Metodos
    /**
     * Se utiliza cuando no se encuentra el archivo con cuentas junto al programa.
     */
    private void crearArchivoCuentas() {
        File archivoCuentas = new File("cuentas.txt");

        try {
            archivoCuentas.createNewFile();
            FileWriter escrituraFWCuentas = new FileWriter(archivoCuentas);
            PrintWriter escrituraPWCuentas = new PrintWriter(escrituraFWCuentas);

            escrituraPWCuentas.println("N.Jugador\t\tPassword");
            System.out.println("\nSe creo el archivo cuentas.txt");

            escrituraPWCuentas.close();
            escrituraFWCuentas.close();

        } catch (Exception e) {
            System.out.printf("Problema crearArchivoCuentas: %s", e);
        }
    }

    /**
     * Se utiliza cuando no se encuentra el archivo con datos junto al programa.
     */
    private void crearArchivoDatos() {
        File archivoDatos = new File("datos.txt");

        try {
            archivoDatos.createNewFile();
            FileWriter escrituraFWDatos = new FileWriter(archivoDatos);
            PrintWriter escrituraPWDatos = new PrintWriter(escrituraFWDatos);

            escrituraPWDatos.println("N.Jugador\t\tP.Jugadas\tL.Elimin\tP.Altos");
            System.out.println("\nSe creo el archivo datos.txt");

            escrituraPWDatos.close();
            escrituraFWDatos.close();

        } catch (Exception e) {
            System.out.printf("Problema en crearArchivoDatos: %s", e);
        }
    }

    /**
     * Inicia la creacion de un usuario, el cual luego se guarda en el archivo
     * "cuentas.txt".
     * 
     * @param scanner scanner para datos de entrada que se utiliza en todo el
     *                programa
     */
    public void crearUsuario(Scanner scanner) {
        int verificador;
        boolean flag;
        String nombre, password, passwordAux;

        flag = true;
        verificador = 0;
        do {
            System.out.print("\nIngrese el nombre de usuario: ");
            nombre = scanner.nextLine();
            System.out.print("Ingrese la password: ");
            password = scanner.nextLine();
            System.out.print("Ingrese la password nuevamente: ");
            passwordAux = scanner.nextLine();

            if (password.equals(passwordAux)) {
                verificador = agregarCuenta(nombre, password);
            }

            if (verificador == 1) {
                flag = false;
            }

        } while (flag);
    }

    /**
     * Escribe la cuenta nueva dentro del archivo "cuentas.txt". Previamente
     * verifica que el nombre de usuario no exista anteriormente.
     * 
     * @param nombre   Nombre del usuario
     * @param password Password del usuario
     * @return 0 si es que el nombre de usuario ya existe, 1 Si es que se crea la
     *         cuenta,
     */
    public int agregarCuenta(String nombre, String password) {
        File archivoCuentas = new File("cuentas.txt");

        if (!archivoCuentas.exists()) {
            crearArchivoCuentas();
        }

        try {
            FileReader lecturaFRCuentas = new FileReader(archivoCuentas);
            BufferedReader lecturaBRCuentas = new BufferedReader(lecturaFRCuentas);
            FileWriter escrituraFWCuentas = new FileWriter(archivoCuentas, true);
            PrintWriter escrituraPWCuentas = new PrintWriter(escrituraFWCuentas);

            int verificador;
            String linea;
            String[] lineaSplit;
            boolean flag;

            verificador = 0;
            flag = true;
            linea = lecturaBRCuentas.readLine();
            while (linea != null && flag) {
                lineaSplit = linea.split("\t\t\t");
                if (lineaSplit[0].equals(nombre)) { // El nombre de usuario ya existe
                    System.out.printf("\nEse nombre de usuario ya esta ocupado");
                    verificador = 0;
                    flag = false;

                } else {
                    linea = lecturaBRCuentas.readLine();
                }
            }

            if (flag) { // El nombre de usuario no existe
                if (nombre.length() > 3 && nombre.length() < 8) {
                    escrituraPWCuentas.println(nombre + "\t\t\t" + password);
                    guardarDatosCuenta(nombre); // Agrega el usuario nuevo a datos, con cero en los valores
                    System.out.printf("\nSe ha creado el usuario: %s\n", nombre);
                    verificador = 1;
                    flag = false;

                } else {
                    System.out.println("\nEl nombre de usuario debe tener entre 4 a 7 caracteres");
                    verificador = 0;
                }

            }

            escrituraPWCuentas.close();
            escrituraFWCuentas.close();
            lecturaBRCuentas.close();
            lecturaFRCuentas.close();
            return verificador;

        } catch (Exception e) {
            System.out.printf("Problema en agregarCuenta: %s\n", e);
            return 0;
        }
    }

    /**
     * Solicita datos de entrada para intentar iniciar sesion.
     * 
     * @param scanner scanner para datos de entrada que se utiliza en todo el
     *                programa
     */
    public void iniciarSesion(Scanner scanner) {
        int verificador;
        boolean flag;
        String nombre, password;

        flag = true;
        do {
            System.out.print("\nIngrese el nombre de usuario: ");
            nombre = scanner.nextLine();
            System.out.print("Ingrese la password: ");
            password = scanner.nextLine();

            verificador = buscarUsuario(nombre, password);

            if (verificador == 2) {
                this.nombre = nombre;
                recuperarDatosCuenta();
                System.out.printf("\nSe ha iniciado sesion para el usuario: %s\n", this.nombre);
                flag = false;
            }

        } while (flag);
    }

    /**
     * Verifica que un usuario existe, tanto el usuario solo como la combinacion
     * usuario-password
     * 
     * @param nombre Nombre del usuario
     * @param pass   Password del usuario
     * @return 0 si el usuario no existe, 1 si la password es incorrecta, 2 si la
     *         combinacion es correcta
     */
    public int buscarUsuario(String nombre, String pass) {
        File archivoCuentas = new File("cuentas.txt");

        if (!archivoCuentas.exists()) {
            crearArchivoCuentas();
        }

        try {
            FileReader lecturaFRCuentas = new FileReader(archivoCuentas);
            BufferedReader lecturaBRCuentas = new BufferedReader(lecturaFRCuentas);

            int verificador;
            String linea;
            String[] lineaSplit;
            boolean flag;

            // Revision de si la cuenta existe.
            verificador = 0;
            flag = true;
            do {
                linea = lecturaBRCuentas.readLine();
                if (linea != null) {
                    lineaSplit = linea.split("\t\t\t");
                    if (lineaSplit[0].equals(nombre)) { // El nombre de usuario existe
                        if (lineaSplit[1].equals(pass)) { // La password corresponde al usuario
                            flag = false;
                            verificador = 2;

                        } else { // La password no corresponde al nombre de usuario
                            System.out.println("\nLa password ingresada es incorrecta");
                            flag = false;
                            verificador = 1;
                        }
                    }
                }
            } while (flag && linea != null);

            if (flag) { // No se encontro el nombre de usuario ingresado
                System.out.println("\nNo existe un usuario con ese nombre");
                verificador = 0;
            }

            lecturaBRCuentas.close();
            lecturaFRCuentas.close();
            return verificador;

        } catch (Exception e) {
            System.out.printf("Problema en buscarUsuario: %s\n", e);
            return 0;
        }
    }

    /**
     * Lee el archivo "datos.txt" y recupera todos los datos como cantidad de
     * partidas, total de lineas eliminadas y mejores 3 puntajes.
     */
    public void recuperarDatosCuenta() {
        File archivoDatos = new File("datos.txt");

        if (!archivoDatos.exists()) {
            crearArchivoDatos();
        }

        try {
            FileReader lecturaFRDatos = new FileReader(archivoDatos);
            BufferedReader lecturaBRDatos = new BufferedReader(lecturaFRDatos);

            String linea;
            String[] lineaSplit, puntajesSplit;
            boolean flag;

            flag = true;
            linea = lecturaBRDatos.readLine();
            while (linea != null && flag) {
                lineaSplit = linea.split("\t\t\t");
                if (lineaSplit[0].equals(this.nombre)) {
                    this.partidasJugadas = Integer.parseInt(lineaSplit[1]);
                    this.lineasEliminadas = Integer.parseInt(lineaSplit[2]);

                    puntajesSplit = lineaSplit[3].split(",");
                    this.puntajesAltos[0] = Integer.parseInt(puntajesSplit[0]);
                    this.puntajesAltos[1] = Integer.parseInt(puntajesSplit[1]);
                    this.puntajesAltos[2] = Integer.parseInt(puntajesSplit[2]);

                    flag = false;

                } else {
                    linea = lecturaBRDatos.readLine();
                }
            }

            lecturaBRDatos.close();
            lecturaFRDatos.close();

        } catch (Exception e) {
            System.out.printf("Problema en recuperarDatosCuenta: %s\n", e);
        }
    }

    /**
     * Escribe los datos que sean necesarios en el archivo "datos.txt".
     * 
     * @param nombre Nombre del usuario
     */
    public void guardarDatosCuenta(String nombre) {
        File archivoDatos = new File("datos.txt");

        if (!archivoDatos.exists()) {
            crearArchivoDatos();
        }

        try {
            FileReader lecturaFRDatos = new FileReader(archivoDatos);
            BufferedReader lecturaBRDatos = new BufferedReader(lecturaFRDatos);

            String linea, auxiliar;
            String[] lineaSplit;
            boolean flag;

            flag = true;
            auxiliar = "";
            linea = lecturaBRDatos.readLine();
            while (linea != null) {
                lineaSplit = linea.split("\t\t\t");
                if (lineaSplit[0].equals(nombre)) {
                    auxiliar += nombre + "\t\t\t";
                    auxiliar += Integer.toString(this.partidasJugadas) + "\t\t\t";
                    auxiliar += Integer.toString(this.lineasEliminadas) + "\t\t\t";
                    auxiliar += Integer.toString(this.puntajesAltos[0]) + ",";
                    auxiliar += Integer.toString(this.puntajesAltos[1]) + ",";
                    auxiliar += Integer.toString(this.puntajesAltos[2]) + "\n";

                    linea = lecturaBRDatos.readLine();
                    flag = false;

                } else {
                    auxiliar += linea + "\n";
                    linea = lecturaBRDatos.readLine();
                }
            }

            if (flag) { // No existia registro de datos del usuario
                auxiliar += nombre + "\t\t\t";
                auxiliar += "0\t\t\t";
                auxiliar += "0\t\t\t";
                auxiliar += "0,";
                auxiliar += "0,";
                auxiliar += "0\n";
            }

            FileWriter escrituraFWDatos = new FileWriter(archivoDatos);
            PrintWriter escrituraPWDatos = new PrintWriter(escrituraFWDatos);

            escrituraPWDatos.print(auxiliar);

            escrituraPWDatos.close();
            escrituraFWDatos.close();
            lecturaBRDatos.close();
            lecturaFRDatos.close();

        } catch (Exception e) {
            System.out.printf("\nProblema en guardarDatosCuenta: %s", e);
        }
    }
}