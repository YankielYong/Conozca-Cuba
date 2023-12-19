package utils;

import javax.swing.table.DefaultTableModel;

public class ContractTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ContractTableModel(){
		String[] nombreColumnas = {"C�digo", "Tipo de Contrato", "Paquete Tur�stico"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
