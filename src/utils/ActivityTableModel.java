package utils;

import javax.swing.table.DefaultTableModel;

public class ActivityTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ActivityTableModel(){
		String[] nombreColumnas = {"C�digo", "Fecha", "Precio", "Descripci�n"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
