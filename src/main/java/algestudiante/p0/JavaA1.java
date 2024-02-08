package algestudiante.p0 ;

import java.util.ArrayList;
import java.util.List;

public class JavaA1
{

	static boolean primoA1(int m)
	{
		boolean p=true;
		for (int i=2;i<=m-1;i++)
			if (m%i==0)
				p=false;
		return p;
	}
	
	static List<Integer> listadoPrimos(int n) {
		//""" calcula y devuelve todos los primos hasta n"""
		List<Integer> lSal = new ArrayList<Integer>();
		
		//for (int i = 0; i < (2,n+1); i++) {
		for (int i = 2; i < (n+1); i++) {
			if(primoA1(i)) {
				lSal.add(i);
			}
		}
		return lSal;
	}

 
    public static void main (String arg [] ){
        long t1,t2;                  //obligatoriamente de tipo long para no desbordar
        		// la toma de tiempos en Java se verá con más profundidad en la sesión siguiente
	

        System.out.println("TIEMPOS DEL ALGORITMO A1");
        int n=10000;
        for (int casos = 0; casos < 8; casos++) {
        	t1=System.currentTimeMillis();  //da el tiempo en seg. y con decimales(real)
        	List<Integer> lPrimos = listadoPrimos(n);
        	t2=System.currentTimeMillis();
        	System.out.println("n= " + n + "***" + "tiempo =" + (int)(1000*(t2-t1)) + " milisegundos)");
		    //print(lPrimos)
        	n=n*2;
        }
    



     } // fin de main

} // fin de clase

