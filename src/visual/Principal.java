package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.RoleDTO;
import dto.UserDTO;
import services.RoleServices;
import services.ServicesLocator;
import utils.MyButtonModel;
import utils.Paneles;

public class Principal extends JFrame{

	private RoleServices roleServices = ServicesLocator.getRoleServices();

	private static final long serialVersionUID = 1L;

	private Principal este;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	private Color colorFondoBotones = new Color(58, 239, 235);
	private int panelAbierto;
	private UserDTO user;
	private RoleDTO roleUser;

	private JPanel contentPane;

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JButton btnMinimizar;
	private JButton btnPerfil;
	private JButton btnDescubrir;
	private JButton btnGestion;
	private JButton btnReportes;

	private JPanel panelPrincipal;

	/*
	 * Paneles
	 */
	private Perfil panelPerfil;
	private Descubrir panelDescubrir;
	private Gestion panelGestion;
	private AgregarUsuario panelAgregarUsuario;
	private AgregarHotel panelAgregarHotel;
	private AgregarrProvincia panelAgregarProvincia;
	private AgregarCadenaHotelera panelAgregarCadenaHotelera;
	private AgregarLugar panelAgregarLugar;
	private AgregarVehiculo panelAgregarVehiculo;
	private AgregarHabitacion panelAgregarHabitacion;
	private AgregarEvento panelAgregarEvento;
	private AgregarActividad panelAgregarActividad;
	private AgregarModalidad panelAgregarModalidad;
	private AgregarTransporte panelAgregarTransporte;
	private AgregarHospedaje panelAgregarHospedaje;
	private AgregarPaquete panelAgregarPaquete;
	private AgregarContrato panelAgregarContrato;
	private ConsultarLugares panelConsultarLugares;
	private ConsultarActividades panelConsultarActividades;
	private ConsultarVehiculos panelConsultarVehiculos;
	private ConsultarPaquetes panelConsultarPaquetes;
	private ConsultarModalidades panelConsultarModalidades;
	private ConsultarEventos panelConsultarEventos;
	private ConsultarHospedajes panelConsultarHospedajes;
	private ConsultarTransportes panelConsultarTransportes;
	private ConsultarHoteles panelConsultarHoteles;
	private ConsultarHabitaciones panelConsultarHabitaciones;
	private ConsultarTemporadas panelConsultarTemporadas;
	private RenovarContrato panelRenovarContrato;
	private VerHotel panelVerHotel;
	private VerPaquete panelVerPaquete;
	private VerVehiculo panelVerVehiculo;
	private VerUsuario panelVerUsuario;
	private VerHabitacion panelVerHabitacion;
	private VerLugar panelVerLugar;
	private VerActividad panelVerActividad;
	private VerContrato panelVerContrato;
	private VerModalidad panelVerModalidad;
	private VerTransporte panelVerTransporte;
	private VerHospedaje panelVerHospedaje;
	private VerEvento panelVerEvento;
	private EditarCadenaHotelera panelEditarCadenaHotelera;
	private EditarHabitacion panelEditarHabitacion;
	private EditarLugar panelEditarLugar;
	private EditarModalidad panelEditarModalidad;
	private EditarHotel panelEditarHotel;
	private EditarUsuario panelEditarUsuario;
	private EditarPaquete panelEditarPaquete;
	private EditarHospedaje panelEditarHospedaje;
	private EditarActividad panelEditarActividad;
	private Reportes panelReportes;
	private MostrarReporte panelMostrarReporte;

	public Principal(UserDTO u){
		este = this;
		user = u;
		establecerRol();
		setUndecorated(true);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, pantalla.width, pantalla.height);

		contentPane = new JPanel(null);
		setContentPane(contentPane);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(0, 0, pantalla.width, 50);
		panelSuperior.setBackground(colorAzul);
		contentPane.add(panelSuperior);

		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/minimize.png"));
		Image image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		Icon iconMinimizar = new ImageIcon(image);

		btnMinimizar = new JButton(iconMinimizar);
		btnMinimizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMinimizar.setContentAreaFilled(false);
				setExtendedState(ICONIFIED);
			}
		});
		btnMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMinimizar.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMinimizar.setContentAreaFilled(false);
			}
		});
		btnMinimizar.setModel(new MyButtonModel());
		btnMinimizar.setBounds(pantalla.width-150, 0, 75, 50);
		btnMinimizar.setBackground(colorFondoBotones);
		btnMinimizar.setFocusable(false);
		btnMinimizar.setBorderPainted(false);
		btnMinimizar.setContentAreaFilled(false);
		panelSuperior.add(btnMinimizar);

		img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
		image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		Icon iconCerrar = new ImageIcon(image);

		btnCerrar = new JButton(iconCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
		btnCerrar.setModel(new MyButtonModel());
		btnCerrar.setBounds(pantalla.width-75, 0, 75, 50);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		panelSuperior.add(btnCerrar);

		img = new ImageIcon(getClass().getResource("/visual/imagenes/usuario.png"));
		image = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Icon iconPerfil = new ImageIcon(image);

		btnPerfil = new JButton(iconPerfil);
		btnPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removerPanel();
				panelPerfil = new Perfil(este, user, roleUser);
				panelPrincipal.add(panelPerfil);
				panelPrincipal.repaint();
			}
		});
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPerfil.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPerfil.setContentAreaFilled(false);
			}
		});
		btnPerfil.setModel(new MyButtonModel());
		btnPerfil.setBounds(0, 0, 75, 50);
		btnPerfil.setBackground(colorFondoBotones);
		btnPerfil.setFocusable(false);
		btnPerfil.setBorderPainted(false);
		btnPerfil.setContentAreaFilled(false);
		panelSuperior.add(btnPerfil);

		img = new ImageIcon(getClass().getResource("/visual/imagenes/reservas.png"));
		image = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Icon iconReservas = new ImageIcon(image);

		btnDescubrir = new JButton(iconReservas);
		btnDescubrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removerPanel();
				panelDescubrir = new Descubrir(este);
				panelPrincipal.add(panelDescubrir);
				panelPrincipal.repaint();
			}
		});
		btnDescubrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDescubrir.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDescubrir.setContentAreaFilled(false);
			}
		});
		btnDescubrir.setModel(new MyButtonModel());
		btnDescubrir.setBounds(75, 0, 75, 50);
		btnDescubrir.setBackground(colorFondoBotones);
		btnDescubrir.setFocusable(false);
		btnDescubrir.setBorderPainted(false);
		btnDescubrir.setContentAreaFilled(false);
		panelSuperior.add(btnDescubrir);

		btnGestion = new JButton();
		btnGestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removerPanel();
				panelGestion = new Gestion(este, roleUser);
				panelPrincipal.add(panelGestion);
				panelPrincipal.repaint();
			}
		});
		btnGestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestion.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGestion.setContentAreaFilled(false);
			}
		});
		btnGestion.setModel(new MyButtonModel());
		btnGestion.setBounds(150, 0, 75, 50);
		btnGestion.setBackground(colorFondoBotones);
		btnGestion.setFocusable(false);
		btnGestion.setBorderPainted(false);
		btnGestion.setContentAreaFilled(false);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/reporte.png"));
		image = img.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
		Icon iconReporte = new ImageIcon(image);

		btnReportes = new JButton(iconReporte);
		btnReportes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removerPanel();
				panelReportes = new Reportes(este);
				panelPrincipal.add(panelReportes);
				panelPrincipal.repaint();
			}
		});
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReportes.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReportes.setContentAreaFilled(false);
			}
		});
		btnReportes.setModel(new MyButtonModel());
		btnReportes.setBounds(225, 0, 75, 50);
		btnReportes.setBackground(colorFondoBotones);
		btnReportes.setFocusable(false);
		btnReportes.setBorderPainted(false);
		btnReportes.setContentAreaFilled(false);
		
		panelPrincipal = new JPanel(null){
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/visual/imagenes/principal.jpg"));
				g.drawImage(img, 0, 0, panelPrincipal.getWidth(), panelPrincipal.getHeight(), this);
			}
		};
		panelPrincipal.setBounds(0, 50, pantalla.width, pantalla.height-50);
		contentPane.add(panelPrincipal);

		if(roleUser.getRoleName().equals("Gestor de Agencia"))
			vistaGestorAgencia();
		else if(roleUser.getRoleName().equals("Gestor de Ventas"))
			vistaGestorVentas();
	}

	private void removerPanel(){
		if(panelAbierto == Paneles.PANEL_PERFIL)
			panelPrincipal.remove(panelPerfil);
		if(panelAbierto == Paneles.PANEL_DESCUBRIR)
			panelPrincipal.remove(panelDescubrir);
		else if(panelAbierto == Paneles.PANEL_GESTION)
			panelPrincipal.remove(panelGestion);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_USUARIO)
			panelPrincipal.remove(panelAgregarUsuario);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_HOTEL)
			panelPrincipal.remove(panelAgregarHotel);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_PROVINCIA)
			panelPrincipal.remove(panelAgregarProvincia);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_CADENA_HOTELERA)
			panelPrincipal.remove(panelAgregarCadenaHotelera);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_LUGAR)
			panelPrincipal.remove(panelAgregarLugar);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_VEHICULO)
			panelPrincipal.remove(panelAgregarVehiculo);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_HABITACION)
			panelPrincipal.remove(panelAgregarHabitacion);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_EVENTO)
			panelPrincipal.remove(panelAgregarEvento);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_ACTIVIDAD)
			panelPrincipal.remove(panelAgregarActividad);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_MODALIDAD)
			panelPrincipal.remove(panelAgregarModalidad);
		else if(panelAbierto == Paneles.PANEL_VER_VEHICULO)
			panelPrincipal.remove(panelVerVehiculo);
		else if(panelAbierto == Paneles.PANEL_VER_USUARIO)
			panelPrincipal.remove(panelVerUsuario);
		else if(panelAbierto == Paneles.PANEL_VER_HABITACION)
			panelPrincipal.remove(panelVerHabitacion);
		else if(panelAbierto == Paneles.PANEL_VER_LUGAR)
			panelPrincipal.remove(panelVerLugar);
		else if(panelAbierto == Paneles.PANEL_VER_MODALIDAD)
			panelPrincipal.remove(panelVerModalidad);
		else if(panelAbierto == Paneles.PANEL_VER_ACTIVIDAD)
			panelPrincipal.remove(panelVerActividad);
		else if(panelAbierto == Paneles.PANEL_VER_HOTEL)
			panelPrincipal.remove(panelVerHotel);
		else if(panelAbierto == Paneles.PANEL_VER_TRANSPORTE)
			panelPrincipal.remove(panelVerTransporte);
		else if(panelAbierto == Paneles.PANEL_VER_HOSPEDAJE)
			panelPrincipal.remove(panelVerHospedaje);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_LUGARES)
			panelPrincipal.remove(panelConsultarLugares);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_ACTIVIDADES)
			panelPrincipal.remove(panelConsultarActividades);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_PAQUETES)
			panelPrincipal.remove(panelConsultarPaquetes);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_VEHICULOS)
			panelPrincipal.remove(panelConsultarVehiculos);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_MODALIDADES)
			panelPrincipal.remove(panelConsultarModalidades);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_TRANSPORTES)
			panelPrincipal.remove(panelConsultarTransportes);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_EVENTOS)
			panelPrincipal.remove(panelConsultarEventos);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_HOSPEDAJES)
			panelPrincipal.remove(panelConsultarHospedajes);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_HOTELES)
			panelPrincipal.remove(panelConsultarHoteles);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_HABITACIONES)
			panelPrincipal.remove(panelConsultarHabitaciones);
		else if(panelAbierto == Paneles.PANEL_CONSULTAR_TEMPORADAS)
			panelPrincipal.remove(panelConsultarTemporadas);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_TRANSPORTE)
			panelPrincipal.remove(panelAgregarTransporte);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_PAQUETE)
			panelPrincipal.remove(panelAgregarPaquete);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_CONTRATO)
			panelPrincipal.remove(panelAgregarContrato);
		else if(panelAbierto == Paneles.PANEL_AGREGAR_HOSPEDAJE)
			panelPrincipal.remove(panelAgregarHospedaje);
		else if(panelAbierto == Paneles.PANEL_VER_EVENTO)
			panelPrincipal.remove(panelVerEvento);
		else if(panelAbierto == Paneles.PANEL_VER_CONTRATO)
			panelPrincipal.remove(panelVerContrato);
		else if(panelAbierto == Paneles.PANEL_VER_PAQUETE)
			panelPrincipal.remove(panelVerPaquete);
		else if(panelAbierto == Paneles.PANEL_EDITAR_CADENA_HOTELERA)
			panelPrincipal.remove(panelEditarCadenaHotelera);
		else if(panelAbierto == Paneles.PANEL_EDITAR_MODALIDAD)
			panelPrincipal.remove(panelEditarModalidad);
		else if(panelAbierto == Paneles.PANEL_RENOVAR_CONTRATO)
			panelPrincipal.remove(panelRenovarContrato);
		else if(panelAbierto == Paneles.PANEL_EDITAR_HOTEL)
			panelPrincipal.remove(panelEditarHotel);
		else if(panelAbierto == Paneles.PANEL_EDITAR_LUGAR)
			panelPrincipal.remove(panelEditarLugar);
		else if(panelAbierto == Paneles.PANEL_EDITAR_USUARIO)
			panelPrincipal.remove(panelEditarUsuario);
		else if(panelAbierto == Paneles.PANEL_EDITAR_HABITACION)
			panelPrincipal.remove(panelEditarHabitacion);
		else if(panelAbierto == Paneles.PANEL_EDITAR_PAQUETES)
			panelPrincipal.remove(panelEditarPaquete);
		else if(panelAbierto == Paneles.PANEL_EDITAR_HOSPEDAJES)
			panelPrincipal.remove(panelEditarHospedaje);
		else if(panelAbierto == Paneles.PANEL_EDITAR_ACTIVIDAD)
			panelPrincipal.remove(panelEditarActividad);
		else if(panelAbierto == Paneles.PANEL_REPORTES)
			panelPrincipal.remove(panelReportes);
		else if(panelAbierto == Paneles.PANEL_MOSTRAR_REPORTES)
			panelPrincipal.remove(panelMostrarReporte);
	}

	private void vistaGestorVentas(){
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/gestion venta.png"));
		Image image = img.getImage().getScaledInstance(38, 38, Image.SCALE_SMOOTH);
		Icon iconGestion = new ImageIcon(image);

		btnGestion.setIcon(iconGestion);
		panelSuperior.add(btnGestion);
		
		panelSuperior.add(btnReportes);
	}

	private void vistaGestorAgencia(){
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/gestion agencia.png"));
		Image image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		Icon iconGestion = new ImageIcon(image);

		btnGestion.setIcon(iconGestion);
		panelSuperior.add(btnGestion);
		
		panelSuperior.add(btnReportes);
	}

	public void setPanelAgregarUsuario(AgregarUsuario agregarUsuario) {
		this.panelAgregarUsuario = agregarUsuario;
	}

	public void setPanelAgregarHotel(AgregarHotel panelAgregarHotel) {
		this.panelAgregarHotel = panelAgregarHotel;
	}

	public void setPanelAgregarProvincia(AgregarrProvincia panelAgregarProvincia) {
		this.panelAgregarProvincia = panelAgregarProvincia;
	}

	public void setPanelAgregarCadenaHotelera(AgregarCadenaHotelera panelAgregarCadenaHotelera) {
		this.panelAgregarCadenaHotelera = panelAgregarCadenaHotelera;
	}

	public void setPanelAgregarLugar(AgregarLugar panelAgregarLugar) {
		this.panelAgregarLugar = panelAgregarLugar;
	}

	public void setPanelAgregarVehiculo(AgregarVehiculo panelAgregarVehiculo) {
		this.panelAgregarVehiculo = panelAgregarVehiculo;
	}

	public void setPanelAgregarHabitacion(AgregarHabitacion panelAgregarHabitacion) {
		this.panelAgregarHabitacion = panelAgregarHabitacion;
	}

	public void setPanelAgregarEvento(AgregarEvento panelAgregarEvento) {
		this.panelAgregarEvento = panelAgregarEvento;
	}

	public void setPanelAgregarActividad(AgregarActividad panelAgregarActividad) {
		this.panelAgregarActividad = panelAgregarActividad;
	}

	public void setPanelVerVehiculo(VerVehiculo panelVerVehiculo) {
		this.panelVerVehiculo = panelVerVehiculo;
	}

	public void setPanelAgregarModalidad(AgregarModalidad panelAgregarModalidad) {
		this.panelAgregarModalidad = panelAgregarModalidad;
	}

	public void setPanelVerUsuario(VerUsuario panelVerUsuario) {
		this.panelVerUsuario = panelVerUsuario;
	}

	public void setPanelVerHabitacion(VerHabitacion panelVerHabitacion) {
		this.panelVerHabitacion = panelVerHabitacion;
	}

	public void setPanelVerLugar(VerLugar panelVerLugar) {
		this.panelVerLugar = panelVerLugar;
	}

	public void setPanelVerActividad(VerActividad panelVerActividad) {
		this.panelVerActividad = panelVerActividad;
	}


	public void setPanelVerModalidad(VerModalidad panelVerModalidad) {
		this.panelVerModalidad = panelVerModalidad;
	}

	public void setPanelVerTransporte(VerTransporte panelVerTransporte) {
		this.panelVerTransporte = panelVerTransporte;
	}

	public void setPanelVerHospedaje(VerHospedaje panelVerHospedaje) {
		this.panelVerHospedaje = panelVerHospedaje;
	}

	public void setPanelConsultarLugares(ConsultarLugares panelConsultarLugares) {
		this.panelConsultarLugares = panelConsultarLugares;
	}

	public void setPanelConsultarActividades(
			ConsultarActividades panelConsultarActividades) {
		this.panelConsultarActividades = panelConsultarActividades;
	}

	public void setPanelAgregarTransporte(AgregarTransporte panelAgregarTransporte) {
		this.panelAgregarTransporte = panelAgregarTransporte;
	}

	public void setPanelConsultarVehiculos(
			ConsultarVehiculos panelConsultarVehiculos) {
		this.panelConsultarVehiculos = panelConsultarVehiculos;
	}

	public void setPanelConsultarModalidades(ConsultarModalidades panelConsultarModalidades) {
		this.panelConsultarModalidades = panelConsultarModalidades;
	}

	public void setPanelVerHotel(VerHotel panelVerHotel) {
		this.panelVerHotel = panelVerHotel;
	}

	public void setPanelVerEvento(VerEvento panelVerEvento) {
		this.panelVerEvento = panelVerEvento;
	}

	public void setPanelAgregarHospedaje(AgregarHospedaje panelAgregarHospedaje) {
		this.panelAgregarHospedaje = panelAgregarHospedaje;
	}


	public void setPanelEditarHabitacion(EditarHabitacion panelEditarHabitacion) {
		this.panelEditarHabitacion = panelEditarHabitacion;
	}

	public void setPanelConsultarHoteles(ConsultarHoteles panelConsultarHoteles) {
		this.panelConsultarHoteles = panelConsultarHoteles;
	}

	public void setPanelEditarUsuario(EditarUsuario panelEditarUsuario) {
		this.panelEditarUsuario = panelEditarUsuario;
	}

	public void setPanelConsultarHabitaciones(
			ConsultarHabitaciones panelConsultarHabitaciones) {
		this.panelConsultarHabitaciones = panelConsultarHabitaciones;
	}

	public void setPanelConsultarTemporadas(
			ConsultarTemporadas panelConsultarTemporadas) {
		this.panelConsultarTemporadas = panelConsultarTemporadas;
	}

	public void setPanelEditarCadenaHotelera(
			EditarCadenaHotelera panelEditarCadenaHotelera) {
		this.panelEditarCadenaHotelera = panelEditarCadenaHotelera;
	}

	public void setPanelEditarLugar(EditarLugar panelEditarLugar) {
		this.panelEditarLugar = panelEditarLugar;
	}
	public void setPanelEditarModalidad(EditarModalidad panelEditarModalidad) {
		this.panelEditarModalidad = panelEditarModalidad;
	}

	public void setPanelEditarHotel(EditarHotel panelEditarHotel) {
		this.panelEditarHotel = panelEditarHotel;
	}

	public void setPanelAgregarPaquete(AgregarPaquete panelAgregarPaquete) {
		this.panelAgregarPaquete = panelAgregarPaquete;
	}

	public void setPanelAgregarContrato(AgregarContrato panelAgregarContrato) {
		this.panelAgregarContrato = panelAgregarContrato;
	}

	public void setPanelConsultarEventos(ConsultarEventos panelConsultarEventos) {
		this.panelConsultarEventos = panelConsultarEventos;
	}

	public void setPanelConsultarHospedajes(
			ConsultarHospedajes panelConsultarHospedajes) {
		this.panelConsultarHospedajes = panelConsultarHospedajes;
	}

	public void setPanelConsultarPaquetes(ConsultarPaquetes panelConsultarPaquetes) {
		this.panelConsultarPaquetes = panelConsultarPaquetes;
	}

	public void setPanelConsultarTransportes(
			ConsultarTransportes panelConsultarTransportes) {
		this.panelConsultarTransportes = panelConsultarTransportes;
	}

	public void setPanelEditarPaquete(EditarPaquete panelEditarPaquete) {
		this.panelEditarPaquete = panelEditarPaquete;
	}

	public void setPanelEditarHospedaje(EditarHospedaje panelEditarHospedaje) {
		this.panelEditarHospedaje = panelEditarHospedaje;
	}

	public void setPanelRenovarContrato(RenovarContrato panelRenovarContrato) {
		this.panelRenovarContrato = panelRenovarContrato;
	}

	public void setPanelEditarActividad(EditarActividad panelEditarActividad) {
		this.panelEditarActividad = panelEditarActividad;
	}

	public void setPanelVerContrato(VerContrato panelVerContrato) {
		this.panelVerContrato = panelVerContrato;
	}

	public void setPanelVerPaquete(VerPaquete panelVerPaquete) {
		this.panelVerPaquete = panelVerPaquete;
	}

	public void setPanelReportes(Reportes panelReportes) {
		this.panelReportes = panelReportes;
	}

	public void setPanelMostrarReporte(MostrarReporte panelMostrarReporte) {
		this.panelMostrarReporte = panelMostrarReporte;
	}

	public void setPanelAbierto(int panel){
		panelAbierto = panel;
	}

	public JPanel getPanelPrincipal(){
		return panelPrincipal;
	}

	private void establecerRol(){
		try {
			roleUser = roleServices.findRole(user.getRoleCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}