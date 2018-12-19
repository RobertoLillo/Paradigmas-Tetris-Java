public class Jugador {
    // Atributos estaticos

    // Atributos instanciables
    private String nombre, password;
    private int partidasJugadas, lineasEliminadas;
    private int[] puntajesAltos = new int[3];

    // Constructor
    public Jugador(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    // Setters
    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }
    
    public void setLineasEliminadas(int lineasEliminadas) {
        this.lineasEliminadas = lineasEliminadas;
    }

    public void setPuntajesAltos(int p1, int p2, int p3) {
        this.puntajesAltos[0] = p1;
        this.puntajesAltos[1] = p2;
        this.puntajesAltos[2] = p3;
    }

    // Getters
    public String getNombreJugador() {
        return this.nombreJugador;
    }

    public String getPassword() {
        return this.contrasena;
    }

    public int getNumPartidasJugadas() {
        return this.numPartidasJugadas;
    }

    public int getNumLineasEliminadas() {
        return this.numLineasEliminadas;
    }

    public int[] getPuntajesAltos() {
        return this.puntajesAltos;
    }

    // Metodos
    public void crearUsuario(String usuario, String pass) {

    }

    public void iniciarSesion(String usuario, String pass) {

    } 

    // cargarPartida
}