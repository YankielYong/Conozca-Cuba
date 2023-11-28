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
	
	public static DefaultComboBoxModel<String> cadenasHotelerasModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Iberostar");
		model.addElement("Meliá");
		return model;
	}
	
	public static DefaultComboBoxModel<String> provinciasModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("La Habana");
		model.addElement("Matanzas");
		return model;
	}

	public static DefaultComboBoxModel<String> localizacionesModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Ciudad");
		model.addElement("Playa");
		return model;
	}
}
