package servicio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import modelo.CategoriaEnum;
import modelo.Cliente;

/*						IMPORTANTE!!!
 * La clase ArchivoServicio se extendió desde Exportador según se solicitó en el documento de la prueba
 * Ahora, no tiene ninguna coherencia realizarlo ya que esta clase corresponde a la carga de datos y no
 * a la exportación de información. De todas formas se heredó desde la clase Exportador tal cual se pidió.
 */
public class ArchivoServicio extends Exportador {

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {
		
	}
	
	public void cargarDatos(String fileName, ClienteServicioImp listaClientes) {
		try {
			String noCargados = "---- Clientes no cargados ----";
			BufferedReader bR = new BufferedReader(new FileReader(fileName));
			String lineas;
			while((lineas=bR.readLine()) != null) {
				String[] datosCliente = lineas.split(",");
				String rut = datosCliente[0];
				String categoria = datosCliente[4];
				CategoriaEnum catEnum = categoria.toUpperCase().equals("ACTIVO")?CategoriaEnum.ACTIVO:CategoriaEnum.INACTIVO;
				if(listaClientes.retornaCliente(rut)==null) {
					Cliente cliente = new Cliente(rut, datosCliente[1], datosCliente[2], datosCliente[3], catEnum);
				}else {
					noCargados += "El RUN \"" + rut + "\" no se cargó ya que el cliente existe en el sistema\n";
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
