package utils;

import javax.swing.DefaultComboBoxModel;

public class ComboBoxModel {
	
	public static DefaultComboBoxModel<String> catHoteleraModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("1 Estrella");
		model.addElement("2 Estrellas");
		model.addElement("3 Estrellas");
		model.addElement("4 Estrellas");
		model.addElement("5 Estrellas");
		return model;
	}

	public static DefaultComboBoxModel<String> localizacionesModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Ciudad");
		model.addElement("Playa");
		return model;
	}
	
	public static DefaultComboBoxModel<String> tiposServiciosModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Entretenimiento");
		model.addElement("Gastronómico");
		return model;
	}
	
	public static DefaultComboBoxModel<String> habitacionesModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Simple");
		model.addElement("Doble");
		model.addElement("Suite");
		model.addElement("Junior Suite");
		return model;
	}
	
	public static DefaultComboBoxModel<Integer> diasModel(int dias){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		for(int i=1; i<=dias; i++)
			model.addElement(i);
		return model;
	}
	
	public static DefaultComboBoxModel<Integer> yearsModel(){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		for(int i=2020; i<=2045; i++)
			model.addElement(i);
		return model;
	}
	
	public static DefaultComboBoxModel<Integer> mesesModel(){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		model.addElement(1);		model.addElement(2);
		model.addElement(3);		model.addElement(4);
		model.addElement(5);		model.addElement(6);
		model.addElement(7);		model.addElement(8);
		model.addElement(9);		model.addElement(10);
		model.addElement(11);		model.addElement(12);
		return model;
	}
	
	public static DefaultComboBoxModel<String> meridianosModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("AM");
		model.addElement("PM");
		return model;
	}
}
