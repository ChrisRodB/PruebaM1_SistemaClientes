package vista;

import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ClienteServicio;
import servicio.ClienteServicioImp;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;

public class Menu extends MenuTemplate {
	
	//private Utilidad utilidad;
	private ClienteServicio clienteServicio = new ClienteServicioImp();
	//private ArchivoServicio archivoServicio;
	private ExportadorCsv exportadorCsv =  new ExportadorCsv();
	private ExportadorTxt exportadorTxt =  new ExportadorTxt();

	@Override
	public void listarCliente() {
		clienteServicio.retornoListarClientes().stream().forEach(c -> {
			System.out.println("RUN del Cliente: " + c.getRunCliente());
			System.out.println("Nombre del Cliente: " + c.getNombreCliente());
			System.out.println("Apellido del Cliente: " + c.getApellidoCliente());
			System.out.println("Años como Cliente: " + c.getAniosCliente() + " años");
			System.out.println("Categoría del Cliente: " + c.getNombreCategoria().name());
			});
	}

	@Override
	public void agregarCliente() {
		System.out.print("Ingresa RUN del Cliente: ");
		String run = scanner.nextLine();
		System.out.print("Ingresa Nombre del Cliente: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingresa Apellido del Cliente: ");
		String apellido = scanner.nextLine();
		System.out.print("Ingresa Años del Cliente: ");
		String anios = scanner.nextLine();
		
		Cliente cliente = new Cliente(run, nombre, apellido, anios, CategoriaEnum.ACTIVO);
		
		clienteServicio.agregarCliente(cliente);

	}

	@Override
	public void editarCliente() {
		String run;
		Cliente cliente;
		ArrayList<String> arrDatosModificar  = new ArrayList<String>();
		
		if(clienteServicio.getListaClientes().size()>0) {
			System.out.println("Seleccione que desea hacer:");
			System.out.println("1.- Cambiar el estado del cliente");
			System.out.println("2.- Editar los datos ingresados del cliente");
			System.out.print("Ingrese opción: ");
			String opcion = scanner.nextLine();
			switch(opcion) {
				case "1":
					System.out.print("Ingrese RUN del cliente a editar: ");
					run = scanner.nextLine();
					cliente = clienteServicio.retornaCliente(run);
					if (cliente!=null) {
						System.out.println("El estado actual del cliente \"" + cliente.getRunCliente() + "\" es : " + cliente.getNombreCategoria().name());
						System.out.print("¿Esta seguro de modificar el estado del cliente [Y/N]?: ");
						String respuesta = scanner.nextLine();
						if(respuesta.toUpperCase().equals("Y")) {
							arrDatosModificar.add("5");
							arrDatosModificar.add(String.valueOf(cliente.getNombreCategoria().ordinal()));
							clienteServicio.editarCliente(cliente.getRunCliente(), arrDatosModificar);
							System.out.println("Datos cambiados con éxito.");
						}else {
							System.out.println("El estado del cliente no fue modificado.");
						}
					}else {
						System.out.println("El run del cliente no existe");
					}
					break;
				case "2":
					System.out.print("Ingrese RUN del cliente a editar: ");
					run = scanner.nextLine();
					cliente = clienteServicio.retornaCliente(run);
					if (cliente!=null) {
						System.out.println("1.- El RUN del cliente es: " + cliente.getRunCliente());
						System.out.println("2.- El Nombre del cliente es: " + cliente.getNombreCliente());
						System.out.println("3.- El Apellido del cliente es: " + cliente.getApellidoCliente());
						System.out.println("4.- Los años como cliente son: " + cliente.getAniosCliente() + " años");
						System.out.print("Ingrese opción a editar de los datos del cliente: ");
						String opcionModifica = scanner.nextLine();
						String nuevoDato="";
						switch(opcionModifica) {
						case "1":
							System.out.print("Ingrese nuevo RUN del Cliente: ");
							nuevoDato = scanner.nextLine();
							if(!nuevoDato.isEmpty()) {
								arrDatosModificar.add(opcionModifica);
								arrDatosModificar.add(nuevoDato);
								clienteServicio.editarCliente(run, arrDatosModificar);
								System.out.println("Datos cambiados con éxito.");
							}else {
								System.out.println("No puede ingresar un RUN vacío.");
							}
							break;
						case "2":
							System.out.print("Ingrese nuevo Nombre del Cliente: ");
							nuevoDato = scanner.nextLine();
							if(!nuevoDato.isEmpty()) {
								arrDatosModificar.add(opcionModifica);
								arrDatosModificar.add(nuevoDato);
								clienteServicio.editarCliente(run, arrDatosModificar);
								System.out.println("Datos cambiados con éxito.");
							}else {
								System.out.println("No puede ingresar un Nombre vacío.");
							}
							break;
						case "3":
							System.out.print("Ingrese nuevo Apellido del Cliente: ");
							nuevoDato = scanner.nextLine();
							if(!nuevoDato.isEmpty()) {
								arrDatosModificar.add(opcionModifica);
								arrDatosModificar.add(nuevoDato);
								clienteServicio.editarCliente(run, arrDatosModificar);
								System.out.println("Datos cambiados con éxito.");
							}else {
								System.out.println("No puede ingresar un Apellido vacío.");
							}
							break;
						case "4":
							System.out.print("Ingrese la nueva cantidad de Años del Cliente: ");
							nuevoDato = scanner.nextLine();
							if(!nuevoDato.isEmpty()) {
								arrDatosModificar.add(opcionModifica);
								arrDatosModificar.add(nuevoDato);
								clienteServicio.editarCliente(run, arrDatosModificar);
								System.out.println("Datos cambiados con éxito.");
							}else {
								System.out.println("No puede ingresar cantidad de años vacía.");
							}
							break;
						default: System.out.println("Opción no válida.");
						}
					}
					break;
				default: System.out.println("Opción no válida");
			}
		}else {
			System.out.println("No exiten clientes para editar");
		}

	}

	@Override
	public void importarCliente() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportarCliente() {
		String ruta = "";
		if(clienteServicio.getListaClientes().size()>0) {
			System.out.println("Seleccione el formato a exportar: ");
			System.out.println("1.- Formato Csv");
			System.out.println("2.- Formato Txt");
			System.out.print("Ingrese una opción para exportar: ");
			String opcion = scanner.nextLine();
			switch(opcion) {
			case "1":
				System.out.print("Ingrese la ruta en donde desea exportar el archivo \"Clientes.csv\": ");
				ruta = scanner.nextLine();
				exportadorCsv.exportar(ruta + "/Clientes.csv", clienteServicio.getListaClientes());
				System.out.println("Datos de clientes exportados correctamente en formato csv.");
				break;
			case "2":
				System.out.print("Ingrese la ruta en donde desea exportar el archivo \"Clientes.txt\": ");
				ruta = scanner.nextLine();
				exportadorTxt.exportar(ruta + "/Clientes.txt", clienteServicio.getListaClientes());
				System.out.println("Datos de clientes exportados correctamente en formato txt.");
				break;
			default: System.out.println("Opción no válida.");
			}
		}else {
			System.out.println("No existen Clientes para exportar");
		}

	}

	@Override
	public void terminarCliente() {
		System.out.println("Gracias por operar con Edutecno SW.\n   Hasta Pronto!!");
		System.exit(0);
	}

}
