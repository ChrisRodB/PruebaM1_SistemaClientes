package vista;

import java.util.Scanner;

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
			System.out.println("1. Listar Clientes\n" + "2. Agregar Cliente\n" + "3. Editar Cliente\n" + "4. Cargar Datos\n"
					+ "5. Exportar Datos\n" + "6. Salir\n");
			System.out.print("Ingrese una opción: ");
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
				System.out.println("Opción no válida.");
			}
		} while(opcion!="6");
		
	}

}
