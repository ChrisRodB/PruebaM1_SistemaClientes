package servicio;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.util.List;

import modelo.Cliente;

public abstract class Exportador {
	
	public abstract boolean exportar(String ruta, String fileName, List<Cliente> listaClientes);
	

}
