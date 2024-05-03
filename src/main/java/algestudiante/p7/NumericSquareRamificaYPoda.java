package algestudiante.p7;


import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import algestudiante.p6.NumericSquareAll;
import algestudiante.util.RamificaYPoda;

public class NumericSquareRamificaYPoda{
	
	private static String PATH= "src/main/java/algestudiante/p6/";
	private static String[][] table;

	@Test
	public void test00() {
		loadData(PATH+"test00.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		long t1 = System.currentTimeMillis();
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 00: "+(t2-t1));
	}
	
	@Test
	public void test01() {
		loadData(PATH+"test01.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 01: "+(t2-t1));
	}
	
	@Test
	public void test02() {
		loadData(PATH+"test02.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 02: "+(t2-t1));
	}
	
	@Test
	public void test03() {
		loadData(PATH+"test03.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 03: "+(t2-t1));
	}
	
	@Test
	public void test04() {
		loadData(PATH+"test04.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 04: "+(t2-t1));
	}
	
	@Test
	public void test05() {
		loadData(PATH+"test05.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 05: "+(t2-t1));
	}
	
	@Test
	public void test06() {
		loadData(PATH+"test06.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 06: "+(t2-t1));
	}
	
	@Test
	public void test07() {
		loadData(PATH+"test07.txt"); //carga en table el tablero generado en base al fichero
		
		// Creamos una instancia para resolver el problema
		long t1 = System.currentTimeMillis();
		RamificaYPoda problemaNumericSquare = new RamificaYPoda(false); 

		// Ejecutamos el metodo que va recorriendo el espacio de soluciones con ramifica y poda
		problemaNumericSquare.ramificaYPoda(new EstadoNumericSquare(table)); 
		
		long t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo 07: "+(t2-t1));
	}

	/**
	 * Load data from file and store in array with operations and results
	 * @param file
	 */
	private static void loadData(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int i = 0;
			int size = Integer.valueOf(reader.readLine());
			table = new String[size*2+1][size*2+1]; //the number of rows and columns are twice + 1 the number of operations in each direction
			while (reader.ready()) { //each line
				String[] parts = reader.readLine().split(" ");
				int j = 0; //columns
				for (String part : parts) {
					table[i][j] = part; 
					if ((i%2 == 1)||(i == size*2)) //to separate operators in odd rows (i%2 == 1) and in the last row (i == size*2)
						j++; //we skip an extra column to create the table
					j++;
				}
				i++; //next line
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Converts a table including numbers, operations and results to a table with only numbers but in String format
	 * @param sol table with operations and results
	 * @return numTable converted to only numeric values
	 */
	private String[][] convertNum(String[][] sol) {
		int numSize= sol.length/2;  // number table size
		String[][] numTable= new String[numSize][numSize];
		
		int a= 0; int b= 0;  // number table index
		for (int i = 0; i < sol.length-2; i+= 2) {  // sol.length-2 because we don't convert the result
			for (int j = 0; j < sol.length-2; j+= 2) {
				if (sol[i][j] != null) {
					numTable[a][b]= sol[i][j];
					b++;
				}
			}
			a++; b= 0;
		}

		return numTable;
	}
	
	/**
	 * Print any two-dimensional table with a title via console
	 * @param table for printing
	 * @param title
	 */
	private void printTable(String[][] table, String title) {  //title es opcional
		if (title != null)
			System.out.printf("NUMERIC SQUARE - %s\n", title);
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j] == null)
					System.out.printf("%5s", "");
				else
					System.out.printf("%5s", table[i][j]);
			}
			System.out.printf("\n\n");
		}
	}
}