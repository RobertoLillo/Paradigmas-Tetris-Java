import java.util.Random;
import java.util.ArrayList;

class tablero {
    // Atributos estaticos:

    // Atributos instanciables:
    private int ancho, alto, cantidadDePiezas, puntaje;
    private ArrayList<int[]> tableroActual = new ArrayList<int[]>();
    private ArrayList<pieza> listaPiezas = new ArrayList<pieza>();

    // Constructor:
    public tablero(int anchoEntrada, int altoEntrada) {
        // Asignacion de los atributos instanciables y creacion del tablero vacio.
        ancho = anchoEntrada;
        alto = altoEntrada;
        cantidadDePiezas = 0;
        puntaje = 0;

        int i, j;
        for (i = 0; i < alto; i++) {
            int[] fila = new int[ancho];
            for (j = 0; j < ancho; j++) {
                fila[j] = 0;
            }
            tableroActual.add(fila);
        }
    }

    // Getters:
    public int getAncho() {
        return ancho;
    }
    public int getCantidadDePiezas() {
        return cantidadDePiezas;
    }

    public int getPuntaje() {
        return puntaje;
    }


    // Metodos:
    public int play(pieza piezaEntrada, int posicionEntrada) {
        int i, anchoPieza, altoDisponible, contador;
        int[] copiaFila;
        boolean flag1, flag2;
        anchoPieza = piezaEntrada.getAnchoPieza();

        // Verificacion de que la pieza puede quedar colocada dentro del ancho maximo del tablero.
        flag1 = true;
        while (flag1) {
            if (posicionEntrada + anchoPieza > ancho) {
                return 1; // No se puede colocar en esa posicion.
            } else {
                flag1 = false;
            }
        }

        // Busqueda de que tan abajo se puede colocar la pieza.
        flag1 = true;
        contador = alto - 1;
        altoDisponible = 0;
        copiaFila = tableroActual.get(contador);
        while (flag1 && contador >= 0) {
            flag2 = true;
            for (i = 0; i < anchoPieza; i++) {
                if (copiaFila[posicionEntrada + i] == 1) {
                    flag2 = false;
                }
            }
            if (flag2 && contador > 0) {
                contador--;
                copiaFila = tableroActual.get(contador);
                altoDisponible++;
            }
            else if (flag2 && contador == 0) {
                altoDisponible++;
                flag1 = false;
            }
            else {
                flag1 = false;
            }
        }

        // Verificacion de que la pieza puede colocarse en la cantidad de alto disponible.
        if (altoDisponible == 0) {
            return 0; // Game Over
        }
        else {
            // Dependiendo del tipo de pieza la colocación es distinta.
            // Pieza 1
            if (piezaEntrada.getIdPieza() == 1) {
                if (altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }
            // Pieza 2
            else if (piezaEntrada.getIdPieza() == 2) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 4) {
                    if (copiaFila[posicionEntrada] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 1) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1 || copiaFila[posicionEntrada + 3] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }
            // Pieza 3
            else if (piezaEntrada.getIdPieza() == 3) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }
            // Pieza 4
            else if (piezaEntrada.getIdPieza() == 4) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }
            // Pieza 5
            else if (piezaEntrada.getIdPieza() == 5) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 2 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 3 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }
            // Pieza 6
            else if (piezaEntrada.getIdPieza() == 6) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 2 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 3 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }
            // Pieza 7
            else if (piezaEntrada.getIdPieza() == 7) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 2 && altoDisponible >= 3) {
                    if (copiaFila[posicionEntrada] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else if (piezaEntrada.getRotaciones() == 3 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                }
                else {
                    return 0; // Game Over
                }
            }

            // Colocar la pieza.
            int alturaActual;
            int[] coordenadaBloque;
            alturaActual = 0;
            for (i = 1; i <= 4; i++) {
                coordenadaBloque = piezaEntrada.getPosicion(i);
                if (coordenadaBloque[0] == alturaActual) {
                    copiaFila[posicionEntrada + coordenadaBloque[1]] = 1;
                }
                else if (coordenadaBloque[0] != alturaActual) {
                    alturaActual++;
                    tableroActual.set(contador, copiaFila);
                    contador++;
                    copiaFila = tableroActual.get(contador);
                    copiaFila[posicionEntrada + coordenadaBloque[1]] = 1;
                }
            }
            listaPiezas.add(piezaEntrada);
            cantidadDePiezas++;
            return 2; // Se logró colocar la pieza correctamente.
        }
    }

    public void imprimirTablero() {
        int i, j;
        String stringTablero = "\t\n";
        int[] auxiliar = new int[ancho];
        // Tapa superior del tablero.
        for (i = 0; i < ancho + 2; i++) {
            stringTablero += "-";
        }
        // Parte interior del tablero.
        stringTablero += "\n\t";
        for (i = alto - 1; i >= 0; i--) {
            auxiliar = tableroActual.get(i);
            stringTablero += "|";
            for (j = 0; j < ancho; j++) {
                if (auxiliar[j] == 0) {
                    stringTablero += " ";
                }
                else if (auxiliar[j] == 1) {
                    stringTablero += "#";
                }
            }
            stringTablero += "|\n\t";
        }
        // Tapa inferior del tablero.
        for (i = 0; i < ancho + 2; i++) {
            stringTablero += "-";
        }
        stringTablero += "\n";
        System.out.println(stringTablero);
    }
}