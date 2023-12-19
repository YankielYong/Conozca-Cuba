package utils;

import javax.swing.table.DefaultTableModel;

public class FoodPlanTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public FoodPlanTableModel(){
		String[] nombreColumnas = {"Código", "Tipo de Plan Alimenticio"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
