package algestudiante.p6;

public class CuadradoNumerico {

	static int tamCuadrado;
	
	//forma mejor?
	static char[][] cuadradoComplejo; //que en pos pares tenga los numeros/? y en las impares las operaciones
	//tambiem habrian casillas (las diagonales) que serian huecos
	
	static boolean[][] casillasRellenas; //true si hay numero
	
	static int[] resOperacionCadaFila;
	static int[] resOperacionCadaColumna;  //podria usarme uno bidimensional para ambos
	
	static boolean solEncontrada = false;
	
	
	public static void backtracking(int nivel) { //
		if(nivel == tamCuadrado*tamCuadrado) { //hemos llegado al nivel del arbol donde debe haber solucion
			mostrarSolucion();
		}else {
			
		}
	}
	
	private static boolean cuadradoEstaLleno() { //asumiendo que se inicialice a -1s
		
		return true;
	}

	private static void mostrarSolucion() {
		
		
	}
	
	public static void main (String arg[]) {
		
	}

}
