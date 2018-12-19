import java.io.*;

public class Archivos {
    // *** Atributos estaticos ***
    private File archivoCuentas = new File("cuenta.txt");
    private File archivoDatos = new File("datos.txt");
    // *** Atributos instanciables ***

    // *** Constructor ***

    // *** Getters ***

    // *** Setters ***

    // *** Metodos ***

    // Relacionados a cuentas de usuario

    // crearArchivoCuentas
    // Crea el archivo en el que se guardan las cuentas de usuario
    public void crearArchivoCuentas() {
        FileWriter escrituraFR = new FileWriter(archivoCuentas);
        PrintWriter escrituraPW = new PrintWriter(escrituraFR);
        
        escrituraPW.println("N.Jugador\t\tPassword");

        escrituraPW.close();
        escrituraFR.close();
    }

    // leerExisteCuenta
    // Busca un usuario en el archivo de cuentas
    // return 0 -> el usuario no existe
    // return 1 -> la password es incorrecta
    // return 2 -> la combinacion es correcta
    public int leerExisteCuenta(String nombre, String pass) {
        FileReader lecturaFR = new FileReader(archivoCuentas);
        BufferedReader lecturaBR = new BufferedReader(lecturaFR);

        int verificador;
        String linea;
        String[] lineaSplit;
        boolean flag;

        // Revision de si la cuenta existe.
        verificador = 0;
        flag = true;
        do {
            linea = lecturaBR.readLine();
            if (linea != null) {
                lineaSplit = linea.split("\t\t\t");
                if (lineaSplit[0].equals(nombre)) { // El nombre de usuario existe
                    if (lineaSplit[1].equals(pass)) { // La password corresponde al usuario
                        System.out.printf("\nSe ha iniciado sesion para el usuario: %s\n", nombre);
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
            System.out.println("No existe un usuario con ese nombre");
            verificador = 0;
        }

        lecturaBR.close();
        lecturaFR.close();
        return verificador;
    }

    // agregarCuenta
    // Agrega una cuenta de usuario al archivo de cuentas si es que el nombre no se encuentra ya registrado
    // return 0 -> El nombre de usuario ya existe
    // return 1 -> Se crea la cuenta
    public int agregarCuenta(String nombre, String pass) {
        FileReader lecturaFR = new FileReader(archivoCuentas);
        BufferedReader lecturaBR = new BufferedReader(lecturaFR);
        FileWriter escrituraFR = new FileWriter(archivoCuentas, true);
        PrintWriter escrituraPW = new PrintWriter(escrituraFR);

        int verificador;
        String linea;
        String[] lineaSplit;
        boolean flag;

        verificador = 0;
        flag = true;
        do {
            linea = lecturaBR.readLine();
            if (linea != null) {
                lineaSplit = linea.split("\t\t\t");
                if (lineaSplit[0].equals(nombre)) { // El nombre de usuario ya existe
                    System.out.printf("\nEse nombre de usuario ya esta ocupado");
                    flag = false;
                    verificador = 0;

                } else { // El nombre de usuario no existe
                    escrituraPW.println(nombre + "\t\t\t" + pass);
                    System.out.printf("\nSe ha creado el usuario: %s\n", nombre);
                    flag = false;
                    verificador = 1;
                }
            }
        } while (flag && linea != null);

        escrituraPW.close();
        escrituraFR.close();
        lecturaBR.close();
        lecturaFR.close();
        return verificador;
    }

    // Relacionados a los datos de cada usuario

    // crearArchivoDatos
    // Crea el archivo en el que se guardan los datos de cada cuenta
    public void crearArchivoDatos() {
        FileWriter escrituraFR = new FileWriter(archivoDatos);
        PrintWriter escrituraPW = new PrintWriter(escrituraFR);
        
        escrituraPW.println("N.Jugador\t\tPartidas jugadas\t\tLineas eliminadas\t\tPuntajes altos");

        escrituraPW.close();
        escrituraFR.close();
    } 

    // recuperarDatosCuenta
    // Lee el archivo de datos y los recupera para ser usados en la ejecucion
    public void recuperarDatosCuenta(String nombre, Jugador usuario) {
        FileReader lecturaFR = new FileReader(archivoDatos);
        BufferedReader lecturaBR = new BufferedReader(lecturaFR);

        String linea;
        String[] lineaSplit, puntajesSplit;
        boolean flag;

        flag = true;
        do {
            linea = lecturaBR.readLine();
            lineaSplit = linea.split("\t\t\t");
            if (lineaSplit[0].equals(nombre)) {
                usuario.setPartidasJugadas(Integer.parseInt(lineaSplit[1]));
                usuario.setLineasEliminadas(Integer.parseInt(lineaSplit[2]));

                puntajesSplit = lineaSplit[3].split(",");
                usuario.setPuntajesAltos(Integer.parseInt(puntajesSplit[0]), Integer.parseInt(puntajesSplit[1]), Integer.parseInt(puntajesSplit[2]));
            }
        } while (flag);

        lecturaBR.close();
        lecturaFR.close();
    }
}