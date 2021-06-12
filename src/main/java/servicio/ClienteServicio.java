package servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public interface ClienteServicio {
	
	public List<Cliente> getListaClientes();
	
	public void agregarCliente(Cliente cliente);
	
	public List<Cliente> retornoListarClientes();
	
	public void editarCliente(String run, ArrayList<String> arrDatosModificar);
	
	public Cliente retornaCliente(String run);
}
