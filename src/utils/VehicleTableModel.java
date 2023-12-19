package utils;

import javax.swing.table.DefaultTableModel;

public class VehicleTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public VehicleTableModel(){
		String[] nombreColumnas = {"C�digo", "Chapa", "Marca", "A�o de Fabricaci�n"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
