package algestudiante.p3;

public class PruebasMezcla {
	
	public static void main (String arg []){
		int[] v1 = new int[4];
		v1[0] = 3;
		v1[1] = 6;
		v1[2] = 1;
		v1[3] = 5;
		int[] v2 = new int[5];
		v2[0] = 7;
		v2[1] = 2;
		v2[2] = 1;
		v2[3] = 6;
		v2[4] = 9;
		Mezcla.mezcla(v1);
		Mezcla.mezcla(v2);
		for (int i = 0; i < v1.length; i++) {
			System.out.print(v1[i] + " - ");
		}
		System.out.println();
		
		for (int i = 0; i < v2.length; i++) {
			System.out.print(v2[i] + " - ");
		}
		 
	}
}
