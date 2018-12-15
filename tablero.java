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

    // Metodos:
    public void play(pieza piezaEntrada, int posicionEntrada) {
        int i, anchoPieza, altoPieza, contador;
        int[] copiaFila;
        boolean flag1, flag2;
        anchoPieza = piezaEntrada.getAnchoPieza();
        altoPieza = piezaEntrada.getAltoPieza();

        // Busqueda de que tan abajo se puede colocar la pieza.
        flag1 = true;
        contador = alto - 1;
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
            } else {
                flag1 = false;
            }
        }

        // Dependiendo del tipo de pieza la fila en la que se coloca es distinta.
        // Pieza 1
        if (piezaEntrada.getIdPieza() == 1) {
            if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                contador++;
                copiaFila = tableroActual.get(contador);
            }
        }
        // Pieza 2
        else if (piezaEntrada.getIdPieza() == 2) {
            if (piezaEntrada.getRotaciones() == 0) {
                if (copiaFila[posicionEntrada] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 1) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1
                        || copiaFila[posicionEntrada + 3] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            }
        }
        // Pieza 3
        else if (piezaEntrada.getIdPieza() == 3) {
            if (piezaEntrada.getRotaciones() == 0) {
                if (copiaFila[posicionEntrada] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 1) {
                if (copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            }
        }
        // Pieza 4
        else if (piezaEntrada.getIdPieza() == 4) {
            if (piezaEntrada.getRotaciones() == 0) {
                if (copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 1) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            }
        }
        // Pieza 5
        else if (piezaEntrada.getIdPieza() == 5) {
            if (piezaEntrada.getRotaciones() == 0) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 1) {
                if (copiaFila[posicionEntrada] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 2) {
                if (copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 3) {
                if (copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            }
        }
        // Pieza 6
        else if (piezaEntrada.getIdPieza() == 6) {
            if (piezaEntrada.getRotaciones() == 0) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 1) {
                if (copiaFila[posicionEntrada] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 2) {
                if (copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 3) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            }
        }
        // Pieza 7
        else if (piezaEntrada.getIdPieza() == 7) {
            if (piezaEntrada.getRotaciones() == 0) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 1) {
                if (copiaFila[posicionEntrada] == 1 || copiaFila[posicionEntrada + 1] == 1 || copiaFila[posicionEntrada + 2] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 2) {
                if (copiaFila[posicionEntrada] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
            } else if (piezaEntrada.getRotaciones() == 3) {
                if (copiaFila[posicionEntrada + 2] == 1) {
                    contador++;
                    copiaFila = tableroActual.get(contador);
                }
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
    }

    public void imprimirTablero() {
        int i, j;
        int[] auxiliar = new int[ancho];
        for (i = alto - 1; i >= 0; i--) {
            auxiliar = tableroActual.get(i);
            for (j = 0; j < ancho; j++) {
                System.out.print(auxiliar[j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}