package algestudiante.p4;

public class PrimClase {
	
	int masProximo = 0; //Partimos del nodo 0
	int distMinima = Integer.MAX_VALUE; //Para que cualquier distancia a otro nodo sea menor
	
	boolean[] recorridos;
	
	//Grafo: La matriz de adyacencias del grafo dado
	public int[][] Prim(int[][] grafo, int[][] sol) {	//asumo que pos esta vacio
		//masProximo = 0; //Partimos del nodo 0
		//distMinima = Integer.MAX_VALUE;
		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo[0].length; j++) {
				sol[i][j] = -1;
			}
		}
		
		recorridos = new boolean[grafo.length];
		recorridos[masProximo] = true;  //el cero esta recorrido, pues es de donde partimos
		
		for (int i = 0; i < grafo.length; i++) {
			masProximo = InicProx(grafo, sol); //partiendo de 0, miramos el siguiente
			
		}
		
		return sol;
	}
	
	//private int InicProx(int[][] grafo, int masProximo, int distMinima) {
	private int InicProx(int[][] grafo, int[][] sol) {	//los tengo como atributos
		int pivote = this.masProximo;
		int distDesdePivote = this.distMinima;
		
		for (int i = 0; i < grafo[pivote].length; i++) { //recorremos la fila del nodoActual
			if(i != pivote && !recorridos[i] && grafo[pivote][i] < distDesdePivote) {
				this.masProximo = i;
				this.distMinima = grafo[pivote][i];
			}
		}
		
		sol[pivote][this.masProximo] = this.distMinima;
		recorridos[this.masProximo] = true;
		
		this.distMinima = Integer.MAX_VALUE; //lo reseteamos para la siguiente iteracion
		
		return this.masProximo;
	}
	
	private int getCosteOptimo(int[][] grafo) {
		return 0;
	}
	
	public static void main() {
		
	}
}
