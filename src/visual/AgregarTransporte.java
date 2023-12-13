package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import services.ServicesLocator;
import services.TransportModalityServices;
import services.TransportServices;
import services.VehicleServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

public class AgregarTransporte extends MiJPanel{
	
	private VehicleServices vehicleServices = ServicesLocator.getVehicleServices();
	private TransportModalityServices transportModalityServices = ServicesLocator.getTransportModalityServices();
	private TransportServices transportServices = ServicesLocator.getTransportServices();

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtVehiculo;
	private JButton btnVehiculo;
	private JTextField txtModalidad;
	private JButton btnModalidad;
	private JComboBox<String> cbTranportista;
	private JButton btnAgregar;
	
	private Principal padre;
	private Gestion anterior;
	private AgregarTransporte este;
	
	public AgregarTransporte(Principal p, Gestion a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_TRANSPORTE);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarTransporte(este);
		setBounds(pantalla.width/2-201, pantalla.height/2-206, 402, 362);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 400, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Transporte");
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
		btnCerrar.setBounds(355, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 400, 330);
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
		image = img.getImage().getScaledInstance(250, 76, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(75, 10, 250, 76);
		panelInferior.add(logo);
		
		JLabel vehi = new JLabel("Código del Vehículo:");
		vehi.setBounds(50, 110, 150, 30);
		vehi.setForeground(Color.black);
		vehi.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(vehi);
		
		txtVehiculo = new JTextField();
		txtVehiculo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtVehiculo.setBounds(200, 110, 90, 30);
		txtVehiculo.setForeground(Color.black);
		txtVehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtVehiculo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtVehiculo);
		
		btnVehiculo = new JButton("Ver");
		btnVehiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnVehiculo.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarVehiculos panel = new ConsultarVehiculos(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnVehiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVehiculo.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnVehiculo.setBackground(colorAzul);
			}
		});
		btnVehiculo.setBounds(300, 110, 50, 30);
		btnVehiculo.setModel(new MyButtonModel());
		btnVehiculo.setFont(new Font("Arial", Font.PLAIN, 15));
		btnVehiculo.setMargin(new Insets(2, 5, 2, 5));
		btnVehiculo.setBackground(colorAzul);
		btnVehiculo.setForeground(Color.black);
		btnVehiculo.setFocusable(false);
		btnVehiculo.setBorderPainted(false);
		panelInferior.add(btnVehiculo);
		
		JLabel modali = new JLabel("Código de la Modalidad:");
		modali.setBounds(50, 160, 180, 30);
		modali.setForeground(Color.black);
		modali.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(modali);
		
		txtModalidad = new JTextField();
		txtModalidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtModalidad.setBounds(230, 160, 60, 30);
		txtModalidad.setForeground(Color.black);
		txtModalidad.setFont(new Font("Arial", Font.PLAIN, 16));
		txtModalidad.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtModalidad);
		
		btnModalidad = new JButton("Ver");
		btnModalidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnModalidad.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarModalidades panel = new ConsultarModalidades(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnModalidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnModalidad.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnModalidad.setBackground(colorAzul);
			}
		});
		btnModalidad.setBounds(300, 160, 50, 30);
		btnModalidad.setModel(new MyButtonModel());
		btnModalidad.setFont(new Font("Arial", Font.PLAIN, 15));
		btnModalidad.setMargin(new Insets(2, 5, 2, 5));
		btnModalidad.setBackground(colorAzul);
		btnModalidad.setForeground(Color.black);
		btnModalidad.setFocusable(false);
		btnModalidad.setBorderPainted(false);
		panelInferior.add(btnModalidad);
		
		cbTranportista = new JComboBox<String>();
		cbTranportista.setBounds(50, 210, 300, 30);
		cbTranportista.setBackground(Color.white);
		cbTranportista.setFocusable(false);
		cbTranportista.setForeground(Color.black);
		cbTranportista.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTranportista.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbTranportista.setModel(utils.ComboBoxModel.transportistasModel());
		cbTranportista.setUI(PropiedadesComboBox.createUI(getRootPane(), cbTranportista.getBounds()));
		panelInferior.add(cbTranportista);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					String veh = txtVehiculo.getText();
					String mod = txtModalidad.getText();
					Validaciones.transporte(veh, mod);
					int vehiculo = Integer.valueOf(veh);
					int modalidad = Integer.valueOf(mod);
					try{
						vehicleServices.findVehicle(vehiculo);
					} catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ningún vehículo con ese código");
					}
					try{
						transportModalityServices.findTransportModality(modalidad);
					} catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ninguna modalidad con ese código");
					}
					String borrower = (String)cbTranportista.getSelectedItem();
					transportServices.insertTransport(vehiculo, modalidad, borrower);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El transporte fue agregado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerTransportes();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					ma.setVisible(true);
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
		btnAgregar.setBounds(50, 270, 300, 35);
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
	
	public void setVehiculo(int code){
		txtVehiculo.setText(String.valueOf(code));
	}
	
	public void setModalidad(int code){
		txtModalidad.setText(String.valueOf(code));
	}
}
