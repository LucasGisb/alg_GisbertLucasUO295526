package algestudiante.p11;

/** Esta clase utiliza los métodos estáticos de Vector1.
 *  Realiza la medición de tiempos de la operacion de suma
 **/
public class Vector2
{
static int []v;

public static void main (String arg [] )
{
	int n= Integer.parseInt (arg[0]);  //tamaño del problema leido de línea de comandos
	v = new int [n] ;
	Vector1.rellena (v);

	// Declara variables de tipo long para recoger el datos de milisegundos
	long t1,t2;

	t1=System.currentTimeMillis();	// milisegundos actuales antes del código a medir
	int s=Vector1.suma (v);
	t2=System.currentTimeMillis();	// milisegundos actuales después del código a medir

	System.out.println ("t1="+t1+"  *** t2="+t2);
	System.out.println ("Tamaño del problema = "+n+"\t"+"Tiempo método suma = "+(t2-t1));
	//en este caso, el Tiempo metodo suma sale igual a 0, pues es tan poco que no lo dectecta si quiera
	//RESPUESTA A PREGUNTA PRACTICA: para que salga algo mas, en el parametro en run configurations puse 50000 (con este sale 1, en el caso de ESTE PROCESADOR (Importante))
	//En la respuesta, poner a partir de que umbral cambia y cual es el procesador empleado
	
	
	System.out.println ("Resultado de la suma de elementos = "+ s);
} // fin de main

} // fin de clase

