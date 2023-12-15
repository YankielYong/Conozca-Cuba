package utils;

import javax.swing.table.DefaultTableModel;

public class RoomTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public RoomTableModel(){
		String[] nombreColumnas = {"Código", "Tipo de Habitación", "Plan Alimenticio", "Recargo de Habitación"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
