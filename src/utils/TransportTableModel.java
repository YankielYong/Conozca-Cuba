package utils;

import javax.swing.table.DefaultTableModel;

public class TransportTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public TransportTableModel(){
		String[] nombreColumnas = {"Código", "Vehículo", "Modalidad de Transporte", "Transportista"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
