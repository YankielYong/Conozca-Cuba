package utils;

import javax.swing.table.DefaultTableModel;

public class TransportModalityTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public TransportModalityTableModel(){
		String[] nombreColumnas = {"Código", "Tipo de Modalidad"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
