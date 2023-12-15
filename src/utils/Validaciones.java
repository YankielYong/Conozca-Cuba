package utils;

import java.awt.event.KeyEvent;
import java.time.LocalDate;
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
			throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
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
	
	public static void contrato(String desc, Date fechaInicio, Date fechaFin, Date fechaConc) throws IllegalArgumentException{
		if(fechaConc.compareTo(fechaInicio)>0) throw new IllegalArgumentException("La fecha de inicio no puede ser antes de la fecha de conciliación");
		try{
			noVacio(desc);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de descripción"+e.getMessage());
		}
	}
	
	public static void paquete(String nombre, int personas, int dias, int noches) throws IllegalArgumentException{
		try{noVacio(nombre);}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del nombre promocional"+e.getMessage());}
		if(personas < 1) throw new IllegalArgumentException("El paquete debe ser para al menos 1 persona");
		if(dias < 2) throw new IllegalArgumentException("El paquete debe ser para al menos 2 días");
		if(noches < 1) throw new IllegalArgumentException("El paquete debe ser para al menos 1 noche");
		if(dias != noches+1) throw new IllegalArgumentException("Si son "+dias+" días, deberían ser "+(dias-1)+" noches");
	}
	
	public static void evento(String lugar, String act) throws IllegalArgumentException{
		try{
			noVacio(lugar);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del lugar"+e.getMessage());
		}
		try{
			noVacio(act);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la actividad"+e.getMessage());
		}
	}
	
	public static void transporte(String vehiculo, String modalidad) throws IllegalArgumentException{
		try{
			noVacio(vehiculo);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del vehículo"+e.getMessage());
		}
		try{
			noVacio(modalidad);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la modalidad"+e.getMessage());
		}
	}
	
	public static void hospedaje(String hotel, String habitacion, String temporada, String precio) throws IllegalArgumentException{
		try{noVacio(hotel);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del hotel"+e.getMessage());}
		try{noVacio(habitacion);}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la habitación"+e.getMessage());}
		try{noVacio(temporada);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la temporada"+e.getMessage());}
		try{noVacio(precio);}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del precio"+e.getMessage());}
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
		
		LocalDate f = LocalDate.now();
		int year = f.getYear();

		if(yFabr>year) throw new IllegalArgumentException("El año de fabricación no es válido");
		if(capCE > capSE) throw new IllegalArgumentException("No puede tener más capacidad con equipaje que sin equipaje");
		if(capTT < capCE || capTT < capSE) throw new IllegalArgumentException("La capacidad total no puede ser menor que las demás");
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
	
	public static void hotel(String nombre, String direccion, String telefono, String fax, String correo, String cantP,
			String cantH, String distC, String distA){
		try{noVacio(nombre);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del nombre del hotel"+e.getMessage());}
		try{noVacio(direccion);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la dirección del hotel"+e.getMessage());}
		try{noVacio(telefono);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del teléfono del hotel"+e.getMessage());}
		try{noVacio(fax);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del fax del hotel"+e.getMessage());}
		try{noVacio(correo);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo del correo del hotel"+e.getMessage());}
		try{noVacio(cantP);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la cantidad de pisos del hotel"+e.getMessage());}
		try{noVacio(cantH);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la cantidad de habitaciones del hotel"+e.getMessage());}
		try{noVacio(distC);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de distancia a la ciudad más cercana"+e.getMessage());}
		try{noVacio(distA);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de distancia al aereopuerto"+e.getMessage());}
	}
	
	public static void direccion(String dir){
		try{noVacio(dir);} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("El campo de la descripción del hotel"+e.getMessage());}
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
		boolean hayComa = c.contains(".");
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
