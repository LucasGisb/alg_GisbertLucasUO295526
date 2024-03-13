package algestudiante.p3;

public class Mezcla {
	
	static int []v;
	static int []v2; 
	
	public static void mezcla(int[] v, int[] v2) {	//Complejidad siempre O(n*logn)
		mezR(v, v2, 0, v.length-1);
	}
	
	private static void mezR(int[] v, int[] v2, int iz, int de) { //seria a=2, b=2 y k=1(complejidad de combinar)
		if(iz < de) {
			int m = (iz+de)/2;
			mezR(v, v2, iz, m);
			mezR(v, v2, m+1, de);
			combinar(v, v2, iz, m, m+1, de);
		}
	}

	private static void combinar(int[] v, int[] v2, int iz, int m, int m1, int de) {
		//v2 = new int[v.length];		//NO CONVIENE HACER ESTO
		if(iz > m || m1 > de) {
			return;
		}
		for(int i = iz; i <= de; i++) {	//realizamos la copia del vector (imp <=)
			v2[i] = v[i];
		}
		int i1 = iz;
		int i2 = m1;
		for (int i = iz; i <= de; i++) {
			if(v2[i1] <= v2[i2]) {
				v[i] = v2[i1];
				if(i1 < m) {
					i1++;
				}else {
					v2[i1] = Integer.MAX_VALUE;  //no seguro de que asi sea correcto
				}
			}else {
				v[i] = v2[i2];
				if(i2 < de) {
					i2++;
				}else {
					v2[i2] = Integer.MAX_VALUE;  //no seguro de que asi sea correcto
				}
			}
		}
	}
}
