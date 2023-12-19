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
		model.addElement("Campo");
		model.addElement("Cayo");
		model.addElement("Ciudad");
		model.addElement("Playa");
		return model;
	}
	
	public static DefaultComboBoxModel<String> transportistasModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Gaviota");
		model.addElement("Panataxi");
		model.addElement("Taxi Ok");
		return model;
	}
	
	public static DefaultComboBoxModel<String> tiposServiciosModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Aprendizaje");
		model.addElement("Entretenimiento");
		model.addElement("Espectáculo Nocturno");
		model.addElement("Gastronómico");
		model.addElement("Museo");
		return model;
	}
	
	public static DefaultComboBoxModel<String> contratosModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Hotelero");
		model.addElement("Transporte");
		model.addElement("Servicios Complementarios");
		return model;
	}
	
	public static DefaultComboBoxModel<String> modalidadesModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Costo por kilometraje");
		model.addElement("Costo por horas y kilómetros");
		model.addElement("Costo por recorridos establecidos");
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
	
	public static DefaultComboBoxModel<String> reportesModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Listado de contratos conciliados con hoteles");
		model.addElement("Listado de temporadas de los contratos hoteleros");
		model.addElement("Listado de contratos de transporte");
		model.addElement("Listado de contratos de servicios complementarios");
		model.addElement("Listado de hoteles activos");
		model.addElement("Listado de hoteles inactivos");
		model.addElement("Programa del paquete turístico");
		model.addElement("Plan de ingreso por concepto de venta del paquete turístico");
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
