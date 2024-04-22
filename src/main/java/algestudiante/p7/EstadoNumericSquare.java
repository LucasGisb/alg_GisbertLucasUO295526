package algestudiante.p7;

import java.util.ArrayList;
import java.util.Arrays;

import algestudiante.util.Estado;
import algestudiante.util.RamificaYPoda;


public class EstadoNumericSquare extends Estado{

	protected int valorHeuristico; //maxInt y no lo tocamos?  (eso dijo del Heuristico de poda)
	
	private int n;	// Tamaño del problema
	//estado
	private String[][] table;
	
	private boolean[] tareaConTrabajador;
	
	private ArrayList<EstadoNumericSquare> hijos = new ArrayList<EstadoNumericSquare>();

	
	public EstadoNumericSquare(EstadoNumericSquare padre, int j, int x, int y) { //j es el valor a nadir, x y las coordenadas donde meterlo
		super();
		//copiar info padre
		//copiar tabla
		profundidad = padre.profundidad;
		idPadre = padre.getId();
		this.table= Arrays.copyOf(padre.table,n);
		
		//anadimos lo correspondiente al hijo
		this.table[x][y] = ""+ j; //valor a asignar (este se le debe pasar por param de su padre, siendo sacado con el for de expandir)
		profundidad++;
		calcularValorHeuristico();
	}
	
	public EstadoNumericSquare(String[][] table) { //int i
		super();
		this.table = table; //copiar cuadrado con operaciones
		this.n = table.length;
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
		
		//recorre todas las tareas
		for (int j = 0; j < 10; j++) {  //vamos creando hijos rellenando con los valores
			if(!tareaConTrabajador[j]) { //SOLO GENERAR LOS HIJOS VALIDOS  (ESTO ES LO QUE SE COMPRUEBA AQUI, habria que modificarlo)
				Estado estadoHijos = new EstadoNumericSquare(this, j, hueco[0], hueco[1]); //rellenamos el siguiente hueco del hijo con 0,1,2,3,4...
				listaHijos.add(estadoHijos);
				
				if(RamificaYPoda.TRAZA) {
					System.out.println(); //nivel, profu, hijo);
					System.out.println();//printeamos tablero
				}
			}
			
		}
		return listaHijos;
	}

	@Override
	public boolean solucion() {  //n siendo el num de ?, para asi verificar que hemos rellenado todos?
		//en mi cado, hacer lo de arriba supongo
		if (profundidad==n)
			return true;
		else
			return false;
	}
	
	//supongo que se pueden hacer uso de metodos del numericSquare como getSig, esValida y etc
	
}
