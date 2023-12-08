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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import utils.ComboBoxModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;

public class AgregarUsuario extends MiJPanel {

	private static final long serialVersionUID = 1L;
	/*
	 * Utils
	 */
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	
	private Principal padre;
	private AgregarUsuario este;
	private MiJPanel anterior;
	
	/*
	 * Panel Superior
	 */
	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	/*
	 * Panel Inferior
	 */
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtNombre;
	private JComboBox<String> cbRol;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnRegistrarse;
	
	private boolean userChanged = false;
	private boolean nameChanged = false;
	private boolean passChanged = false;

	public AgregarUsuario(Principal p, MiJPanel a) {
		anterior = a;
		este = this;
		padre = p;
		setTipoPanel(Paneles.PANEL_AGREGAR_USUARIO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarUsuario(este);
		setBounds(pantalla.width/2-201, pantalla.height/2-253, 402, 457);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 400, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Usuario");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 200, 30);
		panelSuperior.add(lblNombre);

		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
		Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconCerrar = new ImageIcon(image);

		btnCerrar = new JButton();
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
		btnCerrar.setIcon(iconCerrar);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 400, 425);
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
		image = img.getImage().getScaledInstance(270, 82, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(63, 20, 270, 82);
		panelInferior.add(logo);
		
		txtNombre = new JTextField("Nombre");
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNombre.getText().equals("Nombre") && !nameChanged){
					txtNombre.setText("");
					nameChanged = true;
					txtNombre.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtNombre.getText().equals("")){
					txtNombre.setText("Nombre");
					nameChanged = false;
					txtNombre.setForeground(Color.gray);
				}
			}
		});
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setForeground(Color.gray);
		txtNombre.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtNombre.setBounds(70, 130, 260, 30);
		panelInferior.add(txtNombre);

		txtUsuario = new JTextField("Usuario");
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsuario.getText().equals("Usuario") && !userChanged){
					txtUsuario.setText("");
					userChanged = true;
					txtUsuario.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsuario.getText().equals("")){
					txtUsuario.setText("Usuario");
					userChanged = false;
					txtUsuario.setForeground(Color.gray);
				}
			}
		});
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUsuario.setForeground(Color.gray);
		txtUsuario.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtUsuario.setBounds(70, 190, 260, 30);
		panelInferior.add(txtUsuario);

		txtPassword = new JPasswordField("Contraseña");
		txtPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().equals("Contraseña") && !passChanged){
					txtPassword.setText("");
					passChanged = true;
					txtPassword.setEchoChar('●');
					txtPassword.setForeground(Color.black);
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals("")){
					txtPassword.setText("Contraseña");
					passChanged = false;
					txtPassword.setEchoChar((char) 0);
					txtPassword.setForeground(Color.gray);
				}
			}
		});
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPassword.setForeground(Color.gray);
		txtPassword.setEchoChar((char) 0);
		txtPassword.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtPassword.setBounds(70, 250, 260, 30);
		panelInferior.add(txtPassword);
		
		cbRol = new JComboBox<String>();
		cbRol.setBounds(70, 310, 260, 30);
		cbRol.setBackground(Color.white);
		cbRol.setFocusable(false);
		cbRol.setModel(ComboBoxModel.localizacionesModel());
		cbRol.setFont(new Font("Arial", Font.PLAIN, 16));
		cbRol.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbRol.setUI(PropiedadesComboBox.createUI(getRootPane(), cbRol.getBounds()));
		panelInferior.add(cbRol);
		
		btnRegistrarse = new JButton("Registrar");
		btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 18));
		btnRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		btnRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistrarse.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistrarse.setBackground(colorAzul);
			}
		});
		btnRegistrarse.setModel(new MyButtonModel());
		btnRegistrarse.setBounds(70, 370, 260, 35);
		btnRegistrarse.setBackground(colorAzul);
		btnRegistrarse.setForeground(Color.black);
		btnRegistrarse.setFocusable(false);
		btnRegistrarse.setBorderPainted(false);
		panelInferior.add(btnRegistrarse);
		
	}
}
