package utils;

import javax.swing.table.DefaultTableModel;

public class EventTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public EventTableModel(){
		String[] nombreColumnas = {"C�digo", "Lugar", "Actividad"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
