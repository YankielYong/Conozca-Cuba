package utils;

import javax.swing.table.DefaultTableModel;

public class SeasonTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public SeasonTableModel(){
		String[] nombreColumnas = {"Código", "Nombre", "Descripción", "Fecha de Inicio", "Fecha de Final"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
