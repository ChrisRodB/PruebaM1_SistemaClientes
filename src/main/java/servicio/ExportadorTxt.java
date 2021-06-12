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
					bw.write("--------------------Datos del Cliente--------------------");
					bw.newLine();
					bw.write("RUN del Cliente: " + c.getRunCliente());
					bw.write("Nombre del Cliente: " + c.getNombreCliente());
					bw.write("Apellido del Cliente: " + c.getApellidoCliente());
					bw.write("Años como Cliente: " + c.getAniosCliente());
					bw.write("Categoria del Cliente: " + c.getNombreCategoria().name());
					bw.newLine();
					bw.write("---------------------------------------------------------");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
