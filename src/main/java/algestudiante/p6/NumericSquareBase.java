package algestudiante.p6;

public class NumericSquareBase extends NumericSquare{

	static int tamCuadrado;
	
	//forma mejor?
	static char[][] cuadradoComplejo; //que en pos pares tenga los numeros/? y en las impares las operaciones
	//tambiem habrian casillas (las diagonales) que serian huecos
	
	static boolean[][] casillasRellenas; //true si hay numero
	
	static int[] resOperacionCadaFila;
	static int[] resOperacionCadaColumna;  //podria usarme uno bidimensional para ambos
	
	static boolean solEncontrada = false;
	static String[][] estadoSolucionEncontrada; //solo se llena al encontrarse una solucion, siendo una copia del tablero ante esta solucion
			//para el caso de multiples soluciones, hacerlos un array de String[][] que almacene todas las soluciones encontradas
	//public NumericSquareBase(String[][] table) {	
	//}
	
	public NumericSquareBase(String[][] table) {
		super(table);
		
		//tamCuadrado = 
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				cuadradoComplejo[i][j] = table[i][j].charAt(0); //copiamos el tablero pasado
				if((i == 0 || i % 2 == 0) && j == table[0].length -1) { //caso de que sea una de las soluciones de fila
					resOperacionCadaFila[i] = cuadradoComplejo[i][j];
				}else if((j == 0 || j % 2 == 0) && i == table.length -1) { //caso de que sea una de las soluciones de columna
					resOperacionCadaColumna[j] =  cuadradoComplejo[i][j];
				}else if((i == 0 || i % 2 == 0) && ((j == 0 || j % 2 == 0))){ //caso de que estemos antes un NUMERO
					casillasRellenas[i][j] = true;
				}
			}
		}
	}

	@Override
	public void backtracking() { //
		//if(solEncontrada) {  //al ser recursivo, debemos establecer el caso de parada
		if(esSolucion()) { //llamada a una funcion que mira si seria un estado valido, NO la var solEncontrada. (ESTE METODO MIRARIA SI HEMOS LLEGADO AL FINAL DEL TABLERO Y SE CUMPLE TODO)
			solEncontrada = true; //pues estamos en el Estado que es solucion
			
			
		}else { //no se ha encontrado solucion, aun hay ? en el cuadrado
			//for de 0 a 9
			for (int i = 0; i < 10; i++) {
				int posARellenar = getSigCasillaLibre();
				//llamamos a una funcion que nos lleva a la siguiente casilla a rellenar
				//ponemos en esta casilla el numero i
				
				//comprobamos si lo puesto es valido (si aun queda algo en la fila LO ES SI O SI)
				//if()...
				//	si la fila esta completa y no da lo que debe, return false, si si, return true (y ponemos el valor en el tablero)
				//y hacemos la llamda recursiva
			}
			
			
			for (int i = 0; i < cuadradoComplejo.length; i++) {
				for (int j = 0; j < cuadradoComplejo[0].length; j++) {
//					}			//el switch hacerlo a la hora de interpreta la operacion segun el char, no aqui
					if(cuadradoComplejo[i][j] == '?') {
						cuadradoComplejo[i][j] = establecerCasillaValida(i, j);
					}
				}
			}
		}
	}
	
	//esquema mejor backtracking
	public void backtracking(int nivel, int i, int j) {
		if(esSolucion(i, j)) {  //ya se ha llenado
			//dentro miramos si las columnas tambien son validas
			solEncontrada = true;
		}else {
			int[] posSig = getSigCasillaLibre(i, j); //pasandole la pos actual (va recorriendo de dos en dos por las columnas, cuidado con no saltarse)
			for (int k = 0; k < 10; k++) {
				if(!solEncontrada) {
					cuadradoComplejo[i][j] = String.valueOf(k);
					if() { //si se completo, si es valido lo puesto:  isValid(nextPosition[0] y nextPosition[1], i) esta i siendo la fila en la que se ha asignado
						//llamada a backtracking con nivel+1, nextPosition[0] y nextPosition[1] (estas son las coordenadas i j de la siguiente posicion)
					}
				}
			}
		}
	}
	
	public int getSigCasillaLibre(int fila, int columna) {
		for (int i = 0; i < casillasRellenas.length; i++) {
			
		}
		return 0;
	}

	private char establecerCasillaValida(int filaActual, int columnaActual) {
		for (int i = 0; i <= 9; i++) {
			
		}
		return 0;
	}

	private static boolean cuadradoEstaLleno() { //asumiendo que se inicialice a -1s
		
		return true;
	}

	public boolean esSolucion() {
		//metodo que, dado un tablero completo, comprueba si llegamos al final y cumple las condiciones para ser una solucion
		int acumCadaFila = 0;
		for (int k = 0; k < cuadradoComplejo.length; k = k+2) {
			acumCadaFila = 0;
			//if(cuadradoComplejo[i][j]) {
			//	
			//}
			
			for (int i = 0; i < cuadradoComplejo.length; i++) {
				for (int j = 0; j < cuadradoComplejo[0].length; j++) {
					estadoSolucionEncontrada[i][j] = cuadradoComplejo[i][j] + ""; //le hacemos conversion a string a cada char
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String[][] getSol() {
		return this.estadoSolucionEncontrada;
	}
	
	@Override
	public int getNumberOfSolutions() {
		//De momento en esta clase solo devolvera o 0 o 1, pues este metodo esta mas pensado para la clase con varias soluciones
		if(solEncontrada) {
			return 1;
		}
		return 0;
	}

}
