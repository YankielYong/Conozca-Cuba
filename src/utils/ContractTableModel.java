package utils;

import javax.swing.table.DefaultTableModel;

public class ContractTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public ContractTableModel(){
		String[] nombreColumnas = {"Código", "Tipo de Contrato", "Paquete Turístico"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
