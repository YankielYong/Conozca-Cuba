package utils;

import java.awt.event.KeyEvent;
import java.util.Date;

public class Validaciones {

	public static void provincia(String cadena) throws IllegalArgumentException{
		try{
			noVacio(cadena);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El nombre de la provincia"+e.getMessage());
		}
	}

	public static void cadenaHotelera(String cadena) throws IllegalArgumentException{
		try{
			noVacio(cadena);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El nombre de la Cadena Hotelera"+e.getMessage());
		}
	}

	public static void lugar(String cadena) throws IllegalArgumentException{
		try{
			noVacio(cadena);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El nombre del lugar"+e.getMessage());
		}
	}

	public static void usuario(String nombre, String usuario, String pass) throws IllegalArgumentException{
		if(nombre != null){
			try{
				noVacio(nombre);
			}
			catch(IllegalArgumentException e){
				throw new IllegalArgumentException("El nombre"+e.getMessage());
			}
		}
		try{
			noVacio(usuario);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El nombre de usuario"+e.getMessage());
		}
		try{
			noVacio(pass);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la contraseña"+e.getMessage());
		}
		if(pass.length() < 8)
			throw new IllegalArgumentException("La contraseña debe tener al menos caracteres");
	}

	public static void actividad(Date fecha, String desc) throws IllegalArgumentException{
		if(fecha.compareTo(new Date())<0) throw new IllegalArgumentException("La fecha introducida ya ha pasado, rectifique");
		try{
			noVacio(desc);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de descripción"+e.getMessage());
		}
	}

	public static void vehiculo(String chapa, String marca, String yFab, String capS, String capC, String capT) 
			throws IllegalArgumentException{
		try{noVacio(chapa);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la chapa"+e.getMessage());}
		try{noVacio(marca);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la marca"+e.getMessage());}
		try{noVacio(yFab);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del año de fabricación"+e.getMessage());}
		try{noVacio(capS);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la capacidad sin equipajes"+e.getMessage());}
		try{noVacio(capC);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la capacidad con equipajes"+e.getMessage());}
		try{noVacio(capT);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la capacidad total"+e.getMessage());}

		int yFabr = Integer.valueOf(yFab);
		int capSE = Integer.valueOf(capS);
		int capCE = Integer.valueOf(capC);
		int capTT = Integer.valueOf(capT);

		if(yFabr>2023) throw new IllegalArgumentException("El año de fabricación no es válido");
		if(capCE > capSE) throw new IllegalArgumentException("No puede tener más capacidad con equipaje que sin equipaje");
	}

	public static void costoKilometraje(String costoKm, String costoKmIV, String costoHrEsp){
		try{noVacio(costoKm);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por kilómetro"+e.getMessage());}
		try{noVacio(costoKmIV);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por kilómetro ida y vuelta"+e.getMessage());}
		try{noVacio(costoHrEsp);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por horas de espera"+e.getMessage());}
	}
	
	public static void costoHorasKilometro(String costoKmR, String costoHr, String costoKmEx, String costoHrEx){
		try{noVacio(costoKmR);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por kilómetro recorrido"+e.getMessage());}
		try{noVacio(costoHr);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por hora"+e.getMessage());}
		try{noVacio(costoKmEx);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por kilómetros extras"+e.getMessage());}
		try{noVacio(costoHrEx);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por horas extras"+e.getMessage());}
	}
	
	public static void costoRecorrido(String descR, String costoR, String costoRIV){
		try{noVacio(descR);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la descripción del recorrido"+e.getMessage());}
		try{noVacio(costoR);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por recorrido"+e.getMessage());}
		try{noVacio(costoRIV);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del costo por recorrido ida y vuelta"+e.getMessage());}
	}

	public static void soloLetras(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = key==32 || (key>=65&&key<=90) || (key>=97&&key<=122) || key==225 || key==233 || key==241 ||
				key==237 || key==243 || key==250 || key==193 || key==201 || key==205 || key==211 || key==218 || key==209;
		if(!valido)
			e.consume();
	}

	public static void hora(KeyEvent e, String c){
		int key = e.getKeyChar();
		boolean valido = (key>=48&&key<=57);
		if(!valido)
			e.consume();
		if(valido){
			int hora = Integer.valueOf(c+e.getKeyChar());
			if(hora<1 || hora > 12)
				e.consume();
		}
	}

	public static void minuto(KeyEvent e, String c){
		int key = e.getKeyChar();
		boolean valido = (key>=48&&key<=57);
		if(!valido)
			e.consume();
		if(valido){
			int minuto = Integer.valueOf(c+e.getKeyChar());
			if(minuto<0 || minuto > 59)
				e.consume();
		}
	}

	public static void soloNumeros(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = (key>=48&&key<=57);
		if(!valido)
			e.consume();
	}

	public static void soloNumeroYUnaComa(KeyEvent e, String c){
		int key = e.getKeyChar();
		boolean valido;
		boolean hayComa = c.contains(",");
		if(!hayComa){
			if(c.length()==0)
				valido = (key>=48&&key<=57);
			else
				valido = key==46 || (key>=48&&key<=57);
		}
		else
			valido = (key>=48&&key<=57);
		if(!valido)
			e.consume();
	}

	public static void letrasNumeros(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = (key>=48&&key<=57) || key==32 || (key>=65&&key<=90) || (key>=97&&key<=122) || key==225 || key==233 || key==241 ||
				key==237 || key==243 || key==250 || key==193 || key==201 || key==205 || key==211 || key==218 || key==209;
		if(!valido)
			e.consume();
	}

	public static void chapa(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = (key>=48&&key<=57) || (key>=65&&key<=90);
		if(!valido)
			e.consume();
	}

	public static void letrasNumerosSignosEspacio(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = (key>=32&&key<=90) || (key>=97&&key<=122) || (key>=48&&key<=57) || key==241 || key==209 
				|| key==46 || key==95;
		if(!valido)
			e.consume();
	}

	public static void letrasNumerosSignos(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = (key>=65&&key<=90) || (key>=97&&key<=122) || (key>=48&&key<=57) || key==241 || key==209 
				|| key==46 || key==95;
		if(!valido)
			e.consume();
	}

	public static void letrasNumerosYTodosSignos(KeyEvent e){
		int key = e.getKeyChar();
		boolean valido = (key>=33&&key<=90) || (key>=97&&key<=122) || (key>=48&&key<=57) || key==241 || key==209 || key==95;
		if(!valido)
			e.consume();
	}

	public static void noVacio(String cadena) throws IllegalArgumentException{
		if(cadena.length()>0){
			boolean nombreVacio = true;
			for(int i=0; i<cadena.length() && nombreVacio; i++)
				if(!Character.isSpaceChar(cadena.charAt(i)))
					nombreVacio = false;
			if(nombreVacio)
				throw new IllegalArgumentException(" está vacío");
		}
		else
			throw new IllegalArgumentException(" está vacío");
	}
}
