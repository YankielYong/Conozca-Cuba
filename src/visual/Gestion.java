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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

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

public class Gestion extends MiJPanel{

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
	
	public Gestion(Principal p){
		este = this;
		padre = p;
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
		
		vistaGestorDeAgencia();
	}
	
	public void vistaGestorDeAgencia(){
		esGestorAgencia = true;
		scrollPane.setBounds(300, 20, 880, 580);
		btnVer.setBounds(300, 615, 205, 35);
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
				
				btnVer.setVisible(true);
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

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
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

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
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

				btnVer.setVisible(true);
				btnAgregar.setVisible(true);
				btnEliminar.setVisible(true);
				btnEditar.setVisible(true);
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
		
		btnVehiculos = new JButton("  Vehiculos");
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
				btnEditar.setVisible(true);
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
		activityTableModel = new ActivityTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(activityTableModel);
	}
	
	public void ponerContratos(){
		contractTableModel = new ContractTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(contractTableModel);
	}
	
	public void ponerEventos(){
		eventTableModel = new EventTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(eventTableModel);
	}
	
	public void ponerHospedajes(){
		lodgingTableModel = new LodgingTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(lodgingTableModel);
	}
	
	public void ponerModalidades(){
		transportModalityTableModel = new TransportModalityTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(transportModalityTableModel);
	}
	
	public void ponerPaquetes(){
		touristPackageTableModel = new TouristPackageTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(touristPackageTableModel);
	}
	
	public void ponerTransportes(){
		transportTableModel = new TransportTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(transportTableModel);
	}
	
	public void ponerCadenasHoteleras(){
		hotelChainTableModel = new HotelChainTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(hotelChainTableModel);
	}
	
	public void ponerHabitacion(){
		roomTableModel = new RoomTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(roomTableModel);
	}
	
	public void ponerHotel(){
		hotelTableModel = new HotelTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(hotelTableModel);
	}
	
	public void ponerLugares(){
		placeTableModel = new PlaceTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(placeTableModel);
	}
	
	public void ponerRoles(){
		roleTableModel = new RoleTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(roleTableModel);
	}
	
	public void ponerPlanesAlimenticios(){
		foodPlanTableModel = new FoodPlanTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(foodPlanTableModel);
	}
	
	public void ponerProvincias(){
		provinceTableModel = new ProvinceTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(provinceTableModel);
	}
	
	public void ponerTemporadas(){
		seasonTableModel = new SeasonTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(seasonTableModel);
	}
	
	public void ponerUsuarios(){
		userTableModel = new UserTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(userTableModel);
	}
	
	public void ponerVehiculos(){
		vehicleTableModel = new VehicleTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(vehicleTableModel);
	}
	
	private void crearTabla(){
		table = new JTable();
		table.setFocusable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(30);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setBackground(Color.WHITE);
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
				
			}
			else if(btnHoteles.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarHotel panel = new AgregarHotel(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnLugares.isBorderPainted()){
				
			}
			else if(btnRoles.isBorderPainted()){
				
			}
			else if(btnPlanAlimenticio.isBorderPainted()){
				
			}
			else if(btnProvincias.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarProvincia panel = new AgregarProvincia(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnTemporadas.isBorderPainted()){
				
			}
			else if(btnUsuarios.isBorderPainted()){
				padre.getPanelPrincipal().remove(este);
				AgregarUsuario panel = new AgregarUsuario(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
			else if(btnVehiculos.isBorderPainted()){
				
			}
		}
		else{
			if(btnActividades.isBorderPainted()){
				
			}
			else if(btnContratos.isBorderPainted()){
				
			}
			else if(btnEventos.isBorderPainted()){
				
			}
			else if(btnHospedajes.isBorderPainted()){
				
			}
			else if(btnModalidades.isBorderPainted()){
				
			}
			else if(btnPaquetes.isBorderPainted()){
				
			}
			else if(btnTransportes.isBorderPainted()){
				
			}
		}
		btnAgregar.setBackground(colorAzul);
	}
}
