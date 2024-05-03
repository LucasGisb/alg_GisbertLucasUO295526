package algestudiante.p7;

import java.util.ArrayList;
import java.util.Arrays;

import algestudiante.util.Estado;
import algestudiante.util.RamificaYPoda;


public class EstadoNumericSquare extends Estado {

	protected int valorHeuristico; //maxInt y no lo tocamos?  (eso dijo del Heuristico de poda)
	
	private int n;	// Tamano del problema (¿num de ?, ?)
	//estado
	private String[][] table;
	
	private ArrayList<EstadoNumericSquare> hijos = new ArrayList<EstadoNumericSquare>();

	//
	private int filaActual = -1;
	private int columnaActual = -1;
	
	private boolean restantes;
	
	public EstadoNumericSquare(EstadoNumericSquare padre, int j, int x, int y) { //j es el valor a nadir, x y las coordenadas donde meterlo
		super();
		//copiar info padre
		//copiar tabla
		profundidad = padre.profundidad;
		idPadre = padre.getId();
		this.n = padre.table.length;
		
		//this.table= Arrays.copyOf(padre.table,n);
		
		table = new String[padre.table.length][padre.table[0].length];
		for (int i = 0; i < padre.table.length; i++) {
			for (int k = 0; k < padre.table[0].length; k++) {
				table[i][k] = padre.table[i][k];
			}
		}
		
		
		
		this.filaActual = x;
		this.columnaActual = y;
		
		//anadimos lo correspondiente al hijo
		this.table[x][y] = ""+ j; //valor a asignar (este se le debe pasar por param de su padre, siendo sacado con el for de expandir)
		profundidad++;
		
		this.restantes = getRestantes(x, y);
		
		calcularValorHeuristico();
		
		//comprobar aqui si se ha acabado? 
	}
	
	public EstadoNumericSquare(String[][] table) { //int i
		super();
		this.table = table; //copiar cuadrado con operaciones
		this.n = table.length;
		
		this.restantes = getRestantes(0,0);
	}
	
	
	private boolean getRestantes(int x, int y) {
		int[] hueco = new int[2];
		if(filaActual == -1 || columnaActual == -1) {
			hueco = getSigCasillaLibre(0, 0);
		}else {
			hueco = getSigCasillaLibre(filaActual, columnaActual);
		}
		
		if(hueco[0] >= this.table.length-2) {
			return false;
		}
		return true;
	}

	@Override
	public void calcularValorHeuristico() {
		this.valorHeuristico = 0;
		
	}

	@Override
	public ArrayList<Estado> expandir() {  //SOLO GENERAR LOS HIJOS VALIDOS
		ArrayList<Estado> listaHijos = new ArrayList<Estado>();
		int[] hueco = new int[2];
		
		//buscar la casilla siguiente a rellenar, siendo esta hueco
		if(filaActual == -1 && columnaActual == -1 && table[0][0].equals("?")) { //caso de que se haya creado directamente de un tablero (el caso inicial)
			hueco[0] = 0;
			hueco[1] = 0;
		}else if(filaActual == -1 && columnaActual == -1){
			hueco = getSigCasillaLibre(0, 0);
		}else {
			hueco = getSigCasillaLibre(filaActual, columnaActual);
		}
		
		//meter aqui comprobacion si hemos acabado ya o no?
		int valorK;
		//recorre todas las tareas
		for (int j = 0; j < 10; j++) {  //vamos creando hijos rellenando con los valores
			if(esHijoValido(j, hueco[0], hueco[1])) { //SOLO GENERAR LOS HIJOS VALIDOS  (ESTO ES LO QUE SE COMPRUEBA AQUI, habria que modificarlo)
				valorK = j;
				Estado estadoHijos = new EstadoNumericSquare(this, valorK, hueco[0], hueco[1]); //rellenamos el siguiente hueco del hijo con 0,1,2,3,4...
				listaHijos.add(estadoHijos);
				
				/*
				if(RamificaYPoda.TRAZA) {
					System.out.println("Nivel: "+profundidad+" - Hijo: "+j); //nivel, profu, hijo);
					System.out.println(estadoHijos);//printeamos tablero
				}
				*/
			}
			
		}
		return listaHijos;
	}

	@Override
	public boolean solucion() {  //n siendo el num de ?, para asi verificar que hemos rellenado todos?
		//en mi caso, hacer lo de arriba supongo
		/*
		if (profundidad==n)
			return true;
		else
			return false;
			*/
		return !restantes; //si es true, es que no quedan ?
	}
	
	///////////uso de metodos del numericSquare como getSig, esValida y etc
	
	public int[] getSigCasillaLibre(int filaActual, int columnaActual) {
		int[] res = new int[2];
		boolean cond = true;
		
		do
		 {
			columnaActual = columnaActual + 2;
			 if (columnaActual >= table.length-2) //sizeNum	
			  {
				 filaActual = filaActual+2;
				 columnaActual = 0;
			  }					//sizeNum
			 cond = (filaActual>=table.length-2) || table[filaActual][columnaActual].equals("?");  //reestablecemos la condicion para ver si seguimos iterando
		 }
		while (!cond);
		
		//asignamos los resultados x y
		res[0] = filaActual;
		res[1] = columnaActual;
		
		return res;
	}
	
	private boolean esHijoValido(int j, int filaHueco, int columnaHueco) {
		String[][] tableroHijo = new String[table.length][table[0].length];
		for (int i = 0; i < table.length; i++) {
			for (int k = 0; k < table[0].length; k++) {
				tableroHijo[i][k] = table[i][k];
			}
		}
		// = Arrays.copyOf(this.table,n);
		tableroHijo[filaHueco][columnaHueco] = "" + j;
		if(esFilaValida(tableroHijo, filaHueco, columnaHueco) && esColumnaValida(tableroHijo, filaHueco, columnaHueco)) {
			return true;
		}
		return false;
	}
	
	private boolean esFilaValida(String[][] table, int fila, int colum) {
		//Comprobamos la fila
		for (int k = colum; k < table[0].length - 2; k++) {
			if(table[fila][k].equals("?")) {
				return true;
			}
		}
		int acum = Integer.valueOf(table[fila][0]);
		for (int k = 1; k < table[0].length - 2; k = k+2) { //vamos recorriendo los operadores (este -2 para evitar el = y el resultado)
			switch(table[fila][k]) {
			case("+"):
				acum = acum + Integer.valueOf(table[fila][k+1]) ;
				break;
			case("-"):
				acum = acum - Integer.valueOf(table[fila][k+1]) ;
				break;
			case("*"):
				acum = acum * Integer.valueOf(table[fila][k+1]) ;
				break;
			case("/"):
				if(Integer.valueOf(table[fila][k+1]) == 0) { //asi evito que divida entre 0
					return false;
				}
				if(acum % Integer.valueOf(table[fila][k+1]) > 0) {
					return false;
				}
				acum = acum / Integer.valueOf(table[fila][k+1]) ;
				break;	
			}
		}
		if(acum != Integer.valueOf(table[fila][table[0].length-1])) {
			return false;
		}
		
		return true;
	}
	
	
	private boolean esColumnaValida(String[][] table, int fila, int colum) {
		//Comprobamos la columna
		for (int k = fila; k < table.length - 2; k++) {
			if(table[k][colum].equals("?")) {
				return true;
			}
		}
		int acumC = Integer.valueOf(table[0][colum]);
		for (int k = 1; k < table.length - 2; k = k+2) { //vamos recorriendo los operadores (este -2 para evitar el = y el resultado)
			switch(table[k][colum]) {
			case("+"):
				acumC = acumC + Integer.valueOf(table[k+1][colum]) ;
				break;
			case("-"):
				acumC = acumC - Integer.valueOf(table[k+1][colum]) ;
				break;
			case("*"):
				acumC = acumC * Integer.valueOf(table[k+1][colum]) ;
				break;
			case("/"):
				if(Integer.valueOf(table[k+1][colum]) == 0) { //asi evito que divida entre 0
					return false;
				}
				if(acumC % Integer.valueOf(table[k+1][colum]) > 0) {
					return false;
				}
				acumC = acumC / Integer.valueOf(table[k+1][colum]) ;
				break;	
			}
			if(acumC != Integer.valueOf(table[table.length-1][colum])) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
