import java.util.Random;
import java.util.ArrayList;

/**
 * Clase Tablero Clase en la que se encuentran todos los datos y manejos
 * respectivos al tablero.
 * 
 * @author: Roberto Lillo Toloza
 * @version: 27/12/2018
 */
public class Tablero {
    // Atributos de la clase
    private int ancho, alto, lineasEliminadas, puntaje;
    private ArrayList<int[]> tableroActual = new ArrayList<int[]>();

    /**
     * Constructor del tablero de juego, se instancia estando vacio
     * 
     * @param anchoEntrada Numero que anteriormente se verifica sea mayor o igual a
     *                     5
     * @param altoEntrada  Numero que anteriormente se verifica sea mayor o igual a
     *                     10
     */
    public Tablero(int anchoEntrada, int altoEntrada) {
        ancho = anchoEntrada;
        alto = altoEntrada;
        lineasEliminadas = 0;
        puntaje = 0;

        int i, j;
        int[] fila;
        for (i = 0; i < alto; i++) {
            fila = new int[ancho];
            for (j = 0; j < ancho; j++) {
                fila[j] = 0;
            }
            tableroActual.add(fila);
        }
    } // Cierre del constructor

    // Getters:
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getLineasEliminadas() {
        return lineasEliminadas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    // Metodos:
    /**
     * Verifica la existencia de lineas completas dentro del tablero, si llega a
     * encontrarse una la elimina y agrega una linea vacia al final de este. Tambien
     * dependiendo de la cantidad de lineas que elimina aumenta el puntaje del
     * juego.
     */
    public void checkBoard() {  // requerimiento funcional checkBoard
        int i, j, contador;
        int[] copiaFila, nuevaFila;

        contador = 0;
        boolean flag;
        for (i = 0; i < alto; i++) {
            copiaFila = tableroActual.get(i);
            flag = true;
            for (j = 0; j < ancho; j++) {
                if (copiaFila[j] == 0) {
                    flag = false;
                }
            }
            if (flag) {
                tableroActual.remove(i);
                contador++;
                lineasEliminadas++;
                nuevaFila = new int[ancho];
                for (j = 0; j < ancho; j++) {
                    nuevaFila[j] = 0;
                }
                tableroActual.add(nuevaFila);
                i--;
            }
        }
        if (contador == 1) {
            puntaje += 150;

        } else if (contador == 2) {
            puntaje += 300;

        } else if (contador == 3) {
            puntaje += 600;

        } else if (contador == 4) {
            puntaje += 1200;
        }
    }

    /**
     * Genera una serie de piezas a partir del parametro cantidad y la semilla, y
     * las coloca dentro del tablero vacio que se construye al inicio del juego.
     * 
     * @param cantidad    Cantidad de piezas a colocar
     * @param seedEntrada Semilla para los numeros aleatorios
     */
    public void colocarPiezasIniciales(int cantidad, int seedEntrada) {
        int idPieza, cantGiros, posicion, verificador;
        boolean flag;
        Random numeroRand = new Random();
        numeroRand.setSeed(seedEntrada);
        Pieza sigPieza;
        while (cantidad > 0) {
            idPieza = 1 + numeroRand.nextInt(7);
            cantGiros = numeroRand.nextInt(4);
            posicion = numeroRand.nextInt(ancho);

            sigPieza = new Pieza(idPieza);
            while (cantGiros > 0) {
                sigPieza.rotarPieza();
                cantGiros--;
            }

            flag = true;
            while (flag) {
                // Verificacion de que la pieza puede quedar colocada dentro del ancho maximo
                // del tablero.
                // De no ser posible esa colocacion, se asigna una nueva posicion para ubicar la
                // pieza en el tablero.
                if (posicion + sigPieza.getAnchoPieza() > ancho) {
                    posicion = numeroRand.nextInt(ancho);
                } else {
                    verificador = play(sigPieza, posicion);
                    if (verificador == 1) {
                        checkBoard();
                        cantidad--;
                        flag = false;
                    }
                }
            }
        }
    }

    /**
     * Coloca una pieza dentro del tablero, a partir de una posicion que se le
     * indique.
     * 
     * @param piezaEntrada    Pieza a colocar
     * @param posicionEntrada Posicion dentro del rango posible
     * @return 0 si se llega a una situacion en la que se pierde el juego, 1 si se
     *         logra colocar la pieza,
     */
    public int play(Pieza piezaEntrada, int posicionEntrada) {  // requerimiento funcional play
        int i, anchoPieza, altoDisponible, contador;
        int[] copiaFila;
        boolean flag1, flag2;
        anchoPieza = piezaEntrada.getAnchoPieza();

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
            } else if (flag2 && contador == 0) {
                altoDisponible++;
                flag1 = false;
            } else {
                flag1 = false;
            }
        }

        // Verificacion de que la pieza puede colocarse en la cantidad de alto
        // disponible.
        if (altoDisponible == 0) {
            return 0; // Game Over
        } else {
            // Dependiendo del tipo de pieza la colocación es distinta.
            // Pieza 1
            if (piezaEntrada.getIdPieza() == 1) {
                if (altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                } else {
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
                } else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 1) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1
                            || copiaFila[posicionEntrada + 2] == 1 || copiaFila[posicionEntrada + 3] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                } else {
                    return 0; // Game Over
                }
            }
            // Pieza 3
            else if (piezaEntrada.getIdPieza() == 3) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1) {
                        if (altoDisponible >= 3) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 1) {
                    if ((copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1)) {
                        if (altoDisponible >= 2) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else {
                    return 0; // Game Over
                }
            }
            // Pieza 4
            else if (piezaEntrada.getIdPieza() == 4) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        if (altoDisponible >= 3) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 1) {
                    if ((copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1)) {
                        if (altoDisponible >= 2) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else {
                    return 0; // Game Over
                }
            }
            // Pieza 5
            else if (piezaEntrada.getIdPieza() == 5) {
                if (piezaEntrada.getRotaciones() == 0 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1
                            || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                } else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1) {
                        if (altoDisponible >= 3) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else if (piezaEntrada.getRotaciones() == 2 && altoDisponible >= 1) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        if (altoDisponible >= 2) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else if (piezaEntrada.getRotaciones() == 3 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada + 1] == 1) {
                        if (altoDisponible >= 3) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Ober
                        }
                    }
                } else {
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
                } else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 1) {
                    if (copiaFila[posicionEntrada] == 1) {
                        if (altoDisponible >= 2) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            return 0; // Game Over
                        }
                    }
                } else if (piezaEntrada.getRotaciones() == 2 && altoDisponible >= 1) {
                    if (contador > 0) {
                        int[] copiaFilaAux = tableroActual.get(contador - 1);
                        if (copiaFila[posicionEntrada + 1] == 0 && copiaFilaAux[posicionEntrada + 1] == 0) {
                            contador--;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            if (copiaFila[posicionEntrada + 1] == 1) {
                                if (altoDisponible >= 3) {
                                    contador++;
                                    copiaFila = tableroActual.get(contador);
                                } else {
                                    return 0; // Game Over
                                }
                            }
                        }
                    } else {
                        if (copiaFila[posicionEntrada + 1] == 1) {
                            if (altoDisponible >= 3) {
                                contador++;
                                copiaFila = tableroActual.get(contador);
                            } else {
                                return 0; // Game Over
                            }
                        }
                    }

                } else if (piezaEntrada.getRotaciones() == 3 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1
                            || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                } else {
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
                } else if (piezaEntrada.getRotaciones() == 1 && altoDisponible >= 2) {
                    if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1
                            || copiaFila[posicionEntrada + 2] == 1) {
                        contador++;
                        copiaFila = tableroActual.get(contador);
                    }
                } else if (piezaEntrada.getRotaciones() == 2 && altoDisponible >= 1) {
                    if (contador > 0) {
                        int[] copiaFilaAux = tableroActual.get(contador - 1);
                        if (copiaFila[posicionEntrada] == 0 && copiaFilaAux[posicionEntrada] == 0) {
                            contador--;
                            copiaFila = tableroActual.get(contador);
                        } else {
                            if (copiaFila[posicionEntrada] == 1) {
                                if (altoDisponible >= 3) {
                                    contador++;
                                    copiaFila = tableroActual.get(contador);
                                } else {
                                    return 0; // Game Over
                                }
                            }
                        }
                    } else {
                        if (copiaFila[posicionEntrada] == 1) {
                            if (altoDisponible >= 3) {
                                contador++;
                                copiaFila = tableroActual.get(contador);
                            } else {
                                return 0; // Game Over
                            }
                        }
                    }
                } else if (piezaEntrada.getRotaciones() == 3 && altoDisponible >= 1) {
                    if (copiaFila[posicionEntrada + 2] == 1) {
                        if (altoDisponible >= 2) {
                            contador++;
                            copiaFila = tableroActual.get(contador);
                        }
                    }
                } else {
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
                } else if (coordenadaBloque[0] != alturaActual) {
                    alturaActual++;
                    tableroActual.set(contador, copiaFila);
                    contador++;
                    copiaFila = tableroActual.get(contador);
                    copiaFila[posicionEntrada + coordenadaBloque[1]] = 1;
                }
            }
            return 1; // Se logró colocar la pieza correctamente.
        }
    }

    /**
     * Muestra por pantalla en tablero actual Corresponde al requerimiento funcional
     * boardToString.
     */
    public void imprimirTablero() { // requerimiento funcional imprimirTablero
        int i, j;
        String stringTablero = "\t";
        int[] auxiliar = new int[ancho];

        // Tapa superior del tablero.
        for (i = 0; i < (ancho * 2) + 1; i++) {
            stringTablero += "_";
        }

        // Parte interior del tablero.
        stringTablero += "\n\t";
        for (i = alto - 1; i >= 0; i--) {
            stringTablero += "|";
            auxiliar = tableroActual.get(i);
            for (j = 0; j < ancho; j++) {
                if (auxiliar[j] == 0) {
                    stringTablero += " ";
                } else if (auxiliar[j] == 1) {
                    stringTablero += "#";
                }
                stringTablero += "|";
            }
            stringTablero += "\n\t";
        }

        // Numeros para las columnas
        for (i = 1; i <= ancho; i++) {
            stringTablero += "|" + Integer.toString(i);
        }
        stringTablero += "|\n\t";

        System.out.println(stringTablero);
    }
}