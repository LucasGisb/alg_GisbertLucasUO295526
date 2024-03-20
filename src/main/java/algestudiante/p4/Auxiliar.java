package algestudiante.p4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Auxiliar {
	
	
	public static int[][] matrizTriangularDesdeFichero(String fich) throws IOException {
        /* Genera una matriz triangular (i < j) que lee desde un fichero de entrada, con formato visto */
        BufferedReader fi = new BufferedReader(new FileReader(fich));
        int n = Integer.parseInt(fi.readLine());
        int[][] m = crearMatriz(n, 0);
        int i = 0;
        String linea;
        while ((linea = fi.readLine()) != null) {
            String[] lista = linea.split(",");
            int k = 0;
            for (int j = i + 1; j < n; j++) {
                m[i][j] = Integer.parseInt(lista[k]);
                k++;
            }
            i++;
        }
        fi.close();
        return m;
    }
	
	public static int[][] matrizAdyacenciasDesdeFichero(String fich) throws IOException {
        /* Genera una matriz triangular (i < j) que lee desde un fichero de entrada, con formato visto */
        BufferedReader fi = new BufferedReader(new FileReader(fich));
        int n = Integer.parseInt(fi.readLine());
        int[][] m = new int[n][n];
        int i = 0;
        String linea;
        while ((linea = fi.readLine()) != null) {
            String[] lista = linea.split(",");
            int k = 0;
            for (int j = i + 1; j < n; j++) {
                int value = Integer.parseInt(lista[k]);
                m[i][j] = value;
                m[j][i] = value; // Aquí se copia el valor en la diagonal inferior
                k++;
            }
            i++;
        }
        fi.close();
        return m;
    }

	private static int[][] crearMatriz(int n, int val) {
		int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = val;
            }
        }
        return matriz;
	}
	
	public static int[][] matrizTriangularEnterosAleatorios(int n, int inf, int sup) {
        int[][] m = new int[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                m[i][j] = random.nextInt(sup - inf + 1) + inf;
            }
        }
        return m;
    }
	
}
