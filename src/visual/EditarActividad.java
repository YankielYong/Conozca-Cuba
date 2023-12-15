package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import dto.ContractEventDTO;
import dto.EventDTO;
import dto.TouristPackageDTO;
import services.ActivityServices;
import services.ContractEventServices;
import services.ContractServices;
import services.EventServices;
import services.ServicesLocator;
import services.TouristPackageServices;
import utils.ComboBoxModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

public class EditarActividad extends MiJPanel{

	private ActivityServices activityServices = ServicesLocator.getActivityServices();
	private ContractServices contractServices = ServicesLocator.getContractServices();
	private ContractEventServices contractEventServices = ServicesLocator.getContractEventServices();
	private EventServices eventServices = ServicesLocator.getEventServices();
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private ActivityDTO act;

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JComboBox<Integer> cbDia;
	private JComboBox<Integer> cbMes;
	private JComboBox<Integer> cbYear;
	private JTextField txtHora;
	private JTextField txtMin;
	private JComboBox<String> cbMeridiano;
	private JTextField txtPrecio;
	private JTextField txtDescripcion;
	private JButton btnEditar;
	private boolean priceChanged = true;
	private boolean descChanged = true;

	private Principal padre;
	private Gestion anterior;
	private EditarActividad este;

	@SuppressWarnings("deprecation")
	public EditarActividad(Principal p, Gestion a, ActivityDTO ac){
		este = this;
		padre = p;
		anterior = a;
		act = ac;
		setTipoPanel(Paneles.PANEL_EDITAR_ACTIVIDAD);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelEditarActividad(este);
		setBounds(pantalla.width/2-181, pantalla.height/2-246, 362, 442);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Editar Actividad");
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
		btnCerrar.setBounds(315, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 360, 410);
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
		logo.setBounds(68, 15, 220, 67);
		panelInferior.add(logo);

		JLabel dia = new JLabel("Día");
		dia.setHorizontalAlignment(SwingConstants.CENTER);
		dia.setHorizontalTextPosition(SwingConstants.CENTER);
		dia.setFont(new Font("Arial", Font.PLAIN, 14));
		dia.setBounds(50, 100, 50, 20);
		panelInferior.add(dia);

		cbDia = new JComboBox<Integer>();
		cbDia.setBounds(50, 120, 80, 30);
		cbDia.setBackground(Color.white);
		cbDia.setFocusable(false);
		cbDia.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDia.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbDia.setModel(ComboBoxModel.diasModel(31));
		cbDia.setUI(PropiedadesComboBox.createUI(getRootPane(), cbDia.getBounds()));
		((JLabel)cbDia.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbDia);

		JLabel mes = new JLabel("Mes");
		mes.setHorizontalAlignment(SwingConstants.CENTER);
		mes.setHorizontalTextPosition(SwingConstants.CENTER);
		mes.setFont(new Font("Arial", Font.PLAIN, 14));
		mes.setBounds(140, 100, 50, 20);
		panelInferior.add(mes);

		cbMes = new JComboBox<Integer>();
		cbMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlDiasMeses();
			}
		});
		cbMes.setBounds(140, 120, 80, 30);
		cbMes.setBackground(Color.white);
		cbMes.setFocusable(false);
		cbMes.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMes.setModel(ComboBoxModel.mesesModel());
		cbMes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMes.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMes.getBounds()));
		((JLabel)cbMes.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbMes);

		JLabel year = new JLabel("Año");
		year.setHorizontalAlignment(SwingConstants.CENTER);
		year.setHorizontalTextPosition(SwingConstants.CENTER);
		year.setFont(new Font("Arial", Font.PLAIN, 14));
		year.setBounds(230, 100, 50, 20);
		panelInferior.add(year);

		cbYear = new JComboBox<Integer>();
		cbYear.setBounds(230, 120, 80, 30);
		cbYear.setBackground(Color.white);
		cbYear.setFocusable(false);
		cbYear.setFont(new Font("Arial", Font.PLAIN, 16));
		cbYear.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbYear.setModel(ComboBoxModel.yearsModel());
		cbYear.setUI(PropiedadesComboBox.createUI(getRootPane(), cbYear.getBounds()));
		((JLabel)cbYear.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbYear);

		JLabel hora = new JLabel("Hora");
		hora.setHorizontalAlignment(SwingConstants.CENTER);
		hora.setHorizontalTextPosition(SwingConstants.CENTER);
		hora.setFont(new Font("Arial", Font.PLAIN, 14));
		hora.setBounds(84, 170, 50, 20);
		panelInferior.add(hora);

		txtHora = new JTextField(String.valueOf(act.getActivityDate().getHours()));
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.hora(e, txtHora.getText());
			}
		});
		txtHora.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHora.setForeground(Color.black);
		txtHora.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtHora.setBounds(80, 190, 60, 30);
		panelInferior.add(txtHora);

		JLabel pun = new JLabel(":");
		pun.setHorizontalAlignment(SwingConstants.CENTER);
		pun.setHorizontalTextPosition(SwingConstants.CENTER);
		pun.setFont(new Font("Arial", Font.PLAIN, 16));
		pun.setBounds(140, 190, 10, 30);
		panelInferior.add(pun);

		JLabel min = new JLabel("Minutos");
		min.setHorizontalAlignment(SwingConstants.CENTER);
		min.setHorizontalTextPosition(SwingConstants.CENTER);
		min.setFont(new Font("Arial", Font.PLAIN, 14));
		min.setBounds(155, 170, 50, 20);
		panelInferior.add(min);

		txtMin = new JTextField(String.valueOf(act.getActivityDate().getMinutes()));
		txtMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.minuto(e, txtMin.getText());
			}
		});
		txtMin.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMin.setForeground(Color.black);
		txtMin.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtMin.setBounds(150, 190, 60, 30);
		panelInferior.add(txtMin);

		cbMeridiano = new JComboBox<String>();
		cbMeridiano.setBounds(220, 190, 60, 30);
		cbMeridiano.setBackground(Color.white);
		cbMeridiano.setFocusable(false);
		cbMeridiano.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMeridiano.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMeridiano.setModel(ComboBoxModel.meridianosModel());
		cbMeridiano.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMeridiano.getBounds()));
		((JLabel)cbMeridiano.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbMeridiano);
		SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");

		txtPrecio = new JTextField(String.valueOf(act.getActivityPrice()));
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtPrecio.getText());
				priceChanged = true;
			}
		});
		txtPrecio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPrecio.getText().equals("Precio")){
					txtPrecio.setText("");
					txtPrecio.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtPrecio.getText().equals("")){
					txtPrecio.setText("Precio");
					txtPrecio.setForeground(Color.gray);
					priceChanged = false;
				}
				else{
					String ca = txtPrecio.getText();
					if(ca.charAt(ca.length()-1)=='.'){
						ca = ca.substring(0, ca.length()-1);
						txtPrecio.setText(ca);
					}
				}
			}
		});
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecio.setForeground(Color.black);
		txtPrecio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtPrecio.setBounds(50, 240, 260, 30);
		panelInferior.add(txtPrecio);

		txtDescripcion = new JTextField(act.getActivityDescription());
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
		txtDescripcion.setForeground(Color.black);
		txtDescripcion.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtDescripcion.setBounds(50, 290, 260, 30);
		panelInferior.add(txtDescripcion);

		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();

				try{
					int codigo = act.getActivityCode();
					String desc = "";
					String precio = "";
					if(priceChanged) precio = txtPrecio.getText();
					else throw new IllegalArgumentException("El campo del precio de la actividad está vacío");
					if(descChanged) desc = txtDescripcion.getText();
					double pre = Double.valueOf(precio);
					int dia = (int)cbDia.getSelectedItem();
					int mes = (int)cbMes.getSelectedItem();
					int year = (int)cbYear.getSelectedItem();
					int hora;
					int min;
					if(!txtHora.getText().isEmpty()) hora = Integer.valueOf(txtHora.getText());
					else throw new IllegalArgumentException("Debe introducir la hora de la actividad");
					if(!txtMin.getText().isEmpty()) min = Integer.valueOf(txtMin.getText());
					else throw new IllegalArgumentException("Debe introducir la hora de la actividad");
					String merid = (String)cbMeridiano.getSelectedItem();
					if(merid.equals("AM") && hora==12) hora=0;
					if(merid.equals("PM") && hora!=12) hora+=12;
					Date fecha = new Date(year-1900, mes-1, dia, hora, min);
					Validaciones.actividad(fecha, desc);
					activityServices.updateActivity(codigo, fecha, pre, desc);
					actualizar(activityServices.findActivity(codigo));
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "La actividad fue editada con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerActividades();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					ma.setVisible(true);
				}
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
		btnEditar.setBounds(50, 350, 260, 35);
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.black);
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);

		cbDia.setSelectedItem(act.getActivityDate().getDate());
		cbMes.setSelectedItem(act.getActivityDate().getMonth()+1);
		cbYear.setSelectedItem(act.getActivityDate().getYear()+1900);
		cbMeridiano.setSelectedItem(format2.format(act.getActivityDate()).substring(6));
		controlDiasMeses();
		cbDia.setSelectedItem(act.getActivityDate().getDate());
	}

	private void actualizar(ActivityDTO actN){
		try {
			double precioV = act.getActivityPrice();
			double precioN = actN.getActivityPrice();
			ArrayList<EventDTO> listaEventos = eventServices.selectAllEvents();
			ArrayList<ContractEventDTO> listaContEve = contractEventServices.selectAllContractEvents();
			for(EventDTO e : listaEventos){
				if(e.getActivityCode()==actN.getActivityCode()){
					for(ContractEventDTO ce : listaContEve){
						if(ce.getEventCode()==e.getEventCode()){
							int codPaquete = contractServices.findContract(ce.getContractCode()).getPackageCode();
							TouristPackageDTO tp = touristPackageServices.findTouristPackage(codPaquete);
							String nombre = tp.getPromotionalName();
							double costo = tp.getPackageCost();
							double precio = tp.getPackagePrice();
							int cantP = tp.getNumberOfPeople();
							int cantD = tp.getNumberOfDays();
							int cantN = tp.getNumberOfNights();
							touristPackageServices.updateTouristPackage(codPaquete, nombre, 
									precio-(precioV*cantP), costo, cantP, cantD, cantN);
							precio-=precioV*cantP;
							touristPackageServices.updateTouristPackage(codPaquete, nombre, 
									precio+(precioN*cantP), costo, cantP, cantD, cantN);
						}
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void controlDiasMeses(){
		int val = (int)cbDia.getSelectedItem();
		int mes = (int)cbMes.getSelectedItem();
		if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
			cbDia.setModel(ComboBoxModel.diasModel(31));
			cbDia.setSelectedItem(val);
		}
		else if(mes==2){
			cbDia.setModel(ComboBoxModel.diasModel(28));
			if(val<=28)
				cbDia.setSelectedItem(val);
		}
		else{
			cbDia.setModel(ComboBoxModel.diasModel(30));
			if(val<=30)
				cbDia.setSelectedItem(val);
		}
	}
}
