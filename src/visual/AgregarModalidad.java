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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import services.CostPerEstablishedToursServices;
import services.CostPerHourKilometerServices;
import services.CostPerKilometerServices;
import services.ServicesLocator;
import services.TransportModalityServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

public class AgregarModalidad extends MiJPanel{

	private TransportModalityServices transportModalityServices = ServicesLocator.getTransportModalityServices();
	private CostPerEstablishedToursServices costPerEstablishedToursServices = ServicesLocator.getCostPerEstablishedToursServices();
	private CostPerHourKilometerServices costPerHourKilometerServices = ServicesLocator.getCostPerHourKilometerServices();
	private CostPerKilometerServices costPerKilometerServices = ServicesLocator.getCostPerKilometerServices();

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JComboBox<String> cbTipo;
	private JTextField txtCKm = null;
	private JTextField txtCKmIV = null;
	private JTextField txtCHrEspera = null;
	private JTextField txtCKmRec = null;
	private JTextField txtCHr = null;
	private JTextField txtCKmExtra = null;
	private JTextField txtCHrExtra = null;
	private JTextField txtDescR = null;
	private JTextField txtCostoR = null;
	private JTextField txtCostoIVR = null;
	private JButton btnAgregar;

	private Principal padre;
	private Gestion anterior;
	private AgregarModalidad este;

	public AgregarModalidad(Principal p, Gestion a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_MODALIDAD);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarModalidad(este);
		setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 412);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 380, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Agregar Modalidad de Transporte");
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

		cbTipo = new JComboBox<String>();
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((String)cbTipo.getSelectedItem()).equals("Costo por kilometraje")){
					costoPorKilometraje();
				}
				else if(cbTipo.getItemAt(cbTipo.getSelectedIndex()).equals("Costo por horas y kilómetros")){
					costoPorHorasKilometros();
				}
				else if(cbTipo.getItemAt(cbTipo.getSelectedIndex()).equals("Costo por recorridos establecidos")){
					costoPorRecorridosEstablecidos();
				}
			}
		});
		cbTipo.setBounds(50, 110, 280, 30);
		cbTipo.setBackground(Color.white);
		cbTipo.setFocusable(false);
		cbTipo.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbTipo.setModel(utils.ComboBoxModel.modalidadesModel());
		cbTipo.setUI(PropiedadesComboBox.createUI(getRootPane(), cbTipo.getBounds()));
		panelInferior.add(cbTipo);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					
				} catch(IllegalArgumentException e1){
					
				}
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
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);

		costoPorKilometraje();
	}

	private void costoPorKilometraje(){
		boolean yaEsta = false;
		if(txtCKmRec != null){
			panelInferior.remove(txtCKmRec);
			panelInferior.remove(txtCHr);
			panelInferior.remove(txtCKmExtra);
			panelInferior.remove(txtCHrExtra);
			txtCKmRec = null;
			txtCHr = null;
			txtCKmExtra = null;
			txtCHrExtra = null;
		}
		else if(txtDescR != null){
			panelInferior.remove(txtDescR);
			panelInferior.remove(txtCostoR);
			panelInferior.remove(txtCostoIVR);
			txtDescR = null;
			txtCostoR = null;
			txtCostoIVR = null;
		}
		else if(txtCKm != null) yaEsta=true;

		if(!yaEsta){
			txtCKm = new JTextField("Costo Por Kilómetro");
			txtCKm.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCKm.getText());
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
			txtCKm.setForeground(Color.gray);
			txtCKm.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCKm.setBounds(50, 160, 280, 30);
			panelInferior.add(txtCKm);

			txtCKmIV = new JTextField("Costo Por Kilómetro Ida Y Vuelta");
			txtCKmIV.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCKmIV.getText());
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
			txtCKmIV.setForeground(Color.gray);
			txtCKmIV.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCKmIV.setBounds(50, 210, 280, 30);
			panelInferior.add(txtCKmIV);

			txtCHrEspera = new JTextField("Costo Por Horas De Espera");
			txtCHrEspera.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCHrEspera.getText());
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
			txtCHrEspera.setForeground(Color.gray);
			txtCHrEspera.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCHrEspera.setBounds(50, 260, 280, 30);
			panelInferior.add(txtCHrEspera);

			btnAgregar.setBounds(50, 320, 280, 35);
			panelInferior.setBounds(1, 31, 380, 380);
			este.setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 412);
		}
		panelInferior.repaint();
		padre.getPanelPrincipal().repaint();
	}

	private void costoPorHorasKilometros(){
		boolean yaEsta = false;
		if(txtCKm != null){
			panelInferior.remove(txtCKm);
			panelInferior.remove(txtCKmIV);
			panelInferior.remove(txtCHrEspera);
			txtCKm = null;
			txtCKmIV = null;
			txtCHrEspera = null;
		}
		else if(txtDescR != null){
			panelInferior.remove(txtDescR);
			panelInferior.remove(txtCostoR);
			panelInferior.remove(txtCostoIVR);
			txtDescR = null;
			txtCostoR = null;
			txtCostoIVR = null;
		}
		else if(txtCKmRec != null) yaEsta = true;

		if(!yaEsta){
			txtCKmRec = new JTextField("Costo Por Kilómetro Recorrido");
			txtCKmRec.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCKmRec.getText());
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
			txtCKmRec.setForeground(Color.gray);
			txtCKmRec.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCKmRec.setBounds(50, 160, 280, 30);
			panelInferior.add(txtCKmRec);

			txtCHr = new JTextField("Costo Por Horas");
			txtCHr.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCHr.getText());
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
			txtCHr.setForeground(Color.gray);
			txtCHr.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCHr.setBounds(50, 210, 280, 30);
			panelInferior.add(txtCHr);

			txtCKmExtra = new JTextField("Costo Por Kilómetros Extras");
			txtCKmExtra.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCKmExtra.getText());
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
			txtCKmExtra.setForeground(Color.gray);
			txtCKmExtra.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCKmExtra.setBounds(50, 260, 280, 30);
			panelInferior.add(txtCKmExtra);

			txtCHrExtra = new JTextField("Costo Por Horas Extras");
			txtCHrExtra.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCHrExtra.getText());
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
			txtCHrExtra.setForeground(Color.gray);
			txtCHrExtra.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCHrExtra.setBounds(50, 310, 280, 30);
			panelInferior.add(txtCHrExtra);

			btnAgregar.setBounds(50, 370, 280, 35);
			panelInferior.setBounds(1, 31, 380, 430);
			este.setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 462);
		}
		panelInferior.repaint();
		padre.getPanelPrincipal().repaint();
	}

	private void costoPorRecorridosEstablecidos(){
		boolean yaEsta = false;
		if(txtCKm != null){
			panelInferior.remove(txtCKm);
			panelInferior.remove(txtCKmIV);
			panelInferior.remove(txtCHrEspera);
			txtCKm = null;
			txtCKmIV = null;
			txtCHrEspera = null;
		}
		else if(txtCKmRec != null){
			panelInferior.remove(txtCKmRec);
			panelInferior.remove(txtCHr);
			panelInferior.remove(txtCKmExtra);
			panelInferior.remove(txtCHrExtra);
			txtCKmRec = null;
			txtCHr = null;
			txtCKmExtra = null;
			txtCHrExtra = null;
		}
		else if(txtDescR != null) yaEsta = true;

		if(!yaEsta){
			txtDescR = new JTextField("Descripción Del Recorrido");
			txtDescR.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.letrasNumerosSignosEspacio(e);
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
						}
					}
				}
			});
			txtDescR.setFont(new Font("Arial", Font.PLAIN, 16));
			txtDescR.setForeground(Color.gray);
			txtDescR.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtDescR.setBounds(50, 160, 280, 30);
			panelInferior.add(txtDescR);

			txtCostoR = new JTextField("Costo Del Recorrido");
			txtCostoR.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCostoR.getText());
				}
			});
			txtCostoR.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(txtCostoR.getText().equals("Costo Del Recorrido")){
						txtCostoR.setText("");
						txtCostoR.setForeground(Color.black);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if(txtCostoR!=null){
						if(txtCostoR.getText().equals("")){
							txtCostoR.setText("Costo Del Recorrido");
							txtCostoR.setForeground(Color.gray);
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
			txtCostoR.setForeground(Color.gray);
			txtCostoR.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCostoR.setBounds(50, 210, 280, 30);
			panelInferior.add(txtCostoR);

			txtCostoIVR = new JTextField("Costo Por Ida Y Vuelta");
			txtCostoIVR.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Validaciones.soloNumeroYUnaComa(e, txtCostoIVR.getText());
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
			txtCostoIVR.setForeground(Color.gray);
			txtCostoIVR.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
			txtCostoIVR.setBounds(50, 260, 280, 30);
			panelInferior.add(txtCostoIVR);

			btnAgregar.setBounds(50, 320, 280, 35);
			panelInferior.setBounds(1, 31, 380, 380);
			este.setBounds(pantalla.width/2-191, pantalla.height/2-240, 382, 412);
		}
		panelInferior.repaint();
		padre.getPanelPrincipal().repaint();
	}
}
