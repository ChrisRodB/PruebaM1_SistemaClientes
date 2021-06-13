package servicio;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidad.Utilidad;

public class ClienteServicioImp implements ClienteServicio {

	private List<Cliente> listaClientes = new ArrayList<Cliente>();

	@Override
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	@Override
	public void agregarCliente(Cliente cliente) {
		if (cliente != null) {
			listaClientes.add(cliente);
		}
	}

	@Override
	public List<Cliente> retornoListarClientes() {
		return listaClientes;
	}

	@Override
	public void editarCliente(String run, ArrayList<String> arrDatosModificar) {
		int indexLista = -1;
		int contador = 0;
		for (Cliente cliente : listaClientes) {
			if (cliente.getRunCliente().equals(run)) {
				indexLista = contador;
				break;
			} else {
				contador++;
			}
		}
		if(indexLista>=0) {
			if (arrDatosModificar.size() > 0) {
				String opcionModificar = arrDatosModificar.get(0);
				String datoModificar = arrDatosModificar.get(1);
				switch (opcionModificar) {
				case "1": // Modificar RUN
					listaClientes.get(indexLista).setRunCliente(datoModificar);
					break;
				case "2": // Modificar Nombre
					listaClientes.get(indexLista).setNombreCliente(datoModificar);
					break;
				case "3": // Modificar Apellido
					listaClientes.get(indexLista).setApellidoCliente(datoModificar);
					break;
				case "4": // Modificar Años
					listaClientes.get(indexLista).setAniosCliente(datoModificar);
					break;
				case "5": // Modificar Categoría
					if(datoModificar.equals("0")) {
						listaClientes.get(indexLista).setNombreCategoria(CategoriaEnum.INACTIVO);
					}else {
						listaClientes.get(indexLista).setNombreCategoria(CategoriaEnum.ACTIVO);
					}
					break;
				}
			} else {
				Utilidad.imprimeConsolaConTitulo("------Error------", "No se han ingresado datos a modificar.", true, 1, 2000);
			}
		}else {
			Utilidad.imprimeConsolaConTitulo("------Error------", "No existe RUN del cliente", true, 1, 2000);
		}

	}


	@Override
	public Cliente retornaCliente(String run) {
		return listaClientes.stream().filter(c -> c.getRunCliente().equals(run)).findAny().orElse(null);
	}

}
