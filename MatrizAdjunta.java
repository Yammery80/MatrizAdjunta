import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario

public class MatrizAdjunta {

    // Método para calcular el determinante de una matriz 2x2
    public static int determinante2x2(int[][] matriz) {
        return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0]; // Calcula y devuelve el determinante de la matriz 2x2
    }

    // Método para calcular el determinante de una submatriz 2x2 eliminando fila i y columna j de una matriz 3x3
    public static int[][] submatriz(int[][] matriz, int fila, int col) {
        int[][] submatriz = new int[2][2]; // Crea una matriz 2x2 para almacenar la submatriz
        int f = 0; // Índice de filas en la submatriz

        // Recorre las filas de la matriz 3x3
        for (int i = 0; i < 3; i++) {
            if (i == fila) continue; // Salta la fila a eliminar
            int c = 0; // Índice de columnas en la submatriz

            // Recorre las columnas de la matriz 3x3
            for (int j = 0; j < 3; j++) {
                if (j == col) continue; // Salta la columna a eliminar
                submatriz[f][c] = matriz[i][j]; // Asigna el valor correspondiente en la submatriz
                c++; // Incrementa el índice de columnas en la submatriz
            }
            f++; // Incrementa el índice de filas en la submatriz
        }
        return submatriz; // Devuelve la submatriz 2x2
    }

    // Método para calcular el cofactor de un elemento en una matriz 3x3
    public static int cofactor(int[][] matriz, int fila, int col) {
        int[][] sub = submatriz(matriz, fila, col); // Obtiene la submatriz eliminando la fila y columna especificadas
        int detSubmatriz = determinante2x2(sub); // Calcula el determinante de la submatriz
        return (int) Math.pow(-1, fila + col) * detSubmatriz; // Calcula el cofactor aplicando el signo alternante
    }

    // Método para calcular la matriz de cofactores de una matriz 3x3
    public static int[][] matrizCofactores(int[][] matriz) {
        int[][] cofactores = new int[3][3]; // Crea una matriz 3x3 para almacenar los cofactores

        // Recorre cada elemento de la matriz 3x3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cofactores[i][j] = cofactor(matriz, i, j); // Calcula y asigna el cofactor de cada elemento
            }
        }
        return cofactores; // Devuelve la matriz de cofactores
    }

    // Método para calcular la adjunta (transposición de la matriz de cofactores)
    public static int[][] adjunta(int[][] cofactores) {
        int[][] adjunta = new int[3][3]; // Crea una matriz 3x3 para almacenar la adjunta

        // Recorre cada elemento de la matriz de cofactores
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adjunta[i][j] = cofactores[j][i]; // Transpone la matriz de cofactores
            }
        }
        return adjunta; // Devuelve la matriz adjunta
    }

    // Método para imprimir la matriz en consola
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < 3; i++) { // Recorre cada fila de la matriz
            for (int j = 0; j < 3; j++) { // Recorre cada columna de la fila actual
                System.out.printf("%8d", matriz[i][j]); // Imprime el elemento con un formato de ancho fijo
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }

    // Método principal donde se ejecuta el programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario

        // Solicita el número de filas de la matriz
        System.out.print("Ingrese el número de filas: ");
        int filas = scanner.nextInt(); // Lee el número de filas

        // Solicita el número de columnas de la matriz
        System.out.print("Ingrese el número de columnas: ");
        int columnas = scanner.nextInt(); // Lee el número de columnas

        // Verifica si la matriz es cuadrada (requisito para calcular la adjunta)
        if (filas != columnas) {
            System.out.println("La matriz no es cuadrada. El programa terminará."); // Muestra un mensaje de error
            scanner.close(); // Cierra el objeto Scanner
            return; // Termina el programa si la matriz no es cuadrada
        }

        // Crea la matriz y solicita al usuario que ingrese sus valores
        int[][] matriz = new int[filas][columnas]; // Inicializa la matriz con las dimensiones dadas
        System.out.println("Ingrese los valores de la matriz " + filas + "x" + columnas + ":");

        for (int i = 0; i < filas; i++) { // Recorre cada fila de la matriz
            for (int j = 0; j < columnas; j++) { // Recorre cada columna de la fila actual
                System.out.print("Matriz[" + (i + 1) + "][" + (j + 1) + "]: "); // Solicita el valor de la posición actual
                matriz[i][j] = scanner.nextInt(); // Asigna el valor ingresado a la matriz
            }
        }

        // Calcula la matriz de cofactores y la matriz adjunta
        int[][] cofactores = matrizCofactores(matriz); // Calcula la matriz de cofactores
        int[][] adjunta = adjunta(cofactores); // Calcula la matriz adjunta (transpuesta de cofactores)

        // Muestra la matriz adjunta
        System.out.println("La matriz adjunta es:");
        imprimirMatriz(adjunta); // Imprime la matriz adjunta en consola

        scanner.close(); // Cierra el objeto Scanner
    }
}
