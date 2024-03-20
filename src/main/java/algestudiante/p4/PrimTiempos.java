package algestudiante.p4;

import java.io.IOException;
import java.util.Random;

import algestudiante.p2.Vector;
import algestudiante.p3.Mezcla;

public class PrimTiempos {
	static int [][] grafo;

	public static void main (String arg []) {
		long t1,t2;
			
		for (int n= 256; n<=1_000_000_000; n*= 2)
		{
			//Random r = new Random();
			//int a = r.nextInt(4, 500);
			//grafo = Auxiliar.matrizTriangularEnterosAleatorios(a, -1000, 1000);
			grafo = Auxiliar.matrizTriangularEnterosAleatorios(n, -10000, 10000);
				
		  	t1 = System.currentTimeMillis();
		  	
		  	
			Prim.Prim(grafo);

		         
			t2 = System.currentTimeMillis();

		  	System.out.println (n+"\t"+(t2-t1));
		}
	}
}
