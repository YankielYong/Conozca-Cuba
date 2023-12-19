package utils;

import javax.swing.table.DefaultTableModel;

public class ViewHotelTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ViewHotelTableModel(){
		String[] nombreColumnas = {"Nombre", "Categor�a", "Cadena Hotelera", "Provincia"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
