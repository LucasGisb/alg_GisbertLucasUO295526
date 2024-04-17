package algestudiante.p6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import algestudiante.p2.Vector;
import algestudiante.p3.Mezcla;

public class TiemposBacktracking {
	
	private static String PATH= "src/main/java/algestudiante/p6/";
	private static String[][] table;
	
	public static void main (String arg []) {
		long t1,t2;
		int repes= Integer.valueOf(arg[0]);
	
		loadData(PATH+"test06.txt"); //carga en table el tablero generado en base al fichero
		NumericSquareAll numericSquare = new NumericSquareAll(table);
		
		t1 = System.currentTimeMillis();
	  	
		numericSquare.backtracking();

	    t2 = System.currentTimeMillis();

	  	System.out.println ("Tiempo: "+(t2-t1));
	  	System.out.println(numericSquare.getNumberOfSolutions());
	}
	
	
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
}
