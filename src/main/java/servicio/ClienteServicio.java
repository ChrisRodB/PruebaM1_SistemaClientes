package servicio;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public interface ClienteServicio {
	
	List<Cliente> getListaClientes();
	
	void setListaClientes(List<Cliente> listaClientes);
	
	void agregarCliente(Cliente cliente);
	
	List<Cliente> retornoListarClientes();
	
	void editarCliente(String run, ArrayList<String> arrDatosModificar);
	
	Cliente retornaCliente(String run);
}
