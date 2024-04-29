package algestudiante.p6;

import java.util.ArrayList;
import java.util.List;

public class NumericSquareAll2 extends NumericSquare{
	
	//Anadir dos nuevas: 
		//Le anadimos tres booleanos (cF comprobarFilas, cC comprobarColumnas, cT comprobarTodo)
		//NO SE REPITAN LOS VALORES POR FILAS (para ser valido, a parte de cumplirse, no se pueden repetir valores POR FILAS)
		//NO SE REPITAN POR COLUMNAS
		//NO SE REPITAN EN TODO EL CUADRADO
	
	private String[][] cuadradoComplejo; //que en pos pares tenga los numeros/? y en las impares las operaciones
	private int size;
	private int sizeNum;
	private int sizeRes;
	
	//private boolean solEncontrada = false;
	
	private int numSolucionesEncontradas = 0;
	private List<String[][]> estadosSolucionEncontrada = new ArrayList<>();
	
	//cambio examen
	private boolean cF;
	private boolean cC;
	private boolean cT;
	
	public NumericSquareAll2(String[][] table, boolean cF, boolean cC, boolean cT) {
		super(table);
		this.cuadradoComplejo = new String[table.length][table[0].length];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				cuadradoComplejo[i][j] = table[i][j];
			}
		}
		this.size = cuadradoComplejo.length;
		this.sizeNum = size - 2;
		this.sizeRes = size/2;
		//cambio examen
		this.cF = cF;
		this.cC = cC;
		this.cT = cT;
	}

	@Override
	public void backtracking() {
		if(cuadradoComplejo[0][0].equals("?")) { //caso el primero ya sea un ?, habra que rellenarlo
			backtracking(-1, 0, 0);
		}else { //caso de que haya un valor preestablecido en la primera casilla, iniciaremos desde el primer ?
			int[] inicio = getSigCasillaLibre(0, 0);
			backtracking(-1, inicio[0], inicio[1]);
		}
	}
	
	private void backtracking(int nivel, int i, int j) {
		if(i>=sizeNum) {
			if(esSolucionValida()) {
				//solEncontrada = true;
				numSolucionesEncontradas++;
				establecerEstadoSolucion();
			}
		}else {
			int[] posSig = getSigCasillaLibre(i, j);
			for (int k = 0; k < 10; k++) {
				cuadradoComplejo[i][j] = "" + k;  //rellenemos la actual
				
				if(esFilaValida(i, j)) {  //comprobacion para ahorrar muchisimos casos perdidos
					backtracking(nivel+1, posSig[0], posSig[1]);
				}
				
				//backtracking(nivel+1, posSig[0], posSig[1]);
			}
			cuadradoComplejo[i][j] = "?"; //reseteamos la casilla
		}
	}
	
	private void establecerEstadoSolucion() {
		String[][] estadoSolucionEncontrada = new String[cuadradoComplejo.length][cuadradoComplejo[0].length];
		for (int i = 0; i < cuadradoComplejo.length; i++) {
			for (int j = 0; j < cuadradoComplejo[0].length; j++) {
				estadoSolucionEncontrada[i][j] = cuadradoComplejo[i][j];
			}
		}
		estadosSolucionEncontrada.add(estadoSolucionEncontrada);
	}
	
	private boolean esFilaValida(int fila, int colum) {
		//Comprobamos la fila
		for (int k = colum; k < cuadradoComplejo[0].length - 2; k++) {
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
			case("-"):
				acum = acum - Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
				break;
			case("*"):
				acum = acum * Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
				break;
			case("/"):
				if(Integer.valueOf(cuadradoComplejo[fila][k+1]) == 0) { //asi evito que divida entre 0
					return false;
				}
				if(acum % Integer.valueOf(cuadradoComplejo[fila][k+1]) > 0) {
					return false;
				}
				acum = acum / Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
				break;	
			}
		}
		if(acum != Integer.valueOf(cuadradoComplejo[fila][cuadradoComplejo[0].length-1])) {
			return false;
		}
		
		return true;
	}
	
	public int[] getSigCasillaLibre(int filaActual, int columnaActual) {
		int[] res = new int[2];
		boolean cond = true;
		
		do
		 {
			columnaActual = columnaActual + 2;
			 if (columnaActual >= sizeNum)	//caso de que hayamos llegado a la ultima columna de la fila, pasamos a la fila siguiente desde la pos 0
			  {
				 filaActual = filaActual+2;
				 columnaActual = 0;
			  }
			 cond = (filaActual>=sizeNum) || cuadradoComplejo[filaActual][columnaActual].equals("?");  //reestablecemos la condicion para ver si seguimos iterando
		 }
		while (!cond);
		
		//asignamos los resultados x y
		res[0] = filaActual;
		res[1] = columnaActual;
		
		return res;
	}

	
	public boolean esSolucion(int fila, int colum) {
		//Comprobamos la fila
		for (int k = colum; k < cuadradoComplejo[0].length - 2; k = k+2) {
			if(cuadradoComplejo[fila][k].equals("?")) {
				return false;
			}
		}		
		for (int k = fila; k < cuadradoComplejo.length - 2; k = k+2) {
			if(cuadradoComplejo[k][colum].equals("?")) {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean esSolucionValida() {
		if(this.cF) { //sera necesario comprobar los repetidos de las filas
			if(repetidosFilas2()) {
				return false;
			}
		}
		if(this.cC) {//sera necesario comprobar los repetidos de las columnas
			if(repetidosColumnas2()) {
				return false;
			}
		}
		if(this.cT) {//sera necesario comprobar los repetidos de todo
			if(repetidosTodos()) {
				return false;
			}
		}
		//hasta aqui el cambio del examen
		
		for (int i = 0; i < cuadradoComplejo.length -2 ; i = i+2) {
			if(!esSolucionValida(i, i)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean repetidosFilas2() {
		ArrayList<String> elementosFila = new ArrayList<String>(); 
		for (int i = 0; i < cuadradoComplejo.length-2; i=i+2) {
			for (int j = 0; j < cuadradoComplejo[0].length-2; j=j+2) {
				if(elementosFila.contains(cuadradoComplejo[i][j])) {
					return true;
				}else {
					elementosFila.add(cuadradoComplejo[i][j]);
				}
				
			}
			elementosFila.clear();
		}
		return false;
	}
	
	private boolean repetidosColumnas2() {
		ArrayList<String> elementosFila = new ArrayList<String>(); 
		for (int i = 0; i < cuadradoComplejo.length-2; i=i+2) {
			for (int j = 0; j < cuadradoComplejo[0].length-2; j=j+2) {
				if(elementosFila.contains(cuadradoComplejo[j][i])) {
					return true;
				}else {
					elementosFila.add(cuadradoComplejo[j][i]);
				}
				
			}
			elementosFila.clear();
		}
		return false;
	}
	/*
	private boolean repetidosFilas() {
		for (int i = 0; i < cuadradoComplejo.length - 2; i++) {
			if(repetidoEnFila(i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean repetidoEnFila(int i) {
		ArrayList<String> elementosFila = new ArrayList<String>(); 
		for (int k = 0; k < cuadradoComplejo[0].length - 2; k = k+2) {
			if(elementosFila.contains(cuadradoComplejo[i][k])) {
				return true;
			}else {
				elementosFila.add(cuadradoComplejo[i][k]);
			}
		}
		return false;
	}

	private boolean repetidosColumnas() {
		for (int i = 0; i < cuadradoComplejo[0].length - 2; i++) {
			if(repetidoEnColumna(i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean repetidoEnColumna(int i) {
		ArrayList<String> elementosColumna = new ArrayList<String>(); 
		for (int k = 0; k < cuadradoComplejo[0].length - 2; k = k+2) {
			if(elementosColumna.contains(cuadradoComplejo[k][i])) {
				return true;
			}else {
				elementosColumna.add(cuadradoComplejo[k][i]);
			}
		}
		return false;
	}
	*/
	private boolean repetidosTodos() {
		ArrayList<String> elementosTodos = new ArrayList<String>(); 
		for (int i = 0; i < cuadradoComplejo.length-2; i=i+2) {
			for (int j = 0; j < cuadradoComplejo[0].length-2; j=j+2) {
				if(elementosTodos.contains(cuadradoComplejo[i][j])) {
					return true;
				}else {
					elementosTodos.add(cuadradoComplejo[i][j]);
				}
				
			}
		}
		return false;
	}

	

	public boolean esSolucionValida(int fila, int colum) {
		
		int acum = Integer.valueOf(cuadradoComplejo[fila][0]);
		int acumC = Integer.valueOf(cuadradoComplejo[0][colum]);
		
		for (int k = 1; k < cuadradoComplejo[0].length - 2; k = k+2) { //vamos recorriendo los operadores (este -2 para evitar el = y el resultado)
			switch(cuadradoComplejo[fila][k]) {
				case("+"):
					acum = acum + Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
					break;
				case("-"):
					acum = acum - Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
					break;
				case("*"):
					acum = acum * Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
					break;
				case("/"):
					if(Integer.valueOf(cuadradoComplejo[fila][k+1]) == 0) { //caso dividir entre 0
						return false;
					}
					if(acum % Integer.valueOf(cuadradoComplejo[fila][k+1]) > 0) {
						return false;
					}
					acum = acum / Integer.valueOf(cuadradoComplejo[fila][k+1]) ;
					break;	
			}
			switch(cuadradoComplejo[k][colum]) {
			case("+"):
				acumC = acumC + Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;
			case("-"):
				acumC = acumC - Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;
			case("*"):
				acumC = acumC * Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;
			case("/"):
				if(Integer.valueOf(cuadradoComplejo[k+1][colum]) == 0) { //asi evito que divida entre 0
					return false;
				}
				if(acumC % Integer.valueOf(cuadradoComplejo[k+1][colum]) > 0) {
					return false;
				}
				acumC = acumC / Integer.valueOf(cuadradoComplejo[k+1][colum]) ;
				break;	
			}
		}
		if(acum != Integer.valueOf(cuadradoComplejo[fila][cuadradoComplejo[0].length-1]) || acumC != Integer.valueOf(cuadradoComplejo[cuadradoComplejo.length-1][colum])) {
			return false;
		}
				
		return true;
	}

	@Override
	public String[][] getSol() {
		return this.estadosSolucionEncontrada.get(0); //devuelve la primera encontrada
		
	}

	@Override
	public int getNumberOfSolutions() {
		return this.numSolucionesEncontradas;
	}
	
}
