package utils;

import javax.swing.table.DefaultTableModel;

public class HotelChainTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public HotelChainTableModel(){
		String[] nombreColumnas = {"Código", "Nombre"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
