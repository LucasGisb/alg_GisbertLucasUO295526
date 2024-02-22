package algestudiante.p2;

/* Esta clase mide tiempos del metodo Burbuja
para los 3 supuestos de orden incial (ordenado, inverso y aleatorio) */

public class RapidoInsercionTiempos
{

static int []v;

public static void main (String arg [])
{
long t1,t2;
String opcion=arg[0];
int limInf = Integer.parseInt(arg[1]);
int limSup = Integer.parseInt(arg[2]);

	
for (int n= 250_000; n<=1_000_000_000; n*= 2)
{
	v=new int [n];

   	if (opcion.compareTo("ordenado")==0)
		Vector.ordenDirecto(v);
   	else if (opcion.compareTo("inverso")==0)
		Vector.ordenInverso(v);
   	else
		Vector.ordenAleatorio(v);
		
  	t1 = System.currentTimeMillis();
	  
  	//int[] v2 = copia
  	
	InsercionNuevo.insercion(v, limInf, limSup);

         
	t2 = System.currentTimeMillis();

  	System.out.println (n+"\t"+(t2-t1));
}
}
}
