package servicio;
/**
 * @author Christian Rodríguez Bugueño
 * @category Prueba módulo 1 - Clase 003
 * @version 1.0
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelo.Cliente;
import utilidad.Utilidad;

public class ExportadorCsv extends Exportador {

	@Override
	public boolean exportar(String ruta, String fileName, List<Cliente> listaClientes) {
		String strDirFile = ruta.isEmpty()?null:ruta;
		File fArchivo = new File(strDirFile, fileName);
		
		if (!Utilidad.crearDirectorio(strDirFile)) {
			return false;
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fArchivo));
			for (Cliente cliente : listaClientes) {
				bw.write(cliente.getRunCliente() + "," + cliente.getNombreCliente() + "," + cliente.getApellidoCliente() + ","
						+ cliente.getAniosCliente() + "," + cliente.getNombreCategoria().name() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			Utilidad.imprimeConsolaConTitulo("------Error------", "Se produjo un error.\n" + e.getLocalizedMessage(), true, 1, 2000);
			return false;
		}
		
		return true;
	}

}
