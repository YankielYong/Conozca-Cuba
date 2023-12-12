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

import services.ServicesLocator;
import services.TouristPackageServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

import javax.swing.SwingConstants;

public class AgregarPaquete extends MiJPanel{

	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtNombre;
	private JTextField txtCosto;
	private JTextField txtPersonas;
	private JTextField txtDias;
	private JTextField txtNoches;
	private boolean nameChanged = false;
	private boolean costChanged = false;
	private boolean personasChanged = false;
	private boolean diasChanged = false;
	private boolean nochesChanged = false;
	private JButton btnAgregar;
	
	private Principal padre;
	private AgregarPaquete este;
	private Gestion anterior;
	
	public AgregarPaquete(Principal p, Gestion a) {
		anterior = a;
		este = this;
		padre = p;
		setTipoPanel(Paneles.PANEL_AGREGAR_USUARIO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarPaquete(este);
		setBounds(pantalla.width/2-181, pantalla.height/2-231, 362, 412);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Agregar Paquete Turístico");
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
		btnCerrar.setBounds(315, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 360, 380);
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

		txtNombre = new JTextField("Nombre Promocional");
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumeros(e);
			}
		});
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNombre.getText().equals("Nombre Promocional") && !nameChanged){
					txtNombre.setText("");
					nameChanged = true;
					txtNombre.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtNombre.getText().equals("")){
					txtNombre.setText("Nombre Promocional");
					nameChanged = false;
					txtNombre.setForeground(Color.gray);
				}
			}
		});
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setForeground(Color.gray);
		txtNombre.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtNombre.setBounds(50, 110, 260, 30);
		panelInferior.add(txtNombre);
		
		txtPersonas = new JTextField("Cantidad de personas");
		txtPersonas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
				personasChanged = true;
			}
		});
		txtPersonas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPersonas.getText().equals("Cantidad de personas")){
					txtPersonas.setText("");
					txtPersonas.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtPersonas.getText().equals("")){
					txtPersonas.setText("Cantidad de personas");
					txtPersonas.setForeground(Color.gray);
					personasChanged = false;
				}
				
			}
		});
		txtPersonas.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPersonas.setForeground(Color.gray);
		txtPersonas.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtPersonas.setBounds(50, 160, 260, 30);
		panelInferior.add(txtPersonas);
		
		txtDias = new JTextField("Días");
		txtDias.setHorizontalAlignment(SwingConstants.CENTER);
		txtDias.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
				diasChanged = true;
			}
		});
		txtDias.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtDias.getText().equals("Días")){
					txtDias.setText("");
					txtDias.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtDias.getText().equals("")){
					txtDias.setText("Días");
					txtDias.setForeground(Color.gray);
					diasChanged = false;
				}
				
			}
		});
		txtDias.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDias.setForeground(Color.gray);
		txtDias.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtDias.setBounds(50, 210, 110, 30);
		panelInferior.add(txtDias);
		
		txtNoches = new JTextField("Noches");
		txtNoches.setHorizontalAlignment(SwingConstants.CENTER);
		txtNoches.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
				nochesChanged = true;
			}
		});
		txtNoches.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNoches.getText().equals("Noches")){
					txtNoches.setText("");
					txtNoches.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtNoches.getText().equals("")){
					txtNoches.setText("Noches");
					txtNoches.setForeground(Color.gray);
					nochesChanged = false;
				}
				
			}
		});
		txtNoches.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNoches.setForeground(Color.gray);
		txtNoches.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtNoches.setBounds(200, 210, 110, 30);
		panelInferior.add(txtNoches);
		
		txtCosto = new JTextField("Costo del paquete");
		txtCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCosto.getText());
				costChanged = true;
			}
		});
		txtCosto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCosto.getText().equals("Costo del paquete")){
					txtCosto.setText("");
					txtCosto.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCosto.getText().equals("")){
					txtCosto.setText("Costo del paquete");
					txtCosto.setForeground(Color.gray);
					costChanged = false;
				}
				else{
					String ca = txtCosto.getText();
					if(ca.charAt(ca.length()-1)=='.'){
						ca = ca.substring(0, ca.length()-1);
						txtCosto.setText(ca);
					}
				}
			}
		});
		txtCosto.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCosto.setForeground(Color.gray);
		txtCosto.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCosto.setBounds(50, 260, 260, 30);
		panelInferior.add(txtCosto);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();

				try{
					String nombre = "";
					String personas = "";
					String dias = "";
					String noches = "";
					String costo = "";
					if(nameChanged) nombre = txtNombre.getText();
					else throw new IllegalArgumentException("El campo del nombre promocional está vacío");
					if(personasChanged) personas = txtPersonas.getText();
					else throw new IllegalArgumentException("El campo de la cantidad de personas está vacío");
					if(diasChanged) dias = txtDias.getText();
					else throw new IllegalArgumentException("El campo de la cantidad de días está vacío");
					if(nochesChanged) noches = txtNoches.getText();
					else throw new IllegalArgumentException("El campo de la cantidad de noches está vacío");
					if(costChanged) costo = txtCosto.getText();
					else throw new IllegalArgumentException("El campo del costo del paquete está vacío");
					int pers = Integer.valueOf(personas);
					int dia = Integer.valueOf(dias);
					int noch = Integer.valueOf(noches);
					double cos = Double.valueOf(costo);
					Validaciones.paquete(nombre, pers, dia, noch);
					touristPackageServices.insertTouristPackage(nombre, 0, cos, pers, dia, noch);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El paquete turístico fue agregado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerPaquetes();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					if(e1.getMessage().equals("El campo de la cantidad de personas está vacío"))
						ma.agrandar(20);
					if(e1.getMessage().equals("El paquete debe ser para al menos a 1 persona"))
						ma.agrandar(20);
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
		btnAgregar.setBounds(50, 320, 260, 35);
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
}
