package algestudiante.p11;


/** Esta clase utiliza los m�todos est�ticos de la clase Vector1.
 * Sirve para medir tiempos de la operaci�n suma, para ello:
 * Va incrementando autom�ticamente el tama�o del problema y adem�s
 * seg�n una escala de tiempos determinada por repeticiones, 
 * que se proporciona como argumento en l�nea de comandos para la ejecuci�n
 */
public class Vector6
{
static int []v;
static int[]m;

public static void main (String arg [] )
{
	int repeticiones = Integer.parseInt (arg[0]);	// veces que se repite la operaci�n

	long t1,t2;

	System.out.println("repeticiones = "+ repeticiones);
	System.out.println ("Tama�o\tTiempo");   
	for ( int n= 10000; n<= 81920000; n*=2) // n se va duplicando   
	{
		v = new int [n] ;
		m = new int [n];
		Vector1.rellena (v);
		Vector1.rellena(m);

		t1=System.currentTimeMillis();

		
		// hay que repetir todo el proceso a medir (lo que que estaba entre t1 y t2) 
		for (int r= 1; r<=repeticiones; r++)
		{  	
			Vector1.coincidencias1(v, m);
		}

		t2=System.currentTimeMillis();
		System.out.println (n+"\t"+(t2-t1));   

	} // fin de for
		
	System.out.println("\nFin de la medici�n de tiempos *****");

} // fin de main

} // fin de clase