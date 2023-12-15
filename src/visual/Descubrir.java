package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import services.ActivityServices;
import services.EventServices;
import services.HotelChainServices;
import services.HotelServices;
import services.PlaceServices;
import services.ProvinceServices;
import services.ServicesLocator;
import services.TouristPackageServices;
import services.TransportModalityServices;
import services.TransportServices;
import services.VehicleServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.ViewEventTableModel;
import utils.ViewHotelTableModel;
import utils.ViewTouristPackageTableModel;
import utils.ViewTransportTableModel;

import java.awt.Font;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import dto.ActivityDTO;
import dto.EventDTO;
import dto.HotelChainDTO;
import dto.HotelDTO;
import dto.PlaceDTO;
import dto.ProvinceDTO;
import dto.TouristPackageDTO;
import dto.TransportDTO;
import dto.TransportModalityDTO;
import dto.VehicleDTO;

import java.awt.Cursor;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;

public class Descubrir extends MiJPanel{
	
	private ActivityServices activityServices = ServicesLocator.getActivityServices();
	private EventServices eventServices = ServicesLocator.getEventServices();
	private HotelChainServices hotelChainServices = ServicesLocator.getHotelChainServices();
	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private PlaceServices placeServices = ServicesLocator.getPlaceServices();
	private ProvinceServices provinceServices = ServicesLocator.getProvinceServices();
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private TransportModalityServices transportModalityServices = ServicesLocator.getTransportModalityServices();
	private TransportServices transportServices = ServicesLocator.getTransportServices();
	private VehicleServices vehicleServices = ServicesLocator.getVehicleServices();
	
	private ArrayList<EventDTO> listaEventos;
	private ArrayList<HotelDTO> listaHoteles;
	private ArrayList<TouristPackageDTO> listaPaquetes;
	private ArrayList<TransportDTO> listaTransportes;

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	private DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
	private int pos = -1;

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;

	private JPanel este;
	private Principal padre;

	private JButton btnPaquetes;
	private JButton btnHoteles;
	private JButton btnEventos;
	private JButton btnTransportes;
	private JTextField txtBuscarNombre;
	private JTextField txtBuscarProvincia;
	private JTextField txtBuscarCadenaHotelera;
	
	private boolean nameChanged = false;
	private boolean provChanged = false;
	private boolean chainChanged = false;
	private JLabel lblBuscarN;
	private JLabel lblBuscarP;
	private JLabel lblBuscarC;
	
	private JScrollPane scrollPane;
	private JTable table;
	
	private ViewTouristPackageTableModel touristPackageTableModel;
	private ViewHotelTableModel hotelTableModel;
	private ViewEventTableModel eventTableModel;
	private ViewTransportTableModel transportTableModel;

	public Descubrir(Principal p){
		padre = p;
		este = this;
		setTipoPanel(Paneles.PANEL_DESCUBRIR);
		padre.setPanelAbierto(getTipoPanel());
		setBounds(pantalla.width/2-601, pantalla.height/2-366, 1202, 682);
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
		panelInferior.setBounds(1, 31, 1200, 650);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/paquete turistico.png"));
		image = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Icon iconPaquete = new ImageIcon(image);

		btnPaquetes = new JButton(" Paquetes Turísticos");
		btnPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				else if(btnEventos.isBorderPainted()){
					btnEventos.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(false);
				lblBuscarP.setVisible(false);
				txtBuscarCadenaHotelera.setVisible(false);
				lblBuscarC.setVisible(false);
				
				ponerPaquetes();
			}
		});
		btnPaquetes.setBounds(20, 10, 275, 50);
		btnPaquetes.setIcon(iconPaquete);
		btnPaquetes.setForeground(Color.black);
		btnPaquetes.setModel(new MyButtonModel());
		btnPaquetes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaquetes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnPaquetes.setFont(new Font("Arial", Font.BOLD, 22));
		btnPaquetes.setFocusable(false);
		btnPaquetes.setContentAreaFilled(false);
		btnPaquetes.setBorderPainted(true);
		panelInferior.add(btnPaquetes);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/hotel.png"));
		image = img.getImage().getScaledInstance(24, 30, Image.SCALE_SMOOTH);
		Icon iconHotel = new ImageIcon(image);

		btnHoteles = new JButton("  Hoteles");
		btnHoteles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				else if (btnEventos.isBorderPainted()) {
					btnEventos.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(true);
				lblBuscarP.setVisible(true);
				txtBuscarCadenaHotelera.setVisible(true);
				lblBuscarC.setVisible(true);
				
				ponerHoteles();
			}
		});
		btnHoteles.setBounds(315, 10, 275, 50);
		btnHoteles.setIcon(iconHotel);
		btnHoteles.setForeground(Color.black);
		btnHoteles.setModel(new MyButtonModel());
		btnHoteles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHoteles.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnHoteles.setFont(new Font("Arial", Font.BOLD, 22));
		btnHoteles.setFocusable(false);
		btnHoteles.setContentAreaFilled(false);
		btnHoteles.setBorderPainted(false);
		panelInferior.add(btnHoteles);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/evento.png"));
		image = img.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		Icon iconEvento = new ImageIcon(image);

		btnEventos = new JButton("  Eventos");
		btnEventos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				else if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(true);
				lblBuscarP.setVisible(true);
				txtBuscarCadenaHotelera.setVisible(false);
				lblBuscarC.setVisible(false);
				
				ponerEventos();
			}
		});
		btnEventos.setBounds(610, 10, 275, 50);
		btnEventos.setIcon(iconEvento);
		btnEventos.setForeground(Color.black);
		btnEventos.setModel(new MyButtonModel());
		btnEventos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventos.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnEventos.setFont(new Font("Arial", Font.BOLD, 22));
		btnEventos.setFocusable(false);
		btnEventos.setContentAreaFilled(false);
		btnEventos.setBorderPainted(false);
		panelInferior.add(btnEventos);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/taxi.png"));
		image = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Icon iconTransporte = new ImageIcon(image);
		
		btnTransportes = new JButton("  Transportes");
		btnTransportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				else if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				else if(btnEventos.isBorderPainted()){
					btnEventos.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(false);
				lblBuscarP.setVisible(false);
				txtBuscarCadenaHotelera.setVisible(false);
				lblBuscarC.setVisible(false);
				
				ponerTransportes();
			}
		});
		btnTransportes.setBounds(905, 10, 275, 50);
		btnTransportes.setIcon(iconTransporte);
		btnTransportes.setForeground(Color.black);
		btnTransportes.setModel(new MyButtonModel());
		btnTransportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransportes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnTransportes.setFont(new Font("Arial", Font.BOLD, 22));
		btnTransportes.setFocusable(false);
		btnTransportes.setContentAreaFilled(false);
		btnTransportes.setBorderPainted(false);
		panelInferior.add(btnTransportes);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/search.png"));
		image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconBuscar = new ImageIcon(image);
		
		txtBuscarNombre = new JTextField("Nombre");
		txtBuscarNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBuscarNombre.getText().equals("Nombre") && !nameChanged){
					txtBuscarNombre.setText("");
					nameChanged = true;
					txtBuscarNombre.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtBuscarNombre.getText().equals("")){
					txtBuscarNombre.setText("Nombre");
					nameChanged = false;
					txtBuscarNombre.setForeground(Color.gray);
				}
			}
		});
		txtBuscarNombre.setBounds(30, 80, 300, 30);
		txtBuscarNombre.setForeground(Color.gray);
		txtBuscarNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		panelInferior.add(txtBuscarNombre);

		lblBuscarN = new JLabel("");
		lblBuscarN.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarN.setIcon(iconBuscar);
		lblBuscarN.setBounds(330, 80, 50, 30);
		panelInferior.add(lblBuscarN);
		
		txtBuscarProvincia = new JTextField("Provincia");
		txtBuscarProvincia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBuscarProvincia.getText().equals("Provincia") && !provChanged){
					txtBuscarProvincia.setText("");
					provChanged = true;
					txtBuscarProvincia.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtBuscarProvincia.getText().equals("")){
					txtBuscarProvincia.setText("Provincia");
					provChanged = false;
					txtBuscarProvincia.setForeground(Color.gray);
				}
			}
		});
		txtBuscarProvincia.setBounds(420, 80, 300, 30);
		txtBuscarProvincia.setForeground(Color.gray);
		txtBuscarProvincia.setFont(new Font("Arial", Font.PLAIN, 15));
		panelInferior.add(txtBuscarProvincia);
		txtBuscarProvincia.setVisible(false);

		lblBuscarP = new JLabel("");
		lblBuscarP.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarP.setIcon(iconBuscar);
		lblBuscarP.setBounds(720, 80, 50, 30);
		panelInferior.add(lblBuscarP);
		lblBuscarP.setVisible(false);
		
		txtBuscarCadenaHotelera = new JTextField("Cadena Hotelera");
		txtBuscarCadenaHotelera.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBuscarCadenaHotelera.getText().equals("Cadena Hotelera") && !chainChanged){
					txtBuscarCadenaHotelera.setText("");
					chainChanged = true;
					txtBuscarCadenaHotelera.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtBuscarCadenaHotelera.getText().equals("")){
					txtBuscarCadenaHotelera.setText("Cadena Hotelera");
					chainChanged = false;
					txtBuscarCadenaHotelera.setForeground(Color.gray);
				}
			}
		});
		txtBuscarCadenaHotelera.setBounds(810, 80, 300, 30);
		txtBuscarCadenaHotelera.setForeground(Color.gray);
		txtBuscarCadenaHotelera.setFont(new Font("Arial", Font.PLAIN, 15));
		panelInferior.add(txtBuscarCadenaHotelera);
		txtBuscarCadenaHotelera.setVisible(false);
		
		lblBuscarC = new JLabel("");
		lblBuscarC.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarC.setIcon(iconBuscar);
		lblBuscarC.setBounds(1110, 80, 50, 30);
		panelInferior.add(lblBuscarC);
		lblBuscarC.setVisible(false);
		
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
		table.setForeground(Color.black);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setBackground(Color.WHITE);
		Alinear.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.white);
		scrollPane.setBounds(20, 130, 1160, 500);
		scrollPane.getViewport().setBackground(Color.white);
		panelInferior.add(scrollPane);
		
		ponerPaquetes();
	}
	
	private void ponerPaquetes(){
		pos = -1;
		touristPackageTableModel = new ViewTouristPackageTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(touristPackageTableModel);
		table.getColumnModel().getColumn(1).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(2).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(3).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(4).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(165);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(165);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(165);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(165);
		table.getColumnModel().getColumn(4).setResizable(false);
		try {
			listaPaquetes = touristPackageServices.selectAllTouristPackages();
			for(TouristPackageDTO t : listaPaquetes){
				String[] datos = {t.getPromotionalName(), 
						String.valueOf(t.getNumberOfPeople()), String.valueOf(t.getNumberOfDays()), 
						String.valueOf(t.getNumberOfNights()), String.valueOf(t.getPackagePrice())};
				touristPackageTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void ponerHoteles(){
		pos = -1;
		hotelTableModel = new ViewHotelTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(hotelTableModel);
		table.getColumnModel().getColumn(1).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setResizable(false);
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
				String[] datos = {h.getHotelName(), categ,
						hc.getHotelChainName(), pr.getProviceName()};
				hotelTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void ponerEventos(){
		pos = -1;
		eventTableModel = new ViewEventTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(eventTableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(760);
		table.getColumnModel().getColumn(1).setResizable(false);
		try{
			listaEventos = eventServices.selectAllEvents();
			for(EventDTO e : listaEventos){
				ActivityDTO act = activityServices.findActivity(e.getActivityCode());
				PlaceDTO lug = placeServices.findPlace(e.getPlaceCode());
				String[] datos = {lug.getPlaceName(), act.getActivityDescription()};
				eventTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void ponerTransportes(){
		pos = -1;
		transportTableModel = new ViewTransportTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(transportTableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(360);
		table.getColumnModel().getColumn(2).setResizable(false);
		try {
			listaTransportes = transportServices.selectAllTransports();
			for(TransportDTO t : listaTransportes){
				VehicleDTO v = vehicleServices.findVehicle(t.getVehicleCode());
				TransportModalityDTO tm = transportModalityServices.findTransportModality(t.getModalityCode());
				String[] datos = {v.getVehicleBrand(), tm.getModalityType(),
						t.getTransportBorrower()};
				transportTableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
