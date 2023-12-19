package utils;

import javax.swing.table.DefaultTableModel;

public class FoodPlanTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public FoodPlanTableModel(){
		String[] nombreColumnas = {"C�digo", "Tipo de Plan Alimenticio"};
		this.setColumnIdentifiers(nombreColumnas);
	}
}
