package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import services.ActivityServices;
import services.ContractEventServices;
import services.ContractLodgingServices;
import services.ContractServices;
import services.ContractTransportServices;
import services.CostPerEstablishedToursServices;
import services.CostPerHourKilometerServices;
import services.CostPerKilometerServices;
import services.EventServices;
import services.FoodPlanServices;
import services.HotelChainServices;
import services.HotelServices;
import services.LodgingServices;
import services.PlaceServices;
import services.ProvincePlaceServices;
import services.ProvinceServices;
import services.RoleServices;
import services.RoomServices;
import services.SeasonServices;
import services.ServicesLocator;
import services.TouristPackageServices;
import services.TransportModalityServices;
import services.TransportServices;
import services.UserServices;
import services.VehicleServices;
import utils.ActivityTableModel;
import utils.ContractTableModel;
import utils.EventTableModel;
import utils.FoodPlanTableModel;
import utils.HotelChainTableModel;
import utils.HotelTableModel;
import utils.LodgingTableModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PlaceTableModel;
import utils.ProvinceTableModel;
import utils.RoleTableModel;
import utils.RoomTableModel;
import utils.SeasonTableModel;
import utils.TouristPackageTableModel;
import utils.TransportModalityTableModel;
import utils.TransportTableModel;
import utils.UserTableModel;
import utils.VehicleTableModel;

import javax.swing.SwingConstants;

import dto.ActivityDTO;
import dto.ContractDTO;
import dto.ContractEventDTO;
import dto.ContractLodgingDTO;
import dto.EventDTO;
import dto.FoodPlanDTO;
import dto.HotelChainDTO;
import dto.HotelDTO;
import dto.LodgingDTO;
import dto.PlaceDTO;
import dto.ProvinceDTO;
import dto.RoleDTO;
import dto.RoomDTO;
import dto.SeasonDTO;
import dto.TouristPackageDTO;
import dto.TransportDTO;
import dto.TransportModalityDTO;
import dto.UserDTO;
import dto.VehicleDTO;

public class Gestion extends MiJPanel{

	private ActivityServices activityServices = ServicesLocator.getActivityServices();
	private ContractEventServices contractEventServices = ServicesLocator.getContractEventServices();
	private ContractLodgingServices contractLodgingServices = ServicesLocator.getContractLodgingServices();
	private ContractServices contractServices = ServicesLocator.getContractServices();
	private ContractTransportServices contractTransportServices = ServicesLocator.getContractTransportServices();
	private CostPerEstablishedToursServices costPerEstablishedToursServices = ServicesLocator.getCostPerEstablishedToursServices();
	private CostPerHourKilometerServices costPerHourKilometerServices = ServicesLocator.getCostPerHourKilometerServices();
	private CostPerKilometerServices costPerKilometerServices = ServicesLocator.getCostPerKilometerServices();
	private EventServices eventServices = ServicesLocator.getEventServices();
	private FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();
	private HotelChainServices hotelChainServices = ServicesLocator.getHotelChainServices();
	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private LodgingServices lodgingServices = ServicesLocator.getLodgingServices();
	private PlaceServices placeServices = ServicesLocator.getPlaceServices();
	private ProvincePlaceServices provincePlaceServices = ServicesLocator.getProvincePlaceServices();
	private ProvinceServices provinceServices = ServicesLocator.getProvinceServices();
	private RoleServices roleServices = ServicesLocator.getRoleServices();
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	private SeasonServices seasonServices = ServicesLocator.getSeasonServices();
	private TransportModalityServices transportModalityServices = ServicesLocator.getTransportModalityServices();
	private TransportServices transportServices = ServicesLocator.getTransportServices();
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private UserServices userServices = ServicesLocator.getUserServices();
	private VehicleServices vehicleServices = ServicesLocator.getVehicleServices();

	private ArrayList<ActivityDTO> listaActividades;
	private ArrayList<HotelChainDTO> listaCadenasHoteleras;
	private ArrayList<ContractDTO> listaContratos;
	private ArrayList<EventDTO> listaEventos;
	private ArrayList<RoomDTO> listaHabitaciones;
	private ArrayList<LodgingDTO> listaHospedajes;
	private ArrayList<HotelDTO> listaHoteles;
	private ArrayList<PlaceDTO> listaLugares;
	private ArrayList<TransportModalityDTO> listaModalidades;
	private ArrayList<TouristPackageDTO> listaPaquetes;
	private ArrayList<FoodPlanDTO> listaPlanesAlimenticios;
	private ArrayList<ProvinceDTO> listaProvincias;
	private ArrayList<RoleDTO> listaRoles;
	private ArrayList<SeasonDTO> listaTemporadas;
	private ArrayList<TransportDTO> listaTransportes;
	private ArrayList<UserDTO> listaUsuarios;
	private ArrayList<VehicleDTO> listaVehiculos;

	private DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();

	private int pos = -1;

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private Gestion este;
	private Principal padre;

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnVer;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;

	private boolean esGestorAgencia;
	/*
	 * Gestor de agencia
	 */
	private JButton btnCadenas;
	private JButton btnHabitaciones;
	private JButton btnHoteles;
	private JButton btnLugares;
	private JButton btnRoles;
	private JButton btnPlanAlimenticio;
	private JButton btnProvincias;
	private JButton btnTemporadas;
	private JButton btnUsuarios;
	private JButton btnVehiculos;
	private HotelChainTableModel hotelChainTableModel;
	private RoomTableModel roomTableModel;
	private HotelTableModel hotelTableModel;
	private PlaceTableModel placeTableModel;
	private RoleTableModel roleTableModel;
	private FoodPlanTableModel foodPlanTableModel;
	private ProvinceTableModel provinceTableModel;
	private SeasonTableModel seasonTableModel;
	private UserTableModel userTableModel;
	private VehicleTableModel vehicleTableModel;
	/*
	 * Gestor de ventas
	 */
	private JButton btnActividades;
	private JButton btnContratos;
	private JButton btnEventos;
	private JButton btnHospedajes;
	private JButton btnModalidades;
	private JButton btnPaquetes;
	private JButton btnTransportes;
	private ActivityTableModel activityTableModel;
	private ContractTableModel contractTableModel;
	private EventTableModel eventTableModel;
	private LodgingTableModel lodgingTableModel;
	private TransportModalityTableModel transportModalityTableModel;
	private TouristPackageTableModel touristPackageTableModel;
	private TransportTableModel transportTableModel;

	private UserDTO user;
	private RoleDTO roleUser;

	public Gestion(Principal p, UserDTO u, RoleDTO r){
		este = this;
		padre = p;
		user = u;
		roleUser = r;
		setTipoPanel(Paneles.PANEL_GESTION);
		padre.setPanelAbierto(getTipoPanel());
		setBounds(pantalla.width/2-601, pantalla.height/2-376, 1202, 702);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 1200, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

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
		btnCerrar.setBounds(1155, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 1200, 670);
		panelInferior.setBackground(Color.white);
		add(panelInferior);

		crearTabla();

		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.white);
		scrollPane.getViewport().setBackground(Color.white);
		panelInferior.add(scrollPane);

		btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accionVer();
			}
		});
		btnVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVer.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnVer.setBackground(colorAzul);
			}
		});
		btnVer.setModel(new MyButtonModel());
		btnVer.setForeground(Color.black);
		btnVer.setBackground(colorAzul);
		btnVer.setFont(new Font("Arial", Font.BOLD, 22));
		btnVer.setFocusable(false);
		btnVer.setBorderPainted(false);
		panelInferior.add(btnVer);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionAgregar();
			}
		});
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAgregar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAgregar.setBackground(colorAzul);
			}
		});
		btnAgregar.setModel(new MyButtonModel());
		btnAgregar.setForeground(Color.black);
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 22));
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionEliminar();
			}
		});
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(colorAzul);
			}
		});
		btnEliminar.setModel(new MyButtonModel());
		btnEliminar.setForeground(Color.black);
		btnEliminar.setBackground(colorAzul);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 22));
		btnEliminar.setFocusable(false);
		btnEliminar.setBorderPainted(false);
		panelInferior.add(btnEliminar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionEditar();
			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(colorAzul);
			}
		});
		btnEditar.setModel(new MyButtonModel());
		btnEditar.setForeground(Color.black);
		btnEditar.setBackground(colorAzul);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 22));
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);

		if(roleUser.getRoleName().equals("Gestor de Agencia"))
			vistaGestorDeAgencia();
		else if(roleUser.getRoleName().equals("Gestor de Ventas"))
			vistaGestorDeVentas();
	}

	public void vistaGestorDeAgencia(){
		esGestorAgencia = true;
		scrollPane.setBounds(300, 20, 880, 580);
		btnVer.setBounds(300, 615, 205, 35);
		btnVer.setVisible(false);
		btnAgregar.setBounds(525, 615, 205, 35);
		btnEliminar.setBounds(750, 615, 205, 35);
		btnEditar.setBounds(975, 615, 205, 35);


		btnCadenas = new JButton("  Cadenas Hoteleras");
		btnCadenas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(true);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(false);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				este.repaint();
				ponerCadenasHoteleras();
			}
		});
		btnCadenas.setModel(new MyButtonModel());
		btnCadenas.setBounds(20, 20, 250, 40);
		btnCadenas.setForeground(Color.black);
		btnCadenas.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadenas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadenas.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnCadenas.setFont(new Font("Arial", Font.BOLD, 22));
		btnCadenas.setFocusable(false);
		btnCadenas.setContentAreaFilled(false);
		btnCadenas.setBorderPainted(true);
		panelInferior.add(btnCadenas);

		btnHabitaciones = new JButton("  Habitaciones");
		btnHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(true);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				este.repaint();
				ponerHabitacion();
			}
		});
		btnHabitaciones.setModel(new MyButtonModel());
		btnHabitaciones.setBounds(20, 80, 250, 40);
		btnHabitaciones.setForeground(Color.black);
		btnHabitaciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnHabitaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHabitaciones.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnHabitaciones.setFont(new Font("Arial", Font.BOLD, 22));
		btnHabitaciones.setFocusable(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBorderPainted(false);
		panelInferior.add(btnHabitaciones);

		btnHoteles = new JButton("  Hoteles");
		btnHoteles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(true);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				este.repaint();
				ponerHotel();
			}
		});
		btnHoteles.setModel(new MyButtonModel());
		btnHoteles.setBounds(20, 140, 250, 40);
		btnHoteles.setForeground(Color.black);
		btnHoteles.setHorizontalAlignment(SwingConstants.LEFT);
		btnHoteles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHoteles.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnHoteles.setFont(new Font("Arial", Font.BOLD, 22));
		btnHoteles.setFocusable(false);
		btnHoteles.setContentAreaFilled(false);
		btnHoteles.setBorderPainted(false);
		panelInferior.add(btnHoteles);

		btnLugares = new JButton("  Lugares");
		btnLugares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(true);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				este.repaint();
				ponerLugares();
			}
		});
		btnLugares.setModel(new MyButtonModel());
		btnLugares.setBounds(20, 200, 250, 40);
		btnLugares.setForeground(Color.black);
		btnLugares.setHorizontalAlignment(SwingConstants.LEFT);
		btnLugares.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLugares.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnLugares.setFont(new Font("Arial", Font.BOLD, 22));
		btnLugares.setFocusable(false);
		btnLugares.setContentAreaFilled(false);
		btnLugares.setBorderPainted(false);
		panelInferior.add(btnLugares);

		btnRoles = new JButton("  Roles");
		btnRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(true);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(false);
				btnAgregar.setVisible(false);
				btnEliminar.setVisible(false);
				btnEditar.setVisible(false);
				este.repaint();
				ponerRoles();
			}
		});
		btnRoles.setModel(new MyButtonModel());
		btnRoles.setBounds(20, 260, 250, 40);
		btnRoles.setForeground(Color.black);
		btnRoles.setHorizontalAlignment(SwingConstants.LEFT);
		btnRoles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoles.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnRoles.setFont(new Font("Arial", Font.BOLD, 22));
		btnRoles.setFocusable(false);
		btnRoles.setContentAreaFilled(false);
		btnRoles.setBorderPainted(false);
		panelInferior.add(btnRoles);

		btnPlanAlimenticio = new JButton("  Planes Alimenticios");
		btnPlanAlimenticio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(true);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(false);
				btnAgregar.setVisible(false);
				btnEliminar.setVisible(false);
				btnEditar.setVisible(false);
				este.repaint();
				ponerPlanesAlimenticios();
			}
		});
		btnPlanAlimenticio.setModel(new MyButtonModel());
		btnPlanAlimenticio.setBounds(20, 320, 250, 40);
		btnPlanAlimenticio.setForeground(Color.black);
		btnPlanAlimenticio.setHorizontalAlignment(SwingConstants.LEFT);
		btnPlanAlimenticio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlanAlimenticio.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnPlanAlimenticio.setFont(new Font("Arial", Font.BOLD, 22));
		btnPlanAlimenticio.setFocusable(false);
		btnPlanAlimenticio.setContentAreaFilled(false);
		btnPlanAlimenticio.setBorderPainted(false);
		panelInferior.add(btnPlanAlimenticio);

		btnProvincias = new JButton("  Provincias");
		btnProvincias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(true);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(false);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(false);
				este.repaint();
				ponerProvincias();
			}
		});
		btnProvincias.setModel(new MyButtonModel());
		btnProvincias.setBounds(20, 380, 250, 40);
		btnProvincias.setForeground(Color.black);
		btnProvincias.setHorizontalAlignment(SwingConstants.LEFT);
		btnProvincias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProvincias.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnProvincias.setFont(new Font("Arial", Font.BOLD, 22));
		btnProvincias.setFocusable(false);
		btnProvincias.setContentAreaFilled(false);
		btnProvincias.setBorderPainted(false);
		panelInferior.add(btnProvincias);

		btnTemporadas = new JButton("  Temporadas");
		btnTemporadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(true);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(false);
				btnAgregar.setVisible(false);
				btnEliminar.setVisible(false);
				btnEditar.setVisible(false);
				este.repaint();
				ponerTemporadas();
			}
		});
		btnTemporadas.setModel(new MyButtonModel());
		btnTemporadas.setBounds(20, 440, 250, 40);
		btnTemporadas.setForeground(Color.black);
		btnTemporadas.setHorizontalAlignment(SwingConstants.LEFT);
		btnTemporadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTemporadas.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnTemporadas.setFont(new Font("Arial", Font.BOLD, 22));
		btnTemporadas.setFocusable(false);
		btnTemporadas.setContentAreaFilled(false);
		btnTemporadas.setBorderPainted(false);
		panelInferior.add(btnTemporadas);

		btnUsuarios = new JButton("  Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(true);
				btnVehiculos.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				este.repaint();
				ponerUsuarios();
			}
		});
		btnUsuarios.setModel(new MyButtonModel());
		btnUsuarios.setBounds(20, 500, 250, 40);
		btnUsuarios.setForeground(Color.black);
		btnUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnUsuarios.setFont(new Font("Arial", Font.BOLD, 22));
		btnUsuarios.setFocusable(false);
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setBorderPainted(false);
		panelInferior.add(btnUsuarios);

		btnVehiculos = new JButton("  Vehículos");
		btnVehiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadenas.setBorderPainted(false);
				btnHabitaciones.setBorderPainted(false);
				btnHoteles.setBorderPainted(false);
				btnLugares.setBorderPainted(false);
				btnRoles.setBorderPainted(false);
				btnPlanAlimenticio.setBorderPainted(false);
				btnProvincias.setBorderPainted(false);
				btnTemporadas.setBorderPainted(false);
				btnUsuarios.setBorderPainted(false);
				btnVehiculos.setBorderPainted(true);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(false);
				este.repaint();
				ponerVehiculos();
			}
		});
		btnVehiculos.setModel(new MyButtonModel());
		btnVehiculos.setBounds(20, 560, 250, 40);
		btnVehiculos.setForeground(Color.black);
		btnVehiculos.setHorizontalAlignment(SwingConstants.LEFT);
		btnVehiculos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVehiculos.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnVehiculos.setFont(new Font("Arial", Font.BOLD, 22));
		btnVehiculos.setFocusable(false);
		btnVehiculos.setContentAreaFilled(false);
		btnVehiculos.setBorderPainted(false);
		panelInferior.add(btnVehiculos);

		ponerCadenasHoteleras();
	}

	public void vistaGestorDeVentas(){
		esGestorAgencia = false;
		scrollPane.setBounds(330, 20, 850, 580);
		btnVer.setBounds(330, 615, 196, 35);
		btnAgregar.setBounds(548, 615, 196, 35);
		btnEliminar.setBounds(766, 615, 196, 35);
		btnEditar.setBounds(984, 615, 196, 35);

		btnActividades = new JButton("  Actividades");
		btnActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(true);
				btnContratos.setBorderPainted(false);
				btnEventos.setBorderPainted(false);
				btnHospedajes.setBorderPainted(false);
				btnModalidades.setBorderPainted(false);
				btnPaquetes.setBorderPainted(false);
				btnTransportes.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				btnEditar.setText("Editar");
				este.repaint();
				ponerActividades();
			}
		});
		btnActividades.setModel(new MyButtonModel());
		btnActividades.setBounds(20, 20, 300, 40);
		btnActividades.setForeground(Color.black);
		btnActividades.setHorizontalAlignment(SwingConstants.LEFT);
		btnActividades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActividades.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnActividades.setFont(new Font("Arial", Font.BOLD, 22));
		btnActividades.setFocusable(false);
		btnActividades.setContentAreaFilled(false);
		btnActividades.setBorderPainted(true);
		panelInferior.add(btnActividades);

		btnContratos = new JButton("  Contratos");
		btnContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(false);
				btnContratos.setBorderPainted(true);
				btnEventos.setBorderPainted(false);
				btnHospedajes.setBorderPainted(false);
				btnModalidades.setBorderPainted(false);
				btnPaquetes.setBorderPainted(false);
				btnTransportes.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				btnEditar.setText("Renovar");
				este.repaint();
				ponerContratos();
			}
		});
		btnContratos.setModel(new MyButtonModel());
		btnContratos.setBounds(20, 80, 300, 40);
		btnContratos.setForeground(Color.black);
		btnContratos.setHorizontalAlignment(SwingConstants.LEFT);
		btnContratos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContratos.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnContratos.setFont(new Font("Arial", Font.BOLD, 22));
		btnContratos.setFocusable(false);
		btnContratos.setContentAreaFilled(false);
		btnContratos.setBorderPainted(false);
		panelInferior.add(btnContratos);

		btnEventos = new JButton("  Eventos");
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(false);
				btnContratos.setBorderPainted(false);
				btnEventos.setBorderPainted(true);
				btnHospedajes.setBorderPainted(false);
				btnModalidades.setBorderPainted(false);
				btnPaquetes.setBorderPainted(false);
				btnTransportes.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(false);
				btnEditar.setText("Editar");
				este.repaint();
				ponerEventos();
			}
		});
		btnEventos.setModel(new MyButtonModel());
		btnEventos.setBounds(20, 140, 300, 40);
		btnEventos.setForeground(Color.black);
		btnEventos.setHorizontalAlignment(SwingConstants.LEFT);
		btnEventos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventos.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnEventos.setFont(new Font("Arial", Font.BOLD, 22));
		btnEventos.setFocusable(false);
		btnEventos.setContentAreaFilled(false);
		btnEventos.setBorderPainted(false);
		panelInferior.add(btnEventos);

		btnHospedajes = new JButton("  Hospedajes");
		btnHospedajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(false);
				btnContratos.setBorderPainted(false);
				btnEventos.setBorderPainted(false);
				btnHospedajes.setBorderPainted(true);
				btnModalidades.setBorderPainted(false);
				btnPaquetes.setBorderPainted(false);
				btnTransportes.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				btnEditar.setText("Editar");
				este.repaint();
				ponerHospedajes();
			}
		});
		btnHospedajes.setModel(new MyButtonModel());
		btnHospedajes.setBounds(20, 200, 300, 40);
		btnHospedajes.setForeground(Color.black);
		btnHospedajes.setHorizontalAlignment(SwingConstants.LEFT);
		btnHospedajes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHospedajes.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnHospedajes.setFont(new Font("Arial", Font.BOLD, 22));
		btnHospedajes.setFocusable(false);
		btnHospedajes.setContentAreaFilled(false);
		btnHospedajes.setBorderPainted(false);
		panelInferior.add(btnHospedajes);

		btnModalidades = new JButton("  Modalidades de Transporte");
		btnModalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(false);
				btnContratos.setBorderPainted(false);
				btnEventos.setBorderPainted(false);
				btnHospedajes.setBorderPainted(false);
				btnModalidades.setBorderPainted(true);
				btnPaquetes.setBorderPainted(false);
				btnTransportes.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				btnEditar.setText("Editar");
				este.repaint();
				ponerModalidades();
			}
		});
		btnModalidades.setModel(new MyButtonModel());
		btnModalidades.setBounds(20, 260, 300, 40);
		btnModalidades.setForeground(Color.black);
		btnModalidades.setHorizontalAlignment(SwingConstants.LEFT);
		btnModalidades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModalidades.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnModalidades.setFont(new Font("Arial", Font.BOLD, 22));
		btnModalidades.setFocusable(false);
		btnModalidades.setContentAreaFilled(false);
		btnModalidades.setBorderPainted(false);
		panelInferior.add(btnModalidades);

		btnPaquetes = new JButton("  Paquetes Turísticos");
		btnPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(false);
				btnContratos.setBorderPainted(false);
				btnEventos.setBorderPainted(false);
				btnHospedajes.setBorderPainted(false);
				btnModalidades.setBorderPainted(false);
				btnPaquetes.setBorderPainted(true);
				btnTransportes.setBorderPainted(false);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
				btnEditar.setText("Editar");
				este.repaint();
				ponerPaquetes();
			}
		});
		btnPaquetes.setModel(new MyButtonModel());
		btnPaquetes.setBounds(20, 320, 300, 40);
		btnPaquetes.setForeground(Color.black);
		btnPaquetes.setHorizontalAlignment(SwingConstants.LEFT);
		btnPaquetes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaquetes.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnPaquetes.setFont(new Font("Arial", Font.BOLD, 22));
		btnPaquetes.setFocusable(false);
		btnPaquetes.setContentAreaFilled(false);
		btnPaquetes.setBorderPainted(false);
		panelInferior.add(btnPaquetes);

		btnTransportes = new JButton("  Transportes");
		btnTransportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBorderPainted(false);
				btnContratos.setBorderPainted(false);
				btnEventos.setBorderPainted(false);
				btnHospedajes.setBorderPainted(false);
				btnModalidades.setBorderPainted(false);
				btnPaquetes.setBorderPainted(false);
				btnTransportes.setBorderPainted(true);

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(false);
				btnEditar.setText("Editar");
				este.repaint();
				ponerTransportes();
			}
		});
		btnTransportes.setModel(new MyButtonModel());
		btnTransportes.setBounds(20, 380, 300, 40);
		btnTransportes.setForeground(Color.black);
		btnTransportes.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransportes.setBorder(new MatteBorder(0, 4, 0, 0, colorAzul));
		btnTransportes.setFont(new Font("Arial", Font.BOLD, 22));
		btnTransportes.setFocusable(false);
		btnTransportes.setContentAreaFilled(false);
		btnTransportes.setBorderPainted(false);
		panelInferior.add(btnTransportes);

		ponerActividades();
	}

	public void ponerActividades(){
		pos = -1;
		activityTableModel = new ActivityTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(activityTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(450);
		table.getColumnModel().getColumn(3).setResizable(false);
		try{
			listaActividades = activityServices.selectAllActivity();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");
			for(ActivityDTO a : listaActividades){
				String[] datos = {String.valueOf(a.getActivityCode()), 
						format.format(a.getActivityDate())+"     "+format2.format(a.getActivityDate()),
						String.valueOf(a.getActivityPrice()), a.getActivityDescription()};
				activityTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerContratos(){
		pos = -1;
		contractTableModel = new ContractTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(contractTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(380);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setResizable(false);
		try{
			listaContratos = contractServices.selectAllContracts();
			for(ContractDTO c : listaContratos){
				TouristPackageDTO t = touristPackageServices.findTouristPackage(c.getPackageCode());
				String[] datos = {String.valueOf(c.getContractCode()), c.getContractType(), t.getPromotionalName()};
				contractTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerEventos(){
		pos = -1;
		eventTableModel = new EventTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(eventTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setResizable(false);
		try{
			listaEventos = eventServices.selectAllEvents();
			for(EventDTO e : listaEventos){
				ActivityDTO act = activityServices.findActivity(e.getActivityCode());
				PlaceDTO lug = placeServices.findPlace(e.getPlaceCode());
				String[] datos = {String.valueOf(e.getEventCode()), lug.getPlaceName(), act.getActivityDescription()};
				eventTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerHospedajes(){
		pos = -1;
		lodgingTableModel = new LodgingTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(lodgingTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(5).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(230);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setResizable(false);
		try{
			listaHospedajes = lodgingServices.selectAllLodgings();
			for(LodgingDTO l : listaHospedajes){
				HotelDTO ho = hotelServices.findHotel(l.getHotelCode());
				RoomDTO r = roomServices.findRoom(l.getRoomCode());
				FoodPlanDTO f = foodPlanServices.findFoodPlan(r.getFoodPlanCode());
				SeasonDTO s = seasonServices.findSeason(l.getSeasonCode());
				String[] datos = {String.valueOf(l.getLodgingCode()), ho.getHotelName(), r.getRoomType(),
						f.getTypeOfFoodPlan(), s.getSeasonName(), String.valueOf(l.getLodgingPrice())};
				lodgingTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerModalidades(){
		pos = -1;
		transportModalityTableModel = new TransportModalityTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(transportModalityTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(580);
		table.getColumnModel().getColumn(1).setResizable(false);
		try{
			listaModalidades = transportModalityServices.selectAllTransportModality();
			for(TransportModalityDTO t : listaModalidades){
				String[] datos = {String.valueOf(t.getModalityCode()), t.getModalityType()};
				transportModalityTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerPaquetes(){
		pos = -1;
		touristPackageTableModel = new TouristPackageTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(touristPackageTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(2).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(3).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(4).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(5).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(6).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setResizable(false);
		try {
			listaPaquetes = touristPackageServices.selectAllTouristPackages();
			for(TouristPackageDTO t : listaPaquetes){
				String[] datos = {String.valueOf(t.getPackageCode()), t.getPromotionalName(), 
						String.valueOf(t.getNumberOfPeople()), String.valueOf(t.getNumberOfDays()), 
						String.valueOf(t.getNumberOfNights()), String.valueOf(t.getPackagePrice()), 
						String.valueOf(t.getPackageCost())};
				touristPackageTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerTransportes(){
		pos = -1;
		transportTableModel = new TransportTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(transportTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(260);
		table.getColumnModel().getColumn(3).setResizable(false);
		try {
			listaTransportes = transportServices.selectAllTransports();
			for(TransportDTO t : listaTransportes){
				VehicleDTO v = vehicleServices.findVehicle(t.getVehicleCode());
				TransportModalityDTO tm = transportModalityServices.findTransportModality(t.getModalityCode());
				String[] datos = {String.valueOf(t.getTransportCode()), v.getVehicleBrand(), tm.getModalityType(),
						t.getTransportBorrower()};
				transportTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerCadenasHoteleras(){
		pos = -1;
		hotelChainTableModel = new HotelChainTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(hotelChainTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(580);
		table.getColumnModel().getColumn(1).setResizable(false);
		try {
			listaCadenasHoteleras = hotelChainServices.selectAllHotelChains();
			for(HotelChainDTO h : listaCadenasHoteleras){
				String[] datos = {String.valueOf(h.getHotelChainCode()), h.getHotelChainName()};
				hotelChainTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerHabitacion(){
		pos = -1;
		roomTableModel = new RoomTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(roomTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(230);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		try{
			listaHabitaciones = roomServices.selectAllRooms();
			for(RoomDTO r : listaHabitaciones){
				FoodPlanDTO f = foodPlanServices.findFoodPlan(r.getFoodPlanCode());
				String[] datos = {String.valueOf(r.getRoomCode()), r.getRoomType(), 
						f.getTypeOfFoodPlan(), String.valueOf(r.getSurchargeRoom())};
				roomTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerHotel(){
		pos = -1;
		hotelTableModel = new HotelTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(hotelTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(220);
		table.getColumnModel().getColumn(4).setResizable(false);
		try{
			listaHoteles = hotelServices.selectAllHotels();
			for(HotelDTO h : listaHoteles){
				HotelChainDTO hc = hotelChainServices.findHotelChain(h.getHotelChainCode());
				ProvinceDTO pr = provinceServices.findProvince(h.getProvinceCode());
				String categ = "";
				if(h.getHotelCategory()==1)
					categ = "1 Estrella";
				else
					categ = h.getHotelCategory()+" Estrellas";
				String[] datos = {String.valueOf(h.getHotelCode()), h.getHotelName(), categ,
						hc.getHotelChainName(), pr.getProviceName()};
				hotelTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerLugares(){
		pos = -1;
		placeTableModel = new PlaceTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(placeTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(180);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		table.getColumnModel().getColumn(3).setResizable(false);
		try{
			listaLugares = placeServices.selectAllPlaces();
			for(PlaceDTO p : listaLugares){
				String[] datos = {String.valueOf(p.getPlaceCode()), p.getPlaceName(), p.getTypeOfService(), 
						String.valueOf(p.getCostPerPerson())};
				placeTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerRoles(){
		pos = -1;
		roleTableModel = new RoleTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(roleTableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(580);
		table.getColumnModel().getColumn(1).setResizable(false);
		try {
			listaRoles = roleServices.selectAllRoles();
			for(RoleDTO r : listaRoles){
				String[] datos = {r.getRoleName(), r.getRoleDescription()};
				roleTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerPlanesAlimenticios(){
		pos = -1;
		foodPlanTableModel = new FoodPlanTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(foodPlanTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(680);
		table.getColumnModel().getColumn(1).setResizable(false);
		try{
			listaPlanesAlimenticios = foodPlanServices.selectAllFoddPlans();
			for(FoodPlanDTO f : listaPlanesAlimenticios){
				String[] datos = {String.valueOf(f.getFoodPlanCode()), f.getTypeOfFoodPlan()};
				foodPlanTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerProvincias(){
		pos = -1;
		provinceTableModel = new ProvinceTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(provinceTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(680);
		table.getColumnModel().getColumn(1).setResizable(false);
		try{
			listaProvincias = provinceServices.selectAllProvinces();
			for(ProvinceDTO p : listaProvincias){
				String[] datos = {String.valueOf(p.getProvinceCode()), p.getProviceName()};
				provinceTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerTemporadas(){
		pos = -1;
		seasonTableModel = new SeasonTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(seasonTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(3).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(4).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setResizable(false);
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM");
			listaTemporadas = seasonServices.selectAllSeasons();
			for(SeasonDTO s : listaTemporadas){
				String[] datos = {String.valueOf(s.getSeasonCode()), s.getSeasonName(), s.getSeasonDescription(),
						format.format(s.getSeasonStartDate()), format.format(s.getSeasonEndDate())};
				seasonTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerUsuarios(){
		pos = -1;
		userTableModel = new UserTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(userTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(240);
		table.getColumnModel().getColumn(3).setResizable(false);
		try{
			listaUsuarios = userServices.selectAllUsers();
			for(UserDTO u : listaUsuarios){
				String rol = roleServices.findRole(u.getRoleCode()).getRoleName();
				String[] datos = {String.valueOf(u.getUserCode()), u.getUserName(), u.getUserNick(), rol};
				userTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ponerVehiculos(){
		pos = -1;
		vehicleTableModel = new VehicleTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(vehicleTableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(3).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(270);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setResizable(false);
		try{
			listaVehiculos = vehicleServices.selectAllVehicles();
			for(VehicleDTO v : listaVehiculos){
				String[] datos = {String.valueOf(v.getVehicleCode()), v.getVehiclePlate(), v.getVehicleBrand(),
						String.valueOf(v.getYearOfProduction())};
				vehicleTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void crearTabla(){
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos = table.getSelectedRow();
			}
			
		});
		table.setFocusable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(40);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setBackground(Color.WHITE);
		Alinear.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void accionAgregar(){
		if(esGestorAgencia){
			if(btnCadenas.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarCadenaHotelera panel = new AgregarCadenaHotelera(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnHabitaciones.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarHabitacion panel = new AgregarHabitacion(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnHoteles.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarHotel panel = new AgregarHotel(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnLugares.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarLugar panel = new AgregarLugar(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnProvincias.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarProvincia panel = new AgregarProvincia(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnUsuarios.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarUsuario panel = new AgregarUsuario(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnVehiculos.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarVehiculo panel = new AgregarVehiculo(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		}
		else{
			if(btnActividades.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarActividad panel = new AgregarActividad(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnContratos.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarContrato panel = new AgregarContrato(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnEventos.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarEvento panel = new AgregarEvento(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnHospedajes.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarHospedaje panel = new AgregarHospedaje(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnModalidades.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarModalidad panel = new AgregarModalidad(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnPaquetes.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarPaquete panel = new AgregarPaquete(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnTransportes.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarTransporte panel = new AgregarTransporte(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		}
		btnAgregar.setBackground(colorAzul);
	}

	private void accionEliminar(){
		btnEliminar.setBackground(colorAzul);
		padre.getPanelPrincipal().remove(este);
		padre.getPanelPrincipal().repaint();
		String mensaje = "";

		try{
			if(pos != -1){
				boolean eliminado = false;
				if(esGestorAgencia){
					if(btnCadenas.isBorderPainted()){
						mensaje = "esta cadena hotelera";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar esta cadena hotelera?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							hotelChainServices.deleteHotelChain(listaCadenasHoteleras.get(pos).getHotelChainCode());
							ponerCadenasHoteleras();
							mensaje = "La cadena hotelera fue eliminada con éxito";
						}
					}
					else if(btnHabitaciones.isBorderPainted()){
						mensaje = "esta habitaci�n";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar esta habitación?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							roomServices.deleteRoom(listaHabitaciones.get(pos).getRoomCode());
							ponerHabitacion();
							mensaje = "La habitaci�n fue eliminada con éxito";
						}
					}
					else if(btnHoteles.isBorderPainted()){
						mensaje = "este hotel";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este hotel?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							hotelServices.deleteHotel(listaHoteles.get(pos).getHotelCode());
							ponerHotel();
							mensaje = "El hotel fue eliminado con éxito";
						}
					}
					else if(btnLugares.isBorderPainted()){
						mensaje = "este lugar";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este lugar?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							int lugar = listaLugares.get(pos).getPlaceCode();
							int prov = provincePlaceServices.getProvinceCodeFromPlace(lugar);
							int prov_lug = provincePlaceServices.getCode(prov, lugar);
							provincePlaceServices.deleteProvincePlace(prov_lug);
							placeServices.deletePlace(lugar);
							ponerLugares();
							mensaje = "El lugar fue eliminado con éxito";
						}
					}
					else if(btnProvincias.isBorderPainted()){
						mensaje = "esta provincia";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar esta provincia?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							provinceServices.deleteProvince(listaProvincias.get(pos).getProvinceCode());
							ponerProvincias();
							mensaje = "La provincia fue eliminada con éxito";
						}
					}
					else if(btnUsuarios.isBorderPainted()){
						mensaje = "este usuario";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este usuario?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							userServices.deleteUser(listaUsuarios.get(pos).getUserCode());
							ponerUsuarios();
							mensaje = "El usuario fue eliminado con éxito";
						}
					}
					else if(btnVehiculos.isBorderPainted()){
						mensaje = "este veh�culo";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este vehículo?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							vehicleServices.deleteVehicle(listaVehiculos.get(pos).getVehicleCode());
							ponerVehiculos();
							mensaje = "El vehículo fue eliminado con éxito";
						}
					}
					if(eliminado){
						MensajeAviso ma = new MensajeAviso(null, padre, este, mensaje, MensajeAviso.CORRECTO);
						ma.setVisible(true);
					}
				}
				else{
					if(btnActividades.isBorderPainted()){
						mensaje = "esta actividad";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar esta actividad?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							activityServices.deleteActivity(listaActividades.get(pos).getActivityCode());
							ponerActividades();
							mensaje = "La actividad fue eliminada con éxito";
						}
					}
					else if(btnContratos.isBorderPainted()){
						mensaje = "este contrato";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este contrato?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							ContractDTO c = listaContratos.get(pos);
							int codigo = c.getContractCode();
							TouristPackageDTO tp = touristPackageServices.findTouristPackage(c.getPackageCode());
							String tipo = listaContratos.get(pos).getContractType();
							if(tipo.equals("Hotelero")){
								ContractLodgingDTO cl = contractLodgingServices.findContractLodging(codigo);
								LodgingDTO l = lodgingServices.findLodging(cl.getLodgingCode());
								contractLodgingServices.deleteContractLodging(codigo);
								touristPackageServices.updateTouristPackage(tp.getPackageCode(), tp.getPromotionalName(), 
										tp.getPackagePrice()-(l.getLodgingPrice()*tp.getNumberOfPeople()*tp.getNumberOfNights()), 
										tp.getPackageCost(), tp.getNumberOfPeople(), tp.getNumberOfDays(), tp.getNumberOfNights());
							}
							else if(tipo.equals("Transporte")){
								contractTransportServices.deleteContractTransport(codigo);
							}
							else if(tipo.equals("Servicios Complementarios")){
								ContractEventDTO ce = contractEventServices.findContractEvent(codigo);
								EventDTO ev = eventServices.findEvent(ce.getEventCode());
								ActivityDTO ac = activityServices.findActivity(ev.getActivityCode());
								PlaceDTO p = placeServices.findPlace(ev.getPlaceCode());
								contractEventServices.deleteContractEvent(codigo);
								touristPackageServices.updateTouristPackage(tp.getPackageCode(),
										tp.getPromotionalName(), 
										tp.getPackagePrice()-(ac.getActivityPrice()*tp.getNumberOfPeople()),
										tp.getPackageCost()-(p.getCostPerPerson()*tp.getNumberOfPeople()),
										tp.getNumberOfPeople(), tp.getNumberOfDays(), tp.getNumberOfNights());
							}
							contractServices.deleteContract(codigo);
							ponerContratos();
							mensaje = "El contrato fue eliminado con éxito";
						}
					}
					else if(btnEventos.isBorderPainted()){
						mensaje = "este evento";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este evento?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							eventServices.deleteEvent(listaEventos.get(pos).getEventCode());
							ponerEventos();
							mensaje = "El evento fue eliminado con éxito";
						}
					}
					else if(btnHospedajes.isBorderPainted()){
						mensaje = "este hospedaje";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este hospedaje?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							lodgingServices.deleteLodging(listaHospedajes.get(pos).getLodgingCode());
							ponerHospedajes();
							mensaje = "El hospedaje fue eliminado con éxito";
						}
					}
					else if(btnModalidades.isBorderPainted()){
						mensaje = "esta modalidad de transporte";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar esta modalidad de transporte?", MensajeAviso.INFORMACION);
						ma.agrandar(40);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							int codigo = listaModalidades.get(pos).getModalityCode();
							String tipo = listaModalidades.get(pos).getModalityType();
							if(tipo.equals("Costo por kilometraje"))
								costPerKilometerServices.deleteCostPerKilometer(codigo);
							else if(tipo.equals("Costo por horas y kilómetros"))
								costPerHourKilometerServices.deleteCostPerHourKilometer(codigo);
							else if(tipo.equals("Costo por recorridos establecidos"))
								costPerEstablishedToursServices.deleteCostPerEstablishedTours(codigo);
							transportModalityServices.deleteTransportModality(codigo);
							ponerModalidades();
							mensaje = "La modalidad de transporte fue eliminada con éxito";
						}
					}
					else if(btnPaquetes.isBorderPainted()){
						mensaje = "este paquete tur�stico";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este paquete turístico?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							touristPackageServices.deleteTouristPackage(listaPaquetes.get(pos).getPackageCode());
							ponerPaquetes();
							mensaje = "El paquete turístico fue eliminado con éxito";
						}
					}
					else if(btnTransportes.isBorderPainted()){
						mensaje = "este transporte";
						MensajeAviso ma = new MensajeAviso(null, padre, este, "¿Desea eliminar este transporte?", MensajeAviso.INFORMACION);
						ma.setVisible(true);
						eliminado = ma.getValor();
						if(eliminado){
							transportServices.deleteTransport(listaTransportes.get(pos).getTransportCode());
							ponerTransportes();
							mensaje = "El transporte fue eliminado con éxito";
						}
					}
					if(eliminado){
						MensajeAviso ma = new MensajeAviso(null, padre, este, mensaje, MensajeAviso.CORRECTO);
						ma.agrandar(50);
						ma.setVisible(true);
					}
				}
			}
			else{
				MensajeAviso ma = new MensajeAviso(null, padre, este, "No seleccionó ningún elemento para eliminar", MensajeAviso.ERROR);
				ma.setVisible(true);
			}
		}
		catch(SQLException | ClassNotFoundException e){
			MensajeAviso ma = new MensajeAviso(null, padre, este, "No fue posible eliminar "+mensaje, MensajeAviso.ERROR);
			ma.setVisible(true);
		}
	}

	private void accionVer(){
		btnVer.setBackground(colorAzul);
		padre.getPanelPrincipal().remove(este);
		padre.getPanelPrincipal().repaint();

		if(pos!=-1){
			if(esGestorAgencia){
				if(btnUsuarios.isBorderPainted()){
					VerUsuario panel = new VerUsuario(padre, este, listaUsuarios.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnVehiculos.isBorderPainted()){
					VerVehiculo panel = new VerVehiculo(padre, este, listaVehiculos.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnHabitaciones.isBorderPainted()){
					VerHabitacion panel = new VerHabitacion(padre, este, listaHabitaciones.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnLugares.isBorderPainted()){
					VerLugar panel = new VerLugar(padre, este, listaLugares.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnHoteles.isBorderPainted()){
					VerHotel panel = new VerHotel(padre, este, listaHoteles.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
			}
			else{
				if(btnActividades.isBorderPainted()){
					VerActividad panel = new VerActividad(padre, este, listaActividades.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnModalidades.isBorderPainted()){
					VerModalidad panel = new VerModalidad(padre, este, listaModalidades.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}

				else if(btnTransportes.isBorderPainted()){
					VerTransporte panel = new VerTransporte(padre, este, listaTransportes.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnHospedajes.isBorderPainted()){
					VerHospedaje panel = new VerHospedaje(padre, este,listaHospedajes.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnEventos.isBorderPainted()){
					VerEvento panel = new VerEvento(padre, este, listaEventos.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnContratos.isBorderPainted()){
					VerContrato panel = new VerContrato(padre, este, listaContratos.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnPaquetes.isBorderPainted()){
					VerPaquete panel = new VerPaquete(padre, este, listaPaquetes.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
			}
		}
		else{
			MensajeAviso ma = new MensajeAviso(null, padre, este, "No seleccionó ningún elemento para ver", MensajeAviso.ERROR);
			ma.setVisible(true);
		}
	}
	
	private void accionEditar(){
		btnEditar.setBackground(colorAzul);
		padre.getPanelPrincipal().remove(este);
		padre.getPanelPrincipal().repaint();

		if(pos!=-1){
			if(esGestorAgencia){
				if(btnCadenas.isBorderPainted()){
					EditarCadenaHotelera panel = new EditarCadenaHotelera(padre, este, listaCadenasHoteleras.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnHabitaciones.isBorderPainted()){
					EditarHabitacion panel = new EditarHabitacion(padre, este, listaHabitaciones.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnLugares.isBorderPainted()){
					EditarLugar panel = new EditarLugar(padre, este, listaLugares.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}	
				else if(btnHoteles.isBorderPainted()){
					EditarHotel panel = new EditarHotel(padre, este, listaHoteles.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}	
				else if(btnUsuarios.isBorderPainted()){
					EditarUsuario panel = new EditarUsuario(padre, este, listaUsuarios.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
			}
			else{
				if(btnModalidades.isBorderPainted()){
					EditarModalidad panel = new EditarModalidad(padre, este, listaModalidades.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnPaquetes.isBorderPainted()){
					EditarPaquete panel = new EditarPaquete(padre, este, listaPaquetes.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnHospedajes.isBorderPainted()){
					EditarHospedaje panel = new EditarHospedaje(padre, este, listaHospedajes.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnActividades.isBorderPainted()){
					EditarActividad panel = new EditarActividad(padre, este, listaActividades.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
				else if(btnContratos.isBorderPainted()){
					RenovarContrato panel = new RenovarContrato(padre, este, listaContratos.get(pos));
					padre.getPanelPrincipal().add(panel);
					padre.getPanelPrincipal().repaint();
				}
			}
		}
		else{
			String err = "No seleccionó ningún elemento para editar";
			if(btnContratos.isBorderPainted()) err = "No seleccionó ningún contrato para renovar";
			MensajeAviso ma = new MensajeAviso(null, padre, este, err, MensajeAviso.ERROR);
			ma.setVisible(true);
		}
	}
}
