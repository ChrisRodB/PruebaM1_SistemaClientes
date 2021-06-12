package servicio;

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
