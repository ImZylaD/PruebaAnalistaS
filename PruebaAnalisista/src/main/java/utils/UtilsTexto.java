package utils;

public class UtilsTexto {
	public static String soloLetras(String cadena) {
		return cadena.replaceAll("[^\\p{L}\\p{Z}]", "").toLowerCase(); 
	}
}
