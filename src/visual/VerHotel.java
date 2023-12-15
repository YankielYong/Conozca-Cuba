package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.HotelChainDTO;
import dto.HotelDTO;
import dto.ProvinceDTO;
import services.HotelChainServices;
import services.ProvinceServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

public class VerHotel extends MiJPanel{
	
	private HotelChainServices hotelChainServices = ServicesLocator.getHotelChainServices();
	private ProvinceServices provinceServices = ServicesLocator.getProvinceServices();
	
	private HotelDTO hotel;
	private HotelChainDTO hotelChain;
	private ProvinceDTO province;

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JLabel lblNombre;
	private JButton btnCerrar;
	private JPanel panelInferior;
	private JButton btnAtras;
	
	private Principal padre;
	private MiJPanel anterior;
	private VerHotel este;
	
	public VerHotel(Principal p, MiJPanel a, HotelDTO h){
		este = this;
		padre = p;
		anterior = a;
		hotel = h;
		buscar();
		setTipoPanel(Paneles.PANEL_VER_HOTEL);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerHotel(este);
		setBounds(pantalla.width/2-501, pantalla.height/2-326, 1002, 602);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 1000, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Información Hotel");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 200, 30);
		panelSuperior.add(lblNombre);

		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
		Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconCerrar = new ImageIcon(image);

		btnCerrar = new JButton(iconCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				padre.setPanelAbierto(0);
			}
		});
		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCerrar.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCerrar.setContentAreaFilled(false);
			}
		});
		btnCerrar.setBounds(955, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 1000, 570);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/atras.png"));
		image = img.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		Icon iconAtras = new ImageIcon(image);
		
		btnAtras = new JButton(iconAtras);
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().add(anterior);
				padre.getPanelPrincipal().repaint();
				padre.setPanelAbierto(anterior.getTipoPanel());
			}
		});
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setContentAreaFilled(false);
			}
		});
		btnAtras.setBounds(5, 5, 40, 40);
		btnAtras.setBackground(colorAzul);
		btnAtras.setFocusable(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setModel(new MyButtonModel());
		panelInferior.add(btnAtras);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/hotel.png"));
		image = img.getImage().getScaledInstance(96, 120, Image.SCALE_SMOOTH);
		Icon iconHotel = new ImageIcon(image);
		
		JLabel fotoHotel = new JLabel(iconHotel);
		fotoHotel.setBounds(200, 30, 96, 120);
		panelInferior.add(fotoHotel);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(400, 122, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(520, 30, 400, 122);
		panelInferior.add(logo);
		
		JLabel nombre = new JLabel("Nombre: "+hotel.getHotelName());
		nombre.setBounds(50, 180, 400, 30);
		nombre.setForeground(Color.black);
		nombre.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(nombre);
		
		String categ = "";
		if(hotel.getHotelCategory()==1)
			categ = "1 Estrella";
		else
			categ = hotel.getHotelCategory()+" Estrellas";
		
		JLabel categoria = new JLabel("Categoría: "+categ);
		categoria.setBounds(50, 220, 400, 30);
		categoria.setForeground(Color.black);
		categoria.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(categoria);
		
		JLabel cadenaHotelera = new JLabel("Cadena Hotelera: "+hotelChain.getHotelChainName());
		cadenaHotelera.setBounds(50, 260, 400, 30);
		cadenaHotelera.setForeground(Color.black);
		cadenaHotelera.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(cadenaHotelera);
		
		JLabel provincia = new JLabel("Provincia: "+province.getProviceName());
		provincia.setBounds(50, 300, 400, 30);
		provincia.setForeground(Color.black);
		provincia.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(provincia);
		
		JLabel direccion = new JLabel("Dirección: "+hotel.getHotelAddress());
		direccion.setBounds(50, 340, 400, 30);
		direccion.setForeground(Color.black);
		direccion.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(direccion);
		
		JLabel localizacion = new JLabel("Localización: "+hotel.getHotelLocation());
		localizacion.setBounds(50, 380, 400, 30);
		localizacion.setForeground(Color.black);
		localizacion.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(localizacion);
		
		JLabel telefono = new JLabel("Teléfono: "+hotel.getHotelPhone());
		telefono.setBounds(50, 420, 400, 30);
		telefono.setForeground(Color.black);
		telefono.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(telefono);
		
		JLabel fax = new JLabel("Fax: "+hotel.getHotelFax());
		fax.setBounds(50, 460, 400, 30);
		fax.setForeground(Color.black);
		fax.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(fax);
		
		JLabel correo = new JLabel("Correo: "+hotel.getHotelMail());
		correo.setBounds(50, 500, 400, 30);
		correo.setForeground(Color.black);
		correo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(correo);
		
		JLabel modalidades = new JLabel("Modalidades: ");
		modalidades.setBounds(550, 180, 400, 30);
		modalidades.setForeground(Color.black);
		modalidades.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(modalidades);
		ponerModalidades();
		
		JLabel cantPisos = new JLabel("Catidad de Pisos: "+hotel.getNumberOfFloors());
		cantPisos.setBounds(550, 380, 400, 30);
		cantPisos.setForeground(Color.black);
		cantPisos.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(cantPisos);
		
		JLabel totalHab = new JLabel("Total de habitaciones: "+hotel.getNumberOfRooms());
		totalHab.setBounds(550, 420, 400, 30);
		totalHab.setForeground(Color.black);
		totalHab.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(totalHab);
		
		JLabel distCiudad = new JLabel("Distancia a la ciudad más cercana (km): "+hotel.getNearbyCityDistance());
		distCiudad.setBounds(550, 460, 400, 30);
		distCiudad.setForeground(Color.black);
		distCiudad.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(distCiudad);
		
		JLabel distAereopuerto = new JLabel("Distancia al aeropuerto (km): "+hotel.getAirportDistance());
		distAereopuerto.setBounds(550, 500, 400, 30);
		distAereopuerto.setForeground(Color.black);
		distAereopuerto.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(distAereopuerto);
	}
	
	private void ponerModalidades(){
		String mod = hotel.getHotelModality();
		ArrayList<String> lista = new ArrayList<String>();
		if(mod.contains("Luna de Miel")) lista.add("Luna de Miel");
		if(mod.contains("Golf")) lista.add("Golf");
		if(mod.contains("Ecoturismo")) lista.add("Ecoturismo");
		if(mod.contains("Histórico")) lista.add("Histórico");
		if(mod.contains("Verano")) lista.add("Verano");
		if(mod.contains("Navidad")) lista.add("Navidad");
		if(mod.contains("Todo Incluido")) lista.add("Todo Incluido");
		
		int x = 600;
		int y = 220;
		for(String s : lista){
			JLabel lbl = new JLabel(s);
			lbl.setBounds(x, y, 300, 30);
			lbl.setForeground(Color.black);
			lbl.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(lbl);
			if(x==600){ x = 800;} else{ x = 600; y+=40;}
		}
		
	}
	
	private void buscar(){
		try {
			hotelChain = hotelChainServices.findHotelChain(hotel.getHotelChainCode());
			province = provinceServices.findProvince(hotel.getProvinceCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
