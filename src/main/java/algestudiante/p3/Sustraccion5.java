package algestudiante.p3;

public class Sustraccion5
{

static long cont;

//Complejidad de este algoritmo: O(3^n/2)					()
//a=3, b=2, k=1 
public static void rec5 (int n)
{
	if (n<=0) 
		cont++;
	else
	{
		for(int i = 0; i < n; i++) {
			cont++;   // O(n)    
		}
		rec5 (n-2);
		rec5 (n-2);
		rec5 (n-2);
	}
}


public static void main (String arg []) 
{
	long t1,t2,cont;
	int nVeces= Integer.parseInt (arg [0]);
	 
	//mejor hacerlo de dos en dos, al llamar menos veces recursivamente puede habe menos errores
	//for (int n=20;n<=100;n++)
	for (int n=20;n<=100;n=n+2)
	{
		t1 = System.currentTimeMillis ();

		for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
		{ 
			cont=0;
			rec5 (n);
		} 

		t2 = System.currentTimeMillis ();

		System.out.println (" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);
	}  // for
} // main
} //class