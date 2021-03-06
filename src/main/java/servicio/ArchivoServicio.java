package servicio;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidad.Utilidad;

/*						IMPORTANTE!!!
 * La clase ArchivoServicio se extendió desde Exportador según se solicitó en el documento de la prueba.
 * Ahora, no tiene ninguna coherencia realizarlo ya que esta clase corresponde a la carga de datos y no
 * a la exportación de información. De todas formas se heredó desde la clase Exportador tal cual se pidió
 * para cumplir con el requerimiento.
 */
public class ArchivoServicio extends Exportador {
	
	@Override
	public boolean exportar(String ruta, String fileName, List<Cliente> listaClientes) {
		return true;
	}
	
	
	public List<Cliente> cargarDatos(String ruta, String fileName, List<Cliente> listaActualClientes) {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String strDirFile = ruta.isEmpty() ? null : ruta;
		
		try {
			String noCargados = "";
			File fArchivo = new File(strDirFile, fileName);
			BufferedReader bR = new BufferedReader(new FileReader(fArchivo));
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
				Utilidad.imprimeConsolaConTitulo("Registros NO cargados", noCargados, true, 1, 2000);
			}
			bR.close();
		} catch (FileNotFoundException e) {
			Utilidad.imprimeConsolaConTitulo("------Error------", "Se produjo un error.\n" + e.getLocalizedMessage(), true, 1, 2000);
		} catch (IOException e) {
			Utilidad.imprimeConsolaConTitulo("------Error------", "Se produjo un error.\n" + e.getLocalizedMessage(), true, 1, 2000);
		}
	
		return listaClientes;
	}

}
