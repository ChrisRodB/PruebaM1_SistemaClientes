package utilidad;

/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.io.File;

import modelo.Cliente;

public class Utilidad {

	public static boolean validarExistenciaArchivo(String ruta, String nombreArchivo) {
		String strDirFile = ruta.isEmpty() ? null : ruta;

		if (strDirFile != null) {
			File fDirectorio = new File(ruta);
			if (!fDirectorio.exists()) {
				imprimeConsolaConTitulo("------Mensaje------", "La carpeta o ruta \"" + ruta + "\" no existe.", true, 1, 2000);
				return false;
			}
		}
		
		File fArchivo = new File(strDirFile, nombreArchivo);
		if (!fArchivo.exists()) {
			imprimeConsolaConTitulo("------Mensaje------", "El archivo \"" + nombreArchivo + "\" no existe.", true, 1, 2000);
			return false;
		}
		
		return true;
	}
	
	public static boolean crearDirectorio(String ruta) {
		if (ruta != null) {
			File fDirectorio = new File(ruta);
			if (!fDirectorio.exists()) {
				if (!fDirectorio.mkdirs()) {
					Utilidad.imprimeConsolaConTitulo("------Error------", "No se pudo crear la carpeta \"" + ruta + "\n.", true, 1, 2000);
					return false;
				}
			}
		}
		return true;
	}

	public static void imprimeConsolaConTitulo(String titulo, String mensaje, boolean conPieDePagina,
			int saltoLineasVacias, int tiempoEspera) {
		desplegarCabecera(titulo, 1);
		System.out.println(mensaje);
		finalSalidas(conPieDePagina, saltoLineasVacias, tiempoEspera);
	}
	
	public static void imprimeConsolaSimple(String mensaje, int saltoLineasVacias, int tiempoEspera) {
		System.out.println(mensaje);
		finalSalidas(false, saltoLineasVacias, tiempoEspera);
	}

	public static String imprimeConsolaDatosCliente(Cliente cliente, boolean despliegaNum) {
		String num1 = despliegaNum ? "1.- " : "";
		String num2 = despliegaNum ? "2.- " : "";
		String num3 = despliegaNum ? "3.- " : "";
		String num4 = despliegaNum ? "4.- " : "";
		String categoria = despliegaNum ? "" : "\nCategoría del Cliente: " + cliente.getNombreCategoria().name();
		return num1 + "RUN del Cliente: " + cliente.getRunCliente() + "\n" + num2 + "Nombre del Cliente: "
				+ cliente.getNombreCliente() + "\n" + num3 + "Apellido del Cliente: " + cliente.getApellidoCliente()
				+ "\n" + num4 + "Años como Cliente: " + cliente.getAniosCliente() + " años" + categoria;
	}

	public static void desplegarCabecera(String mensaje, int cantidadSaltos) {
		String saltos = "";
		for (int i = 0; i < cantidadSaltos; i++) {
			saltos += "\n";
		}
		System.out.println("\n--------------------" + mensaje + "--------------------" + saltos);
	}

	public static void finalSalidas(boolean conPieDePagina, int cantidad, int tiempoEspera) {
		String saltos = "";
		for (int i = 0; i < cantidad; i++) {
			saltos += "\n";
		}
		if (conPieDePagina) {
			System.out.println("\n---------------------------------------------------------" + saltos);
		}
		esperaTiempo(tiempoEspera);
	}

	public static void limpiarConsola(int intCantidad) {
		/*
		 * for (int i = 0; i < intCantidad; i++) { System.out.println("\n"); }
		 */
	}

	public static void esperaTiempo(int tiempoMilSeg) {
		try {
			Thread.sleep(tiempoMilSeg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
