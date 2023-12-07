package utils;

import javax.swing.table.DefaultTableModel;

public class VehicleTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public VehicleTableModel(){
		String[] nombreColumnas = {"Código", "Chapa", "Marca"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
