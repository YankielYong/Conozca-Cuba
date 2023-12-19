package utils;

import javax.swing.table.DefaultTableModel;

public class TouristPackageTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public TouristPackageTableModel(){
		String[] nombreColumnas = {"C�digo", "Nombre Promocional", "Personas", "D�as", "Noches", "Precio", "Costo"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
