package utils;

import javax.swing.table.DefaultTableModel;

public class RoleTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public RoleTableModel(){
		String[] nombreColumnas = {"Nombre", "Descripción"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
