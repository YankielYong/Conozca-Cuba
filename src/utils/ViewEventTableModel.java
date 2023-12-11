package utils;

import javax.swing.table.DefaultTableModel;

public class ViewEventTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ViewEventTableModel(){
		String[] nombreColumnas = {"Lugar", "Actividad"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
