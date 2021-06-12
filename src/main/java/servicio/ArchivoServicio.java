package servicio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	public List<Cliente> cargarDatos(String fileName, List<Cliente> listaActualClientes) {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		try {
			String noCargados = "";
			
			BufferedReader bR = new BufferedReader(new FileReader(fileName));
			String lineas;
			while((lineas=bR.readLine()) != null) {
				String[] datosCliente = lineas.split(",");
				String rut = datosCliente[0];
				String categoria = datosCliente[4];
				String anios = datosCliente[3].replaceAll("[^-?0-9]+", " ").trim();
				CategoriaEnum catEnum = categoria.toUpperCase().equals("ACTIVO")?CategoriaEnum.ACTIVO:CategoriaEnum.INACTIVO;
				Cliente cli = listaActualClientes.stream().filter(c -> c.getRunCliente().equals(rut)).findAny().orElse(null);
				if(cli==null) {
					Cliente cliente = new Cliente(rut, datosCliente[1], datosCliente[2], anios, catEnum);
					listaClientes.add(cliente);
				}else {
					noCargados += "El RUN \"" + rut + "\" no se cargó ya que el cliente existe en el sistema\n";
				}
			}
			if (!noCargados.isEmpty()) {
				System.out.println("Los siguientes registros no fueron cargados: ");
				System.out.println(noCargados);
			}
			bR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return listaClientes;
	}

}
