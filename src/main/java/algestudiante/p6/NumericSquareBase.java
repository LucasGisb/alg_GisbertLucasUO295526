package algestudiante.p6;

//public class NumericSquareBase extends NumericSquare{
public class NumericSquareBase{

	static int nivel;
	
	//forma mejor?
	static String[][] cuadradoComplejo; //que en pos pares tenga los numeros/? y en las impares las operaciones
	static int sizeNum;
	//tambiem habrian casillas (las diagonales) que serian huecos
	
	static boolean[][] casillasRellenas; //true si hay numero
	
	static String[] resOperacionCadaFila;
	static String[] resOperacionCadaColumna;  //podria usarme uno bidimensional para ambos
	
	static boolean solEncontrada = false;
	static String[][] estadoSolucionEncontrada; //solo se llena al encontrarse una solucion, siendo una copia del tablero ante esta solucion
			//para el caso de multiples soluciones, hacerlos un array de String[][] que almacene todas las soluciones encontradas
	//public NumericSquareBase(String[][] table) {	
	//}
	
	public NumericSquareBase(String[][] table) {
		//this.cuadradoComplejo = table;
		this.cuadradoComplejo = new String[table.length][table[0].length];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				cuadradoComplejo[i][j] = table[i][j];
			}
		}
		this.sizeNum = cuadradoComplejo.length/2;
		/*
		for (int i = 0; i < cuadradoComplejo.length; i++) {
			resOperacionCadaFila[i] = cuadradoComplejo[i][cuadradoComplejo[0].length];
		}
		for (int j = 0; j < cuadradoComplejo[0].length; j++) {
			resOperacionCadaFila[j] = cuadradoComplejo[cuadradoComplejo.length][cuadradoComplejo[0].length];
		}
		*/
	}

	public void backtracking() { //
		backtracking(-1, 0, 0);
	}
	
	//esquema mejor backtracking
	public void backtracking(int nivel, int i, int j) {
		if(i == sizeNum && esSolucion(nivel, i, j)) {  //ya se ha llenado
			//dentro miramos si las columnas tambien son validas
			solEncontrada = true;
			
		}else {
			if(esValido(i, j)) {
				//llamada a backtracking con nivel+1, nextPosition[0] y nextPosition[1] (estas son las coordenadas i j de la siguiente posicion)
				int[] posSig = getSigCasillaLibre(i, j); //pasandole la pos actual (va recorriendo de dos en dos por las columnas, cuidado con no saltarse)
				backtracking(nivel+1, posSig[0], posSig[1]);
			}else {
				for (int k = 0; k < 10; k++) {
					if(!solEncontrada) {
						cuadradoComplejo[i][j] = "" + k;  //rellenemos la actual
						int[] posSig = getSigCasillaLibre(i, j); //pasandole la pos actual (va recorriendo de dos en dos por las columnas, cuidado con no saltarse)
						//if(esValido(posSig[0], posSig[1], i)) { //si se completo, si es valido lo puesto:  isValid(nextPosition[0] y nextPosition[1], i) esta i siendo la fila en la que se ha asignado
						if(esValido(posSig[0], posSig[1])) {
							//llamada a backtracking con nivel+1, nextPosition[0] y nextPosition[1] (estas son las coordenadas i j de la siguiente posicion)
							backtracking(nivel+1, posSig[0], posSig[1]);
						}
					}
				}
			}
			
		}
	}
	
	
	private boolean esValido(int fila, int colum) {
		//Comprobamos la fila
		for (int k = colum; k < cuadradoComplejo[0].length; k++) {
			if(cuadradoComplejo[fila][k].equals("?")) {
				return true;
			}
		}
		int acum = Integer.valueOf(cuadradoComplejo[fila][0]);
		for (int k = 1; k < cuadradoComplejo[0].length - 2; k = k+2) { //vamos recorriendo los operadores (este -2 para evitar el = y el resultado)
			switch(cuadradoComplejo[fila][k]) {
			case("+"):
				acum = acum + Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
				break;
			case("*"):
				acum = acum * Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
				break;
			case("/"):
				acum = acum / Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
				break;	
			}
			if(acum != Integer.valueOf(cuadradoComplejo[fila][cuadradoComplejo[0].length])) {
				return false;
			}
		}
		
		
		//Comprobamos la columna
		for (int k = fila; k < cuadradoComplejo.length; k++) {
			if(cuadradoComplejo[k][colum].equals("?")) {
				return true;
			}
		}
		int acumC = Integer.valueOf(cuadradoComplejo[0][colum]);
		for (int k = 1; k < cuadradoComplejo.length - 2; k = k+2) { //vamos recorriendo los operadores (este -2 para evitar el = y el resultado)
			switch(cuadradoComplejo[k][colum]) {
			case("+"):
				acumC = acumC + Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;
			case("*"):
				acumC = acumC * Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;
			case("/"):
				acumC = acumC / Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;	
			}
			if(acumC != Integer.valueOf(cuadradoComplejo[cuadradoComplejo.length][colum])) {
				return false;
			}
		}
		
		return true;
	}

	public int[] getSigCasillaLibre(int filaActual, int columnaActual) {
		int[] res = new int[2];
		boolean cond = true;
		
		do
		 {
		columnaActual = columnaActual + 1;
		 if (columnaActual == nivel)	//caso de que hayamos llegado a la ultima columna de la fila, pasamos a la fila siguiente desde la pos 0
		  {
			 filaActual = filaActual+1;
			 columnaActual = 0;
		  }
		 cond = (filaActual==nivel) || cuadradoComplejo[filaActual][columnaActual].equals("?");  //reestablecemos la condicion para ver si seguimos iterando
		 }
		while (!cond);
		//asignamos los resultados x y
		res[0] = filaActual;
		res[1] = columnaActual;
		return res;
	}

	
	public boolean esSolucion(int nivel, int iP, int jP) {
		if(esValido(iP, jP)) {
			return true;
		}
		return false;
	}
	
	public String[][] getSol() {
		return this.estadoSolucionEncontrada;
	}
	
	public int getNumberOfSolutions() {
		//De momento en esta clase solo devolvera o 0 o 1, pues este metodo esta mas pensado para la clase con varias soluciones
		if(solEncontrada) {
			return 1;
		}
		return 0;
	}

}
