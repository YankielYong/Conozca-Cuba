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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.CostPerEstablishedToursDTO;
import dto.CostPerHourKilometerDTO;
import dto.CostPerKilometerDTO;
import dto.TransportModalityDTO;
import services.CostPerEstablishedToursServices;
import services.CostPerHourKilometerServices;
import services.CostPerKilometerServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

public class EditarModalidad extends MiJPanel{

	private CostPerEstablishedToursServices costPerEstablishedToursServices = ServicesLocator.getCostPerEstablishedToursServices();
	private CostPerHourKilometerServices costPerHourKilometerServices = ServicesLocator.getCostPerHourKilometerServices();
	private CostPerKilometerServices costPerKilometerServices = ServicesLocator.getCostPerKilometerServices();

	private TransportModalityDTO mod;
	private String type;
	private CostPerHourKilometerDTO h;
	private CostPerEstablishedToursDTO t;
	private CostPerKilometerDTO k;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	
	private JTextField txtCKm = null;
	private boolean ckmC = true;
	private JTextField txtCKmIV = null;
	private boolean ckmivC = true;
	private JTextField txtCHrEspera = null;
	private boolean chresperaC = true;
	private JTextField txtCKmRec = null;
	private boolean ckmrecC = true;
	private JTextField txtCHr = null;
	private boolean chrC = true;
	private JTextField txtCKmExtra = null;
	private boolean ckmextraC = true;
	private JTextField txtCHrExtra = null;
	private boolean chrExtraC = true;
	private JTextField txtDescR = null;
	private boolean descrC = true;
	private JTextField txtCostoR = null;
	private boolean costorC = true;
	private JTextField txtCostoIVR = null;
	private boolean costoivrC = true;
	private JButton btnEditar;
	
	private Principal padre;
	private Gestion anterior;
	private EditarModalidad este;

	public EditarModalidad(Principal p, Gestion a, TransportModalityDTO tm){
		este = this;
		padre = p;
		anterior = a;
		mod = tm;
		type = mod.getModalityType();
		setTipoPanel(Paneles.PANEL_EDITAR_MODALIDAD);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelEditarModalidad(este);
		setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 412);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 380, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Editar Modalidad de Transporte");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 300, 30);
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
		btnCerrar.setBounds(335, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 380, 380);
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
		logo.setBounds(78, 15, 220, 67);
		panelInferior.add(logo);
		
		JLabel tip = new JLabel(type);
		tip.setBounds(50, 110, 420, 30);
		tip.setForeground(Color.black);
		tip.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(tip);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					if(type.equals("Costo por kilometraje")){
						String costoKm = "";
						String costoKmIV = "";
						String costoHrEsp = "";
						if(ckmC) costoKm = txtCKm.getText();
						else throw new IllegalArgumentException("El campo del costo por kilómetro está vacío");
						if(ckmivC) costoKmIV = txtCKmIV.getText();
						else throw new IllegalArgumentException("El campo del costo por kilómetro ida y vuelta está vacío");
						if(chresperaC) costoHrEsp = txtCHrEspera.getText();
						else throw new IllegalArgumentException("El campo del costo por horas de espera está vacío");
						Validaciones.costoKilometraje(costoKm, costoKmIV, costoHrEsp);
						double coskm = Double.valueOf(costoKm);
						double coskmiv = Double.valueOf(costoKmIV);
						double coshr = Double.valueOf(costoHrEsp);
						costPerKilometerServices.updateCostPerKilometer(mod.getModalityCode(), coskm, coskmiv, coshr);
					}
					else if(type.equals("Costo por horas y kilómetros")){
						String costkmrec = "";
						String costohr = "";
						String costokmex = "";
						String costohrex = "";
						if(ckmrecC) costkmrec = txtCKmRec.getText();
						else throw new IllegalArgumentException("El campo del costo por kilómetro recorrido está vacío");
						if(chrC) costohr = txtCHr.getText();
						else throw new IllegalArgumentException("El campo del costo por hora está vacío");
						if(ckmextraC) costokmex = txtCKmExtra.getText();
						else throw new IllegalArgumentException("El campo del costo por kilómetros extras está vacío");
						if(chrExtraC) costohrex = txtCHrExtra.getText();
						else throw new IllegalArgumentException("El campo del costo por horas extras está vacío");
						Validaciones.costoHorasKilometro(costkmrec, costohr, costokmex, costohrex);
						double coskmr = Double.valueOf(costkmrec);
						double coshr = Double.valueOf(costohr);
						double coskmex = Double.valueOf(costokmex);
						double coshrex = Double.valueOf(costohrex);
						costPerHourKilometerServices.updateCostPerHourKilometer(mod.getModalityCode(), coskmr, coshr, coskmex, coshrex);
					}
					else if(type.equals("Costo por recorridos establecidos")){
						String des = "";
						String costoR = "";
						String costoIV = "";
						if(descrC) des = txtDescR.getText();
						else throw new IllegalArgumentException("El campo de la descripción del recorrido está vacío");
						if(costorC) costoR = txtCostoR.getText();
						else throw new IllegalArgumentException("El campo del costo por recorrido está vacío");
						if(costoivrC) costoIV = txtCostoIVR.getText();
						else throw new IllegalArgumentException("El campo del costo por recorrido ida y vuelta está vacío");
						Validaciones.costoRecorrido(des, costoR, costoIV);
						double costoRecorrido = Double.valueOf(costoR);
						double costoRecorridoIV = Double.valueOf(costoIV);
						costPerEstablishedToursServices.updateCostPerEstablishedTours(mod.getModalityCode(), des, costoRecorrido, costoRecorridoIV);
					}
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "La modalidad de transporte fue editada con �xito", MensajeAviso.CORRECTO);
					ma.agrandar(45);
					ma.setVisible(true);
					anterior.ponerModalidades();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					if(e1.getMessage().equals("El campo del costo por kilómetro ida y vuelta está vacío"))
						ma.agrandar(80);
					if(e1.getMessage().equals("El campo del costo por horas de espera está vacío"))
						ma.agrandar(50);
					if(e1.getMessage().equals("El campo del costo por kilómetro recorrido está vacío"))
						ma.agrandar(70);
					if(e1.getMessage().equals("El campo del costo por kilómetros extras está vacío"))
						ma.agrandar(60);
					if(e1.getMessage().equals("El campo del costo por horas extras está vacío"))
						ma.agrandar(35);
					if(e1.getMessage().equals("El campo de la descripción del recorrido está vacío"))
						ma.agrandar(50);
					if(e1.getMessage().equals("El campo del costo por recorrido ida y vuelta está vacío"))
						ma.agrandar(80);
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
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.black);
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);
		
		if(type.equals("Costo por kilometraje"))
			costoPorKilometraje();
		else if(type.equals("Costo por horas y kilómetros"))
			costoHorasRecorridas();
		else{
			costoPorRecorrido();
		}
	}
	
	private void costoPorKilometraje(){
		try {
			k=costPerKilometerServices.findCostPerKilometer(mod.getModalityCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		txtCKm = new JTextField(String.valueOf(k.getCostPerKm()));
		txtCKm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCKm.getText());
				ckmC = true;
			}
		});
		txtCKm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCKm.getText().equals("Costo Por Kilómetro")){
					txtCKm.setText("");
					txtCKm.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCKm!=null){
					if(txtCKm.getText().equals("")){
						txtCKm.setText("Costo Por Kilómetro");
						txtCKm.setForeground(Color.gray);
						ckmC = false;
					}
					else{
						String ca = txtCKm.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCKm.setText(ca);
						}
					}
				}
			}
		});
		txtCKm.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCKm.setForeground(Color.black);
		txtCKm.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCKm.setBounds(50, 160, 280, 30);
		panelInferior.add(txtCKm);
		
		txtCKmIV = new JTextField(String.valueOf(k.getCostPerKmRoundTrip()));
		txtCKmIV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCKmIV.getText());
				ckmivC = true;
			}
		});
		txtCKmIV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCKmIV.getText().equals("Costo Por Kilómetro Ida Y Vuelta")){
					txtCKmIV.setText("");
					txtCKmIV.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCKmIV!=null){
					if(txtCKmIV.getText().equals("")){
						txtCKmIV.setText("Costo Por Kilómetro Ida Y Vuelta");
						txtCKmIV.setForeground(Color.gray);
						ckmivC = false;
					}
					else{
						String ca = txtCKmIV.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCKmIV.setText(ca);
						}
					}
				}
			}
		});
		txtCKmIV.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCKmIV.setForeground(Color.black);
		txtCKmIV.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCKmIV.setBounds(50, 210, 280, 30);
		panelInferior.add(txtCKmIV);
		
		txtCHrEspera = new JTextField(String.valueOf(k.getCostPerWaitingHours()));
		txtCHrEspera.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCHrEspera.getText());
				chresperaC = true;
			}
		});
		txtCHrEspera.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCHrEspera.getText().equals("Costo Por Horas De Espera")){
					txtCHrEspera.setText("");
					txtCHrEspera.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCHrEspera!=null){
					if(txtCHrEspera.getText().equals("")){
						txtCHrEspera.setText("Costo Por Horas De Espera");
						txtCHrEspera.setForeground(Color.gray);
						chresperaC = false;
					}
					else{
						String ca = txtCHrEspera.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCHrEspera.setText(ca);
						}
					}
				}
			}
		});
		txtCHrEspera.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCHrEspera.setForeground(Color.black);
		txtCHrEspera.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCHrEspera.setBounds(50, 260, 280, 30);
		panelInferior.add(txtCHrEspera);

		btnEditar.setBounds(50, 320, 280, 35);
		panelInferior.setBounds(1, 31, 380, 380);
		este.setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 412);
	}
	
	private void costoHorasRecorridas(){
		try {
			h=costPerHourKilometerServices.findCostPerHourKilometer(mod.getModalityCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		txtCKmRec = new JTextField(String.valueOf(h.getCostPerKmTraveled()));
		txtCKmRec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCKmRec.getText());
				ckmrecC = true;
			}
		});
		txtCKmRec.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCKmRec.getText().equals("Costo Por Kilómetro Recorrido")){
					txtCKmRec.setText("");
					txtCKmRec.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCKmRec!=null){
					if(txtCKmRec.getText().equals("")){
						txtCKmRec.setText("Costo Por Kilómetro Recorrido");
						txtCKmRec.setForeground(Color.gray);
						ckmrecC = false;
					}
					else{
						String ca = txtCKmRec.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCKmRec.setText(ca);
						}
					}
				}
			}
		});
		txtCKmRec.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCKmRec.setForeground(Color.black);
		txtCKmRec.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCKmRec.setBounds(50, 160, 280, 30);
		panelInferior.add(txtCKmRec);
		
		txtCHr = new JTextField(String.valueOf(h.getCostPerHour()));
		txtCHr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCHr.getText());
				chrC = true;
			}
		});
		txtCHr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCHr.getText().equals("Costo Por Horas")){
					txtCHr.setText("");
					txtCHr.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCHr!=null){
					if(txtCHr.getText().equals("")){
						txtCHr.setText("Costo Por Horas");
						txtCHr.setForeground(Color.gray);
						chrC = false;
					}
					else{
						String ca = txtCHr.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCHr.setText(ca);
						}
					}
				}
			}
		});
		txtCHr.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCHr.setForeground(Color.black);
		txtCHr.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCHr.setBounds(50, 210, 280, 30);
		panelInferior.add(txtCHr);
		
		txtCKmExtra = new JTextField(String.valueOf(h.getCostForExtraKm()));
		txtCKmExtra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCKmExtra.getText());
				ckmextraC = true;
			}
		});
		txtCKmExtra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCKmExtra.getText().equals("Costo Por Kilómetros Extras")){
					txtCKmExtra.setText("");
					txtCKmExtra.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCKmExtra!=null){
					if(txtCKmExtra.getText().equals("")){
						txtCKmExtra.setText("Costo Por Kilómetros Extras");
						txtCKmExtra.setForeground(Color.gray);
						ckmextraC = false;
					}
					else{
						String ca = txtCKmExtra.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCKmExtra.setText(ca);
						}
					}
				}
			}
		});
		txtCKmExtra.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCKmExtra.setForeground(Color.black);
		txtCKmExtra.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCKmExtra.setBounds(50, 260, 280, 30);
		panelInferior.add(txtCKmExtra);
		
		txtCHrExtra = new JTextField(String.valueOf(h.getCostForExtraHours()));
		txtCHrExtra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCHrExtra.getText());
				chrExtraC = true;
			}
		});
		txtCHrExtra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCHrExtra.getText().equals("Costo Por Horas Extras")){
					txtCHrExtra.setText("");
					txtCHrExtra.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCHrExtra!=null){
					if(txtCHrExtra.getText().equals("")){
						txtCHrExtra.setText("Costo Por Horas Extras");
						txtCHrExtra.setForeground(Color.gray);
						chrExtraC = false;
					}
					else{
						String ca = txtCHrExtra.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCHrExtra.setText(ca);
						}
					}
				}
			}
		});
		txtCHrExtra.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCHrExtra.setForeground(Color.black);
		txtCHrExtra.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCHrExtra.setBounds(50, 310, 280, 30);
		panelInferior.add(txtCHrExtra);

		btnEditar.setBounds(50, 370, 280, 35);
		panelInferior.setBounds(1, 31, 380, 430);
		este.setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 462);
	}
	
	private void costoPorRecorrido(){
		try {
			t=costPerEstablishedToursServices.findCostPerEstablishedTours(mod.getModalityCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		txtDescR = new JTextField(t.getTourDescription());
		txtDescR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumerosSignosEspacio(e);
				descrC = true;
			}
		});
		txtDescR.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtDescR.getText().equals("Descripción Del Recorrido")){
					txtDescR.setText("");
					txtDescR.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtDescR!=null){
					if(txtDescR.getText().equals("")){
						txtDescR.setText("Descripción Del Recorrido");
						txtDescR.setForeground(Color.gray);
						descrC = false;
					}
				}
			}
		});
		txtDescR.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescR.setForeground(Color.black);
		txtDescR.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtDescR.setBounds(50, 160, 280, 30);
		panelInferior.add(txtDescR);
		
		txtCostoR = new JTextField(String.valueOf(t.getCostPerTour()));
		txtCostoR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCostoR.getText());
				costorC = true;
			}
		});
		txtCostoR.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCostoR.getText().equals("Costo Por Recorrido")){
					txtCostoR.setText("");
					txtCostoR.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCostoR!=null){
					if(txtCostoR.getText().equals("")){
						txtCostoR.setText("Costo Por Recorrido");
						txtCostoR.setForeground(Color.gray);
						costorC = false;
					}
					else{
						String ca = txtCostoR.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCostoR.setText(ca);
						}
					}
				}
			}
		});
		txtCostoR.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCostoR.setForeground(Color.black);
		txtCostoR.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCostoR.setBounds(50, 210, 280, 30);
		panelInferior.add(txtCostoR);
		
		txtCostoIVR = new JTextField(String.valueOf(t.getCostPerTourRoundTrip()));
		txtCostoIVR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCostoIVR.getText());
				costoivrC = true;
			}
		});
		txtCostoIVR.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCostoIVR.getText().equals("Costo Por Ida Y Vuelta")){
					txtCostoIVR.setText("");
					txtCostoIVR.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCostoIVR!=null){
					if(txtCostoIVR.getText().equals("")){
						txtCostoIVR.setText("Costo Por Ida Y Vuelta");
						txtCostoIVR.setForeground(Color.gray);
						costoivrC = true;
					}
					else{
						String ca = txtCostoIVR.getText();
						if(ca.charAt(ca.length()-1)=='.'){
							ca = ca.substring(0, ca.length()-1);
							txtCostoIVR.setText(ca);
						}
					}
				}
			}
		});
		txtCostoIVR.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCostoIVR.setForeground(Color.black);
		txtCostoIVR.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCostoIVR.setBounds(50, 260, 280, 30);
		panelInferior.add(txtCostoIVR);

		btnEditar.setBounds(50, 320, 280, 35);
		panelInferior.setBounds(1, 31, 380, 380);
		este.setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 412);
	}
}
