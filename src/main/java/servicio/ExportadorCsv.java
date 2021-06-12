package servicio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelo.Cliente;

public class ExportadorCsv extends Exportador {

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			listaClientes.stream().forEach(c -> {
				try {
					bw.write(c.getRunCliente() + "," + c.getNombreCliente() + "," + c.getApellidoCliente() + ","
							+ c.getAniosCliente() + "," + c.getNombreCategoria().name());
				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
