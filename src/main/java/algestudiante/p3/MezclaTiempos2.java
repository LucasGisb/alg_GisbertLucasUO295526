package algestudiante.p3;

import algestudiante.p2.Vector;

public class MezclaTiempos2 {
	static int []v;

	public static void main (String arg []) {
		long t1,t2;
		String opcion=arg[0];

			
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
		  	
		  	
			Mezcla.mezcla(v);

		         
			t2 = System.currentTimeMillis();

		  	System.out.println (n+"\t"+(t2-t1));
		}
	}
}
