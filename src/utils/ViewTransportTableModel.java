package utils;

import javax.swing.table.DefaultTableModel;

public class ViewTransportTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	public ViewTransportTableModel(){
		String[] nombreColumnas = {"Veh�culo", "Modalidad de Transporte", "Transportista"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
