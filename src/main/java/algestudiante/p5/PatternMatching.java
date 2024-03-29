package algestudiante.p5;

public class PatternMatching {

	static String texto;
	static boolean[][] resultado;
	
	//el array debe empezar con cadena vacia, y luego los strings
	//IMPORTANTE EL Boolean, pues si no los asserts de la prueba fallan
	public static Boolean checkPattern(String patron) {
		if(patron == "") {
			if(texto.isBlank()) {
				return true;
			}else {
				return false;
			}
		}
		resultado = new boolean[texto.length()+1][patron.length()+1];
		resultado[0][0] = true; //la coincidencia de las cadenas vacias iniciales
		for (int i = 1; i < texto.length()+1; i++) { //este +1 y -1 por la cadena vacia del comienzo
			char charTex = texto.charAt(i-1);
			for (int j = 1; j < patron.length()+1; j++) {
				char charPat = patron.charAt(j-1);
				if(charTex == charPat) { 
					if(resultado[i-1][j-1]) { //solo lo hacemos si vamos encontrando coincidencias antes
						resultado[i][j] = true;
					}
				}else {
					switch(charPat) {
					case('?'): //caso 4
						if(resultado[i-1][j-1] || resultado[i][j-1]) { //posici�n anterior de cada cadena (fila anterior y columna anterior) habia coincidencia
							resultado[i][j] = true;
						}
						break;
					case('*'): //caso 5
						if(resultado[i-1][j-1] || resultado[i][j-1] || resultado[i-1][j]) {  //posici�n anterior de cada cadena (fila anterior y columna anterior) hab�a una coincidencia
							resultado[i][j] = true;
						}
						break;
				}
				}					
			}
		}
		//return coincideResultado(resultado);
		return resultado[resultado.length-1][resultado[0].length-1]; //si el ultimo de la ultima fila esta a true, entonces hay coincidencia
	}
	
	private static int getMax(int length, int length2) {
		if(length > length2) {
			return length;
		}
		return length2;
	}

	public void printsTable() {
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[0].length; j++) {
				System.out.print(resultado[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public PatternMatching(String texto) {
		this.texto = texto;  //le anadimos la cadena vacia para la comprobacion
	}
}
