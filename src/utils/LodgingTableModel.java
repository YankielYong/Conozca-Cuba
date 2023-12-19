package utils;

import javax.swing.table.DefaultTableModel;

public class LodgingTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public LodgingTableModel(){
		String[] nombreColumnas = {"Código", "Hotel", "Habitación", "Plan Alimenticio", "Temporada", "Precio"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
