package servicio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelo.Cliente;

public class ExportadorTxt extends Exportador {

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			listaClientes.stream().forEach(c -> {
				try {
					bw.write("--------------------Datos del Cliente--------------------\n\n");
					bw.write("RUN del Cliente: " + c.getRunCliente() + "\n");
					bw.write("Nombre del Cliente: " + c.getNombreCliente() + "\n");
					bw.write("Apellido del Cliente: " + c.getApellidoCliente() + "\n");
					bw.write("Años como Cliente: " + c.getAniosCliente() + "\n");
					bw.write("Categoria del Cliente: " + c.getNombreCategoria().name() + "\n\n");
					bw.write("---------------------------------------------------------\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
