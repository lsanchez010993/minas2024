package mines.controlador;

import mines.model.Model;

import java.util.Scanner;

import static mines.model.Model.*;
import static mines.vista.Vista.mostrarCampMines;

public class Controlador {
    public static void jugar() {
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                Menu:
                                 
                1. 8x8     (8 filas y 8 columnas y 10 bombas)
                2. 16x16   (16 filas y 16 columnas y 40 bombas)
                3. 16x30   (16 filas y 30 columnas y 99 bombas)
                Introduce una opcion:
                """);
        int opcion = scan.nextInt();
        scan.nextLine();
        switch (opcion) {
            case 1:
                Model.inicializarJoc(8, 8, 10);
                break;
            case 2:
                Model.inicializarJoc(16, 16, 40);
                break;
            case 3:
                Model.inicializarJoc(16, 30, 99);
                break;
        }
        do {
            System.out.println("Que vol fer? T(Trepitjar), posar B(Bandera) o A(Acabar):");
            char opcio = scan.nextLine().toUpperCase().trim().charAt(0);

            System.out.println("Introduce fila y columna. Ej: E 4");
            char fila = scan.next().toUpperCase().charAt(0);
            int contadorFila = 0;
            for (char i = 'A'; i <= 'Z'; i++) {
                contadorFila++;
                if (i == fila) break;
            }
            int columna = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 'T':

                    trepitjar(contadorFila, columna);

                    break;
                case 'B':
                    posarBandera(contadorFila, columna);
                    break;
                case 'A':
                    System.out.println("Has salido del juego");
                    System.exit(1);
                    break;
            }

        }while (!jocFinalitazat());
    }
}
