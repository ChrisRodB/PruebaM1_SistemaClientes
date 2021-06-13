package vista;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.util.Scanner;

import utilidad.Utilidad;

public abstract class MenuTemplate {

	protected Scanner scanner = new Scanner(System.in);

	public abstract void listarCliente();

	public abstract void agregarCliente();

	public abstract void editarCliente();

	public abstract void importarCliente();

	public abstract void exportarCliente();

	public abstract void terminarCliente();

	public final void iniciarMenu() {
		String opcion="";
		do {
			Utilidad.imprimeConsolaConTitulo("SISTEMA DE CLIENTES", "1. Listar Clientes\n" + "2. Agregar Cliente\n" + "3. Editar Cliente\n" + "4. Cargar Datos\n"
					+ "5. Exportar Datos\n" + "6. Salir\n\n" + "Ingrese una opción: ", false, 0, 0);
			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				listarCliente();
				break;
			case "2":
				agregarCliente();
				break;
			case "3":
				editarCliente();
				break;
			case "4":
				importarCliente();
				break;
			case "5":
				exportarCliente();
				break;
			case "6":
				terminarCliente();
				break;
			default:
				Utilidad.imprimeConsolaConTitulo("------Error------", "Opción no válida!", true, 1, 2000);
			}
		} while(opcion!="6");
		
	}

}
