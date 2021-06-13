package vista;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ClienteServicioImp;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import utilidad.Utilidad;

public class Menu extends MenuTemplate {

	private ClienteServicio clienteServicio = new ClienteServicioImp();
	private ArchivoServicio archivoServicio = new ArchivoServicio();
	private ExportadorCsv exportadorCsv = new ExportadorCsv();
	private ExportadorTxt exportadorTxt = new ExportadorTxt();

	@Override
	public void listarCliente() {
		if (clienteServicio.getListaClientes().size() > 0) {
			clienteServicio.retornoListarClientes().stream().forEach(c -> {
				Utilidad.desplegarCabecera("Datos del Cliente", 1);
				System.out.println(Utilidad.imprimeConsolaDatosCliente(c,false));
				Utilidad.finalSalidas(true, 0, 1000);
			});
		} else {
			Utilidad.imprimeConsolaConTitulo("Listar Clientes", "No existen Clientes en la lista.", true, 1, 2000);
		}

	}

	
	@Override
	public void agregarCliente() {
		Utilidad.desplegarCabecera("Agregar Cliente", 1);
		String run = retornarValorScanner("Ingrese RUN del Cliente: ");
		if (!run.isEmpty()) {
			Cliente cli = clienteServicio.getListaClientes().stream().filter(c -> c.getRunCliente().equals(run)).findAny().orElse(null);
			if (cli!=null) { //El RUN existe y debiera ser único.
				Utilidad.imprimeConsolaConTitulo("------Error------", "El RUN ya existe en la lista.", true, 1, 2000);
			}else {
				String nombre = retornarValorScanner("Ingrese Nombre del Cliente: ");
				if (!nombre.isEmpty()) {
					String apellido = retornarValorScanner("Ingrese Apellido del Cliente: ");
					if (!apellido.isEmpty()) {
						String anios = retornarValorScanner("Ingrese años como Cliente: ");
						if (!anios.isEmpty()) {
							Cliente cliente = new Cliente(run, nombre, apellido, anios, CategoriaEnum.ACTIVO);
							clienteServicio.agregarCliente(cliente);
							Utilidad.imprimeConsolaConTitulo("------Mensaje------", "Cliente agregado a la lista.", true, 1, 2000);
						} else {
							Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar cantidad de años vacía.", true, 1, 2000);
						}
					} else {
						Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar un Apellido vacío.", true, 1, 2000);
					}
				} else {
					Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar un Nombre vacío.", true, 1, 2000);
				}
			}
		} else {
			Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar un RUN vacío.", true, 1, 2000);
		}
	}

	
	@Override
	public void editarCliente() {
		String run = "";
		Cliente cliente;

		if (clienteServicio.getListaClientes().size() > 0) {
			Utilidad.desplegarCabecera("Editar Cliente", 1);
			String opcion = retornarValorScanner("Seleccione que desea hacer:\n" + "1.- Cambiar el estado del cliente\n"
					+ "2.- Editar los datos ingresados del cliente\n\n" + "Ingrese opción: ");
			switch (opcion) {
			case "1":
				Utilidad.desplegarCabecera("Cambiar Estado Cliente", 1);
				run = retornarValorScanner("Ingrese RUN del cliente: ");
				cliente = clienteServicio.retornaCliente(run);
				if (cliente != null) {
					System.out.println("\nEl estado actual del cliente \"" + cliente.getRunCliente() + "\" es : "
							+ cliente.getNombreCategoria().name());
					String respuesta = retornarValorScanner("¿Esta seguro de modificar el estado del cliente [Y/N]?: ");
					if (respuesta.toUpperCase().equals("Y")) {
						modificarDatoCliente("5", String.valueOf(cliente.getNombreCategoria().ordinal()), cliente.getRunCliente());
					} else {
						Utilidad.imprimeConsolaConTitulo("------Mensaje------", "El estado del cliente no fue modificado.", true, 1, 2000);
					}
				} else {
					Utilidad.imprimeConsolaConTitulo("------Mensaje------", "El run del cliente no existe", true, 1, 2000);
				}
				break;
			case "2":
				Utilidad.desplegarCabecera("Editar Datos Cliente", 1);
				run = retornarValorScanner("Ingrese RUN del cliente a editar: ");
				cliente = clienteServicio.retornaCliente(run);
				if (cliente != null) {
					Utilidad.desplegarCabecera("-----------------", 1);
					System.out.println(Utilidad.imprimeConsolaDatosCliente(cliente, true));
					Utilidad.finalSalidas(true, 1, 0);
					String opcionModifica = retornarValorScanner("Ingrese opción a editar de los datos del cliente: ");
					String nuevoDato = "";
					switch (opcionModifica) {
					case "1":
						String nuevoRun = retornarValorScanner("\nIngrese nuevo RUN del Cliente: ");
						if (!nuevoRun.isEmpty()) {
							Cliente cli = clienteServicio.getListaClientes().stream().filter(c -> c.getRunCliente().equals(nuevoRun)).findAny().orElse(null);
							if (cli==null) { //No existe el RUN en la lista
								modificarDatoCliente(opcionModifica, nuevoDato, run);
							}else { //El RUN existe y debiera ser único.
								Utilidad.imprimeConsolaConTitulo("------Error------", "El RUN ya existe en la lista. No fue modificado", true, 1, 2000);
							}
						} else {
							Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar un RUN vacío.", true, 1, 2000);
						}
						break;
					case "2":
						nuevoDato = retornarValorScanner("'\nIngrese nuevo Nombre del Cliente: ");
						if (!nuevoDato.isEmpty()) {
							modificarDatoCliente(opcionModifica, nuevoDato, run);
						} else {
							Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar un Nombre vacío.", true, 1, 2000);
						}
						break;
					case "3":
						nuevoDato = retornarValorScanner("\nIngrese nuevo Apellido del Cliente: ");
						if (!nuevoDato.isEmpty()) {
							modificarDatoCliente(opcionModifica, nuevoDato, run);
						} else {
							Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar un Apellido vacío.", true, 1, 2000);
						}
						break;
					case "4":
						nuevoDato = retornarValorScanner("\nIngrese la nueva cantidad de Años del Cliente: ");
						if (!nuevoDato.isEmpty()) {
							modificarDatoCliente(opcionModifica, nuevoDato, run);
						} else {
							Utilidad.imprimeConsolaConTitulo("------Error------", "No puede ingresar cantidad de años vacía.", true, 1, 2000);
						}
						break;
					default:
						Utilidad.imprimeConsolaConTitulo("------Error------", "Opción no válida!", true, 1, 2000);
					}
				} else {
					Utilidad.imprimeConsolaConTitulo("------Mensaje------", "El run del cliente no existe", true, 1, 2000);
				}
				break;
			default:
				Utilidad.imprimeConsolaConTitulo("------Error------", "Opción no válida!", true, 1, 2000);
			}
		} else {
			Utilidad.imprimeConsolaConTitulo("Editar Clientes", "No existen Clientes para editar.", true, 1, 2000);
		}

	}

	private void modificarDatoCliente(String opcionAModificar, String valorNuevo, String run) {
		ArrayList<String> arrDatosModificar = new ArrayList<String>();
		arrDatosModificar.add(opcionAModificar);
		arrDatosModificar.add(valorNuevo);
		clienteServicio.editarCliente(run, arrDatosModificar);
		Utilidad.imprimeConsolaConTitulo("------Mensaje------", "Dato modificado con éxito.", true, 1, 2000);
	}

	@Override
	public void importarCliente() {
		Utilidad.desplegarCabecera("Cargar Datos CSV", 1);
		String ruta = retornarValorScanner("Ingresa la ruta en donde se encuentra el archivo \"DBClientes.csv\" [Solo ENTER directorio raíz del proyecto]: ");
		if(Utilidad.validarExistenciaArchivo(ruta,"DBClientes.csv")) {
			List<Cliente> listaCliente = archivoServicio.cargarDatos(ruta, "DBClientes.csv", clienteServicio.getListaClientes());
			
			if (listaCliente != null) {
				listaCliente.forEach(c -> clienteServicio.agregarCliente(c));
			}
			Utilidad.imprimeConsolaConTitulo("------Mensaje------", "Datos cargados correctamente en la lista.", true, 1, 2000);
		}
	}

	@Override
	public void exportarCliente() {
		String ruta = "";
		if (clienteServicio.getListaClientes().size() > 0) {
			Utilidad.desplegarCabecera("Exportar Archivo", 1);
			String opcion = retornarValorScanner("Seleccione el formato a exportar:\n" + "1.- Formato Csv\n"
					+ "2.- Formato Txt\n\n" + "Ingrese una opción para exportar: ");
			switch (opcion) {
			case "1":
				Utilidad.desplegarCabecera("Exportar CSV", 1);
				ruta = retornarValorScanner("Ingrese la ruta en donde desea exportar el archivo \"Clientes.csv\".\n"
						+ "Si la ruta no existe, esta será creada: ");
				if(exportadorCsv.exportar(ruta, "Clientes.csv", clienteServicio.getListaClientes())) {
					Utilidad.imprimeConsolaConTitulo("------Mensaje------", "Datos de clientes exportados correctamente en formato csv.", true, 1, 2000);
				}
				break;
			case "2":
				Utilidad.desplegarCabecera("Exportar TXT", 1);
				ruta = retornarValorScanner("Ingrese la ruta en donde desea exportar el archivo \"Clientes.txt\". \n"
						+ "Si la ruta no existe, esta será creada: ");
				if (exportadorTxt.exportar(ruta, "Clientes.txt", clienteServicio.getListaClientes())) {
					Utilidad.imprimeConsolaConTitulo("------Mensaje------", "Datos de clientes exportados correctamente en formato txt.", true, 1, 2000);
				}
				break;
			default:
				Utilidad.imprimeConsolaConTitulo("------Error------", "Opción no válida!", true, 1, 2000);
			}
		} else {
			Utilidad.imprimeConsolaConTitulo("------Mensaje------", "No existen Clientes para exportar", true, 1, 2000);
		}

	}

	@Override
	public void terminarCliente() {
		Utilidad.imprimeConsolaConTitulo("------SALIENDO------", "Gracias por operar con Edutecno Software.\n              Hasta Pronto!!", true, 1, 2000);
		System.exit(0);
	}

	private String retornarValorScanner(String mensaje) {
		System.out.print(mensaje);
		String sc = scanner.nextLine();
		return sc;
	}

}
