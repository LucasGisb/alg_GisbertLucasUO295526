package algestudiante.p0;

import java.util.ArrayList;

public class JavaA3 {
	static boolean primoA3(int m){
		for (int i=2;i<= m/2; i++)
			if (m%i==0) {
				return false;
			}
		return true;
	}
	
	
	static ArrayList<Integer> listadoPrimos(int n){
		ArrayList<Integer> lSal = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			if(primoA3(i)) {
				lSal.add(i);
			}
		}
		return lSal;
	}
	
	public static void main (String arg [] )
	{
	long t1,t2;                  //obligatoriamente de tipo long para no desbordar
	// la toma de tiempos en Java se ver� con m�s profundidad en la sesi�n siguiente
	int n = 10000;
	
	for (int i = 0; i < 8; i++) {  //numero de casos/repeticiones a realizar
		t1=System.currentTimeMillis();	// milisegundos (sin decimales) 
		ArrayList<Integer> lPrimos = listadoPrimos(n);
		t2=System.currentTimeMillis();	
		
		System.out.println("n = " + n + "****, tiempo = " + (t2-t1) + " milisegundos");
		n= n*2;  //aumentar la cantidad para la siguiente iteracion
		}
	} // fin de main
}
