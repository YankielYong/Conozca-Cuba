package utils;

import javax.swing.table.DefaultTableModel;

public class ProvinceTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ProvinceTableModel(){
		String[] nombreColumnas = {"Código", "Nombre"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
