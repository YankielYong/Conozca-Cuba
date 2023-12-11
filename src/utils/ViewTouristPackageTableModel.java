package utils;

import javax.swing.table.DefaultTableModel;

public class ViewTouristPackageTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ViewTouristPackageTableModel(){
		String[] nombreColumnas = {"Nombre Promocional", "Personas", "Días", "Noches", "Precio"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
