package mines.model;

import java.util.Arrays;
import java.util.Random;

import static mines.vista.Vista.mostrarCampMines;

public class Model {
    private static int numFiles;
    private static int numColumnes;
    private static int numBombes;

    private static boolean jocFinalitzat = false;
    public static char[][] campMinesOcult;
    private static char[][] campMinesVisible;

    public static void inicializarJoc(int files, int columnes, int bombes) {
        numBombes = bombes;
        numColumnes = columnes;
        numFiles = files;

        campMinesOcult = new char[files][columnes];
        campMinesVisible = new char[files][columnes];

        inicializarCampMines(campMinesOcult, ' ');
        inicializarCampMines(campMinesVisible, '.');

        bombasRandom();
        contarBombas();
        mostrarCampMines(campMinesOcult);
        mostrarCampMines(campMinesVisible);


    }

    private static void inicializarCampMines(char[][] campMines, char caracter) {
        for (int i = 0; i < numFiles; i++) {
            for (int j = 0; j < numColumnes; j++) {
                campMines[i][j] = caracter;

            }
        }
    }


    private static void bombasRandom() {

        for (int i = 0; i < numBombes; i++) {
            int f = (int) (Math.random() * (numFiles - 2)) + 1;
            //El -2 sirve para indicar que debe colocar minas en 2 filas menos de las que hay realmente.
            int c = (int) (Math.random() * (numColumnes - 2)) + 1;
            //El -2 sirve para indicar que debe colocar minas en 2 columnas menos de las que hay realmente.
            campMinesOcult[f][c] = 'B';

        }
    }


    private static void contarBombas() {
        for (int f = 1; f < campMinesOcult.length - 1; f++) {
            for (int c = 1; c < campMinesOcult[f].length - 1; c++) {
                int contador = 0;

                if (campMinesOcult[f][c] != 'B') {
                    if (campMinesOcult[f - 1][c - 1] == 'B') contador++;
                    if (campMinesOcult[f - 1][c] == 'B') contador++;
                    if (campMinesOcult[f - 1][c + 1] == 'B') contador++;
                    if (campMinesOcult[f][c - 1] == 'B') contador++;
                    if (campMinesOcult[f][c + 1] == 'B') contador++;
                    if (campMinesOcult[f + 1][c - 1] == 'B') contador++;
                    if (campMinesOcult[f + 1][c] == 'B') contador++;
                    if (campMinesOcult[f + 1][c + 1] == 'B') contador++;

                    campMinesOcult[f][c] = (char) (contador + '0'); // Convertir el contador a carácter
                    if (contador == 0) campMinesOcult[f][c] = ' ';
                }
            }


        }

    }

    public static void trepitjar(int fila, int columna) {
        columna += 1;
        if (campMinesVisible[fila][columna] == 'B' || //comprueba si es igual a bandera
                campMinesVisible[fila][columna] != '.') {//comprueba si la casilla se ha descubierto
            System.out.println("Ya se ha pasado por esta casilla. Elige otra");
        }
        if (campMinesOcult[fila][columna] == 'B') {// comprueba si hay una bomba
            jocFinalitzat = true;
            mostrarCampMines(campMinesOcult);

            char letraFila = 'A';
            for (int i = 1; i <= fila; i++) {
                letraFila++;
                if (i == fila) break;
            }
            letraFila--;
            columna--;
            System.out.println("Habia una bomba en la casilla " + letraFila + " " + columna);
            System.exit(1);
        } else {
            if (campMinesOcult[fila][columna] == ' ')
                pisarRecursivo(fila, columna);
            else
                campMinesVisible[fila][columna] = campMinesOcult[fila][columna];
            System.out.println("hola");
            mostrarCampMines(campMinesVisible);
        }
        jocFinalitzat = comprobarHaGuanyat();


    }

    public static void posarBandera(int fila, int columna) {
        columna++;
        if (campMinesVisible[fila][columna] != '.') {
            System.out.println("Esta casilla ya ha sido pisada");
        } else if (campMinesVisible[fila][columna] != 'B') {
            campMinesVisible[fila][columna] = 'B';//Poner bandera si no hay
            mostrarCampMines(campMinesVisible);
            char letraFila = 'A';
            for (int i = 1; i <= fila; i++) {
                letraFila++;
                if (i == fila) break;
            }
            letraFila--;
            columna--;
            System.out.println("Has puesto una bandera en " + letraFila + " " + columna);
        } else {
            campMinesVisible[fila][columna] = '.';//Si hay bandera la quita
        }
        jocFinalitzat = comprobarHaGuanyat();
    }

    public static boolean jocFinalitazat() {
        return jocFinalitzat;
    }


    private static boolean comprobarHaGuanyat() {
        for (int i = 0; i < campMinesOcult.length; i++) {

            if (Arrays.equals(campMinesOcult[i], campMinesVisible[i])) {
                return true;
            }
        }
        return false;
    }

}
