package utils;

import javax.swing.table.DefaultTableModel;

public class RoomTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public RoomTableModel(){
		String[] nombreColumnas = {"C�digo", "Tipo de Habitaci�n", "Plan Alimenticio", "Recargo de Habitaci�n"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
