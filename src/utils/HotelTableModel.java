package utils;

import javax.swing.table.DefaultTableModel;

public class HotelTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public HotelTableModel(){
		String[] nombreColumnas = {"C�digo", "Nombre", "Categor�a", "Cadena Hotelera", "Provincia"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
