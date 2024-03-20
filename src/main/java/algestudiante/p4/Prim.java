package algestudiante.p4;

public class Prim {
	
	//MASPROXIMO[i] vértice del conjunto de vértices tratados hasta el momento más cercano al vértice i
		static int[] masproximo;
		//DISTMINIMA[i] distancia desde i a ese vértice más próximo
		static int[] distmin;
		//nodos ya alcanzados
		static boolean[] alcanzados;
		
		//Lista vertices paso a paso
		static int[] ordenAlcanzados;
	
	static int Prim(int[][] grafo) {
		
		masproximo = new int[grafo.length];
		distmin = new int[grafo.length];
		alcanzados = new boolean[grafo.length];
		
		ordenAlcanzados = new int[grafo.length];
		alcanzados[0] = true;
		//en ordenLancanzados no incluyo el 0, pues es el punto de partida
		
		InicProx(grafo); //establece los valores iniciales de los dos vectores
		
		int min = Integer.MAX_VALUE;
		int minTot = 0;
		int pivote = 0; //pues es de donde partimos
		
		for (int i = 0; i < grafo.length-1; i++) {
			min = Integer.MAX_VALUE;
			for (int j = 1; j < grafo.length; j++) {
				if(distmin[j] < min && distmin[j] != 0 && !alcanzados[j]) {
					min = distmin[j];
					pivote = j;
				}
			}
			//Ya obtenido el siguiente nodo a alcanzar, actualizamos sus arrays
			alcanzados[pivote] = true;
			ordenAlcanzados[i] = pivote;
			
			
			//Desde 1, pues 0 es el de partida y no esta en alcanzados
			for (int j = 1; j < grafo.length; j++) {
				if(grafo[j][pivote] < distmin[j] && !alcanzados[j]) {
					distmin[j] = grafo[j][pivote];
					masproximo[j] = pivote;
				}
			}
			
			minTot = minTot + min; //actualizamos la cuenta de costes minimos una vez sacado el definitivo de esta iteracion
		}
		
		
//		System.out.println("Coste total: " + minTot);
//		for (int i = 0; i < grafo.length-1; i++) {
//			System.out.println(getAristaNumero(i, grafo));
//		}
		
		
		return minTot;
	}

	private static void InicProx(int[][] grafo) {
		int n = grafo.length;
        for (int i = 1; i < n; i++) {
        	masproximo[i] = 0; //de momento solo hemos tratamos el 0
        	//distmin[i] = grafo[0][i]; Da lo mismo
        	distmin[i] = grafo[i][0];
        }
	}
	
	public static int getVerticeMasCercano(int nodoObjetivo) {
		return masproximo[nodoObjetivo];
	}
	
	public static String getAristaNumero(int numeroArista, int[][] grafo) {
		String res = "ARISTA NUMERO {" + numeroArista + "} :  DESDE NODO= {" + getVerticeMasCercano(ordenAlcanzados[numeroArista]) + "}  "
				+ "HASTA NODO {" + ordenAlcanzados[numeroArista] + "} *** COSTE= {" + grafo[getVerticeMasCercano(ordenAlcanzados[numeroArista])][ordenAlcanzados[numeroArista]] + "}";
		return res;
	}
}
