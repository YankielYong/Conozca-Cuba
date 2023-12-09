package utils;

import java.awt.event.KeyEvent;

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
