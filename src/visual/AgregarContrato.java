package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import dto.ActivityDTO;
import dto.ContractDTO;
import dto.ContractLodgingDTO;
import dto.EventDTO;
import dto.LodgingDTO;
import dto.PlaceDTO;
import dto.TouristPackageDTO;
import services.ActivityServices;
import services.ContractEventServices;
import services.ContractLodgingServices;
import services.ContractServices;
import services.ContractTransportServices;
import services.EventServices;
import services.LodgingServices;
import services.PlaceServices;
import services.ServicesLocator;
import services.TouristPackageServices;
import services.TransportServices;
import utils.ComboBoxModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

public class AgregarContrato extends MiJPanel{

	private ActivityServices activityServices = ServicesLocator.getActivityServices();
	private ContractEventServices contractEventServices = ServicesLocator.getContractEventServices();
	private ContractLodgingServices contractLodgingServices = ServicesLocator.getContractLodgingServices();
	private ContractServices contractServices = ServicesLocator.getContractServices();
	private ContractTransportServices contractTransportServices = ServicesLocator.getContractTransportServices();
	private EventServices eventServices = ServicesLocator.getEventServices();
	private LodgingServices lodgingServices = ServicesLocator.getLodgingServices();
	private PlaceServices placeServices = ServicesLocator.getPlaceServices();
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private TransportServices transportServices = ServicesLocator.getTransportServices();
	
	private ArrayList<ContractLodgingDTO> listaContratosHoteleros;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JComboBox<String> cbTipo;
	private JTextField txtDescripcion;
	private JComboBox<Integer> cbDiaInicio;
	private JComboBox<Integer> cbMesInicio;
	private JComboBox<Integer> cbYearInicio;
	private JComboBox<Integer> cbDiaConc;
	private JComboBox<Integer> cbMesConc;
	private JComboBox<Integer> cbYearConc;
	private JTextField txtPaquete;
	private JTextField txtEntidad;
	private JButton btnPaquete;
	private JButton btnEntidad;
	private JButton btnAgregar;
	private JLabel lblEntidad;
	private boolean descChanged = false;
	
	private Principal padre;
	private AgregarContrato este;
	private Gestion anterior;
	
	public AgregarContrato(Principal p, Gestion a) {
		anterior = a;
		este = this;
		padre = p;
		setTipoPanel(Paneles.PANEL_AGREGAR_CONTRATO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarContrato(este);
		setBounds(pantalla.width/2-201, pantalla.height/2-311, 402, 572);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 400, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Agregar Contrato");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 220, 30);
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
		btnCerrar.setBounds(355, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 400, 540);
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

		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(220, 67, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(88, 15, 220, 67);
		panelInferior.add(logo);
		
		cbTipo = new JComboBox<String>();
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((String)cbTipo.getSelectedItem()).equals("Hotelero")){
					hotelero();
				}
				else if(cbTipo.getItemAt(cbTipo.getSelectedIndex()).equals("Transporte")){
					transporte();
				}
				else if(cbTipo.getItemAt(cbTipo.getSelectedIndex()).equals("Servicios Complementarios")){
					servComp();
				}
			}
		});
		cbTipo.setBounds(50, 110, 300, 30);
		cbTipo.setBackground(Color.white);
		cbTipo.setFocusable(false);
		cbTipo.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbTipo.setModel(utils.ComboBoxModel.contratosModel());
		cbTipo.setUI(PropiedadesComboBox.createUI(getRootPane(), cbTipo.getBounds()));
		panelInferior.add(cbTipo);
		
		txtDescripcion = new JTextField("Descripción");
		txtDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumeros(e);
			}
		});
		txtDescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtDescripcion.getText().equals("Descripción") && !descChanged){
					txtDescripcion.setText("");
					descChanged = true;
					txtDescripcion.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtDescripcion.getText().equals("")){
					txtDescripcion.setText("Descripción");
					descChanged = false;
					txtDescripcion.setForeground(Color.gray);
				}
			}
		});
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescripcion.setForeground(Color.gray);
		txtDescripcion.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtDescripcion.setBounds(50, 160, 300, 30);
		panelInferior.add(txtDescripcion);
		
		JLabel fechaInicio = new JLabel("Fecha de inicio");
		fechaInicio.setBounds(50, 210, 150, 20);
		fechaInicio.setForeground(Color.black);
		fechaInicio.setFont(new Font("Arial", Font.PLAIN, 14));
		panelInferior.add(fechaInicio);
		
		cbDiaInicio = new JComboBox<Integer>();
		cbDiaInicio.setBounds(50, 240, 80, 30);
		cbDiaInicio.setBackground(Color.white);
		cbDiaInicio.setFocusable(false);
		cbDiaInicio.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDiaInicio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbDiaInicio.setModel(ComboBoxModel.diasModel(31));
		cbDiaInicio.setUI(PropiedadesComboBox.createUI(getRootPane(), cbDiaInicio.getBounds()));
		((JLabel)cbDiaInicio.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbDiaInicio);
		
		cbMesInicio = new JComboBox<Integer>();
		cbMesInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlDiasMeses(1);
			}
		});
		cbMesInicio.setBounds(160, 240, 80, 30);
		cbMesInicio.setBackground(Color.white);
		cbMesInicio.setFocusable(false);
		cbMesInicio.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMesInicio.setModel(ComboBoxModel.mesesModel());
		cbMesInicio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMesInicio.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMesInicio.getBounds()));
		((JLabel)cbMesInicio.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbMesInicio);
		
		cbYearInicio = new JComboBox<Integer>();
		cbYearInicio.setBounds(270, 240, 80, 30);
		cbYearInicio.setBackground(Color.white);
		cbYearInicio.setFocusable(false);
		cbYearInicio.setFont(new Font("Arial", Font.PLAIN, 16));
		cbYearInicio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbYearInicio.setModel(ComboBoxModel.yearsModel());
		cbYearInicio.setSelectedItem(2024);
		cbYearInicio.setUI(PropiedadesComboBox.createUI(getRootPane(), cbYearInicio.getBounds()));
		((JLabel)cbYearInicio.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbYearInicio);
		
		JLabel fechaConc = new JLabel("Fecha de conciliación");
		fechaConc.setBounds(50, 290, 150, 20);
		fechaConc.setForeground(Color.black);
		fechaConc.setFont(new Font("Arial", Font.PLAIN, 14));
		panelInferior.add(fechaConc);
		
		cbDiaConc = new JComboBox<Integer>();
		cbDiaConc.setBounds(50, 320, 80, 30);
		cbDiaConc.setBackground(Color.white);
		cbDiaConc.setFocusable(false);
		cbDiaConc.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDiaConc.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbDiaConc.setModel(ComboBoxModel.diasModel(31));
		cbDiaConc.setUI(PropiedadesComboBox.createUI(getRootPane(), cbDiaConc.getBounds()));
		((JLabel)cbDiaConc.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbDiaConc);
		
		cbMesConc = new JComboBox<Integer>();
		cbMesConc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlDiasMeses(3);
			}
		});
		cbMesConc.setBounds(160, 320, 80, 30);
		cbMesConc.setBackground(Color.white);
		cbMesConc.setFocusable(false);
		cbMesConc.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMesConc.setModel(ComboBoxModel.mesesModel());
		cbMesConc.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMesConc.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMesConc.getBounds()));
		((JLabel)cbMesConc.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbMesConc);
		
		cbYearConc = new JComboBox<Integer>();
		cbYearConc.setBounds(270, 320, 80, 30);
		cbYearConc.setBackground(Color.white);
		cbYearConc.setFocusable(false);
		cbYearConc.setFont(new Font("Arial", Font.PLAIN, 16));
		cbYearConc.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbYearConc.setModel(ComboBoxModel.yearsModel());
		cbYearConc.setSelectedItem(2024);
		cbYearConc.setUI(PropiedadesComboBox.createUI(getRootPane(), cbYearConc.getBounds()));
		((JLabel)cbYearConc.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbYearConc);
		
		JLabel lblPaquete = new JLabel("Código del Paquete:");
		lblPaquete.setBounds(50, 370, 150, 30);
		lblPaquete.setForeground(Color.black);
		lblPaquete.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(lblPaquete);
		
		txtPaquete = new JTextField();
		txtPaquete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtPaquete.setBounds(200, 370, 90, 30);
		txtPaquete.setForeground(Color.black);
		txtPaquete.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPaquete.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtPaquete);
		
		btnPaquete = new JButton("Ver");
		btnPaquete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPaquete.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarPaquetes panel = new ConsultarPaquetes(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnPaquete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPaquete.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPaquete.setBackground(colorAzul);
			}
		});
		btnPaquete.setBounds(300, 370, 50, 30);
		btnPaquete.setModel(new MyButtonModel());
		btnPaquete.setFont(new Font("Arial", Font.PLAIN, 15));
		btnPaquete.setMargin(new Insets(2, 5, 2, 5));
		btnPaquete.setBackground(colorAzul);
		btnPaquete.setForeground(Color.black);
		btnPaquete.setFocusable(false);
		btnPaquete.setBorderPainted(false);
		panelInferior.add(btnPaquete);
		
		lblEntidad = new JLabel("Código del Evento:");
		lblEntidad.setBounds(50, 420, 140, 30);
		lblEntidad.setForeground(Color.black);
		lblEntidad.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(lblEntidad);
		
		txtEntidad = new JTextField();
		txtEntidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtEntidad.setBounds(190, 420, 100, 30);
		txtEntidad.setForeground(Color.black);
		txtEntidad.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEntidad.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtEntidad);
		
		btnEntidad = new JButton("Ver");
		btnEntidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEntidad.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				if(((String)cbTipo.getSelectedItem()).equals("Hotelero")){
					ConsultarHospedajes panel = new ConsultarHospedajes(padre, este);
					padre.getPanelPrincipal().add(panel);
				}
				else if(((String)cbTipo.getSelectedItem()).equals("Transporte")){
					ConsultarTransportes panel = new ConsultarTransportes(padre, este);
					padre.getPanelPrincipal().add(panel);
				}
				else if(((String)cbTipo.getSelectedItem()).equals("Servicios Complementarios")){
					ConsultarEventos panel = new ConsultarEventos(padre, este);
					padre.getPanelPrincipal().add(panel);
				}
				padre.getPanelPrincipal().repaint();
			}
		});
		btnEntidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEntidad.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEntidad.setBackground(colorAzul);
			}
		});
		btnEntidad.setBounds(300, 420, 50, 30);
		btnEntidad.setModel(new MyButtonModel());
		btnEntidad.setFont(new Font("Arial", Font.PLAIN, 15));
		btnEntidad.setMargin(new Insets(2, 5, 2, 5));
		btnEntidad.setBackground(colorAzul);
		btnEntidad.setForeground(Color.black);
		btnEntidad.setFocusable(false);
		btnEntidad.setBorderPainted(false);
		panelInferior.add(btnEntidad);
		
		btnAgregar = new JButton("Agregar Contrato");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregar();
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
		btnAgregar.setBounds(50, 480, 300, 35);
		btnAgregar.setModel(new MyButtonModel());
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
		
		hotelero();
	}
	
	private void hotelero(){
		lblEntidad.setText("Código del Hospedaje:");
		lblEntidad.setBounds(50, 420, 165, 30);
		txtEntidad.setBounds(215, 420, 75, 30);
	}
	
	private void transporte(){
		lblEntidad.setText("Código del Transporte:");
		lblEntidad.setBounds(50, 420, 165, 30);
		txtEntidad.setBounds(215, 420, 75, 30);
	}
	
	private void servComp(){
		lblEntidad.setText("Código del Evento:");
		lblEntidad.setBounds(50, 420, 140, 30);
		txtEntidad.setBounds(190, 420, 100, 30);
	}
	
	@SuppressWarnings("deprecation")
	private void agregar(){
		padre.getPanelPrincipal().remove(este);
		padre.getPanelPrincipal().repaint();
		try{
			String tipo = (String)cbTipo.getSelectedItem();
			String desc = "";
			if(descChanged) desc = txtDescripcion.getText();
			int dIni = (int)cbDiaInicio.getSelectedItem();
			int mIni = (int)cbMesInicio.getSelectedItem();
			int yIni = (int)cbYearInicio.getSelectedItem();
			int dConc = (int)cbDiaConc.getSelectedItem();
			int mConc = (int)cbMesConc.getSelectedItem();
			int yConc = (int)cbYearConc.getSelectedItem();
			Date fechaInicio = new Date(yIni-1900, mIni-1, dIni);
			Date fechaFin = new Date(yIni-1899, mIni-1, dIni);
			Date fechaConc = new Date(yConc-1900, mConc-1, dConc);
			Validaciones.contrato(desc, fechaInicio, fechaFin, fechaConc);
			String paq = txtPaquete.getText();
			String ent = txtEntidad.getText();
			if(paq.isEmpty()) throw new IllegalArgumentException("El campo del paquete esta vacío");
			int paquete = Integer.valueOf(paq);
			TouristPackageDTO pt = null;
			try{pt=touristPackageServices.findTouristPackage(paquete);} 
			catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
				throw new IllegalArgumentException("No existe ningún paquete turístico con ese código");}
			
			
			if(tipo.equals("Hotelero")){
				if(ent.isEmpty()) throw new IllegalArgumentException("El campo del hospedaje esta vacío");
				int entidad = Integer.valueOf(ent);
				LodgingDTO l = null;
				try{l=lodgingServices.findLodging(entidad);} 
				catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
					throw new IllegalArgumentException("No existe ningún hospedaje con ese código");}
				listaContratosHoteleros = contractLodgingServices.selectAllContractLodging();
				boolean parar = false;
				for(int i=0; i<listaContratosHoteleros.size() && !parar; i++){
					ContractDTO c = contractServices.findContract(listaContratosHoteleros.get(i).getContractCode());
					if(c.getPackageCode() == paquete)
						parar = true;
				}
				if(parar) throw new IllegalArgumentException("Este paquete turístico ya tiene un contrato hotelero");
				contractServices.insertContract(desc, fechaInicio, fechaFin, fechaConc, tipo, paquete);
				int codC = contractServices.getLastContractCode();
				contractLodgingServices.insertContractLodging(codC, entidad);
				touristPackageServices.updateTouristPackage(pt.getPackageCode(), pt.getPromotionalName(), 
						pt.getPackagePrice()+(l.getLodgingPrice()*pt.getNumberOfPeople()*pt.getNumberOfNights()), 
						pt.getPackageCost(), pt.getNumberOfPeople(), pt.getNumberOfDays(), pt.getNumberOfNights());
			}
			
			
			
			else if(tipo.equals("Transporte")){
				if(ent.isEmpty()) throw new IllegalArgumentException("El campo del transporte esta vacío");
				int entidad = Integer.valueOf(ent);
				try{transportServices.findTransport(entidad);} 
				catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
					throw new IllegalArgumentException("No existe ningún transporte con ese código");}
				contractServices.insertContract(desc, fechaInicio, fechaFin, fechaConc, tipo, paquete);
				int codC = contractServices.getLastContractCode();
				contractTransportServices.insertContractTransport(codC, entidad);
			}
			
			
			
			else if(tipo.equals("Servicios Complementarios")){
				if(ent.isEmpty()) throw new IllegalArgumentException("El campo del evento esta vacío");
				int entidad = Integer.valueOf(ent);
				EventDTO ev = null;
				try{ev=eventServices.findEvent(entidad);} 
				catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
					throw new IllegalArgumentException("No existe ningún evento con ese código");}
				contractServices.insertContract(desc, fechaInicio, fechaFin, fechaConc, tipo, paquete);
				int codC = contractServices.getLastContractCode();
				contractEventServices.insertContractEvent(codC, entidad);
				ActivityDTO act = activityServices.findActivity(ev.getActivityCode());
				PlaceDTO pl = placeServices.findPlace(ev.getPlaceCode());
				touristPackageServices.updateTouristPackage(pt.getPackageCode(), pt.getPromotionalName(), 
						pt.getPackagePrice()+(act.getActivityPrice()*pt.getNumberOfPeople()), 
						pt.getPackageCost()+(pl.getCostPerPerson()*pt.getNumberOfPeople()), 
						pt.getNumberOfPeople(), pt.getNumberOfDays(), pt.getNumberOfNights());
			}
			
			
			MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El contrato fue registrado con éxito", MensajeAviso.CORRECTO);
			ma.setVisible(true);
			anterior.ponerContratos();
		}
		catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
			MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
			if(e1.getMessage().equals("No existe ningún paquete turístico con ese código"))
				ma.agrandar(40);
			if(e1.getMessage().equals("La fecha de inicio no puede ser antes de la fecha de conciliación"))
				ma.agrandar(150);
			if(e1.getMessage().equals("Este paquete turístico ya tiene un contrato hotelero"))
				ma.agrandar(80);
			ma.setVisible(true);
		}
	}
	
	private void controlDiasMeses(int fecha){
		if(fecha==1){
			int val = (int)cbDiaInicio.getSelectedItem();
			int mes = (int)cbMesInicio.getSelectedItem();
			if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
				cbDiaInicio.setModel(ComboBoxModel.diasModel(31));
				cbDiaInicio.setSelectedItem(val);
			}
			else if(mes==2){
				cbDiaInicio.setModel(ComboBoxModel.diasModel(28));
				if(val<=28)
					cbDiaInicio.setSelectedItem(val);
			}
			else{
				cbDiaInicio.setModel(ComboBoxModel.diasModel(30));
				if(val<=30)
					cbDiaInicio.setSelectedItem(val);
			}
		}
		else if(fecha==3){
			int val = (int)cbDiaConc.getSelectedItem();
			int mes = (int)cbMesConc.getSelectedItem();
			if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
				cbDiaConc.setModel(ComboBoxModel.diasModel(31));
				cbDiaConc.setSelectedItem(val);
			}
			else if(mes==2){
				cbDiaConc.setModel(ComboBoxModel.diasModel(28));
				if(val<=28)
					cbDiaConc.setSelectedItem(val);
			}
			else{
				cbDiaConc.setModel(ComboBoxModel.diasModel(30));
				if(val<=30)
					cbDiaConc.setSelectedItem(val);
			}
		}
	}
	
	public void setPaquete(int code){
		txtPaquete.setText(String.valueOf(code));
	}
	
	public void setEntidad(int code){
		txtEntidad.setText(String.valueOf(code));
	}
	
}
