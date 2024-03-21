package algestudiante.p5;

public class Pruebas {

	public static void main(String[] args) {
		String cadena1 = "casa";
		String cadena2 = "casa";
		PatternMatching a = new PatternMatching(cadena1);
		a.checkPattern(cadena2);
		boolean[][] res = a.resultado;
		a.printsTable();
		
		System.out.println("-----------------------------");
		String cadena3 = "casa";
		String cadena4 = "ca?a";
		PatternMatching a2 = new PatternMatching(cadena3);
		a.checkPattern(cadena4);
		boolean[][] res2 = a2.resultado;
		a2.printsTable();
		
		//creo que estoy poniendo el texto y el patron al reves (CORREGIR)
	}

}
