package algestudiante.p2;

/* Este programa sirve para ordenar n elementos con un algoritmo cuadratico
excepto si el vector inicialmente esta ordenado o casi ordenado,
que es de complejidad lineal  (INSERCION ) */

public class InsercionNuevo
{
static int []v;


/* Ordenacion por el metodo de Insercion */ 
public static void insercion (int[] a, int posInf, int posSup) 	//Ahora le pasamos nuevos parametros en funcion de lo que queremos hacer
{
	//int n= a.length;
	for (int i=posInf;i<=posSup;i++) 
	{
		int x=a[i];
		int j=i-1;
		while (j>=0 && x<a[j])
		{ 
			a[j+1]=a[j];
			j=j-1;
		}
		a[j+1]=x;
	}  // for
}

public static void main (String arg [] )
{
	int n=Integer.parseInt(arg[0]);  //tamanno del problema  
	v = new int[n] ;
	
	int limInf=Integer.parseInt(arg[1]);  //tamanno del problema  
	int limSup=Integer.parseInt(arg[2]);  //tamanno del problema  

	Vector.ordenDirecto(v);
	System.out.println ("VECTOR A ORDENAR ES");
	Vector.escribe(v);	
	insercion(v, limInf, limSup);
	System.out.println ("VECTOR ORDENADO ES");
	Vector.escribe (v);

	Vector.ordenInverso(v);
	System.out.println ("VECTOR A ORDENAR ES");
	Vector.escribe(v);	
	insercion(v, limInf, limSup);
	System.out.println ("VECTOR ORDENADO ES");
	Vector.escribe(v);

	Vector.ordenAleatorio(v);
	System.out.println ("VECTOR A ORDENAR ES");
	Vector.escribe(v);	
	insercion(v, limInf, limSup);
	System.out.println ("VECTOR ORDENADO ES");
	Vector.escribe(v);
} // fin de main
	  
} 

