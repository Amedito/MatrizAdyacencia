import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obtener el número de nodos
        System.out.print("Ingrese el número de nodos: ");
        int numNodos = scanner.nextInt();

        // Crear la matriz de adyacencia
        int[][] matrizAdyacencia = new int[numNodos][numNodos];

        // Obtener los caminos a recorrer
        System.out.println("Ingrese los caminos a recorrer (en forma de pares de nodos):");
        System.out.println("Ingrese -1 -1 para finalizar la entrada.");

        int nodoOrigen, nodoDestino;
        int caminosIngresados = 0;
        boolean todosConectados = false;
        boolean posicionOcupada = false;

        while (!todosConectados || caminosIngresados == 0 || posicionOcupada) {
            caminosIngresados = 0;
            todosConectados = false;
            posicionOcupada = false;
            matrizAdyacencia = new int[numNodos][numNodos];

            do {
                System.out.print("Nodo origen: ");
                nodoOrigen = scanner.nextInt();

                System.out.print("Nodo destino: ");
                nodoDestino = scanner.nextInt();

                if (nodoOrigen >= 0 && nodoOrigen < numNodos && nodoDestino >= 0 && nodoDestino < numNodos) {
                    if (matrizAdyacencia[nodoOrigen][nodoDestino] == 0 && matrizAdyacencia[nodoDestino][nodoOrigen] == 0) {
                        // Actualizar la matriz de adyacencia
                        matrizAdyacencia[nodoOrigen][nodoDestino] = 1;
                        matrizAdyacencia[nodoDestino][nodoOrigen] = 1;
                        caminosIngresados++;
                    } else {
                        System.out.println("La posición ya está ocupada. Inténtelo de nuevo.");
                        posicionOcupada = true;
                    }
                } else {
                   System.out.println("Los nodos ingresados son inválidos. Inténtelo de nuevo.");
                }
            } while (nodoOrigen != -1 && nodoDestino != -1);

            // Verificar la conectividad de los nodos
            todosConectados = verificarConectividad(matrizAdyacencia);

            if (!todosConectados || caminosIngresados == 0 || posicionOcupada) {
                System.out.println("No todos los nodos están conectados en al menos un camino o se ingresaron caminos inválidos. Inténtelo de nuevo.");
            }
        }

        // Imprimir la matriz de adyacencia
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean verificarConectividad(int[][] matrizAdyacencia) {
        int numNodos = matrizAdyacencia.length;
        boolean[] visitados = new boolean[numNodos];
        int nodoInicial = 0;
        dfs(matrizAdyacencia, visitados, nodoInicial);
        // Verificar si todos los nodos fueron visitados
        for (boolean visitado : visitados) {
            if (!visitado) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int[][] matrizAdyacencia, boolean[] visitados, int nodo) {
        visitados[nodo] = true;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[nodo][i] == 1 && !visitados[i]) {
                dfs(matrizAdyacencia, visitados, i);
            }
        }
    }
}
