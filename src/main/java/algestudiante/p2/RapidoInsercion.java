package algestudiante.p2;

/* Este programa sirve para ordenar n elementos por Hoare */

public class RapidoInsercion
{
static int []v;

public static void rapidoInsercion(int[] v, int k) 
{
	rapirec(v,0,v.length-1, k);		//k es el tamano minimo para dividr los vectores que nostors decidamos
}

private static void rapirec (int[] v, int iz, int de, int k) 
{
	int m;
	//if (de>iz) 	//Esta es si el tamño es de uno. La nueva condicion: tama�o sea k
	if(de - iz > k)	//Si el vector "creado" es mayor al tamaño establecido, lo partimos
	{
		m=particion(v,iz,de);
		rapirec(v,iz,m-1, k);		//asi no incluimos al pivote para devolverlo luego
		rapirec(v,m+1,de, k);		//asi no incluimos al pivote para devolverlo luego
	}else {	//el vector es igual, o menor, de k, por lo que realizamos ya la insercion
		insercion(v, iz, de);
	}
}

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

/* Intercambia los elementos de las posiciones i, j en el array a
 es O(1)	 */
private static void intercambiar (int[] v, int i, int j)
{
	int t;
	t=v[i];v[i]=v[j];v[j]=t;
}	

/** Deja el pivote en una posicion tal que a su izquierda no 
    hay ningun mayor, ni a la derecha ningun menor.
    Es un proceso lineal O(n).  */

private static int particion(int[]v,int iz,int de) 
{
	int i, pivote;
	intercambiar (v, (iz+de)/2,iz);			//esta funcion es la que cambia segun que pivote escojamos (en este caso el central)
	//el pivote es el de centro y se cambia con el primero
	pivote= v[iz]; 
	i= iz;
	for (int s= iz+1; s <= de; s++) 
		if (v[s] <= pivote) 
		{
			i++;
			intercambiar(v,i,s);
		}
	intercambiar(v,iz,i);//se restituye el pivote donde debe estar
	return i; // retorna la posicion en que queda el pivote 
}


public static void main (String arg [] )
{
	int n=Integer.parseInt(arg[0]);  //tamanno del problema  
	v = new int[n] ;

	Vector.ordenDirecto(v);
	System.out.println ("VECTOR A ORDENAR ES");
	Vector.escribe(v);	
	rapidoInsercion(v, 2);
	System.out.println ("VECTOR ORDENADO ES");
	Vector.escribe (v);

	Vector.ordenInverso(v);
	System.out.println ("VECTOR A ORDENAR ES");
	Vector.escribe(v);	
	rapidoInsercion(v, 2);
	System.out.println ("VECTOR ORDENADO ES");
	Vector.escribe(v);

	Vector.ordenAleatorio(v);
	System.out.println ("VECTOR A ORDENAR ES");
	Vector.escribe(v);	
	rapidoInsercion(v, 2);
	System.out.println ("VECTOR ORDENADO ES");
	Vector.escribe(v);
} // fin de main
	  
} 

