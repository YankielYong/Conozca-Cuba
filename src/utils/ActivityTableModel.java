package utils;

import javax.swing.table.DefaultTableModel;

public class ActivityTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ActivityTableModel(){
		String[] nombreColumnas = {"Código", "Fecha", "Precio", "Descripción"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
