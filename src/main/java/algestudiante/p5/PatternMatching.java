package algestudiante.p5;

public class PatternMatching {

	static String cadenaBase;
	static boolean[][] resultado;
	
	//el array debe empezar con cadena vacia, y luego los strings
	//IMPORTANTE EL Boolean, pues si no los asserts de la prueba fallan
	public static Boolean checkPattern(String cadena) {
		switch(cadena) {
			case(""): //caso de que se pase una cadena vacia
				if(cadenaBase.isBlank()) { //unico caso para que devuelva true es que ambas sean vacias
					//rellenarTrue();
					return true;
				}else {
					//rellenarFalse();
					return false;
				}
		}
		//resultado = new boolean[cadenaBase.length()+1][cadena.length()+1];
		resultado = new boolean[cadena.length()+1][cadenaBase.length()+1];
		
		resultado[0][0] = true; //la coincidencia de las cadenas vacias iniciales
		for (int i = 1; i < cadenaBase.length()+1; i++) { //este +1 y -1 por la cadena vacia del comienzo
			//char letraPivote = cadena1.charAt(i-1);
			for (int j = 1; j < cadena.length()+1; j++) {
				//sin contemplar aun caracteres especiales:
				//Para contemplarlos, hacer un switch
				switch(cadena.charAt(j-1)) {
				//cambiar i-j
					case('?'): //caso 4
						if((i-1) >= 0 && (j-1) >= 0 && resultado[i-1][j-1]) { //posición anterior de cada cadena (fila anterior y columna anterior) habia coincidencia
							resultado[i][j] = true;
						}else if((j-1) >= 0 && resultado[i][j-1]) {  //posición anterior del patrón había una coincidencia (misma fila y columna anterior)
							resultado[i][j] = true;
						}else {
							resultado[i][j] = false;
						}
					case('*'): //caso 5
						if((i-1) >= 0 && (j-1) >= 0 && resultado[i-1][j-1]) {  //posición anterior de cada cadena (fila anterior y columna anterior) había una coincidencia
							resultado[i][j] = true;
						}else if((j-1) >= 0 && resultado[i][j-1]) {  //posición anterior del patrón había una coincidencia (misma fila y columna anterior)
							resultado[i][j] = true;
						}else if((i-1) >= 0 && resultado[i-1][j]) { //posición anterior del texto había una coincidencia (fila anterior y misma columna)
							resultado[i][j] = true;
						}else {
							resultado[i][j] = false;
						}
						
					default: //casos 2 y 3
						if((cadenaBase.charAt(i-1))==(cadena.charAt(j-1))) {
							resultado[i][j] = true;
						}else {
							resultado[i][j] = false;
						}
						
				}					
			}
		}
		
		return coincideResultado(resultado);
	}

	//para que coincide (devolver true), hay minimo un true por fila (?)
	//IMPORTANTE EL Boolean, pues si no los asserts de la prueba fallan
	private static Boolean coincideResultado(boolean[][] resultado) {
		int contador = 0;
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[0].length; j++) {
				if(resultado[i][j]) {
					contador++;
					break;
				}
			}
		}
		if(contador==resultado[0].length) {
			return true;
		}
		return false;
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
		this.cadenaBase = texto;
	}
}
