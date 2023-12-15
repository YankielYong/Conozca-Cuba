package utils;

import javax.swing.table.DefaultTableModel;

public class HotelTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public HotelTableModel(){
		String[] nombreColumnas = {"Código", "Nombre", "Categoría", "Cadena Hotelera", "Provincia"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
