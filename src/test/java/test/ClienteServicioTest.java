package test;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ClienteServicioImp;

@DisplayName("::: TEST CLASE ClienteServicio :::")
class ClienteServicioTest {
	
	private ClienteServicioImp clienteServicio;

	@BeforeEach
	public void setup() {
		clienteServicio = new ClienteServicioImp();
	}
	
	@Test
	@DisplayName("-- Test método agregarCliente ---")
	public void agregarClienteTest() {
		//Given
		Cliente cliente = new Cliente("12345678-1", "Juanito", "Dummy", "3", CategoriaEnum.ACTIVO);
		
		//When
		clienteServicio.agregarCliente(cliente);
		
		//Then
		Assertions.assertNotNull(clienteServicio.retornaCliente("12345678-1"));
	}
	
	@Test
	@DisplayName("-- Test método agregarCliente con dato NULL ---")
	public void agregarClienteNullTest() {
		//Given
		Cliente cliente = null;
		
		//When
		clienteServicio.agregarCliente(cliente);
		
		//Then
		Assertions.assertEquals(clienteServicio.getListaClientes().size(), 0);
	}

}
