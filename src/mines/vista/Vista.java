package mines.vista;

import static mines.model.Model.campMinesOcult;

public class Vista {
    public static void mostrarCampMines(char[][] campMines) {

        // Imprimir encabezado de las columnas
        System.out.printf("  ");
        for (int i = 0; i < campMines[0].length - 2; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        // Imprimir filas y columnas
        char letras = 'A';
        for (int f = 1; f < campMines.length - 1; f++) {
            System.out.printf("%c ", letras);
            letras++;

            for (int c = 1; c < campMines[f].length - 1; c++) {
                System.out.printf("%2c ", campMines[f][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

