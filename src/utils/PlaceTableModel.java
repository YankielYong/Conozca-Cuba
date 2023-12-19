package utils;

import javax.swing.table.DefaultTableModel;

public class PlaceTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public PlaceTableModel(){
		String[] nombreColumnas = {"C�digo", "Nombre", "Tipo De Servico", "Costo Por Persona"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
