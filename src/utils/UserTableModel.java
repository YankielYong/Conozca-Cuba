package utils;

import javax.swing.table.DefaultTableModel;

public class UserTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	public UserTableModel(){
		String[] nombreColumnas = {"C�digo", "Nombre", "Nick", "Rol"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
